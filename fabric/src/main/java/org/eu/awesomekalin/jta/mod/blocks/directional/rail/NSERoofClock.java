package org.eu.awesomekalin.jta.mod.blocks.directional.rail;

import org.eu.awesomekalin.jta.mod.blocks.directional.DirectionalBlockEntityBase;
import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mod.block.IBlock;

public class NSERoofClock extends DirectionalBlockEntityBase {
    public NSERoofClock() {
        super(BlockHelper.createBlockSettings(false, false).strength(4.0f).nonOpaque().dynamicBounds());
    }

    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return IBlock.getVoxelShapeByDirection(-2, 0, 0, 18, 26, 8, IBlock.getStatePropertySafe(state, FACING).getOpposite());
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new NSERoofClock.TileEntityNSERoofClock(blockPos, blockState);
    }

    public static class TileEntityNSERoofClock extends BlockEntityExtension {

        public TileEntityNSERoofClock(BlockPos pos, BlockState state) {
            super(BlockEntityTypeInit.NSE_CLOCK.get(), pos, state);
        }

        public boolean shouldRender() {
            return true;
        }
    }//Yes! Spam!
}
