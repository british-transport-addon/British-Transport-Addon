package org.eu.awesomekalin.jta.mod.blocks.directional.road;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mod.block.IBlock;

import javax.annotation.Nonnull;

public class RoadWorkBarrier extends DirectionalBlockExtension {
    public RoadWorkBarrier() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
    }


    @Nonnull
    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return IBlock.getVoxelShapeByDirection(6, 0, -8, 10, 17, 24, IBlock.getStatePropertySafe(state, FACING).rotateYClockwise());
    }
}
