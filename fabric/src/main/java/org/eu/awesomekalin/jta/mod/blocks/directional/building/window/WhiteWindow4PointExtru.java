package org.eu.awesomekalin.jta.mod.blocks.directional.building.window;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mod.block.IBlock;

import javax.annotation.Nonnull;

public class WhiteWindow4PointExtru extends DirectionalBlockExtension {
    public WhiteWindow4PointExtru() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
    }

    @Nonnull
    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        final Direction facing = IBlock.getStatePropertySafe(state, FACING);
        return BlockHelper.shapeUnion(
                IBlock.getVoxelShapeByDirection(-10.95, 16, 18.099999999999994, -9.575, 21, 20.099999999999994, facing),
                IBlock.getVoxelShapeByDirection(-10.95, 1, 18.099999999999994, -9.575, 15, 20.099999999999994, facing),
                IBlock.getVoxelShapeByDirection(0, -16, 6, 16, 0, 8, facing),
                IBlock.getVoxelShapeByDirection(16, -16, 6, 20, 0, 8, facing),
                IBlock.getVoxelShapeByDirection(-4, -16, 6, 0, 0, 8, facing),
                IBlock.getVoxelShapeByDirection(-13.65, -16, 11.65, -1.65, 0, 13.65, facing),
                IBlock.getVoxelShapeByDirection(20, -16, 6, 32, 0, 8, facing),
                IBlock.getVoxelShapeByDirection(21, 1, 6, 31, 15, 8, facing),
                IBlock.getVoxelShapeByDirection(-3, 1, 6, 0, 15, 8, facing),
                IBlock.getVoxelShapeByDirection(16, 1, 6, 19, 15, 8, facing),
                IBlock.getVoxelShapeByDirection(0, 1, 6, 16, 15, 8, facing),
                IBlock.getVoxelShapeByDirection(-12.65, 1, 11.65, -2.6500000000000004, 15, 13.65, facing),
                IBlock.getVoxelShapeByDirection(21, 16, 6, 31, 21, 8, facing),
                IBlock.getVoxelShapeByDirection(-3, 16, 6, 0, 21, 8, facing),
                IBlock.getVoxelShapeByDirection(16, 16, 6, 19, 21, 8, facing),
                IBlock.getVoxelShapeByDirection(0, 16, 6, 16, 21, 8, facing),
                IBlock.getVoxelShapeByDirection(-12.65, 16, 11.65, -2.6500000000000004, 21, 13.65, facing),
                IBlock.getVoxelShapeByDirection(16, 22, 6, 20, 32, 8, facing),
                IBlock.getVoxelShapeByDirection(-13.65, 22, 11.65, -1.65, 32, 13.65, facing),
                IBlock.getVoxelShapeByDirection(-4, 22, 6, 0, 32, 8, facing),
                IBlock.getVoxelShapeByDirection(0, 22, 6, 16, 32, 8, facing),
                IBlock.getVoxelShapeByDirection(20, 22, 6, 32, 32, 8, facing),
                IBlock.getVoxelShapeByDirection(-13.65, 15, 11.65, -1.65, 16, 13.65, facing),
                IBlock.getVoxelShapeByDirection(-4, 15, 6, 0, 16, 8, facing),
                IBlock.getVoxelShapeByDirection(16, 15, 6, 20, 16, 8, facing),
                IBlock.getVoxelShapeByDirection(0, 15, 6, 16, 16, 8, facing),
                IBlock.getVoxelShapeByDirection(20, 15, 6, 32, 16, 8, facing),
                IBlock.getVoxelShapeByDirection(-13.65, 0, 9.65, -1.6500000000000004, 1, 13.65, facing),
                IBlock.getVoxelShapeByDirection(-4, 0, 4, 0, 1, 8, facing),
                IBlock.getVoxelShapeByDirection(16, 0, 4, 20, 1, 8, facing),
                IBlock.getVoxelShapeByDirection(0, 0, 4, 16, 1, 8, facing),
                IBlock.getVoxelShapeByDirection(20, 0, 4, 32, 1, 8, facing),
                IBlock.getVoxelShapeByDirection(-13.65, 21, 11.65, -1.65, 22, 13.65, facing),
                IBlock.getVoxelShapeByDirection(-4, 21, 6, 0, 22, 8, facing),
                IBlock.getVoxelShapeByDirection(16, 21, 6, 20, 22, 8, facing),
                IBlock.getVoxelShapeByDirection(0, 21, 6, 16, 22, 8, facing),
                IBlock.getVoxelShapeByDirection(20, 21, 6, 32, 22, 8, facing),
                IBlock.getVoxelShapeByDirection(19.75, 0, 4.15, 21.35, 1, 6.65, facing),
                IBlock.getVoxelShapeByDirection(-5.299999999999998, 0, 4.3999999999999995, -3.699999999999999, 1, 6.8999999999999995, facing),
                IBlock.getVoxelShapeByDirection(-3.1500000000000004, 16, 10.65, -2.1500000000000004, 21, 12.65, facing),
                IBlock.getVoxelShapeByDirection(-3.1500000000000004, 1, 10.65, -2.1500000000000004, 15, 12.65, facing),
                IBlock.getVoxelShapeByDirection(-4, 16, 6, -3, 21, 8, facing),
                IBlock.getVoxelShapeByDirection(-4, 1, 6, -3, 15, 8, facing),
                IBlock.getVoxelShapeByDirection(16, 16, 6, 19, 21, 8, facing),
                IBlock.getVoxelShapeByDirection(16, 1, 6, 19, 15, 8, facing),
                IBlock.getVoxelShapeByDirection(19, 16, 6, 20, 21, 8, facing),
                IBlock.getVoxelShapeByDirection(19, 1, 6, 20, 15, 8, facing),
                IBlock.getVoxelShapeByDirection(20.5, 1, 5, 21.5, 15, 7, facing),
                IBlock.getVoxelShapeByDirection(20.5, 16, 5, 21.5, 21, 7, facing),
                IBlock.getVoxelShapeByDirection(28.25, 1, 12.75, 29.25, 15, 14.75, facing),
                IBlock.getVoxelShapeByDirection(28.25, 16, 12.75, 29.25, 21, 14.75, facing)
        );
    }
}
