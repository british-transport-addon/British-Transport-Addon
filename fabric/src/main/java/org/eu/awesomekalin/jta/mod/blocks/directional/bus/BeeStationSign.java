package org.eu.awesomekalin.jta.mod.blocks.directional.bus;

import org.eu.awesomekalin.jta.mod.blocks.directional.PoleBase;
import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockWithEntity;

import javax.annotation.Nonnull;

public class BeeStationSign extends PoleBase implements BlockWithEntity {
    public BeeStationSign() {
        super();
    }

    @Nonnull
    @Override
    public ActionResult onUse2(
            @Nonnull BlockState state,
            @Nonnull World world,
            @Nonnull BlockPos pos,
            PlayerEntity player,
            @Nonnull Hand hand,
            @Nonnull BlockHitResult hit
    ) {
        return ActionResult.CONSUME;
    }

    @Nonnull
    @Override
    public BlockEntityExtension createBlockEntity(@Nonnull BlockPos blockPos, @Nonnull BlockState blockState) {
        return new TileEntityBeeStationSign(blockPos, blockState);
    }

    public static class TileEntityBeeStationSign extends BlockEntityExtension {
        public TileEntityBeeStationSign(BlockPos pos, BlockState state) {
            super(BlockEntityTypeInit.BEE_STATION_SIGN_ENTITY.get(), pos, state);
        }

        public boolean shouldRender() {
            return true;
        }
    }
}
