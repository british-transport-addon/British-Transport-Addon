package org.eu.awesomekalin.jta.mod.blocks.directional.rail;


import org.jetbrains.annotations.NotNull;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mapping.tool.HolderBase;
import org.mtr.mod.block.BlockTicketBarrier;
import org.mtr.mod.block.IBlock;

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
            state.with(new Property<>(LOCKED.data), !IBlock.getStatePropertySafe(state, LOCKED));

            world.setBlockState(pos, state);
        });
    }

    @NotNull
    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        final Direction facing = IBlock.getStatePropertySafe(state, FACING);
        return IBlock.getVoxelShapeByDirection(12.0, 0.0, 0.0, 16.0, 24.0, 32.0, facing);
    }
}
