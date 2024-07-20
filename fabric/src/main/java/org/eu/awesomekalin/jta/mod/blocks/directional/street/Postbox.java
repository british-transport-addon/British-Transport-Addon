package org.eu.awesomekalin.jta.mod.blocks.directional.street;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mod.block.IBlock;

public class Postbox extends DirectionalBlockExtension {
    public Postbox() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
    }

    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return IBlock.getVoxelShapeByDirection(2, 0, 2, 14, 28, 14, IBlock.getStatePropertySafe(state, FACING).rotateYClockwise());
    }
}
