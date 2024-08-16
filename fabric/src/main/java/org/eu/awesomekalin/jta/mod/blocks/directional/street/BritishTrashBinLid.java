package org.eu.awesomekalin.jta.mod.blocks.directional.street;

import net.minecraft.server.command.DataCommand;
import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.eu.awesomekalin.jta.mod.entity.block.street.ManchesterTrashBinAltLidEntity;
import org.jetbrains.annotations.NotNull;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.*;
import org.mtr.mod.block.BlockTicketBarrier;
import org.mtr.mod.block.IBlock;
import org.mtr.mod.data.TicketSystem;

import java.util.concurrent.Delayed;

public class BritishTrashBinLid extends BritishTrashBin implements BlockWithEntity {

    public static final BooleanProperty OPEN = BooleanProperty.of("open");

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ManchesterTrashBinAltLidEntity(blockPos, blockState);
    }

    @NotNull
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