package org.eu.awesomekalin.jta.mod.render.rail.signal;

import org.eu.awesomekalin.jta.mod.Init;
import org.mtr.mapping.holder.Direction;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mod.block.BlockSignalBase;
import org.mtr.mod.client.IDrawing;
import org.mtr.mod.render.MainRenderer;
import org.mtr.mod.render.QueuedRenderLayer;
import org.mtr.mod.render.RenderSignalBase;
import org.mtr.mod.render.StoredMatrixTransformations;

public class RenderBannerRepeaterSignal<T extends BlockSignalBase.BlockEntityBase> extends RenderSignalBase<T> {

	private final int proceedColor;

	public RenderBannerRepeaterSignal(Argument dispatcher, int proceedColor) {
		super(dispatcher, 12, 1);
		this.proceedColor = proceedColor;
	}

	@Override
	protected void render(StoredMatrixTransformations storedMatrixTransformations, T entity, float tickDelta, int occupiedAspect, boolean isBackSide) {
		final float y = 0.085F;
		MainRenderer.scheduleRender(new Identifier(Init.MOD_ID, "textures/block/" + (occupiedAspect > 0 ? "banner_horizontal" : "banner_angle") + ".png"), false, QueuedRenderLayer.LIGHT, (graphicsHolder, offset) -> {
			storedMatrixTransformations.transform(graphicsHolder, offset);
			IDrawing.drawTexture(graphicsHolder, -0.35F, y - .025f, -0.19375F, 0.35F, y + 0.675F, -0.19375F, Direction.UP, occupiedAspect > 0 ? 0xFFFF0000 : proceedColor, GraphicsHolder.getDefaultLight());
			graphicsHolder.pop();
		});
	}
}