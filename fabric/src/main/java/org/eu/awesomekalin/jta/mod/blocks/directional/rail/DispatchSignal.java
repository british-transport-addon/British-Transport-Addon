package org.eu.awesomekalin.jta.mod.blocks.directional.rail;

import org.eu.awesomekalin.jta.mod.blocks.directional.DirectionalBlockEntityBase;
import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.eu.awesomekalin.jta.mod.init.BlockInit;
import org.jetbrains.annotations.Nullable;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mod.block.BlockSignalBase;

public class DispatchSignal extends DirectionalBlockEntityBase {
    public DispatchSignal() {
        super(BlockHelper.createBlockSettings(false, false).strength(4.0f).nonOpaque().dynamicBounds());
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new TileEntityDispatchSignal(blockPos, blockState);
    }

    public static class TileEntityDispatchSignal extends BlockEntityExtension {

        public TileEntityDispatchSignal(BlockPos pos, BlockState state) {
            super(BlockEntityTypeInit.DISPATCH_SIGNAL.get(), pos, state);
        }

        public TileEntityDispatchSignal(BlockPos pos, BlockState state, int signalX, int signalY, int signalZ) {
            super(BlockEntityTypeInit.DISPATCH_SIGNAL.get(), pos, state);
        }

        @Override
        public void writeCompoundTag(CompoundTag compoundTag) {
            super.writeCompoundTag(compoundTag);
        }

        @Override
        public void readCompoundTag(CompoundTag compoundTag) {
            super.readCompoundTag(compoundTag);
        }

        public boolean shouldRender() {
            return true;
        }
    }
}
