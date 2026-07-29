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
import org.mtr.mod.render.StoredMatrixTransformations;

public class RenderSignalLight4Aspect<T extends BlockSignalBase.BlockEntityBase> extends RenderBritishSignalBase<T> {

    public RenderSignalLight4Aspect(BlockEntityRenderer.Argument dispatcher) {
        super(dispatcher, 12, 4);
    }

    @Override
    protected void render(StoredMatrixTransformations storedMatrixTransformations, T entity, float tickDelta, int occupiedAspect, boolean isBackSide, int light) {
        int topColor, middleTopColor, middleBottomColor, bottomColor;

        float topY = 0.75F;
        float middleTopY = topY - 0.3F;
        float middleBottomY = middleTopY - 0.3F;
        float bottomY = middleBottomY - 0.3F;

        switch (occupiedAspect) {
            case 1: // Red
                topColor = 0xFF222222; // Dim
                middleTopColor = 0xFF222222; // Dim
                middleBottomColor = 0xFF222222; // Dim
                bottomColor = -65536; // Red
                break;
            case 2: // Yellow
                topColor = 0xFF222222; // Dim
                middleTopColor = 0xFF222222; // Dim
                middleBottomColor = -22016; // Yellow
                bottomColor = 0xFF222222; // Dim
                break;
            case 3: // Double Yellow
                topColor = -22016; // Yellow
                middleTopColor = 0xFF222222; // Dim
                middleBottomColor = -22016; // Yellow
                bottomColor = 0xFF222222; // Dim
                break;
            default: // Green
                topColor = 0xFF222222; // Dim
                middleTopColor = 0xFF29D28F; // Green
                middleBottomColor = 0xFF222222; // Dim
                bottomColor = 0xFF222222; // Dim
        }

        Identifier texture = new Identifier(Init.MOD_ID, "textures/block/digital_signal.png");

        // Render all four aspects
        renderAspect(storedMatrixTransformations, texture, topY, topColor);
        renderAspect(storedMatrixTransformations, texture, middleTopY, middleTopColor);
        renderAspect(storedMatrixTransformations, texture, middleBottomY, middleBottomColor);
        renderAspect(storedMatrixTransformations, texture, bottomY, bottomColor);
    }

    private void renderAspect(StoredMatrixTransformations storedMatrixTransformations, Identifier texture, float yOffset, int color) {
        MainRenderer.scheduleRender(texture, false, QueuedRenderLayer.LIGHT, (graphicsHolder, offset) -> {
            storedMatrixTransformations.transform(graphicsHolder, offset);
            IDrawing.drawTexture(graphicsHolder, -0.125F, yOffset - 0.05F, -0.19375F, 0.125F, yOffset + 0.2F, -0.19375F, Direction.UP, color, GraphicsHolder.getDefaultLight());
            graphicsHolder.pop();
        });
    }
}
