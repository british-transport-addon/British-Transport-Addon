package org.eu.awesomekalin.jta.render.rail.signal;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import org.eu.awesomekalin.jta.JTA;
import org.mtr.block.BlockSignalBase;
import org.mtr.client.IDrawing;
import org.mtr.render.MainRenderer;
import org.mtr.render.QueuedRenderLayer;
import org.mtr.render.StoredMatrixTransformations;

public class RenderBannerRepeaterSignal<T extends BlockSignalBase.BlockEntityBase> extends RenderBritishSignalBase<T> {
    private final int proceedColor;

    public RenderBannerRepeaterSignal(int proceedColor) {
        super(12, 1);
        this.proceedColor = proceedColor;
    }

    @Override
    protected void render(StoredMatrixTransformations storedMatrixTransformations, T entity, float tickDelta, int occupiedAspect, boolean isBackSide, int light) {
        final float y = 0.085F;
        MainRenderer.scheduleRender(Identifier.of(JTA.MOD_ID, "textures/block/" + (occupiedAspect == 0 ? "banner_horizontal" : "banner_angle") + ".png"), false, QueuedRenderLayer.LIGHT, (matrixStack, vertexConsumer, offset) -> {
            storedMatrixTransformations.transform(matrixStack, offset);
            IDrawing.drawTexture(matrixStack, vertexConsumer, -0.35F, y - .025f, -0.19375F, 0.35F, y + 0.675F, -0.19375F, Direction.UP, occupiedAspect > 0 ? 0xFFFFFFFF : proceedColor, light);
            matrixStack.pop();
        });
    }
}
