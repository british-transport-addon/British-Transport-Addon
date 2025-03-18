package org.eu.awesomekalin.jta.mod.render.rail.signal;

import org.eu.awesomekalin.jta.mod.Init;
import org.mtr.mapping.holder.Direction;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.mapper.BlockEntityRenderer;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mod.block.BlockSignalBase;
import org.mtr.mod.client.IDrawing;
import org.mtr.mod.render.MainRenderer;
import org.mtr.mod.render.QueuedRenderLayer;
import org.mtr.mod.render.RenderSignalBase;
import org.mtr.mod.render.StoredMatrixTransformations;

public class RenderShuntSignal<T extends BlockSignalBase.BlockEntityBase> extends RenderSignalBase<T> {

    private static final float SIZE = 0.1F;
    private static final float SIDE_OFFSET = 0.2F;
    private static final float HEIGHT = 0.3F;
    private static final float TOP_HEIGHT = 0.6F;
    private static final float TOP_SIDE_OFFSET = SIDE_OFFSET / 2F; // Half of side offset
    private static final int ACTIVE_COLOR = 0xFFFFFFFF; // White
    private static final int INACTIVE_COLOR = 0xFF222222; // Dim

    private final boolean topOffsetRight;

    public RenderShuntSignal(BlockEntityRenderer.Argument dispatcher, boolean topOffsetRight) {
        super(dispatcher, 12, 2);
        this.topOffsetRight = topOffsetRight;
    }

    @Override
    protected void render(StoredMatrixTransformations storedMatrixTransformations, T entity, float tickDelta, int occupiedAspect, boolean isBackSide) {
        boolean isFlashing = (System.currentTimeMillis() / 500) % 2 == 0;

        int leftColor;
        int rightColor;
        int topColor;

        if (occupiedAspect == 0) { // On — Solid white, no flashing
            leftColor = rightColor = topColor = ACTIVE_COLOR;
        } else if (occupiedAspect == 1) { // Flashing white
            if (topOffsetRight) {
                leftColor = -65536; // Left stays solid
                rightColor = isFlashing ? ACTIVE_COLOR : INACTIVE_COLOR; // Right flashes
            } else {
                leftColor = isFlashing ? ACTIVE_COLOR : INACTIVE_COLOR; // Left flashes
                rightColor = -65536; // Right stays solid
            }
            topColor = !isFlashing ? ACTIVE_COLOR : INACTIVE_COLOR;
        } else { // Off — All dim
            leftColor = rightColor = topColor = INACTIVE_COLOR;
        }

        // Left signal
        MainRenderer.scheduleRender(new Identifier(Init.MOD_ID, "textures/block/digital_signal.png"), false, QueuedRenderLayer.LIGHT, (graphicsHolder, offset) -> {
            storedMatrixTransformations.transform(graphicsHolder, offset);
            IDrawing.drawTexture(graphicsHolder,
                    -SIDE_OFFSET - SIZE, HEIGHT - SIZE, -0.19375F,
                    -SIDE_OFFSET + SIZE, HEIGHT + SIZE, -0.19375F,
                    Direction.UP, leftColor, GraphicsHolder.getDefaultLight());
            graphicsHolder.pop();
        });

        // Right signal
        MainRenderer.scheduleRender(new Identifier(Init.MOD_ID, "textures/block/digital_signal.png"), false, QueuedRenderLayer.LIGHT, (graphicsHolder, offset) -> {
            storedMatrixTransformations.transform(graphicsHolder, offset);
            IDrawing.drawTexture(graphicsHolder,
                    SIDE_OFFSET - SIZE, HEIGHT - SIZE, -0.19375F,
                    SIDE_OFFSET + SIZE, HEIGHT + SIZE, -0.19375F,
                    Direction.UP, rightColor, GraphicsHolder.getDefaultLight());
            graphicsHolder.pop();
        });

        // Top signal (offset left or right based on topOffsetRight)
        float topXOffset = topOffsetRight ? TOP_SIDE_OFFSET : -TOP_SIDE_OFFSET;

        MainRenderer.scheduleRender(new Identifier(Init.MOD_ID, "textures/block/digital_signal.png"), false, QueuedRenderLayer.LIGHT, (graphicsHolder, offset) -> {
            storedMatrixTransformations.transform(graphicsHolder, offset);
            IDrawing.drawTexture(graphicsHolder,
                    topXOffset - SIZE, TOP_HEIGHT - SIZE, -0.19375F,
                    topXOffset + SIZE, TOP_HEIGHT + SIZE, -0.19375F,
                    Direction.UP, topColor, GraphicsHolder.getDefaultLight());
            graphicsHolder.pop();
        });
    }
}
