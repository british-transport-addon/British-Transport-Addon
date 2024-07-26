package org.eu.awesomekalin.jta.mod.blocks;

import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mapping.mapper.SlabBlockExtension;
import org.mtr.mod.block.IBlock;

public class HalfWallSignBase extends DirectionalSlabBlockExtension {
    public HalfWallSignBase() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque());
    }

    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        try {
            SlabType type = (SlabType) IBlock.getStatePropertySafe(state, new EnumProperty(TYPE));
            if (type == SlabType.BOTTOM) {
                return IBlock.getVoxelShapeByDirection(0, 0, 0, 8, 8, 1, IBlock.getStatePropertySafe(state, FACING));
            } else if (type == SlabType.DOUBLE) {
                return IBlock.getVoxelShapeByDirection(4, 4, 0, 12, 12, 1, IBlock.getStatePropertySafe(state, FACING));
            } else if (type == SlabType.TOP) {
                return IBlock.getVoxelShapeByDirection(8, 8, 0, 16, 16, 1, IBlock.getStatePropertySafe(state, FACING));
            }
        } catch (Exception e) {

        }
        return IBlock.getVoxelShapeByDirection(0, 0, 0, 16, 16, 1, IBlock.getStatePropertySafe(state, FACING));
    }

    @Override
    public void onPlaced2(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        assert placer != null;
        final Direction facing = placer.getHorizontalFacing().getOpposite().rotateYClockwise().rotateYClockwise();
        world.setBlockState(pos, state.with(new Property<>(FACING.data), facing.data));
    }
}
