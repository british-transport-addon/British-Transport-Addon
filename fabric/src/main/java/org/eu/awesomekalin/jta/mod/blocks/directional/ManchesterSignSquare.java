package org.eu.awesomekalin.jta.mod.blocks.directional;

import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;

public class ManchesterSignSquare extends DirectionalBlockEntityBase {

    public ManchesterSignSquare() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ManchesterSignSquare.TileEntityManchesterStationSign(blockPos, blockState);
    }

    public static class TileEntityManchesterStationSign extends BlockEntityExtension {

        public TileEntityManchesterStationSign(BlockPos pos, BlockState state) {
            super(BlockEntityTypeInit.MANCHESTER_SIGN_SQUARE.get(), pos, state);
        }

        public boolean shouldRender() {
            return true;
        }
    }//Yes! Spam!
}
