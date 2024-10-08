package org.eu.awesomekalin.jta.mod.blocks.directional.rail.pids;

import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.mapper.BlockEntityExtension;

import javax.annotation.Nonnull;

public class NationalRail10ClockWhite extends NationalRail10Clock {

    @Nonnull
    @Override
    public BlockEntityExtension createBlockEntity(BlockPos pos, BlockState state) {
        return new TileEntityNationalRail10ClockWhite(pos, state);
    }

    public static class TileEntityNationalRail10ClockWhite extends TileEntityNationalRail10Clock {

        public TileEntityNationalRail10ClockWhite(BlockPos pos, BlockState state) {
            super(NationalRail10Clock.MAX_ARRIVALS, BlockEntityTypeInit.PIDS_NATIONALRAIL10CLOCKWHITE.get(), pos, state);
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
