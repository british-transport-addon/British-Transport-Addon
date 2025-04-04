package org.eu.awesomekalin.jta.mod.render.rail.signal;

import org.eu.awesomekalin.jta.mod.Init;
import org.mtr.mapping.holder.Direction;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mod.block.BlockSignalBase;
import org.mtr.mod.client.IDrawing;
import org.mtr.mod.render.MainRenderer;
import org.mtr.mod.render.QueuedRenderLayer;
import org.mtr.mod.render.StoredMatrixTransformations;

public class RenderTramSignal<T extends BlockSignalBase.BlockEntityBase> extends RenderBritishSignalBase<T> {

	public RenderTramSignal(Argument dispatcher) {
		super(dispatcher, 12, 2);
	}

	@Override
	protected void render(StoredMatrixTransformations storedMatrixTransformations, T entity, float tickDelta, int occupiedAspect, boolean isBackSide, int light) {
		Identifier texture;

		switch (occupiedAspect) {
			case 1:
				texture = new Identifier(Init.MOD_ID, "textures/block/signal_horizontal.png");
				break;
			case 2:
				texture = new Identifier(Init.MOD_ID, "textures/block/signal_dot.png");
				break;
			case 3:
			default:
				texture = new Identifier(Init.MOD_ID, "textures/block/signal_vertical.png");
		}

		MainRenderer.scheduleRender(texture, false, QueuedRenderLayer.LIGHT, (graphicsHolder, offset) -> {
			storedMatrixTransformations.transform(graphicsHolder, offset);
			IDrawing.drawTexture(graphicsHolder, -0.65F, -.4F, -0.19375F, 0.65F, 0.9F, -0.19375F, Direction.UP, 0xFFFFFFFF, GraphicsHolder.getDefaultLight());
			graphicsHolder.pop();
		});
	}
}
