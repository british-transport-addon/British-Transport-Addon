package org.eu.awesomekalin.jta.mod.blocks.directional;

import net.minecraft.item.DebugStickItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.tool.HolderBase;
import org.mtr.mod.block.BlockTicketBarrier;
import org.mtr.mod.data.TicketSystem;

import java.util.List;
import java.util.Stack;

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
    public void onPlaced2(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced2(world, pos, state, placer, itemStack);
        world.setBlockState(pos, state.with(new Property<>(LOCKED.data), false));
    }
}
