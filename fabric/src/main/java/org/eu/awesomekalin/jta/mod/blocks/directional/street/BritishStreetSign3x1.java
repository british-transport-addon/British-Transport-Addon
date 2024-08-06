package org.eu.awesomekalin.jta.mod.blocks.directional.street;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.eu.awesomekalin.jta.mod.entity.OneLineBlockEntity;
import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.eu.awesomekalin.jta.mod.screen.road.PlaceSign2LineScreen;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mapping.mapper.BlockWithEntity;

public class BritishStreetSign3x1 extends DirectionalBlockExtension implements BlockWithEntity {
    public BritishStreetSign3x1() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
    }

    @Override
    public ActionResult onUse2(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        PlaceSign2LineScreen.handle(pos);
        return ActionResult.SUCCESS;
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new BritishStreetSign3x1BlockEntity(blockPos, blockState);
    }

    public static class BritishStreetSign3x1BlockEntity extends OneLineBlockEntity {
        public BritishStreetSign3x1BlockEntity(BlockPos pos, BlockState state) {
            super(BlockEntityTypeInit.BRITISH_STREET_SIGN_3X1.get(), pos, state, "St. Marys Walk");
        }

        public boolean shouldRender() {
            return true;
        }
    }
}
