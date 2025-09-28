package org.eu.awesomekalin.jta.blocks.signal;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import org.eu.awesomekalin.jta.init.BlockEntityTypeInit;
import org.mtr.block.BlockSignalBase;
import org.mtr.block.BlockSignalLightBase;

public class BannerRepeaterSignal extends BlockSignalLightBase {
    public BannerRepeaterSignal(AbstractBlock.Settings blockSettings) {
        super(blockSettings, 2, 10);
    }

    public BlockEntity createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new BannerRepeaterSignal.BlockEntity(blockPos, blockState);
    }

    public static class BlockEntity extends BlockSignalBase.BlockEntityBase {
        public BlockEntity(BlockPos pos, BlockState state) {
            super(BlockEntityTypeInit.BANNER_REPEATER_SIGNAL.get(), false, pos, state);
        }
    }
}
