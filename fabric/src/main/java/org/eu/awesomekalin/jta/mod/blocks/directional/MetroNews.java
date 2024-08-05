package org.eu.awesomekalin.jta.mod.blocks.directional;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mod.block.IBlock;

import javax.annotation.Nonnull;

public class MetroNews extends DirectionalBlockExtension {
    public MetroNews() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
    }


    @Nonnull
    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        final Direction facing = IBlock.getStatePropertySafe(state, FACING);
        return BlockHelper.shapeUnion(
                IBlock.getVoxelShapeByDirection(-2, 0, 6.5, 8, 3, 16, facing),
                IBlock.getVoxelShapeByDirection(8, 0, 6.5, 18, 3, 16, facing),
                IBlock.getVoxelShapeByDirection(8, -2.1000000000000005, 4.4750000000000005, 18, 2.4749999999999996, 6.725, facing),
                IBlock.getVoxelShapeByDirection(-2, -2.1000000000000005, 4.4750000000000005, 8, 2.4749999999999996, 6.725, facing),
                IBlock.getVoxelShapeByDirection(8.5, 5, 15.5, 17.5, 21, 16, facing),
                IBlock.getVoxelShapeByDirection(-1.5, 5, 15.5, 7.5, 21, 16, facing),
                IBlock.getVoxelShapeByDirection(-2, 3, 15.5, 8, 5, 16, facing),
                IBlock.getVoxelShapeByDirection(8, 3, 15.5, 18, 5, 16, facing),
                IBlock.getVoxelShapeByDirection(-2, 21, 15.5, 8, 22, 16, facing),
                IBlock.getVoxelShapeByDirection(8, 21, 15.5, 18, 22, 16, facing),
                IBlock.getVoxelShapeByDirection(7.75, 3, 6.25, 8, 22, 15.5, facing),
                IBlock.getVoxelShapeByDirection(8, 3, 6.25, 8.25, 22, 15.5, facing),
                IBlock.getVoxelShapeByDirection(-2, 5, 15.5, -1.5, 21, 16, facing),
                IBlock.getVoxelShapeByDirection(8, 5, 15.5, 8.5, 21, 16, facing),
                IBlock.getVoxelShapeByDirection(17.5, 5, 15.5, 18, 21, 16, facing),
                IBlock.getVoxelShapeByDirection(7.5, 5, 15.5, 8, 21, 16, facing),
                IBlock.getVoxelShapeByDirection(-2, 5, 6.5, -1.75, 21, 15.5, facing),
                IBlock.getVoxelShapeByDirection(17.75, 5, 6.5, 18, 21, 15.5, facing),
                IBlock.getVoxelShapeByDirection(-2, 21, 6.5, -1.75, 22, 15.5, facing),
                IBlock.getVoxelShapeByDirection(-2, 3, 6.5, -1.75, 5, 15.5, facing),
                IBlock.getVoxelShapeByDirection(17.75, 21, 6.5, 18, 22, 15.5, facing),
                IBlock.getVoxelShapeByDirection(17.75, 3, 6.5, 18, 5, 15.5, facing),
                IBlock.getVoxelShapeByDirection(-1.75, 5, 6.25, -0.5, 20, 6.5, facing),
                IBlock.getVoxelShapeByDirection(8.25, 5, 6.25, 9.5, 20, 6.5, facing),
                IBlock.getVoxelShapeByDirection(6.5, 5, 6.25, 7.75, 20, 6.5, facing),
                IBlock.getVoxelShapeByDirection(16.5, 5, 6.25, 17.75, 20, 6.5, facing),
                IBlock.getVoxelShapeByDirection(8.25, 3, 6.25, 8.75, 5, 6.5, facing),
                IBlock.getVoxelShapeByDirection(-1.75, 3, 6.25, -1.25, 5, 6.5, facing),
                IBlock.getVoxelShapeByDirection(7.25, 3, 6.25, 7.75, 5, 6.5, facing),
                IBlock.getVoxelShapeByDirection(17.25, 3, 6.25, 17.75, 5, 6.5, facing)
        );
    }
}
