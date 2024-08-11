package org.eu.awesomekalin.jta.mod.blocks.directional.street.electrical;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.eu.awesomekalin.jta.mod.init.SoundInit;
import org.jetbrains.annotations.Nullable;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockHelper;

public abstract class ElectricalBoxBase extends DirectionalBlockExtension {
    public ElectricalBoxBase() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
    }


    @Override
    public void scheduledTick2(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.playSound(
                (PlayerEntity) null, // Player - if non-null, will play sound for every nearby player except the specified player
                pos, // The position of where the sound will come from
                SoundInit.ELECTRICAL_HUM_1.get(), // The sound that will play
                SoundCategory.getAmbientMapped(), // This determines which of the volume sliders affect this sound
                0.15f, //Volume multiplier, 1 is normal, 0.5 is half volume, etc
                // thats probably why i can hear it from like 50 blocks away
                1 // Pitch multiplier, 1 is normal, 0.5 is half pitch, etc
        );

        scheduleBlockTick(World.cast(world), pos, state.getBlock(), 3 * 20);
    }

    @Override
    public void onPlaced2(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        scheduleBlockTick(world, pos, state.getBlock(), 3 * 20);
    }
}
