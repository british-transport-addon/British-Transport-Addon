package org.eu.awesomekalin.jta.mod.blocks.pids;

import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.mapper.BlockEntityExtension;

import javax.annotation.Nonnull;

public class NationalRail3ClockWhite extends NationalRail3Clock {

    @Nonnull
    @Override
    public BlockEntityExtension createBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
        return new TileEntityNationalRail3ClockWhite(pos, state);
    }

    public static class TileEntityNationalRail3ClockWhite extends NationalRail3Clock.TileEntityNationalRail3Clock {
        public TileEntityNationalRail3ClockWhite(BlockPos pos, BlockState state) {
            super(MAX_ARRIVALS, BlockEntityTypeInit.PIDS_NATIONALRAIL3CLOCKWHITE.get(), pos, state);
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
