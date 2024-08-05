package org.eu.awesomekalin.jta.mod.blocks.directional.street;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.eu.awesomekalin.jta.mod.entity.ThreeLineBlockEntity;
import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.eu.awesomekalin.jta.mod.screen.road.PlaceSign2LineScreen;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mapping.mapper.BlockWithEntity;

public class LondonStreetSign3x2 extends DirectionalBlockExtension implements BlockWithEntity {
    public LondonStreetSign3x2() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
    }

    @Override
    public ActionResult onUse2(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        PlaceSign2LineScreen.handle(pos);
        return ActionResult.SUCCESS;
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new LondonStreetSign3x2BlockEntity(blockPos, blockState);
    }

    public static class LondonStreetSign3x2BlockEntity extends ThreeLineBlockEntity {
        public LondonStreetSign3x2BlockEntity(BlockPos pos, BlockState state) {
            super(
                    BlockEntityTypeInit.LONDON_STREET_SIGN_3X2.get(),
                    pos,
                    state,
                    "Great Tower",
                    "Street",
                    "W1"
            );
        }

        public boolean shouldRender() {
            return true;
        }
    }
}
