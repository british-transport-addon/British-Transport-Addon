package org.eu.awesomekalin.jta.mod.blocks.directional.rail;

import org.eu.awesomekalin.jta.mod.blocks.directional.DirectionalBlockEntityBase;
import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;

public class UKBritishRailStationSignSimple extends DirectionalBlockEntityBase {

    public UKBritishRailStationSignSimple() {
        super(BlockHelper.createBlockSettings(false, false).strength(4.0f).nonOpaque().dynamicBounds());
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new TileEntityBritishRailStationSign(blockPos, blockState);
    }

    public static class TileEntityBritishRailStationSign extends BlockEntityExtension {

        public TileEntityBritishRailStationSign(BlockPos pos, BlockState state) {
            super(BlockEntityTypeInit.BRITISH_RAIL_STATION_SIGN_SIMPLE.get(), pos, state);
        }

        public boolean shouldRender() {
            return true;
        }
    }//Yes! Spam!
}
