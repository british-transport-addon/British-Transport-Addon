package org.eu.awesomekalin.jta.mod.blocks.directional.street.crossing;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockHelper;

import javax.annotation.Nonnull;

public class BelishaTop extends DirectionalBlockExtension {

    public BelishaTop() {
        super(BlockHelper.createBlockSettings(false, (blockState) -> 10).strength(4.0f).nonOpaque().dynamicBounds());
    }

    @Nonnull
    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BlockHelper.shapeUnion(Block.createCuboidShape(6, 0, 6, 10, 16, 10), Block.createCuboidShape(5, 0, 7, 11, 16, 9), Block.createCuboidShape(7, 0, 5, 9, 16, 11));
    }
}
