package org.eu.awesomekalin.jta.mod.blocks.directional.rail.pids;

import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mod.block.BlockPIDSHorizontalBase;
import org.mtr.mod.block.IBlock;

import javax.annotation.Nonnull;

public class NationalRail3ClockWhite extends NationalRail3Clock {

    @Nonnull
    @Override
    public BlockEntityExtension createBlockEntity(BlockPos pos, BlockState state) {
        return new TileEntityNationalRail3ClockWhite(pos, state);
    }

    public static class TileEntityNationalRail3ClockWhite extends NationalRail3Clock.TileEntityNationalRail3Clock {

        public TileEntityNationalRail3ClockWhite(BlockPos pos, BlockState state) {
            super(NationalRail3Clock.MAX_ARRIVALS, BlockEntityTypeInit.PIDS_NATIONALRAIL3CLOCKWHITE.get(), pos, state);
        }

        @Override
        public boolean showArrivalNumber() {
            return true;
        }

        @Override
        public int textColorArrived() {
            return 0xFFFFFF;
        }

        @Override
        public int textColor() {
            return 0xFFFFFF;
        }
    }
}
