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

public class RenderCrossingBarrier<T extends BlockSignalBase.BlockEntityBase> extends RenderBritishSignalBase<T> {

    private final Map<BlockPos, Long> barrierTimings = new HashMap<>();

    public RenderCrossingBarrier(BlockEntityRenderer.Argument dispatcher) {
        super(dispatcher, 12, 2);
    }

    @Override
    protected void render(StoredMatrixTransformations storedMatrixTransformations, T entity, float tickDelta, int occupiedAspect, boolean isBackSide, int light) {
        long currentTime = System.currentTimeMillis();
        BlockPos entityPos = entity.getPos2();

        // Track animation timing only on state change
        barrierTimings.putIfAbsent(entityPos, currentTime);
        if (occupiedAspect != 1) {
            barrierTimings.put(entityPos, currentTime);
        }

        long startTime = barrierTimings.getOrDefault(entityPos, currentTime);
        long elapsed = currentTime - startTime;
        float animationDuration = 4000.0F; // 4 seconds animation time
        float progress = Math.min(elapsed / animationDuration, 1.0F); // Clamps between 0-1

        // Calculate the target angle (right for occupiedAspect == 1, left for occupiedAspect == 0)
        float angle = calculateRotationAngle(occupiedAspect, progress);

        // Schedule rendering of the barrier
        MainRenderer.scheduleRender(new Identifier(Init.MOD_ID, "textures/block/white.png"), false, QueuedRenderLayer.LIGHT, (graphicsHolder, offset) -> {
            storedMatrixTransformations.transform(graphicsHolder, offset);
            graphicsHolder.push();

            // Move the pivot to the bottom left corner (this is the corner of the signal)
            graphicsHolder.translate(-4, 0, 0); // Pivot to the bottom left corner
            graphicsHolder.rotateZDegrees(angle); // Rotate based on calculated angle
            graphicsHolder.translate(4, 0, 0); // Move back to original position

            float width = 8.0F;
            float height = 0.25F; // Barrier height (0.25 blocks tall)

            // Draw the barrier texture with the adjusted position and rotation
            IDrawing.drawTexture(graphicsHolder, -width / 2, 4.0F, -0.2F,
                    width / 2, 4.0F + height, -0.2F,
                    Direction.UP, 0xFFFFFFFF, light);

            graphicsHolder.pop();
        });

        // Reset timing when fully opened (vertical)
        if (occupiedAspect == 0 && progress >= 1.0F) {
            barrierTimings.remove(entityPos);
        }
    }

    private float calculateRotationAngle(int occupiedAspect, float progress) {
        // When occupiedAspect is 1, the barrier rotates down to the right (90 degrees)
        if (occupiedAspect == 1) {
            return progress * 90.0F; // Move down
        } else {
            // When occupiedAspect is 0, the barrier goes back up to the vertical position
            return (1.0F - progress) * 90.0F; // Move up to vertical (0 degrees)
        }
    }
}
