package org.eu.awesomekalin.jta.mod.render.bus;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.eu.awesomekalin.jta.mod.blocks.directional.bus.BeeBusStopSign;
import org.mtr.core.data.Station;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityRenderer;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mapping.mapper.TextHelper;
import org.mtr.mod.InitClient;
import org.mtr.mod.block.IBlock;
import org.mtr.mod.client.IDrawing;
import org.mtr.mod.data.IGui;
import org.mtr.mod.render.MainRenderer;
import org.mtr.mod.render.QueuedRenderLayer;
import org.mtr.mod.render.StoredMatrixTransformations;

import javax.annotation.Nonnull;


public class BeeBusStopSignRender<T extends BeeBusStopSign.TileEntityBeeBusSign> extends BlockEntityRenderer<T> implements IGui, IDrawing {
    private final float maxWidth;
    private final float maxScale;
    private final float xOffset;
    private final float yOffset;
    private final float zOffset;
    private final float xTilt;
    private final int textColor;
    private final Identifier font;

    public BeeBusStopSignRender(Argument dispatcher, float maxWidth, float maxScale, float xOffset, float yOffset, float zOffset, float xTilt, int textColor, Identifier font) {
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
    public void render(@Nonnull T entity, float tickDelta, @Nonnull GraphicsHolder graphicsHolder, int light, int overlay) {
        final Style style = Style.getEmptyMapped(); // TODO custom font not working

        if (!entity.shouldRender()) {
            return;
        }

        final World world = entity.getWorld2();
        if (world == null) {
            return;
        }

        final BlockPos pos = entity.getPos2();
        final BlockState state = world.getBlockState(pos);
        final Direction FACING = IBlock.getStatePropertySafe(state, DirectionalBlockExtension.FACING);
        final DirectionalBlockExtension.EnumBooleanInverted IS_22_5 = IBlock.getStatePropertySafe(state, DirectionalBlockExtension.IS_22_5);
        final DirectionalBlockExtension.EnumBooleanInverted IS_45 = IBlock.getStatePropertySafe(state, DirectionalBlockExtension.IS_45);

        final Station station = InitClient.findStation(pos);
        final MutableText streetText = TextHelper.setStyle(TextHelper.literal(IGui.textOrUntitled(IGui.formatStationName(station == null || !station.getName().contains("/") ? "" : station.getName().split("/")[0]))), style);
        final MutableText streetTextTwo = TextHelper.setStyle(TextHelper.literal(IGui.textOrUntitled(IGui.formatStationName(station == null || !station.getName().contains("/") ? "" : station.getName().split("/")[1]))), style);
        final MutableText towardsText = TextHelper.setStyle(TextHelper.literal(IGui.textOrUntitled(IGui.formatStationName("Shudehill"))), style);
        final MutableText servicesTextOne = TextHelper.setStyle(TextHelper.literal(IGui.textOrUntitled(IGui.formatStationName("320 620 720"))), style);

        final int textWidth = GraphicsHolder.getTextWidth(streetText);
        final int textWidthTwo = GraphicsHolder.getTextWidth(streetTextTwo);
        final int towardsTextWidth = GraphicsHolder.getTextWidth(towardsText);
        final int servicesTextOneWidth = GraphicsHolder.getTextWidth(servicesTextOne);

        final StoredMatrixTransformations storedMatrixTransformations = new StoredMatrixTransformations(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
        storedMatrixTransformations.add(graphicsHolderNew -> {
            graphicsHolderNew.rotateYDegrees(-FACING.asRotation());
            graphicsHolderNew.rotateZDegrees(180);
            if (IS_22_5.booleanValue) graphicsHolderNew.rotateYDegrees(22.5F);
            if (IS_45.booleanValue) graphicsHolderNew.rotateYDegrees(45F);
        });
        MainRenderer.scheduleRender(QueuedRenderLayer.TEXT, (graphicsHolderNew, offset) -> {
            storedMatrixTransformations.transform(graphicsHolderNew, offset);
            // away from traffic side.
            graphicsHolderNew.rotateYDegrees(180);
            // facing traffic side.
            render(graphicsHolderNew, streetText, textWidth + 3, light);
            graphicsHolderNew.translate(0, .1, 0);
            render(graphicsHolderNew, streetTextTwo, textWidthTwo + 3, light);
            graphicsHolderNew.translate(0, .15, 0);
            render(graphicsHolderNew, towardsText, towardsTextWidth + 8, light);
            graphicsHolderNew.translate(0, .25, 0);
            render(graphicsHolderNew, servicesTextOne, servicesTextOneWidth + 3, light);
            graphicsHolderNew.rotateYDegrees(180);
            render(graphicsHolderNew, servicesTextOne, servicesTextOneWidth + 3, light);
            graphicsHolderNew.pop();
        });
    }

    private void render(GraphicsHolder graphicsHolder, MutableText roundelText, int textWidth, int light) {
        graphicsHolder.push();
        graphicsHolder.rotateXDegrees(xTilt);
        graphicsHolder.translate(-xOffset, -yOffset, -zOffset - SMALL_OFFSET * 2);

        final float scale = Math.min((maxWidth) / textWidth, maxScale);
        graphicsHolder.scale(scale, scale, scale);
        graphicsHolder.translate(0, -3.5, 0);
        graphicsHolder.drawText(roundelText, -textWidth / 2, 0, textColor, false, light);

        graphicsHolder.pop();
    }
}