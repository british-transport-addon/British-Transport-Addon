package org.eu.awesomekalin.jta.mod.blocks.directional.street.fuel;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.eu.awesomekalin.jta.mod.entity.block.TwoLineBlockEntity;
import org.mtr.mapping.holder.BlockEntityType;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mapping.mapper.BlockWithEntity;

public abstract class FuelStationPriceBoardTop extends DirectionalBlockExtension implements BlockWithEntity {
    public FuelStationPriceBoardTop() {
        super(BlockHelper.createBlockSettings(false, (blockState) -> {
            return 11;
        }).strength(4.0f).nonOpaque().dynamicBounds());
    }

    public static class FuelStationPriceBoardTopBlockEntity extends TwoLineBlockEntity {
        public FuelStationPriceBoardTopBlockEntity(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state) {
            super(blockEntityType, pos, state, "0.99", "0.99");
        }

        public boolean shouldRender() {
            return true;
        }
    }
}
