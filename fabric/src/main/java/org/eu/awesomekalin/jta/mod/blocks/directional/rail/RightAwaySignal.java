package org.eu.awesomekalin.jta.mod.blocks.directional.rail;

import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.mtr.core.tool.Angle;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mod.block.BlockSignalBase;

import javax.annotation.Nonnull;

public class RightAwaySignal extends BlockSignalBase {
    public RightAwaySignal() {
        super(BlockHelper.createBlockSettings(false, false).strength(4.0f).nonOpaque().dynamicBounds());
    }

    @Override
    public BlockState getPlacementState2(ItemPlacementContext ctx) {
        final int quadrant = Angle.getQuadrant(ctx.getPlayerYaw(), true);
        return getDefaultState2().with(new Property<>(FACING.data), ctx.getPlayerFacing().data);
    }

    @Nonnull
    @Override
    public BlockEntityExtension createBlockEntity(@Nonnull BlockPos blockPos, @Nonnull BlockState blockState) {
        return new TileEntityDispatchSignal(blockPos, blockState);
    }

    public static class TileEntityDispatchSignal extends BlockEntityBase {
        public TileEntityDispatchSignal(BlockPos pos, BlockState state) {
            super(BlockEntityTypeInit.RA_SIGNAL.get(), false, pos, state);
        }

        @Override
        public void writeCompoundTag(@Nonnull CompoundTag compoundTag) {
            super.writeCompoundTag(compoundTag);
        }

        @Override
        public void readCompoundTag(@Nonnull CompoundTag compoundTag) {
            super.readCompoundTag(compoundTag);
        }

        public boolean shouldRender() {
            return true;
        }
    }
}
