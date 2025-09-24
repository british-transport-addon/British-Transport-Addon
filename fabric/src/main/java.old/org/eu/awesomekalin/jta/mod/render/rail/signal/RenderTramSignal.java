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
		final Identifier texture = new Identifier(Init.MOD_ID, "textures/block/digital_signal.png");
		final int activeColor = 0xFFFFFFFF;
		final int inactiveColor = 0xFF222222;

		final float spacing = 0.15F;
		final float dotSize = 0.06F;
		final float centerDotSize = 0.09F;
		final float centerY = 0.4F;

		MainRenderer.scheduleRender(texture, false, QueuedRenderLayer.LIGHT, (graphicsHolder, offset) -> {
			storedMatrixTransformations.transform(graphicsHolder, offset);

			// Vertical (aspect 0 or 3)
			for (int i = -2; i <= 2; i++) {
				int color = (occupiedAspect == 0 || occupiedAspect == 3) ? activeColor : inactiveColor;
				float y = centerY + (i * spacing);
				IDrawing.drawTexture(graphicsHolder, -dotSize, y - dotSize, -0.19375F,
						dotSize, y + dotSize, -0.19375F,
						Direction.UP, color, GraphicsHolder.getDefaultLight());
			}

			// Horizontal (aspect 1)
			for (int i = -2; i <= 2; i++) {
				int color = (occupiedAspect == 1) ? activeColor : inactiveColor;
				float x = i * spacing;
				float y = centerY;
				IDrawing.drawTexture(graphicsHolder, x - dotSize, y - dotSize, -0.19375F,
						x + dotSize, y + dotSize, -0.19375F,
						Direction.UP, color, GraphicsHolder.getDefaultLight());
			}

			// Dot (aspect 2)
			if (occupiedAspect == 2) {
                IDrawing.drawTexture(graphicsHolder, -centerDotSize, centerY - centerDotSize, -0.19375F,
						centerDotSize, centerY + centerDotSize, -0.19375F,
						Direction.UP, activeColor, GraphicsHolder.getDefaultLight());
			}

			graphicsHolder.pop();
		});
	}
}
