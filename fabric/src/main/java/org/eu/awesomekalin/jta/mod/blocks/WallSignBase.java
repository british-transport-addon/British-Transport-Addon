package org.eu.awesomekalin.jta.mod.blocks;

import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mod.block.IBlock;

import javax.annotation.Nonnull;

public class WallSignBase extends DirectionalBlockExtension {
    public WallSignBase() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque());
    }

    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return IBlock.getVoxelShapeByDirection(0, 0, 0, 16, 16, 1, IBlock.getStatePropertySafe(state, FACING));
    }
    @Override
    public void onPlaced2(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        assert placer != null;
        final Direction facing = placer.getHorizontalFacing().getOpposite().rotateYClockwise().rotateYClockwise();
        world.setBlockState(pos, state.with(new Property<>(FACING.data), facing.data));
    }
}
