package org.eu.awesomekalin.jta.mod.blocks.directional.street.fuel.impl;

import org.eu.awesomekalin.jta.mod.blocks.directional.street.fuel.FuelStationPriceBoardTop;
import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.mapper.BlockEntityExtension;

public class MorrisonsFuelStationPriceBoardTop extends FuelStationPriceBoardTop {

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new MorrisonsFuelStationPriceBoardTopBlockEntity(blockPos, blockState);
    }

    public static class MorrisonsFuelStationPriceBoardTopBlockEntity extends FuelStationPriceBoardTopBlockEntity {
        public MorrisonsFuelStationPriceBoardTopBlockEntity(BlockPos pos, BlockState state) {
            super(BlockEntityTypeInit.MORRISONS_FUEL_SIGN_TOP.get(), pos, state);
        }
    }
}
