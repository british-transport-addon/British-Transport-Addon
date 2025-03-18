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

public class RenderDigitalSignalLight2Aspect<T extends BlockSignalBase.BlockEntityBase> extends RenderSignalBase<T> {

	private final boolean redOnTop;

	public RenderDigitalSignalLight2Aspect(BlockEntityRenderer.Argument dispatcher, boolean redOnTop) {
		super(dispatcher, 12, 2);
		this.redOnTop = redOnTop;
	}

	@Override
	protected void render(StoredMatrixTransformations storedMatrixTransformations, T entity, float tickDelta, int occupiedAspect, boolean isBackSide) {
		int topColor;
		int bottomColor;

		float topY = .5F;
		float bottomY = 0.0625F;

		switch (occupiedAspect) {
			case 1: // Red
				topColor = 0xFF222222; // 0xFFFF0000 (Dim)
				bottomColor = -65536; // 0xFFFF0000 (Red)
				break;
			case 2: // Yellow
				topColor = 0xFF222222; // 0xFFFFAA00 (Dim)
				bottomColor = -22016; // 0xFFFFAA00 (Yellow)
				break;
			case 3: // Yellow (both)
				topColor = -22016; // 0xFFFFAA00 (Yellow)
				bottomColor = -22016; // 0xFFFFAA00 (Yellow)
				break;
			default: // Green
				topColor = 0xFF222222; // Dim
				bottomColor = 0xFF29D28F; // Correct green
		}

		// Top signal
		MainRenderer.scheduleRender(new Identifier(Init.MOD_ID, "textures/block/digital_signal.png"), false, QueuedRenderLayer.LIGHT, (graphicsHolder, offset) -> {
			storedMatrixTransformations.transform(graphicsHolder, offset);
			IDrawing.drawTexture(graphicsHolder, -0.15F, topY - .025f, -0.19375F, 0.15F, topY + 0.275F, -0.19375F, Direction.UP, topColor, GraphicsHolder.getDefaultLight());
			graphicsHolder.pop();
		});

		// Bottom signal
		MainRenderer.scheduleRender(new Identifier(Init.MOD_ID, "textures/block/digital_signal.png"), false, QueuedRenderLayer.LIGHT, (graphicsHolder, offset) -> {
			storedMatrixTransformations.transform(graphicsHolder, offset);
			IDrawing.drawTexture(graphicsHolder, -0.15F, bottomY - .025f, -0.19375F, 0.15F, bottomY + 0.275F, -0.19375F, Direction.UP, bottomColor, GraphicsHolder.getDefaultLight());
			graphicsHolder.pop();
		});
	}
}
