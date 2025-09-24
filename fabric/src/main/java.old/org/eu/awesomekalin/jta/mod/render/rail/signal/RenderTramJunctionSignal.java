package org.eu.awesomekalin.jta.mod.render.rail.signal;

import org.eu.awesomekalin.jta.mod.Init;
import org.mtr.libraries.it.unimi.dsi.fastutil.ints.IntAVLTreeSet;
import org.mtr.libraries.it.unimi.dsi.fastutil.ints.IntArrayList;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mod.block.BlockSignalBase;
import org.mtr.mod.client.IDrawing;
import org.mtr.mod.render.MainRenderer;
import org.mtr.mod.render.QueuedRenderLayer;
import org.mtr.mod.render.StoredMatrixTransformations;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.ObjectObjectImmutablePair;

import java.util.stream.Stream;

public class RenderTramJunctionSignal<T extends BlockSignalBase.BlockEntityBase> extends RenderBritishSignalBase<T> {

	public RenderTramJunctionSignal(Argument dispatcher) {
		super(dispatcher, 12, 2);
	}

	@Override
	protected void render(StoredMatrixTransformations storedMatrixTransformations, T entity, float tickDelta, int occupiedAspect, boolean isBackSide, int light) {
		final Identifier texture = new Identifier(Init.MOD_ID, "textures/block/digital_signal.png");
		final int activeColor = 0xFFFF9900; // Orange
		final int inactiveColor = 0xFF222222;

		// Set up spacing and dot sizes to keep the same size constraints as the original tram signal
		final float spacing = 0.15F;
		final float dotSize = 0.06F;
		final float centerY = 0.4F;


		final World world = entity.getWorld2();
		if (world == null) {
			return;
		}

		final BlockPos pos = entity.getPos2();
		final BlockState state = world.getBlockState(pos);


		float angle = BlockSignalBase.getAngle(state);
		// Get the aspects and determine the direction
		ObjectObjectImmutablePair<IntArrayList, IntAVLTreeSet> aspects = RenderBritishSignalBase.getAspects(pos, angle + 90.0F);

		// Determine if the signal is going right or left based on aspects
		boolean goRight = Stream.of(MapColor.getMagentaMapped(), MapColor.getPinkMapped(), MapColor.getPurpleMapped(), MapColor.getRedMapped(), MapColor.getLightBlueMapped(), MapColor.getGrayMapped(), MapColor.getBlueMapped(), MapColor.getBlackMapped())
				.map(MapColor::getColorMapped)
				.anyMatch(c -> aspects.right().contains(c));

		// Use MainRenderer to render the signal
		MainRenderer.scheduleRender(texture, false, QueuedRenderLayer.LIGHT, (graphicsHolder, offset) -> {
			storedMatrixTransformations.transform(graphicsHolder, offset);

			// Middle 3 dots (angled)
			float x, y;

			// Dot 1 at center
			x = 0;
			y = centerY;
			IDrawing.drawTexture(graphicsHolder, x - dotSize, y - dotSize, -0.19375F, x + dotSize, y + dotSize, -0.19375F, Direction.UP, activeColor, GraphicsHolder.getDefaultLight());

			// Dot 2 and Dot 3 (angled)
			if (goRight) {
				// Right-side angle
				x = -spacing;
				y = centerY + (goRight ? spacing : -spacing);
				IDrawing.drawTexture(graphicsHolder, x - dotSize, y - dotSize, -0.19375F, x + dotSize, y + dotSize, -0.19375F, Direction.UP, activeColor, GraphicsHolder.getDefaultLight());

				x = spacing;
				y = centerY - spacing;
				IDrawing.drawTexture(graphicsHolder, x - dotSize, y - dotSize, -0.19375F, x + dotSize, y + dotSize, -0.19375F, Direction.UP, activeColor, GraphicsHolder.getDefaultLight());
			} else {
				// Left-side angle
				x = spacing;
				y = centerY + (goRight ? -spacing : spacing);
				IDrawing.drawTexture(graphicsHolder, x - dotSize, y - dotSize, -0.19375F, x + dotSize, y + dotSize, -0.19375F, Direction.UP, activeColor, GraphicsHolder.getDefaultLight());

				x = -spacing;
				y = centerY - spacing;
				IDrawing.drawTexture(graphicsHolder, x - dotSize, y - dotSize, -0.19375F, x + dotSize, y + dotSize, -0.19375F, Direction.UP, activeColor, GraphicsHolder.getDefaultLight());
			}

			// Top and Bottom offset dots (positioned on opposing sides)
			// Top Dot
			x = goRight ? -spacing : spacing;
			IDrawing.drawTexture(graphicsHolder, x - dotSize, centerY + 2 * spacing - dotSize, -0.19375F, x + dotSize, centerY + 2 * spacing + dotSize, -0.19375F, Direction.UP, activeColor, GraphicsHolder.getDefaultLight());

			// Bottom Dot
			x = goRight ? spacing : -spacing;
			IDrawing.drawTexture(graphicsHolder, x - dotSize, centerY - 2 * spacing - dotSize, -0.19375F, x + dotSize, centerY - 2 * spacing + dotSize, -0.19375F, Direction.UP, activeColor, GraphicsHolder.getDefaultLight());

			graphicsHolder.pop();
		});
	}
}
