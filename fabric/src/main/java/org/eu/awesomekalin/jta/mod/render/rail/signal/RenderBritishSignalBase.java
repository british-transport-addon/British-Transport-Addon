package org.eu.awesomekalin.jta.mod.render.rail.signal;

import org.mtr.core.tool.Utilities;
import org.mtr.libraries.it.unimi.dsi.fastutil.ints.IntAVLTreeSet;
import org.mtr.libraries.it.unimi.dsi.fastutil.ints.IntArrayList;
import org.mtr.libraries.it.unimi.dsi.fastutil.longs.LongArrayList;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.ObjectObjectImmutablePair;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityRenderer;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mod.Init;
import org.mtr.mod.block.*;
import org.mtr.mod.client.IDrawing;
import org.mtr.mod.client.MinecraftClientData;
import org.mtr.mod.data.IGui;
import org.mtr.mod.render.MainRenderer;
import org.mtr.mod.render.QueuedRenderLayer;
import org.mtr.mod.render.RenderRails;
import org.mtr.mod.render.StoredMatrixTransformations;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class RenderBritishSignalBase<T extends BlockSignalBase.BlockEntityBase> extends BlockEntityRenderer<T> implements IBlock, IGui {
    protected final int aspects;
    private final float colorIndicatorHeight;

    protected final Map<BlockPos, Long> signalTimings = new HashMap<>();

    public RenderBritishSignalBase(BlockEntityRenderer.Argument dispatcher, int colorIndicatorHeight, int aspects) {
        super(dispatcher);
        this.aspects = aspects;
        this.colorIndicatorHeight = (float)colorIndicatorHeight / 16.0F + 0.003125F;
    }

    public final void render(T entity, float tickDelta, @Nonnull GraphicsHolder graphicsHolder, int light, int overlay) {
        World world = entity.getWorld2();
        ClientPlayerEntity clientPlayerEntity = MinecraftClient.getInstance().getPlayerMapped();

        if (world == null || clientPlayerEntity == null) return;

        BlockPos pos = entity.getPos2();
        BlockState state = world.getBlockState(pos);

        if (!(state.getBlock().data instanceof BlockSignalLightBase) || !(state.getBlock().data instanceof BlockSignalSemaphoreBase)) return;

        float angle = BlockSignalBase.getAngle(state);
        StoredMatrixTransformations storedMatrixTransformations = new StoredMatrixTransformations(
                0.5 + (double) entity.getPos2().getX(),
                (double) entity.getPos2().getY(),
                0.5 + (double) entity.getPos2().getZ()
        );

        for (int i = 0; i < (entity.isDoubleSided ? 2 : 1); i++) {
            float newAngle = angle + (float)(i * 180);
            boolean isBackSide = i == 1;
            ObjectObjectImmutablePair<IntArrayList, IntAVLTreeSet> aspects = getAspects(pos, newAngle + 90.0F);
            IntArrayList detectedColors = (IntArrayList) aspects.left();

            if (detectedColors.isEmpty()) continue;

            StoredMatrixTransformations storedMatrixTransformationsNew = storedMatrixTransformations.copy();
            storedMatrixTransformationsNew.add((graphicsHolderNew) -> {
                graphicsHolderNew.rotateYDegrees(-newAngle);
            });

            IntAVLTreeSet filterColors = entity.getSignalColors(isBackSide);

            if (RenderRails.isHoldingRailRelated(clientPlayerEntity)) {
                float xStart = -0.015625F * (float)detectedColors.size();

                for (int j = 0; j < detectedColors.size(); j++) {
                    int signalColor = detectedColors.getInt(j);
                    boolean occupied = ((IntAVLTreeSet) aspects.right()).contains(signalColor);
                    float x = xStart + (float) j * 0.03125F;
                    float width = 0.03125F / (float) (!filterColors.isEmpty() && !filterColors.contains(signalColor) ? 8 : 1);

                    MainRenderer.scheduleRender(new Identifier("mtr", "textures/block/white.png"), false, occupied ? QueuedRenderLayer.EXTERIOR : QueuedRenderLayer.LIGHT, (graphicsHolderNew, offset) -> {
                        storedMatrixTransformationsNew.transform(graphicsHolderNew, offset);
                        IDrawing.drawTexture(
                                graphicsHolderNew, x, this.colorIndicatorHeight,
                                -0.15625F, x + 0.03125F,
                                this.colorIndicatorHeight,
                                -0.15625F, x + 0.03125F,
                                this.colorIndicatorHeight, -0.15625F - width,
                                x, this.colorIndicatorHeight, -0.15625F - width,
                                0.0F, 0.0F, 1.0F, 1.0F,
                                Direction.UP,
                                MainRenderer.getFlashingColor(signalColor | -16777216, 1),
                                GraphicsHolder.getDefaultLight()
                        );

                        graphicsHolderNew.pop();
                    });
                }
            }

            int color = 0;

            if (aspects.right().intStream().anyMatch((a) -> filterColors.isEmpty() || filterColors.contains(a))) {
                color = 1;
                signalTimings.put(pos, System.currentTimeMillis());
            } else {
                if (System.currentTimeMillis() - signalTimings.getOrDefault(pos, 0L) < 15_000) {
                    color = 2;
                } else if (System.currentTimeMillis() - signalTimings.getOrDefault(pos, 0L) < 30_000) {
                    color = 3;
                }
            }

            this.render(storedMatrixTransformationsNew, entity, tickDelta, color, isBackSide, light);
        }
    }

    protected abstract void render(StoredMatrixTransformations var1, T var2, float var3, int var4, boolean var5, int light);

    public static ObjectObjectImmutablePair<IntArrayList, IntAVLTreeSet> getAspects(BlockPos blockPos, float angle) {
        ClientWorld clientWorld = MinecraftClient.getInstance().getWorldMapped();
        BlockPos startPos = getNodePos(clientWorld, blockPos, Direction.fromRotation((double)angle));

        if (clientWorld == null || startPos == null) return new ObjectObjectImmutablePair<>(new IntArrayList(), new IntAVLTreeSet());

        MinecraftClientData minecraftClientData = MinecraftClientData.getInstance();
        IntArrayList detectedColors = new IntArrayList();
        IntAVLTreeSet occupiedColors = new IntAVLTreeSet();

        minecraftClientData.positionsToRail.getOrDefault(Init.blockPosToPosition(startPos), new Object2ObjectOpenHashMap<>()).forEach((endPosition, rail) -> {
            if (Math.abs(Utilities.circularDifference(Math.round(Math.toDegrees(Math.atan2((double)(endPosition.getZ() - (long)startPos.getZ()), (double)(endPosition.getX() - (long)startPos.getX())))), (long)Math.round(angle), 360L)) < 90L) {
                IntAVLTreeSet var10000 = rail.getSignalColors();
                Objects.requireNonNull(detectedColors);
                var10000.forEach(detectedColors::add);
                minecraftClientData.railIdToPreBlockedSignalColors.getOrDefault(rail.getHexId(), new LongArrayList()).forEach((color) -> {
                    occupiedColors.add((int)color);
                });
            }
        });

        Collections.sort(detectedColors);
        return new ObjectObjectImmutablePair<>(detectedColors, occupiedColors);
    }

    private static BlockPos getNodePos(ClientWorld world, BlockPos pos, Direction facing) {
        int[] checkDistance = new int[]{0, 1, -1, 2, -2, 3, -3, 4, -4};

        for (int z : checkDistance) {
            for (int x : checkDistance) {
                for (int y = -5; y <= 0; y++) {
                    BlockPos checkPos = pos.up(y).offset(facing.rotateYClockwise(), x).offset(facing, z);
                    BlockState checkState = world.getBlockState(checkPos);
                    if (checkState.getBlock().data instanceof BlockNode) {
                        return checkPos;
                    }
                }
            }
        }

        return null;
    }
}
