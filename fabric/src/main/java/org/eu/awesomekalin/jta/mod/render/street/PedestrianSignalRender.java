package org.eu.awesomekalin.jta.mod.render.street;

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


public class PedestrianSignalRender<T extends BlockSignalBase.BlockEntityBase> extends RenderSignalBase<T> {
    private final boolean redOnTop;
    private final int proceedColor;

    public PedestrianSignalRender(Argument dispatcher, boolean redOnTop, int proceedColor) {
        super(dispatcher, 12, 2);
        this.redOnTop = redOnTop;
        this.proceedColor = proceedColor;
    }

    @Override
    protected void render(StoredMatrixTransformations storedMatrixTransformations, T entity, float tickDelta, int occupiedAspect, boolean isBackSide) {
        final float y = occupiedAspect > 0 == redOnTop ? 0.4375F : 0.0625F;
        MainRenderer.scheduleRender(new Identifier(Init.MOD_ID, "textures/block/white.png"), false, QueuedRenderLayer.LIGHT, (graphicsHolder, offset) -> {
            storedMatrixTransformations.transform(graphicsHolder, offset);
            IDrawing.drawTexture(graphicsHolder, -0.125F, y, -0.19375F, 0.125F, y + 0.25F, -0.19375F, Direction.UP, occupiedAspect > 0 ? 0xFFFF0000 : proceedColor, GraphicsHolder.getDefaultLight());
            graphicsHolder.pop();
        });
    }
}