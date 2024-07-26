package org.eu.awesomekalin.jta.mod.blocks.directional.rail;

import org.eu.awesomekalin.jta.mod.blocks.directional.DirectionalBlockEntityBase;
import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;

public class MerseysideSignSquare extends DirectionalBlockEntityBase {

    public MerseysideSignSquare() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new TileEntityMerseysideStationSign(blockPos, blockState);
    }

    public static class TileEntityMerseysideStationSign extends BlockEntityExtension {

        public TileEntityMerseysideStationSign(BlockPos pos, BlockState state) {
            super(BlockEntityTypeInit.MERSEYSIDE_SIGN_SQUARE.get(), pos, state);
        }

        public boolean shouldRender() {
            return true;
        }
    }//Yes! Spam!
}
