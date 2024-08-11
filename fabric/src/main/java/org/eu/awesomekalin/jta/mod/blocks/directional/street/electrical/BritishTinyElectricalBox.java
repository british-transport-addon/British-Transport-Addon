package org.eu.awesomekalin.jta.mod.blocks.directional.street.electrical;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mod.block.IBlock;

public class BritishTinyElectricalBox extends DirectionalBlockExtension {
    public BritishTinyElectricalBox() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
    }

    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return IBlock.getVoxelShapeByDirection(-2, 0, 0, 18, 26, 8, IBlock.getStatePropertySafe(state, FACING).getOpposite());
    }
}
