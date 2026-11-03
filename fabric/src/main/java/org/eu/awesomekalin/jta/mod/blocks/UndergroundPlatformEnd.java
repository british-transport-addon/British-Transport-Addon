package org.eu.awesomekalin.jta.mod.blocks;

import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.DoorBlockExtension;

import javax.annotation.Nonnull;

public class UndergroundPlatformEnd extends DoorBlockExtension {
    public UndergroundPlatformEnd() {
        super(true, blockSettings -> blockSettings.strength(4.0f).nonOpaque());
    }

    @Override
    public void onPlaced2(
            @Nonnull World world,
            @Nonnull BlockPos pos,
            @Nonnull BlockState state,
            @Nonnull LivingEntity placer,
            @Nonnull ItemStack itemStack
    ) {}
}
