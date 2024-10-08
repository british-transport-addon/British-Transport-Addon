package org.eu.awesomekalin.jta.mod.render.rail.pids;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.eu.awesomekalin.jta.mod.blocks.directional.rail.pids.NationalRail10ClockWhite;
import org.eu.awesomekalin.jta.mod.blocks.directional.rail.pids.NationalRail10ClockWhite;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mapping.mapper.TextHelper;
import org.mtr.mod.block.IBlock;
import org.mtr.mod.render.MainRenderer;
import org.mtr.mod.render.QueuedRenderLayer;
import org.mtr.mod.render.StoredMatrixTransformations;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RenderNationalRail10ClockWhite extends RenderBritishPIDS<NationalRail10ClockWhite.TileEntityNationalRail10ClockWhite> {

    private final float maxWidth;
    private final float maxScale;
    private final float xOffset;
    private final float yOffset;
    private final float zOffset;
    private final float xTilt;
    private final int textColor;

    public RenderNationalRail10ClockWhite(Argument dispatcher, float maxWidth, float maxScale, float xOffset, float yOffset, float zOffset, float xTilt, int textColor) {
        super(dispatcher, 6F, 6.5F, 6, 16.5F, 20, true, 1);
        this.maxWidth = maxWidth;
        this.maxScale = maxScale;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.zOffset = zOffset;
        this.xTilt = xTilt;
        this.textColor = textColor;
    }

    @Override
    public void render(NationalRail10ClockWhite.TileEntityNationalRail10ClockWhite entity, float tickDelta, GraphicsHolder graphicsHolder, int light, int overlay) {
        super.render(entity, tickDelta, graphicsHolder, light, overlay);


        final Style style = Style.getEmptyMapped(); // TODO custom font not working

        final World world = entity.getWorld2();
        if (world == null) {
            return;
        }

        final BlockPos pos = entity.getPos2();
        final BlockState state = world.getBlockState(pos);
        final Direction facing = IBlock.getStatePropertySafe(state, DirectionalBlockExtension.FACING);
        if (!entity.canStoreData.test(world, pos)) {
            return;
        }
        // NEED THIS TO STOP IT MAKING TWO DISPLAYS..

        final MutableText roundelText = TextHelper.setStyle(TextHelper.literal(new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis()))), style);
        final int textWidth = GraphicsHolder.getTextWidth(roundelText);

        final StoredMatrixTransformations storedMatrixTransformations = new StoredMatrixTransformations(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
        storedMatrixTransformations.add(graphicsHolderNew -> {
            graphicsHolderNew.rotateYDegrees(-facing.asRotation());
            graphicsHolderNew.rotateZDegrees(180);
            graphicsHolderNew.rotateYDegrees(90);
        });
        MainRenderer.scheduleRender(QueuedRenderLayer.TEXT, (graphicsHolderNew, offset) -> {
            storedMatrixTransformations.transform(graphicsHolderNew, offset);
            render(graphicsHolderNew, roundelText, textWidth, light);

            graphicsHolderNew.rotateYDegrees(180);
            graphicsHolderNew.translate(1, 0, 0);
            render(graphicsHolderNew, roundelText, textWidth, light);

            graphicsHolderNew.pop();
        });
    }



    private void render(GraphicsHolder graphicsHolder, MutableText roundelText, int textWidth, int light) {
        graphicsHolder.push();
        graphicsHolder.rotateXDegrees(xTilt);
        graphicsHolder.translate(-xOffset, -yOffset, -zOffset - SMALL_OFFSET * 2);

        final float scale = Math.min((maxWidth) / textWidth, maxScale);
        graphicsHolder.scale(scale, scale, scale);
        graphicsHolder.translate(0, -3.5, 0);
        graphicsHolder.drawText(roundelText, -textWidth / 2, 0, textColor, false, GraphicsHolder.getDefaultLight());

        graphicsHolder.pop();
    }
}
