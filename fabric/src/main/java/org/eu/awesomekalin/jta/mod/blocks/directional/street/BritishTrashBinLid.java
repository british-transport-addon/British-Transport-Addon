package org.eu.awesomekalin.jta.mod.blocks.directional.street;

import org.eu.awesomekalin.jta.mod.entity.block.street.ManchesterTrashBinAltLidEntity;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.*;

import javax.annotation.Nonnull;

public class BritishTrashBinLid extends BritishTrashBin implements BlockWithEntity {

    public static final BooleanProperty OPEN = BooleanProperty.of("open");

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ManchesterTrashBinAltLidEntity(blockPos, blockState);
    }

    @Nonnull
    @Override
    public BlockRenderType getRenderType2(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    @Override
    public ActionResult onUse2(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ManchesterTrashBinAltLidEntity entity = (ManchesterTrashBinAltLidEntity) world.getBlockEntity(pos).data;

        if (entity.isOpen()) return ActionResult.FAIL;
        super.onUse2(state, world, pos, player, hand, hit);

        entity.setOpen(true);
        // close after expire?
        return ActionResult.CONSUME;
    }
}
