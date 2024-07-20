package org.eu.awesomekalin.jta.mod.blocks.directional.building.window;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mod.block.IBlock;

import javax.annotation.Nonnull;

public class WhiteWindowSegmented extends DirectionalBlockExtension {
    public WhiteWindowSegmented() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
    }

    @Nonnull
    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        final Direction facing = IBlock.getStatePropertySafe(state, FACING);
        return BlockHelper.shapeUnion(
                IBlock.getVoxelShapeByDirection(-1.8499999999999996, 9, 1.25, 5.4, 16.5, 1.75, facing),
                IBlock.getVoxelShapeByDirection(-1.8499999999999996, 1, 1.25, 5.4, 8.5, 1.75, facing),
                IBlock.getVoxelShapeByDirection(6.15, 1, 1.25, 13.4, 8.5, 1.75, facing),
                IBlock.getVoxelShapeByDirection(6.15, 9, 1.25, 13.4, 16.5, 1.75, facing),
                IBlock.getVoxelShapeByDirection(-2, -2, 0, 16, 0, 2, facing),
                IBlock.getVoxelShapeByDirection(-2, 0, 2, 14, 1, 4, facing),
                IBlock.getVoxelShapeByDirection(-2, 0.5, 1, 14, 1, 2, facing),
                IBlock.getVoxelShapeByDirection(13.25, 1, 1, 14, 16.5, 2, facing),
                IBlock.getVoxelShapeByDirection(6.15, 8.5, 1, 13.25, 9, 2, facing),
                IBlock.getVoxelShapeByDirection(-2, 1, 1, -1.25, 16.5, 2, facing),
                IBlock.getVoxelShapeByDirection(5.4, 1, 1.25, 6.15, 16.5, 1.75, facing),
                IBlock.getVoxelShapeByDirection(-2, 16.5, 1, 14, 17, 2, facing),
                IBlock.getVoxelShapeByDirection(-1.25, 8.5, 1, 5.4, 9, 2, facing)
        );
    }
}
