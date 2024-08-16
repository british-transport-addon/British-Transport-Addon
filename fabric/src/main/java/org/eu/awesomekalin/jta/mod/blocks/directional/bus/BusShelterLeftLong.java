package org.eu.awesomekalin.jta.mod.blocks.directional.bus;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mod.block.IBlock;

import javax.annotation.Nonnull;

public class BusShelterLeftLong extends DirectionalBlockExtension {
    public BusShelterLeftLong() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
    }

    @Nonnull
    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        final Direction facing = IBlock.getStatePropertySafe(state, FACING);
        return BlockHelper.shapeUnion(
                IBlock.getVoxelShapeByDirection(9, -11.5, 14, 30, -9.5, 16, facing),
                IBlock.getVoxelShapeByDirection(-16, -3.75, 9, 28, -2.5, 13, facing),
                IBlock.getVoxelShapeByDirection(26, -12, 9, 28, -3.75, 13, facing),
                IBlock.getVoxelShapeByDirection(-16, -12, 9, -14, -3.75, 13, facing),
                IBlock.getVoxelShapeByDirection(-16, -2.5, 10.75, 28, -2.25, 11.25, facing),
                IBlock.getVoxelShapeByDirection(-16, -2.5, 10, 28, -2.25, 10.5, facing),
                IBlock.getVoxelShapeByDirection(-16, -2.5, 11.5, 28, -2.25, 12, facing),
                IBlock.getVoxelShapeByDirection(-16, -2.5, 12.5, 28, -2.25, 13, facing),
                IBlock.getVoxelShapeByDirection(-16, -2.5, 9, 28, -2.25, 9.5, facing),
                IBlock.getVoxelShapeByDirection(-14, 2, 14, 7, 4, 16, facing),
                IBlock.getVoxelShapeByDirection(30, 2, 0, 32, 4, 14, facing),
                IBlock.getVoxelShapeByDirection(30, -11.5, 0, 32, -9.5, 14, facing),
                IBlock.getVoxelShapeByDirection(8, 3.5, 14.75, 30, 25, 15.25, facing),
                IBlock.getVoxelShapeByDirection(-14, 3.5, 14.75, 8, 25, 15.25, facing),
                IBlock.getVoxelShapeByDirection(-14, -9.5, 14.75, 8, 2, 15.25, facing),
                IBlock.getVoxelShapeByDirection(8, -9.5, 14.75, 30, 2, 15.25, facing),
                IBlock.getVoxelShapeByDirection(30.5, -9.5, 0, 31.5, 2, 14, facing),
                IBlock.getVoxelShapeByDirection(30.5, 3.5, 0, 31.5, 25.5, 14, facing),
                IBlock.getVoxelShapeByDirection(-16, 25, -2, 32, 27, 16, facing),
                IBlock.getVoxelShapeByDirection(30, -16, 14, 32, 25, 16, facing),
                IBlock.getVoxelShapeByDirection(30, -16, -2, 32, 25, 0, facing),
                IBlock.getVoxelShapeByDirection(-16, -16, 14, -14, 25, 16, facing),
                IBlock.getVoxelShapeByDirection(7, -16, 14, 9, 25, 16, facing),
                IBlock.getVoxelShapeByDirection(-14, -11.5, 14, 7, -9.5, 16, facing),
                IBlock.getVoxelShapeByDirection(9, 2, 14, 30, 4, 16, facing)
        );
    }
}
