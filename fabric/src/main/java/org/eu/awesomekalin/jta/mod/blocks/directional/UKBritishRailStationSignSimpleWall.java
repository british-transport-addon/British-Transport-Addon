package org.eu.awesomekalin.jta.mod.blocks.directional;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mapping.mapper.BlockWithEntity;

public class UKBritishRailStationSignSimpleWall extends DirectionalBlockExtension implements BlockWithEntity {

    public UKBritishRailStationSignSimpleWall() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
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
