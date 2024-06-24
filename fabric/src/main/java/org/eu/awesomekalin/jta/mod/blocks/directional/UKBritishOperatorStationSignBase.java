package org.eu.awesomekalin.jta.mod.blocks.directional;

import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;

public class UKBritishOperatorStationSignBase extends DirectionalBlockEntityBase {

    public UKBritishOperatorStationSignBase() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new TileEntityBritishRailOperatorSign(blockPos, blockState);
    }

    public static class TileEntityBritishRailOperatorSign extends BlockEntityExtension {

        public TileEntityBritishRailOperatorSign(BlockPos pos, BlockState state) {
            super(BlockEntityTypeInit.UK_BRITISH_OPERATOR_STATION_SIGN_BASE.get(), pos, state);
        }

        public boolean shouldRender() {
            return true;
        }
    }
}
