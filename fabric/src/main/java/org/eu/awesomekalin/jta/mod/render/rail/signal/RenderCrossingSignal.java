package org.eu.awesomekalin.jta.mod.render.rail.signal;

import org.eu.awesomekalin.jta.mod.Init;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.Direction;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.mapper.BlockEntityRenderer;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mod.block.BlockSignalBase;
import org.mtr.mod.client.IDrawing;
import org.mtr.mod.render.MainRenderer;
import org.mtr.mod.render.QueuedRenderLayer;
import org.mtr.mod.render.StoredMatrixTransformations;

import java.util.HashMap;
import java.util.Map;

public class RenderCrossingSignal<T extends BlockSignalBase.BlockEntityBase> extends RenderBritishSignalBase<T> {

    public RenderCrossingSignal(BlockEntityRenderer.Argument dispatcher) {
        super(dispatcher, 12, 2);
    }

    protected final Map<BlockPos, Long> crossingTimings = new HashMap<>();

    @Override
    protected void render(StoredMatrixTransformations storedMatrixTransformations, T entity, float tickDelta, int occupiedAspect, boolean isBackSide, int light) {
        final float xOffset = 0.4F;
        final float bottomYOffset = 0.1F; // Adjusted Y offset for bottom light
        final int activeColor = 0xFFFF0000; // Red (On)
        final int inactiveColor = 0xFF222222; // Dim (Off)
        final int yellowColor = 0xFFFFAA00; // Yellow (On)

        long currentTime = System.currentTimeMillis();
        BlockPos entityPos = entity.getPos2();

        if (occupiedAspect != 1) {
            crossingTimings.put(entityPos, currentTime);
        }

        long elapsed = currentTime - crossingTimings.getOrDefault(entityPos, currentTime);
        boolean isFlashing = (currentTime / 500) % 2 == 0; // Flash every 500ms

        boolean topLightsOn = elapsed < 10000; // First 10 sec solid red
        boolean topLightsFlash = elapsed >= 10000 && occupiedAspect == 1; // After 10 sec, start flashing

        int bottomColor = occupiedAspect == 1 && elapsed < 5000 ? yellowColor : inactiveColor; // Yellow for first 5 sec

        int leftDisplayColor = (occupiedAspect == 1 && elapsed > 5000 && topLightsOn || (topLightsFlash && isFlashing)) ? activeColor : inactiveColor;
        int rightDisplayColor = (occupiedAspect == 1 && elapsed > 5000 && topLightsOn || (topLightsFlash && !isFlashing)) ? activeColor : inactiveColor;

        MainRenderer.scheduleRender(new Identifier(Init.MOD_ID, "textures/block/digital_signal.png"), false, QueuedRenderLayer.LIGHT, (graphicsHolder, offset) -> {
            storedMatrixTransformations.transform(graphicsHolder, offset);

            float y = .5F;
            float size = 0.2F;

            // Left light
            IDrawing.drawTexture(graphicsHolder, -xOffset - size, y - size, -0.19375F,
                    -xOffset + size, y + size, -0.19375F,
                    Direction.UP, leftDisplayColor, GraphicsHolder.getDefaultLight());

            // Right light
            IDrawing.drawTexture(graphicsHolder, xOffset - size, y - size, -0.19375F,
                    xOffset + size, y + size, -0.19375F,
                    Direction.UP, rightDisplayColor, GraphicsHolder.getDefaultLight());

            // Bottom light
            IDrawing.drawTexture(graphicsHolder, -size, bottomYOffset - size, -0.19375F,
                    size, bottomYOffset + size, -0.19375F,
                    Direction.UP, bottomColor, GraphicsHolder.getDefaultLight());

            graphicsHolder.pop();
        });
    }
}
