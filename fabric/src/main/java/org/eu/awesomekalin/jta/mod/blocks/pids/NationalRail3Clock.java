package org.eu.awesomekalin.jta.mod.blocks.pids;

import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mod.block.BlockPIDSHorizontalBase;
import org.mtr.mod.block.IBlock;

import javax.annotation.Nonnull;

public class NationalRail3Clock extends BlockPIDSHorizontalBase {

    public static final int MAX_ARRIVALS = 3;

    public NationalRail3Clock() {
        super(MAX_ARRIVALS);
    }

    @Nonnull
    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BlockHelper.shapeUnion(
                IBlock.getVoxelShapeByDirection(6, 0, 0, 10, 9, 16, IBlock.getStatePropertySafe(state, FACING)),
                IBlock.getVoxelShapeByDirection(7.5, 9, 12.5, 8.5, 16, 13.5, IBlock.getStatePropertySafe(state, FACING))
        );
    }

    @Nonnull
    @Override
    public BlockEntityExtension createBlockEntity(BlockPos pos, BlockState state) {
        return new TileEntityNationalRail3Clock(pos, state);
    }

    public static class TileEntityNationalRail3Clock extends BlockEntityHorizontalBase {

        public TileEntityNationalRail3Clock(int maxArrivals, BlockEntityType<?> type, BlockPos pos, BlockState state) {
            super(maxArrivals, type, pos, state);
        }

        public TileEntityNationalRail3Clock(BlockPos pos, BlockState state) {
            super(MAX_ARRIVALS, BlockEntityTypeInit.PIDS_NATIONALRAIL3CLOCK.get(), pos, state);
        }

        @Override
        public boolean showArrivalNumber() {
            return true;
        }
    }
}
