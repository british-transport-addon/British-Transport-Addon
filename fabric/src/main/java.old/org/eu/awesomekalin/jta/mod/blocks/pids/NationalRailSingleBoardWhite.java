package org.eu.awesomekalin.jta.mod.blocks.pids;

import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.jetbrains.annotations.NotNull;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mapping.tool.HolderBase;
import org.mtr.mod.block.BlockPIDSBase;
import org.mtr.mod.block.IBlock;

import javax.annotation.Nonnull;
import java.util.List;

public class NationalRailSingleBoardWhite extends NationalRailSingleBoard {


    public NationalRailSingleBoardWhite() {
    }

    @NotNull
    @Override
    public BlockEntityExtension createBlockEntity(BlockPos pos, BlockState state) {
        return new TileEntityNationalRailSingleBoardWhite(pos, state);
    }

    public static class TileEntityNationalRailSingleBoardWhite extends NationalRailSingleBoard.TileEntityNationalRailSingleBoard {



        public TileEntityNationalRailSingleBoardWhite(BlockPos pos, BlockState state) {
            super(LINES, NationalRailSingleBoardWhite::canStoreData, NationalRailSingleBoardWhite::getBlockPosWithData, BlockEntityTypeInit.PIDS_NATIONALRAILSINGLEBOAR_DWHITE.get(), pos, state);
        }

        @Override
        public boolean showArrivalNumber() {
            return false;
        }


        public boolean alternateLines() {
            return false;
        }

        public int textColorArrived() {
            return 0xFFFFFF;
        }

        public int textColor() {
            return 0xFFFFFF;
        }
    }
}
