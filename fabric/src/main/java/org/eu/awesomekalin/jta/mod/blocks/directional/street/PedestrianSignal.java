package org.eu.awesomekalin.jta.mod.blocks.directional.street;

import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mod.block.BlockSignalBase;

public class PedestrianSignal extends BlockSignalBase {

    public PedestrianSignal() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new TileEntityPedestrianSignal(blockPos, blockState);
    }

    public static class TileEntityPedestrianSignal extends BlockSignalBase.BlockEntityBase {

        public TileEntityPedestrianSignal(BlockPos pos, BlockState state) {
            super(BlockEntityTypeInit.PEDESTRIAN_SIGNAL.get(), false, pos, state);
        }

        public boolean shouldRender() {
            return true;
        }
    }//Yes! Spam!
}
