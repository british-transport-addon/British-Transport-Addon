package org.eu.awesomekalin.jta.mod.blocks.directional;

import org.eu.awesomekalin.jta.mod.init.CustomResourceLoader;
import org.eu.awesomekalin.jta.mod.screen.DisplaySelectorScreen;
import org.jetbrains.annotations.NotNull;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockExtension;
import org.mtr.mapping.mapper.BlockWithEntity;
import org.mtr.mapping.mapper.DirectionHelper;
import org.mtr.mod.Blocks;
import org.mtr.mod.block.IBlock;

public class DisplayBlock extends BlockExtension implements DirectionHelper, BlockWithEntity {
    public final int width;
    public final int height;
    private final BlockEntityType<?> type;

    public DisplayBlock(int width, int height, BlockEntityType<?> type) {
        super(Blocks.createDefaultBlockSettings(false));
        this.width = width;
        this.height = height;
        this.type = type;
    }

    @NotNull
    @Override
    public ActionResult onUse2(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return IBlock.checkHoldingBrush(world, player, () -> {
            MinecraftClient.getInstance().openScreen(new Screen(DisplaySelectorScreen.create(pos)));
        });
    }

    @Override
    public BlockState getPlacementState2(ItemPlacementContext ctx) {
        final Direction facing = ctx.getPlayerFacing();
        return getDefaultState2().with(new Property<>(FACING.data), facing.data);
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new DisplayBlockEntity(type, blockPos, blockState, width, height);
    }

    public static class DisplayBlockEntity extends BlockEntityExtension {
        private final ObjectArrayList<String> selectedIds;
        private final int width;
        private final int height;

        public DisplayBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, int width, int height) {
            super(type, pos, state);
            selectedIds = new ObjectArrayList<>();
            selectedIds.add(CustomResourceLoader.DEFAULT_DISPLAY);

            this.width = width;
            this.height = height;
        }
    }
}
