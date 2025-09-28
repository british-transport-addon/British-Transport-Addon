package org.eu.awesomekalin.jta.render.rail.signal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import net.minecraft.block.BlockState;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.mtr.core.tool.Utilities;
import it.unimi.dsi.fastutil.ints.IntAVLTreeSet;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectObjectImmutablePair;
import org.mtr.MTR;
import org.mtr.block.BlockNode;
import org.mtr.block.BlockSignalBase;
import org.mtr.block.BlockSignalLightBase;
import org.mtr.block.BlockSignalSemaphoreBase;
import org.mtr.block.IBlock;
import org.mtr.client.IDrawing;
import org.mtr.client.MinecraftClientData;
import org.mtr.data.IGui;
import org.mtr.render.*;

public abstract class RenderBritishSignalBase<T extends BlockSignalBase.BlockEntityBase> extends BlockEntityRendererExtension<T> implements IBlock, IGui {
    protected final int aspects;
    private final float colorIndicatorHeight;

    protected final Map<BlockPos, Long> signalTimings = new HashMap<>();

    public RenderBritishSignalBase(int colorIndicatorHeight, int aspects) {
        this.aspects = aspects;
        this.colorIndicatorHeight = (float)colorIndicatorHeight / 16.0F + 0.003125F;
    }

    public final void render(T entity, ClientWorld world, ClientPlayerEntity clientPlayerEntity, float tickDelta, int light, int overlay) {
        if (world != null) {
            if (clientPlayerEntity != null) {
                BlockPos pos = entity.getPos();
                BlockState state = world.getBlockState(pos);
                if (state.getBlock() instanceof BlockSignalLightBase || state.getBlock() instanceof BlockSignalSemaphoreBase) {
                    float angle = BlockSignalBase.getAngle(state);
                    StoredMatrixTransformations storedMatrixTransformations = new StoredMatrixTransformations(0.5 + (double)entity.getPos().getX(), (double)entity.getPos().getY(), 0.5 + (double)entity.getPos().getZ());

                    for(int i = 0; i < (entity.isDoubleSided ? 2 : 1); ++i) {
                        float newAngle = angle + (float)(i * 180);
                        boolean isBackSide = i == 1;
                        ObjectObjectImmutablePair<IntArrayList, IntAVLTreeSet> aspects = getAspects(pos, newAngle + 90.0F, world);
                        IntArrayList detectedColors = (IntArrayList)aspects.left();
                        if (!detectedColors.isEmpty()) {
                            StoredMatrixTransformations storedMatrixTransformationsNew = storedMatrixTransformations.copy();
                            storedMatrixTransformationsNew.add((matrixStack) -> {
                                IDrawing.rotateYDegrees(matrixStack, -newAngle);
                            });
                            IntAVLTreeSet filterColors = entity.getSignalColors(isBackSide);
                            if (RenderRails.isHoldingRailRelated(clientPlayerEntity)) {
                                float xStart = -0.015625F * (float)detectedColors.size();

                                for(int j = 0; j < detectedColors.size(); ++j) {
                                    int signalColor = detectedColors.getInt(j);
                                    boolean occupied = ((IntAVLTreeSet)aspects.right()).contains(signalColor);
                                    float x = xStart + (float)j * 0.03125F;
                                    float width = 0.03125F / (float)(!filterColors.isEmpty() && !filterColors.contains(signalColor) ? 8 : 1);
                                    MainRenderer.scheduleRender(Identifier.of("mtr", "textures/block/white.png"), false, occupied ? QueuedRenderLayer.EXTERIOR : QueuedRenderLayer.LIGHT, (matrixStack, vertexConsumer, offset) -> {
                                        storedMatrixTransformationsNew.transform(matrixStack, offset);
                                        IDrawing.drawTexture(matrixStack, vertexConsumer, x, this.colorIndicatorHeight, -0.15625F, x + 0.03125F, this.colorIndicatorHeight, -0.15625F, x + 0.03125F, this.colorIndicatorHeight, -0.15625F - width, x, this.colorIndicatorHeight, -0.15625F - width, 0.0F, 0.0F, 1.0F, 1.0F, Direction.UP, MainRenderer.getFlashingColor(signalColor | -16777216, 1), light);
                                        matrixStack.pop();
                                    });
                                }
                            }


                            int color = 0;
                            if (aspects.right().intStream().anyMatch((a) -> {
                                return filterColors.isEmpty() || filterColors.contains(a);
                            })) {
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

                }
            }
        }
    }

    protected abstract void render(StoredMatrixTransformations var1, T var2, float var3, int var4, boolean var5, int light);

    public static ObjectObjectImmutablePair<IntArrayList, IntAVLTreeSet> getAspects(BlockPos blockPos, float angle, ClientWorld clientWorld) {
        if (clientWorld == null) {
            return new ObjectObjectImmutablePair(new IntArrayList(), new IntAVLTreeSet());
        } else {
            BlockPos startPos = getNodePos(clientWorld, blockPos, Direction.fromHorizontalDegrees((double)angle));
            if (startPos == null) {
                return new ObjectObjectImmutablePair(new IntArrayList(), new IntAVLTreeSet());
            } else {
                MinecraftClientData minecraftClientData = MinecraftClientData.getInstance();
                IntArrayList detectedColors = new IntArrayList();
                IntAVLTreeSet occupiedColors = new IntAVLTreeSet();
                minecraftClientData.positionsToRail.getOrDefault(MTR.blockPosToPosition(startPos), new Object2ObjectOpenHashMap<>()).forEach((endPosition, rail) -> {
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
                return new ObjectObjectImmutablePair(detectedColors, occupiedColors);
            }
        }
    }

    private static BlockPos getNodePos(ClientWorld world, BlockPos pos, Direction facing) {
        int[] checkDistance = new int[]{0, 1, -1, 2, -2, 3, -3, 4, -4};
        int[] var4 = checkDistance;
        int var5 = checkDistance.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            int z = var4[var6];
            int[] var8 = checkDistance;
            int var9 = checkDistance.length;

            for(int var10 = 0; var10 < var9; ++var10) {
                int x = var8[var10];

                for(int y = -5; y <= 0; ++y) {
                    BlockPos checkPos = pos.up(y).offset(facing.rotateYClockwise(), x).offset(facing, z);
                    BlockState checkState = world.getBlockState(checkPos);
                    if (checkState.getBlock() instanceof BlockNode) {
                        return checkPos;
                    }
                }
            }
        }

        return null;
    }
}
