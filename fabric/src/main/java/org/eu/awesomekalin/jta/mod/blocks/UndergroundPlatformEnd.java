package org.eu.awesomekalin.jta.mod.blocks;

import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.DoorBlockExtension;

public class UndergroundPlatformEnd extends DoorBlockExtension {
    public UndergroundPlatformEnd() {
        super(true, blockSettings -> blockSettings.strength(4.0f).nonOpaque());
    }

    @Override
    public void onPlaced2(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {

    }
}
