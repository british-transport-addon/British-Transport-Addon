package org.eu.awesomekalin.jta.mod.blocks.directional.roundel;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.eu.awesomekalin.jta.mod.entity.block.OneLineBlockEntity;
import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.jetbrains.annotations.NotNull;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mapping.mapper.BlockWithEntity;
import org.mtr.mod.block.IBlock;

import javax.annotation.Nonnull;

public class RoundelPlatformBase extends DirectionalBlockExtension implements BlockWithEntity {
    private final String defaultText;

    public RoundelPlatformBase(String defaultText) {
        super(BlockHelper.createBlockSettings(false, false).strength(4.0f).nonOpaque().dynamicBounds());
        this.defaultText = defaultText;
    }

    @Nonnull
    @Override
    public BlockEntityExtension createBlockEntity(@Nonnull BlockPos blockPos, @Nonnull BlockState blockState) {
        return new RoundelBaseBlockEntity(blockPos, blockState, defaultText);
    }

    @Override
    public @NotNull VoxelShape getCollisionShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Nonnull
    @Override
    public VoxelShape getOutlineShape2(
            @Nonnull BlockState state,
            @Nonnull BlockView world,
            @Nonnull BlockPos pos,
            @Nonnull ShapeContext context
    ) {
        final Direction facing = IBlock.getStatePropertySafe(state, FACING);
        return BlockHelper.shapeUnion(
                IBlock.getVoxelShapeByDirection(-4, 0, 0, 20, 21, 2.85, facing));
    }

    @Nonnull
    @Override
    public ActionResult onUse2(
            @Nonnull BlockState state,
            @Nonnull World world,
            @Nonnull BlockPos pos,
            PlayerEntity player,
            @Nonnull Hand hand,
            @Nonnull BlockHitResult hit
    ) {
        return ActionResult.SUCCESS;
    }

    public static class RoundelBaseBlockEntity extends OneLineBlockEntity {
        public RoundelBaseBlockEntity(BlockPos pos, BlockState state) {
            super(BlockEntityTypeInit.ROUNDEL_PLATFORM.get(), pos, state, "");
        }

        public RoundelBaseBlockEntity(BlockPos pos, BlockState state, String defaultText) {
            super(BlockEntityTypeInit.ROUNDEL_PLATFORM.get(), pos, state, defaultText);
        }

        public boolean shouldRender() {
            return true;
        }
    }
}
