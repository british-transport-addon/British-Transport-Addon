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

public class RenderDirectionSignalLight5Aspect<T extends BlockSignalBase.BlockEntityBase> extends RenderSignalBase<T> {

    private final boolean rightSignal; // Inverted left to right

    public RenderDirectionSignalLight5Aspect(BlockEntityRenderer.Argument dispatcher, boolean rightSignal) {
        super(dispatcher, 12, 2);
        this.rightSignal = rightSignal;
    }

    @Override
    protected void render(StoredMatrixTransformations storedMatrixTransformations, T entity, float tickDelta, int occupiedAspect, boolean isBackSide) {
        final float xOffset = rightSignal ? 0.2F : -0.2F; // Inverted offset
        final int activeColor = 0xFFFFFFFF; // White (On)
        final int inactiveColor = 0xFF222222; // Dim (Off)

        boolean isSignalOn = occupiedAspect > 0; // If any aspect is active, turn all lights on

        MainRenderer.scheduleRender(new Identifier(Init.MOD_ID, "textures/block/white.png"), false, QueuedRenderLayer.LIGHT, (graphicsHolder, offset) -> {
            storedMatrixTransformations.transform(graphicsHolder, offset);

            for (int i = 0; i < 5; i++) {
                int displayColor = isSignalOn ? activeColor : inactiveColor;

                float y = 0.25F + (i * 0.08F); // Stack vertically
                float size = 0.06F; // Make it a square "go brrr"
                IDrawing.drawTexture(graphicsHolder, (i * xOffset) - size, y - size, -0.19375F,
                        (i * xOffset) + size, y + size, -0.19375F,
                        Direction.UP, displayColor, GraphicsHolder.getDefaultLight());
            }

            graphicsHolder.pop();
        });
    }
}
