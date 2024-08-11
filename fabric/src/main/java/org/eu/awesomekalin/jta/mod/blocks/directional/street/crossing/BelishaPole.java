package org.eu.awesomekalin.jta.mod.blocks.directional.street.crossing;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockHelper;

import javax.annotation.Nonnull;

public class BelishaPole extends DirectionalBlockExtension {

    public BelishaPole() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
    }

    @Nonnull
    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BlockHelper.shapeUnion(Block.createCuboidShape(6, 0, 6, 10, 16, 10), Block.createCuboidShape(5, 0, 7, 11, 16, 9), Block.createCuboidShape(7, 0, 5, 9, 16, 11));
    }
}
