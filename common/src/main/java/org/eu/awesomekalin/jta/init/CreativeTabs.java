package org.eu.awesomekalin.jta.init;

import net.minecraft.item.ItemStack;
import org.eu.awesomekalin.jta.JTA;

public final class CreativeTabs {
    public static final String JTA_BLOCKS;
    public static final String JTA_RAILWAYS;

    static {
        JTA_BLOCKS = Registry.registerItemGroup("blocks", () -> new ItemStack(BlockInit.ASPHALT.createAndGet()));
        JTA_RAILWAYS = Registry.registerItemGroup("railways", () -> new ItemStack(BlockInit.BANNER_REPEATER_SIGNAL.createAndGet()));
    }

    public static void init() {
        JTA.LOGGER.info("Initialising Creative Mode Tabs");
    }
}
