package org.eu.awesomekalin.jta.mod.blocks.directional;

import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;

public class DispatchSignal extends DirectionalBlockEntityBase {

    public DispatchSignal() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new TileEntityDispatchSignal(blockPos, blockState);
    }

    public static class TileEntityDispatchSignal extends BlockEntityExtension {

        public TileEntityDispatchSignal(BlockPos pos, BlockState state) {
            super(BlockEntityTypeInit.DISPATCH_SIGNAL.get(), pos, state);
        }

        public boolean shouldRender() {
            return true;
        }
    }//Yes! Spam!
}
