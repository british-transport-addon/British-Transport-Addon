package org.eu.awesomekalin.jta.mod.blocks.directional.rail;

import org.eu.awesomekalin.jta.mod.blocks.directional.PoleBase;
import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.eu.awesomekalin.jta.mod.util.DyeColor;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockWithEntity;

public class UKBritishOperatorStationSignPoleBase extends PoleBase implements BlockWithEntity {

    private DyeColor color;

    public UKBritishOperatorStationSignPoleBase(DyeColor dyeColor) {
        super();
        this.color = dyeColor;
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        if (color == DyeColor.WHITE) return new TileEntityBritishRailOperatorSignWhite(blockPos, blockState);
        if (color == DyeColor.BLUE) return new TileEntityBritishRailOperatorSignBlue(blockPos, blockState);
        else return new TileEntityBritishRailOperatorSign(blockPos, blockState);
    }

    public static class TileEntityBritishRailOperatorSign extends BlockEntityExtension {

        public TileEntityBritishRailOperatorSign(BlockPos pos, BlockState state) {
            super(BlockEntityTypeInit.RUSTY_UK_BRITISH_OPERATOR_STATION_SIGN_BASE.get(), pos, state);
        }

        public boolean shouldRender() {
            return true;
        }
    }//Yes! Spam!

    public static class TileEntityBritishRailOperatorSignWhite extends BlockEntityExtension {

        public TileEntityBritishRailOperatorSignWhite(BlockPos pos, BlockState state) {
            super(BlockEntityTypeInit.RUSTY_UK_BRITISH_OPERATOR_STATION_SIGN_BASE_WHITE.get(), pos, state);
        }

        public boolean shouldRender() {
            return true;
        }
    }

    public static class TileEntityBritishRailOperatorSignBlue extends BlockEntityExtension {

        public TileEntityBritishRailOperatorSignBlue(BlockPos pos, BlockState state) {
            super(BlockEntityTypeInit.RUSTY_UK_BRITISH_OPERATOR_STATION_SIGN_BASE_BLUE.get(), pos, state);
        }

        public boolean shouldRender() {
            return true;
        }
    }
}
