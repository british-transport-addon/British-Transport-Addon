package org.eu.awesomekalin.jta.mod.render.rail.signal;

import org.eu.awesomekalin.jta.mod.Init;
import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.eu.awesomekalin.jta.mod.blocks.directional.rail.DispatchSignal;
import org.eu.awesomekalin.jta.mod.blocks.signal.DigitalRouteSignal;
import org.mtr.libraries.it.unimi.dsi.fastutil.ints.IntAVLTreeSet;
import org.mtr.libraries.it.unimi.dsi.fastutil.ints.IntArrayList;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.ObjectObjectImmutablePair;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityRenderer;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mapping.mapper.TextHelper;
import org.mtr.mod.block.BlockSignalBase;
import org.mtr.mod.block.IBlock;
import org.mtr.mod.client.IDrawing;
import org.mtr.mod.data.IGui;
import org.mtr.mod.render.MainRenderer;
import org.mtr.mod.render.QueuedRenderLayer;
import org.mtr.mod.render.StoredMatrixTransformations;
import org.mtr.mod.screen.SignalColorScreen;

import javax.annotation.Nonnull;


public class RenderDigitalRouteSignal<T extends DigitalRouteSignal.BlockEntity> extends BlockEntityRenderer<T> implements IGui, IDrawing {
    private final float maxWidth;
    private final float maxScale;
    private final float xOffset;
    private final float yOffset;
    private final float zOffset;
    private final float xTilt;
    private final int textColor;
    private final Identifier font;

    public RenderDigitalRouteSignal(Argument dispatcher, float maxWidth, float maxScale, float xOffset, float yOffset, float zOffset, float xTilt, int textColor, Identifier font) {
        super(dispatcher);
        this.maxWidth = maxWidth;
        this.maxScale = maxScale;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.zOffset = zOffset;
        this.xTilt = xTilt;
        this.textColor = textColor;
        this.font = font;
    }

    @Override
    public void render(@Nonnull DigitalRouteSignal.BlockEntity entity, float tickDelta, @Nonnull GraphicsHolder graphicsHolder, int light, int overlay) {
        final Style style = Style.getEmptyMapped(); // TODO custom font not working

        final World world = entity.getWorld2();
        if (world == null) {
            return;
        }

        final BlockPos pos = entity.getPos2();
        final BlockState state = world.getBlockState(pos);


        float angle = BlockSignalBase.getAngle(state);
        final StoredMatrixTransformations storedMatrixTransformations = new StoredMatrixTransformations(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
        storedMatrixTransformations.add(graphicsHolderNew -> {
            graphicsHolderNew.rotateYDegrees(-angle);
            graphicsHolderNew.rotateZDegrees(180);
        });
        ObjectObjectImmutablePair<IntArrayList, IntAVLTreeSet> aspects = RenderBritishSignalBase.getAspects(pos, angle + 90.0F);
        IntArrayList detectedColors = (IntArrayList)aspects.left();
        if (!detectedColors.isEmpty()) {
            StoredMatrixTransformations storedMatrixTransformationsNew = storedMatrixTransformations.copy();
            storedMatrixTransformationsNew.add((graphicsHolderNew) -> {
                graphicsHolderNew.rotateYDegrees(-angle);
            });
            IntAVLTreeSet filterColors = entity.getSignalColors(false);

            int route = 0;
            if (aspects.right().contains(MapColor.getWhiteMapped().getColorMapped())) {
                route = 1;
            }

            if (aspects.right().contains(MapColor.getYellowMapped().getColorMapped())) {
                route = 2;
            }

            if (aspects.right().contains(MapColor.getLightGrayMapped().getColorMapped())) {
                route = 3;
            }

            if (aspects.right().contains(MapColor.getBrownMapped().getColorMapped())) {
                route = 4;
            }

            if (aspects.right().contains(MapColor.getOrangeMapped().getColorMapped())) {
                route = 5;
            }

            if (aspects.right().contains(MapColor.getLimeMapped().getColorMapped())) {
                route = 6;
            }

            if (aspects.right().contains(MapColor.getCyanMapped().getColorMapped())) {
                route = 7;
            }

            if (aspects.right().contains(MapColor.getGreenMapped().getColorMapped())) {
                route = 8;
            }

            if (aspects.right().contains(MapColor.getMagentaMapped().getColorMapped())) {
                route = 9;
            }
            if (aspects.right().contains(MapColor.getPinkMapped().getColorMapped())) {
                route = 10;
            }
            if (aspects.right().contains(MapColor.getPurpleMapped().getColorMapped())) {
                route = 11;
            }
            if (aspects.right().contains(MapColor.getRedMapped().getColorMapped())) {
                route = 12;
            }
            if (aspects.right().contains(MapColor.getLightBlueMapped().getColorMapped())) {
                route = 13;
            }
            if (aspects.right().contains(MapColor.getGrayMapped().getColorMapped())) {
                route = 14;
            }
            if (aspects.right().contains(MapColor.getBlueMapped().getColorMapped())) {
                route = 15;
            }
            if (aspects.right().contains(MapColor.getBlackMapped().getColorMapped())) {
                route = 16;
            }

            if (route == 0) {
                return;
            }

            final float y = -0.25F;

            MainRenderer.scheduleRender(new Identifier(Init.MOD_ID, "textures/route/" + (route + ".png")), false, QueuedRenderLayer.LIGHT, (a, offset) -> {
                storedMatrixTransformations.transform(a, offset);
                IDrawing.drawTexture(a, -0.35F, y - .025f, -0.19375F, 0.35F, y + 0.675F, -0.19375F, Direction.UP, 0xFFFFFFFF, GraphicsHolder.getDefaultLight());
                a.pop();
            });
        }
    }
}