package org.eu.awesomekalin.jta.mod.render.street;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.eu.awesomekalin.jta.mod.blocks.directional.BPPetrolRoof;
import org.eu.awesomekalin.jta.mod.blocks.directional.street.BritishStreetSign2x1;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityRenderer;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mod.block.BlockSignalLight2Aspect1;
import org.mtr.mod.block.IBlock;
import org.mtr.mod.client.IDrawing;
import org.mtr.mod.data.IGui;
import org.mtr.mod.render.MainRenderer;
import org.mtr.mod.render.QueuedRenderLayer;
import org.mtr.mod.render.RenderSignalLight4Aspect;
import org.mtr.mod.render.StoredMatrixTransformations;

import javax.annotation.Nonnull;
import java.awt.*;

public class BPPetrolRoofRender<T extends BPPetrolRoof.BPPetrolRoofTileEntity> extends BlockEntityRenderer<T> implements IGui, IDrawing {

    public BPPetrolRoofRender(Argument argument) {
        super(argument);
    }

    @Override
    public void render(@Nonnull T entity, float tickDelta, @Nonnull GraphicsHolder a, int light, int overlay) {
        final World world = entity.getWorld2();
        if (world == null) {
            return;
        }

        final BlockPos pos = entity.getPos2();
        final BlockState state = world.getBlockState(pos);
        final Direction facing = IBlock.getStatePropertySafe(state, DirectionalBlockExtension.FACING);

        final StoredMatrixTransformations storedMatrixTransformations = new StoredMatrixTransformations(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
        storedMatrixTransformations.add(graphicsHolderNew -> {
            graphicsHolderNew.rotateYDegrees(-facing.asRotation());
            graphicsHolderNew.rotateZDegrees(180);
            graphicsHolderNew.rotateYDegrees(180);
        });

        MainRenderer.scheduleRender(new Identifier("mtr", "textures/block/white.png"), false, QueuedRenderLayer.LIGHT, (graphicsHolder, offset) -> {
            storedMatrixTransformations.transform(graphicsHolder, offset);
            IDrawing.drawTexture(graphicsHolder, 0, .8f, 8.5f, 16, .8f, 7.5f, facing, Color.GREEN.getRGB(), GraphicsHolder.getDefaultLight());

            graphicsHolder.pop();
        });
    }
}