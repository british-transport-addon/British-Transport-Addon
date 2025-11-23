package org.eu.awesomekalin.jta.render;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import org.eu.awesomekalin.jta.blocks.BarrierPlatformBlock;
import org.mtr.MTRClient;
import org.mtr.client.IDrawing;
import org.mtr.render.*;

public class RenderBarrierPlatform<T extends BarrierPlatformBlock.BarrierPlatformBlockEntity> extends BlockEntityRendererExtension<T> {
    public RenderBarrierPlatform() {
        super();
    }

    @Override
    public void render(BarrierPlatformBlock.BarrierPlatformBlockEntity blockEntity, ClientWorld world, ClientPlayerEntity clientPlayerEntity, float tickDelta, int light, int overlay) {
        if (world == null) {
            return;
        }

        if (clientPlayerEntity == null) {
            return;
        }

        final StoredMatrixTransformations storedMatrixTransformations = new StoredMatrixTransformations(0.5 + blockEntity.getPos().getX(), blockEntity.getPos().getY(), 0.5 + blockEntity.getPos().getZ());

        if (RenderRails.isHoldingRailRelated(clientPlayerEntity)) {
            MainRenderer.scheduleRender(Identifier.of("minecraft", "textures/item/barrier.png"), false, QueuedRenderLayer.INTERIOR, (graphicsHolderNew, vertexConsumer, offset) -> {
                storedMatrixTransformations.transform(graphicsHolderNew, offset);
                graphicsHolderNew.translate(0, 0.5, 0);
                MTRClient.transformToFacePlayer(graphicsHolderNew, blockEntity.getPos().getX() + 0.5, blockEntity.getPos().getY() + 0.5, blockEntity.getPos().getZ() + 0.5);
                IDrawing.drawTexture(graphicsHolderNew, vertexConsumer, -0.5F, -0.5F, 1, 1, Direction.UP, 15);
                graphicsHolderNew.pop();
            });
        }
    }
}
