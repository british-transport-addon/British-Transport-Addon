package org.eu.awesomekalin.jta.mod.blocks.directional.road;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mod.block.IBlock;

import javax.annotation.Nonnull;

public class Drain extends DirectionalBlockExtension {
    public Drain() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
    }


    @Nonnull
    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return IBlock.getVoxelShapeByDirection(0, 0, 0, 16, 2, 16, IBlock.getStatePropertySafe(state, FACING).rotateYClockwise());
    }
}
