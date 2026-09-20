package org.eu.awesomekalin.jta.mod.blocks.pids;

import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.jetbrains.annotations.NotNull;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;

import javax.annotation.Nonnull;

public class NationalRailSingleBoardWhite extends NationalRailSingleBoard {
    public NationalRailSingleBoardWhite() {}

    @Nonnull
    @Override
    public BlockEntityExtension createBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
        return new TileEntityNationalRailSingleBoardWhite(pos, state);
    }

    public static class TileEntityNationalRailSingleBoardWhite extends NationalRailSingleBoard.TileEntityNationalRailSingleBoard {
        public TileEntityNationalRailSingleBoardWhite(BlockPos pos, BlockState state) {
            super(
                    LINES,
                    NationalRailSingleBoardWhite::canStoreData,
                    NationalRailSingleBoardWhite::getBlockPosWithData,
                    BlockEntityTypeInit.PIDS_NATIONALRAILSINGLEBOAR_DWHITE.get(),
                    pos,
                    state
            );
        }

        public int textColorArrived() {
            return 0xFFFFFF;
        }

        public int textColor() {
            return 0xFFFFFF;
        }
    }
}
