package org.eu.awesomekalin.jta.mod.blocks.directional.bus;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mod.block.IBlock;

import javax.annotation.Nonnull;

public class BusShelterRightWithAdvertOpen extends DirectionalBlockExtension {
    public BusShelterRightWithAdvertOpen() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
    }


    @Nonnull
    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        final Direction facing = IBlock.getStatePropertySafe(state, FACING).getOpposite();
        return BlockHelper.shapeUnion(

                IBlock.getVoxelShapeByDirection(-8, 25, -8, 32, 27, 16, facing),
                IBlock.getVoxelShapeByDirection(-8, -13.5, -6, -6, -9.5, 14, facing),
                IBlock.getVoxelShapeByDirection(-8, -13.5, -6, -6, -9.5, 14, facing),
                IBlock.getVoxelShapeByDirection(-8, 19, -6, -6, 21, 14, facing),
                IBlock.getVoxelShapeByDirection(-7.5, -10, -6, -6.5, 20, 14, facing),
                IBlock.getVoxelShapeByDirection(-8, -15.5, -8, -6, 21, -6, facing),
                IBlock.getVoxelShapeByDirection(-8, 20.5, -1, -6, 27, 1, facing),
                IBlock.getVoxelShapeByDirection(-8, -15.5, 14, -6, 25, 16, facing),
                IBlock.getVoxelShapeByDirection(-8, 20.5, 7, -6, 27, 9, facing),
                IBlock.getVoxelShapeByDirection(13, -11.5, 14, 30, -9.5, 16, facing),
                IBlock.getVoxelShapeByDirection(12, -9.5, 14.75, 30, 2, 15.25, facing),
                IBlock.getVoxelShapeByDirection(12, -16, 14, 14, 25, 16, facing),
                IBlock.getVoxelShapeByDirection(12, 3.5, 14.75, 30, 25, 15.25, facing),
                IBlock.getVoxelShapeByDirection(13, 2, 14, 30, 4, 16, facing)
        );
    }
}
