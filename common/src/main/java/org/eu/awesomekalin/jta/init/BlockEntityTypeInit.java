package org.eu.awesomekalin.jta.init;

import net.minecraft.block.entity.BlockEntityType;
import org.eu.awesomekalin.jta.JTA;
import org.eu.awesomekalin.jta.blocks.signal.BannerRepeaterSignal;
import org.mtr.registry.ObjectHolder;

public class BlockEntityTypeInit {
    public static final ObjectHolder<BlockEntityType<BannerRepeaterSignal.BlockEntity>> BANNER_REPEATER_SIGNAL;

    static {
        BANNER_REPEATER_SIGNAL = Registry.registerBlockEntityType("apg_door", BannerRepeaterSignal.BlockEntity::new, BlockInit.BANNER_REPEATER_SIGNAL::get);
    }

    public static void init() {
        JTA.LOGGER.info("Initialising Block entities");
    }
}
