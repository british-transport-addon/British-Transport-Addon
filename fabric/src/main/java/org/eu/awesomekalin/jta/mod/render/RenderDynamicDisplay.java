package org.eu.awesomekalin.jta.mod.render;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.eu.awesomekalin.jta.mod.blocks.directional.DisplayBlock;
import org.eu.awesomekalin.jta.mod.init.CustomResourceLoader;
import org.eu.awesomekalin.jta.mod.resource.DisplayResource;
import org.mtr.libraries.it.unimi.dsi.fastutil.longs.LongArrayList;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.ObjectImmutableList;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityRenderer;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mod.block.IBlock;
import org.mtr.mod.client.IDrawing;
import org.mtr.mod.render.MainRenderer;
import org.mtr.mod.render.QueuedRenderLayer;
import org.mtr.mod.render.StoredMatrixTransformations;

public class RenderDynamicDisplay extends BlockEntityRenderer<DisplayBlock.DisplayBlockEntity> {
    private int tickCounter = 0;

    public RenderDynamicDisplay(Argument dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(DisplayBlock.DisplayBlockEntity blockEntity, float tickDelta, GraphicsHolder graphicsHolder, int light, int overlay) {
        tickCounter++;
        final World world = blockEntity.getWorld2();
        if (world == null) {
            return;
        }

        final BlockPos pos = blockEntity.getPos2();
        final BlockState state = world.getBlockState(pos);
        final Direction facing = IBlock.getStatePropertySafe(state, DirectionalBlockExtension.FACING);

        final MinecraftClient minecraftClient = MinecraftClient.getInstance();
        final ClientPlayerEntity clientPlayerEntity = minecraftClient.getPlayerMapped();
        if (clientPlayerEntity == null) {
            return;
        }

        StoredMatrixTransformations storedMatrixTransformations = new StoredMatrixTransformations(
                0.5 + blockEntity.getPos2().getX(),
                0.5 + blockEntity.getPos2().getY(),
                0.98 + blockEntity.getPos2().getZ()
        );

        switch (facing) {
            case NORTH:
                storedMatrixTransformations = new StoredMatrixTransformations(
                        0.5 + blockEntity.getPos2().getX(),
                        0.5 + blockEntity.getPos2().getY(),
                        0.02 + blockEntity.getPos2().getZ()
                );
                break;

            case EAST:
                storedMatrixTransformations = new StoredMatrixTransformations(
                        0.98 + blockEntity.getPos2().getX(),
                        0.5 + blockEntity.getPos2().getY(),
                        0.51 + blockEntity.getPos2().getZ()
                );
                break;

            case WEST:
                storedMatrixTransformations = new StoredMatrixTransformations(
                        0.02 + blockEntity.getPos2().getX(),
                        0.5 + blockEntity.getPos2().getY(),
                        0.51 + blockEntity.getPos2().getZ()
                );
                break;
        }

        DisplayResource displayResource = getDisplayResource(blockEntity);

        StoredMatrixTransformations finalStoredMatrixTransformations = storedMatrixTransformations;
        MainRenderer.scheduleRender(displayResource.getTexture(), false, QueuedRenderLayer.INTERIOR, (graphicsHolderNew, offset) -> {
            finalStoredMatrixTransformations.transform(graphicsHolderNew, offset);
            graphicsHolderNew.rotateZDegrees(180);

            graphicsHolderNew.rotateYDegrees(facing.asRotation());

            IDrawing.drawTexture(graphicsHolderNew, -0.5F * blockEntity.getWidth(), -0.5F * blockEntity.getHeight(), blockEntity.getWidth(), blockEntity.getHeight(), Direction.UP, GraphicsHolder.getDefaultLight());
            graphicsHolderNew.pop();
        });

        renderBackground(blockEntity, facing);
    }

    private DisplayResource getDisplayResource(DisplayBlock.DisplayBlockEntity blockEntity) {
        final ObjectImmutableList<DisplayResource> allDisplays = CustomResourceLoader.getDisplays();
        final ObjectArrayList<String> selectedIds = blockEntity.getSelectedIds();
        final LongArrayList selectedIndices = new LongArrayList();

        for (int i = 0; i < allDisplays.size(); i++) {
            final DisplayResource display = allDisplays.get(i);
            if (selectedIds.contains(display.getId())) {
                selectedIndices.add(i);
            }
        }

        int frame = blockEntity.getFrame();
        if (this.tickCounter == 600) {
            frame++;
            if (frame >= selectedIndices.size()) frame = 0;
            blockEntity.setFrame(frame);
        }

        if (this.tickCounter == 601) tickCounter = 0;

        return CustomResourceLoader.getDisplays().get((int) selectedIndices.getLong(frame));
    }

    private void renderBackground(DisplayBlock.DisplayBlockEntity blockEntity, Direction facing) {
        StoredMatrixTransformations storedMatrixTransformations = new StoredMatrixTransformations(
                0.5 + blockEntity.getPos2().getX(),
                0.5 + blockEntity.getPos2().getY(),
                0.99 + blockEntity.getPos2().getZ()
        );

        switch (facing) {
            case NORTH:
                storedMatrixTransformations = new StoredMatrixTransformations(
                        0.5 + blockEntity.getPos2().getX(),
                        0.5 + blockEntity.getPos2().getY(),
                        0.01 + blockEntity.getPos2().getZ()
                );
                break;

            case EAST:
                storedMatrixTransformations = new StoredMatrixTransformations(
                        0.99 + blockEntity.getPos2().getX(),
                        0.5 + blockEntity.getPos2().getY(),
                        0.51 + blockEntity.getPos2().getZ()
                );
                break;

            case WEST:
                storedMatrixTransformations = new StoredMatrixTransformations(
                        0.01 + blockEntity.getPos2().getX(),
                        0.5 + blockEntity.getPos2().getY(),
                        0.51 + blockEntity.getPos2().getZ()
                );
                break;
        }

        StoredMatrixTransformations finalStoredMatrixTransformations = storedMatrixTransformations;
        MainRenderer.scheduleRender(new Identifier("jta", "textures/block/black.png"), false, QueuedRenderLayer.INTERIOR, (graphicsHolderNew, offset) -> {
            finalStoredMatrixTransformations.transform(graphicsHolderNew, offset);
            graphicsHolderNew.rotateZDegrees(180);

            graphicsHolderNew.rotateYDegrees(facing.asRotation());

            IDrawing.drawTexture(graphicsHolderNew, -0.5F * blockEntity.getWidth(), -0.5F * blockEntity.getHeight(), blockEntity.getWidth(), blockEntity.getHeight(), Direction.UP, GraphicsHolder.getDefaultLight());
            graphicsHolderNew.pop();
        });
    }
}
