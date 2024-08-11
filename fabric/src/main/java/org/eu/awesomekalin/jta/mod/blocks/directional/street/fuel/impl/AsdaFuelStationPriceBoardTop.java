package org.eu.awesomekalin.jta.mod.blocks.directional.street.fuel.impl;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.eu.awesomekalin.jta.mod.blocks.directional.street.fuel.FuelStationPriceBoardTop;
import org.eu.awesomekalin.jta.mod.entity.block.TwoLineBlockEntity;
import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.mtr.mapping.holder.BlockEntityType;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mapping.mapper.BlockWithEntity;

public class AsdaFuelStationPriceBoardTop extends FuelStationPriceBoardTop {

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new AsdaFuelStationPriceBoardTopBlockEntity(blockPos, blockState);
    }

    public static class AsdaFuelStationPriceBoardTopBlockEntity extends FuelStationPriceBoardTopBlockEntity {
        public AsdaFuelStationPriceBoardTopBlockEntity(BlockPos pos, BlockState state) {
            super(BlockEntityTypeInit.ASDA_FUEL_SIGN_TOP.get(), pos, state);
        }
    }
}
