package org.eu.awesomekalin.jta.mod.blocks.directional.street.electrical.impl;

import org.eu.awesomekalin.jta.mod.blocks.directional.street.electrical.ElectricalBoxBase;
import org.mtr.mapping.holder.*;
import org.mtr.mod.block.IBlock;

public class BritishLargeVentedElectricalBox extends ElectricalBoxBase {
    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return IBlock.getVoxelShapeByDirection(0, 0, 0, 16, 26, 8, IBlock.getStatePropertySafe(state, FACING).getOpposite());
    }
}
