package org.eu.awesomekalin.jta.mod.render.roundel;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.eu.awesomekalin.jta.mod.blocks.directional.roundel.RoundelSquareBase;
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

public class RoundelSquareBaseRender<T extends RoundelSquareBase.RoundelBaseBlockEntity> extends BlockEntityRenderer<T> implements IGui, IDrawing {
    private final float maxWidth;
    private final float maxScale;
    private final float xOffset;
    private final float yOffset;
    private final float zOffset;
    private final float xTilt;
    private final int textColor;
    private final Identifier font;

    public RoundelSquareBaseRender(Argument dispatcher, float maxWidth, float maxScale, float xOffset, float yOffset, float zOffset, float xTilt, int textColor, Identifier font) {
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
        final Direction facing = IBlock.getStatePropertySafe(state, DirectionalBlockExtension.FACING);

        final Station station = InitClient.findStation(pos);
        final MutableText roundelText =
                !entity.line0.getString().isEmpty() ? entity.line0 : TextHelper.setStyle(TextHelper.literal(IGui.textOrUntitled(IGui.formatStationName(station == null ? "" : station.getName()))), style);
        final int textWidth = GraphicsHolder.getTextWidth(roundelText);

        final StoredMatrixTransformations storedMatrixTransformations = new StoredMatrixTransformations(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
        storedMatrixTransformations.add(graphicsHolderNew -> {
            graphicsHolderNew.rotateYDegrees(-facing.asRotation());
            graphicsHolderNew.rotateZDegrees(180);
            graphicsHolderNew.rotateYDegrees(180);
        });
        MainRenderer.scheduleRender(QueuedRenderLayer.TEXT, (graphicsHolderNew, offset) -> {
            storedMatrixTransformations.transform(graphicsHolderNew, offset);
            for (int i = 0; i < 4; i++) {
                graphicsHolderNew.rotateYDegrees(90);
                render(graphicsHolderNew, roundelText, textWidth, light);
            }
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
        graphicsHolder.drawText(roundelText, -textWidth / 2, 0, textColor, false, GraphicsHolder.getDefaultLight());

        graphicsHolder.pop();
    }
}
