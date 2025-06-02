package org.eu.awesomekalin.jta.mod.blocks.directional.rail;


import org.jetbrains.annotations.NotNull;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mapping.mapper.TextHelper;
import org.mtr.mapping.tool.HolderBase;
import org.mtr.mod.block.BlockTicketBarrier;
import org.mtr.mod.block.IBlock;
import org.mtr.mod.generated.lang.TranslationProvider;

import java.util.List;

public class BritishTicketBarrier extends BlockTicketBarrier {

    public static final BooleanProperty LOCKED = BooleanProperty.of("locked");

    public BritishTicketBarrier(boolean isEntrance) {
        super(isEntrance);
    }

    @Override
    public void addBlockProperties(List<HolderBase<?>> properties) {
        properties.add(LOCKED);
        super.addBlockProperties(properties);
    }

    @Override
    public void onEntityCollision2(BlockState state, World world, BlockPos blockPos, Entity entity) {
        if (!state.get(new Property<>(LOCKED.data))) {
            super.onEntityCollision2(state, world, blockPos, entity);
        }
    }

    @Override
    public void onPlaced2(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        super.onPlaced2(world, pos, state, placer, itemStack);
        world.setBlockState(pos, state.with(new Property<>(LOCKED.data), false));
    }

    @NotNull
    @Override
    public ActionResult onUse2(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return IBlock.checkHoldingBrush(world, player, () -> {
            final boolean locked = !IBlock.getStatePropertySafe(state, LOCKED);
            BlockState newState = state.with(new Property<>(LOCKED.data), locked);

            world.setBlockState(pos, newState);
            player.sendMessage(Text.of(TextHelper.translatable(locked ? "gui.jta.ticket_barrier_locked" : "gui.jta.ticket_barrier_unlocked").getString()), true);
        });
    }

    @NotNull
    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        final Direction facing = IBlock.getStatePropertySafe(state, FACING);
        return IBlock.getVoxelShapeByDirection(12.0, 0.0, -16.0, 16.0, 24.0, 16.0, facing);
    }
}
