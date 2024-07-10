package org.eu.awesomekalin.jta.mod.blocks.directional;

import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockWithEntity;

public class BeeStationSign extends RustyPoleBase implements BlockWithEntity {

    public BeeStationSign() {
        super();
    }

    @Override
    public ActionResult onUse2(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        return ActionResult.CONSUME;
    }
    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new TileEntityBeeStationSign(blockPos, blockState);
    }

    public static class TileEntityBeeStationSign extends BlockEntityExtension {

        public TileEntityBeeStationSign(BlockPos pos, BlockState state) {
            super(BlockEntityTypeInit.BEE_STATION_SIGN_ENTITY.get(), pos, state);
        }

        public boolean shouldRender() {
            return true;
        }
    }//Yes! Spam!
}
