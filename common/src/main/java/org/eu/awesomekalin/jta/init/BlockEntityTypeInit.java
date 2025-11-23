package org.eu.awesomekalin.jta.init;

import net.minecraft.block.entity.BlockEntityType;
import org.eu.awesomekalin.jta.JTA;
import org.eu.awesomekalin.jta.blocks.BarrierPlatformBlock;
import org.eu.awesomekalin.jta.blocks.signal.BannerRepeaterSignal;
import org.mtr.registry.ObjectHolder;

public class BlockEntityTypeInit {
    public static final ObjectHolder<BlockEntityType<BannerRepeaterSignal.BlockEntity>> BANNER_REPEATER_SIGNAL;
    public static final ObjectHolder<BlockEntityType<BarrierPlatformBlock.BarrierPlatformBlockEntity>> BARRIER_PLATFORM;

    static {
        BANNER_REPEATER_SIGNAL = Registry.registerBlockEntityType("apg_door", BannerRepeaterSignal.BlockEntity::new, BlockInit.BANNER_REPEATER_SIGNAL::get);
        BARRIER_PLATFORM = Registry.registerBlockEntityType("barrier_platform", BarrierPlatformBlock.BarrierPlatformBlockEntity::new, BlockInit.BARRIER_PLATFORM::get);
    }

    public static void init() {
        JTA.LOGGER.info("Initialising Block entities");
    }
}
