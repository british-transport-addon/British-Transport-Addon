package org.eu.awesomekalin.jta.mod.blocks;

import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockExtension;
import org.mtr.mapping.mapper.DirectionHelper;
import org.mtr.mapping.tool.HolderBase;

import java.util.List;

public abstract class DirectionalBlockExtension extends BlockExtension implements DirectionHelper {

    public DirectionalBlockExtension(BlockSettings blockSettings) {
        super(blockSettings);
    }

    @Override
    public void addBlockProperties(List<HolderBase<?>> properties) {
        properties.add(FACING);
    }

    @Override
    public void onPlaced2(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        assert placer != null;
        final Direction facing = placer.getHorizontalFacing().getOpposite();
        world.setBlockState(pos, state.with(new Property<>(FACING.data), facing.data));
    }
}
