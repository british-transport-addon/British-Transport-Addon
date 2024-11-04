package org.eu.awesomekalin.jta.mod.blocks.signal;

import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockSettings;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mod.BlockEntityTypes;
import org.mtr.mod.block.BlockSignalBase;
import org.mtr.mod.block.BlockSignalLight2Aspect1;
import org.mtr.mod.block.BlockSignalLightBase;

import javax.annotation.Nonnull;

public class DigitalBritishSignal2Aspect extends BlockSignalLightBase {

    public DigitalBritishSignal2Aspect(BlockSettings blockSettings) {
        super(blockSettings, 2, 14);
    }

    @Nonnull
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new DigitalBritishSignal2Aspect.BlockEntity(blockPos, blockState);
    }

    public static class BlockEntity extends BlockSignalBase.BlockEntityBase {
        public BlockEntity(BlockPos pos, BlockState state) {
            super(BlockEntityTypeInit.DIGITAL_SIGNAL_LIGHT_2_ASPECT.get(), false, pos, state);
        }
    }
}
