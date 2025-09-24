package org.eu.awesomekalin.jta.mod.blocks.directional.rail;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.eu.awesomekalin.jta.mod.blocks.directional.DirectionalBlockEntityBase;
import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.eu.awesomekalin.jta.mod.init.BlockInit;
import org.jetbrains.annotations.Nullable;
import org.mtr.core.tool.Angle;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mod.block.BlockSignalBase;

public class DispatchSignal extends BlockSignalBase {
    public DispatchSignal() {
        super(BlockHelper.createBlockSettings(false, false).strength(4.0f).nonOpaque().dynamicBounds());
    }

    @Override
    public BlockState getPlacementState2(ItemPlacementContext ctx) {
        final int quadrant = Angle.getQuadrant(ctx.getPlayerYaw(), true);
        return getDefaultState2().with(new Property<>(FACING.data), ctx.getPlayerFacing().data);
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new TileEntityDispatchSignal(blockPos, blockState);
    }

    public static class TileEntityDispatchSignal extends BlockSignalBase.BlockEntityBase {

        public TileEntityDispatchSignal(BlockPos pos, BlockState state) {
            super(BlockEntityTypeInit.DISPATCH_SIGNAL.get(), false, pos, state);
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
