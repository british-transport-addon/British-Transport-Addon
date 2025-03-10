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

public class RenderCrossingSignal<T extends BlockSignalBase.BlockEntityBase> extends RenderSignalBase<T> {

    public RenderCrossingSignal(BlockEntityRenderer.Argument dispatcher) {
        super(dispatcher, 12, 2);
    }

    @Override
    protected void render(StoredMatrixTransformations storedMatrixTransformations, T entity, float tickDelta, int occupiedAspect, boolean isBackSide) {
        final float xOffset = 0.2F; // Offset from center
        final int activeColor = 0xFFFF0000; // Red (On)
        final int inactiveColor = 0xFF222222; // Dim (Off)

        boolean isSignalOn = occupiedAspect > 0; // If active, turn lights on
        boolean isFlashing = (System.currentTimeMillis() / 500) % 2 == 0; // Flash every 500ms

        MainRenderer.scheduleRender(new Identifier(Init.MOD_ID, "textures/block/red.png"), false, QueuedRenderLayer.LIGHT, (graphicsHolder, offset) -> {
            storedMatrixTransformations.transform(graphicsHolder, offset);

            int leftDisplayColor = (isSignalOn && isFlashing) ? activeColor : inactiveColor;
            int rightDisplayColor = (isSignalOn && !isFlashing) ? activeColor : inactiveColor;
            float y = 0.25F; // Fixed position
            float size = 0.06F; // Square size

            // Left light
            IDrawing.drawTexture(graphicsHolder, -xOffset - size, y - size, -0.19375F,
                    -xOffset + size, y + size, -0.19375F,
                    Direction.UP, leftDisplayColor, GraphicsHolder.getDefaultLight());

            // Right light
            IDrawing.drawTexture(graphicsHolder, xOffset - size, y - size, -0.19375F,
                    xOffset + size, y + size, -0.19375F,
                    Direction.UP, rightDisplayColor, GraphicsHolder.getDefaultLight());

            graphicsHolder.pop();
        });
    }
}
