package org.eu.awesomekalin.jta.mod.blocks.directional;

import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockWithEntity;
import org.mtr.mapping.mapper.PlayerHelper;
import org.mtr.mapping.mapper.TextHelper;

public class BeeBusStopSign extends RustyPoleBase implements BlockWithEntity {

    public BeeBusStopSign() {
        super();
    }

    @Override
    public ActionResult onUse2(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        return ActionResult.CONSUME;
    }
    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new TileEntityBeeBusSign(blockPos, blockState);
    }

    public static class TileEntityBeeBusSign extends BlockEntityExtension {

        public TileEntityBeeBusSign(BlockPos pos, BlockState state) {
            super(BlockEntityTypeInit.BEE_BUS_STOP.get(), pos, state);
        }

        public boolean shouldRender() {
            return true;
        }
    }//Yes! Spam!
}
