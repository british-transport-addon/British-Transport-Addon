package org.eu.awesomekalin.jta.mod.render;

import org.eu.awesomekalin.jta.mod.blocks.BarrierPlatformBlock;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityRenderer;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mod.Init;
import org.mtr.mod.InitClient;
import org.mtr.mod.block.BlockEyeCandy;
import org.mtr.mod.block.IBlock;
import org.mtr.mod.client.CustomResourceLoader;
import org.mtr.mod.client.IDrawing;
import org.mtr.mod.render.MainRenderer;
import org.mtr.mod.render.QueuedRenderLayer;
import org.mtr.mod.render.RenderRails;
import org.mtr.mod.render.StoredMatrixTransformations;

public class RenderBarrierPlatform extends BlockEntityRenderer<BarrierPlatformBlock.BarrierPlatformBlockEntity> {

	public RenderBarrierPlatform(Argument dispatcher) {
		super(dispatcher);
	}

	@Override
	public void render(BarrierPlatformBlock.BarrierPlatformBlockEntity blockEntity, float tickDelta, GraphicsHolder graphicsHolder, int light, int overlay) {
		final World world = blockEntity.getWorld2();
		if (world == null) {
			return;
		}

		final MinecraftClient minecraftClient = MinecraftClient.getInstance();
		final ClientPlayerEntity clientPlayerEntity = minecraftClient.getPlayerMapped();
		if (clientPlayerEntity == null) {
			return;
		}

		final StoredMatrixTransformations storedMatrixTransformations = new StoredMatrixTransformations(0.5 + blockEntity.getPos2().getX(), blockEntity.getPos2().getY(), 0.5 + blockEntity.getPos2().getZ());

		if (RenderRails.isHoldingRailRelated(clientPlayerEntity) && minecraftClient.getCurrentScreenMapped() == null) {
			MainRenderer.scheduleRender(new Identifier("minecraft", "textures/item/barrier.png"), false, QueuedRenderLayer.INTERIOR, (graphicsHolderNew, offset) -> {
				storedMatrixTransformations.transform(graphicsHolderNew, offset);
				graphicsHolderNew.translate(0, 0.5, 0);
				InitClient.transformToFacePlayer(graphicsHolderNew, blockEntity.getPos2().getX() + 0.5, blockEntity.getPos2().getY() + 0.5, blockEntity.getPos2().getZ() + 0.5);
				IDrawing.drawTexture(graphicsHolderNew, -0.5F, -0.5F, 1, 1, Direction.UP, GraphicsHolder.getDefaultLight());
				graphicsHolderNew.pop();
			});
		}
	}


	@Override
	public boolean isInRenderDistance(BarrierPlatformBlock.BarrierPlatformBlockEntity blockEntity, Vector3d position) {
		return true;
	}
}