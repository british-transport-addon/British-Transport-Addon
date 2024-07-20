package org.eu.awesomekalin.jta.mod.blocks.directional.rail;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockHelper;

import javax.annotation.Nonnull;

public class AWSRailDecoration extends DirectionalBlockExtension {

    public AWSRailDecoration() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
    }


    @Nonnull
    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BlockHelper.shapeUnion(Block.createCuboidShape(6, 0, 6, 10, 2, 10), Block.createCuboidShape(5, 0, 7, 11, 2, 9), Block.createCuboidShape(7, 0, 5, 9, 2, 11));
    }
}
