package org.eu.awesomekalin.jta.mod.blocks.directional;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mod.block.IBlock;

import javax.annotation.Nonnull;

public class StationStepsRight extends DirectionalBlockExtension {

    public StationStepsRight() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
    }

    @Nonnull
    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        final Direction facing = IBlock.getStatePropertySafe(state, FACING);
        return BlockHelper.shapeUnion(
                IBlock.getVoxelShapeByDirection(0, 0, 12, 16, 16, 16, facing),
                IBlock.getVoxelShapeByDirection(0, 0, 8, 16, 12, 12, facing),
                IBlock.getVoxelShapeByDirection(0, 0, 4, 16, 8, 8, facing),
                IBlock.getVoxelShapeByDirection(0, 0, 0, 16, 4, 4, facing)
        );
    }

}
