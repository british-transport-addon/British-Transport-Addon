package org.eu.awesomekalin.jta.mod.wallsigns;

import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mod.block.IBlock;

import javax.annotation.Nonnull;

public class DangerElectricShock extends WallSignBase {
    public DangerElectricShock() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque());
    }

    @Nonnull
    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return IBlock.getVoxelShapeByDirection(0, 0, 0, 16, 16, 1, IBlock.getStatePropertySafe(state, FACING));
    }
}
