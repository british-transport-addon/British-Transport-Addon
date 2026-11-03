package org.eu.awesomekalin.jta.mod.blocks;

import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mod.block.IBlock;

import javax.annotation.Nonnull;

public class WallSignBase extends DirectionalBlockExtension {
    public WallSignBase() {
        super(BlockHelper.createBlockSettings(false, false).strength(4.0f).nonOpaque());
    }

    @Nonnull
    @Override
    public VoxelShape getOutlineShape2(
            @Nonnull BlockState state,
            @Nonnull BlockView world,
            @Nonnull BlockPos pos,
            @Nonnull ShapeContext context
    ) {
        return IBlock.getVoxelShapeByDirection(0, 0, 0, 16, 16, 1, IBlock.getStatePropertySafe(state, FACING));
    }

    @Override
    public void onPlaced2(
            World world,
            @Nonnull BlockPos pos,
            BlockState state,
            LivingEntity placer,
            @Nonnull ItemStack itemStack
    ) {
        assert placer != null;
        final Direction facing = placer.getHorizontalFacing().getOpposite().rotateYClockwise().rotateYClockwise();
        world.setBlockState(pos, state.with(new Property<>(FACING.data), facing.data));
    }
}
