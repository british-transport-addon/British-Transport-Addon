package org.eu.awesomekalin.jta.mod.blocks.directional.street.fuel.impl;

import org.eu.awesomekalin.jta.mod.blocks.directional.street.fuel.FuelStationPriceBoardTop;
import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.mapper.BlockEntityExtension;

public class SainsburysFuelStationPriceBoardTop extends FuelStationPriceBoardTop {

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new SainsburysFuelStationPriceBoardTopBlockEntity(blockPos, blockState);
    }

    public static class SainsburysFuelStationPriceBoardTopBlockEntity extends FuelStationPriceBoardTopBlockEntity {
        public SainsburysFuelStationPriceBoardTopBlockEntity(BlockPos pos, BlockState state) {
            super(BlockEntityTypeInit.SAINSBURYS_FUEL_SIGN_TOP.get(), pos, state);
        }
    }
}
