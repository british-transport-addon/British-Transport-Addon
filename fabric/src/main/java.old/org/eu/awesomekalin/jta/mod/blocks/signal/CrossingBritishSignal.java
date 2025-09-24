package org.eu.awesomekalin.jta.mod.blocks.signal;

import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockSettings;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mod.block.BlockSignalLightBase;

import javax.annotation.Nonnull;

public class CrossingBritishSignal extends BlockSignalLightBase {

    public CrossingBritishSignal(BlockSettings blockSettings) {
        super(blockSettings, 2, 14);
    }

    @Nonnull
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new CrossingBritishSignal.BlockEntity(blockPos, blockState);
    }

    public static class BlockEntity extends BlockEntityBase {
        public BlockEntity(BlockPos pos, BlockState state) {
            super(BlockEntityTypeInit.CROSSING_BRITISH_SIGNAL.get(), false, pos, state);
        }
    }
}
