package org.eu.awesomekalin.jta.mod.init;

import org.eu.awesomekalin.jta.mod.Init;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.holder.ItemConvertible;
import org.mtr.mapping.holder.ItemStack;
import org.mtr.mapping.registry.CreativeModeTabHolder;

public class CreativeTabInit {
    public static final CreativeModeTabHolder JTA_BLOCKS = Init.REGISTRY.createCreativeModeTabHolder(new Identifier(Init.MOD_ID, "jta_blocks"), () -> new ItemStack(new ItemConvertible(BlockInit.BRITISH_RAIL_PLATFORM.get().data)));
    public static final CreativeModeTabHolder JTA_SIGNS = Init.REGISTRY.createCreativeModeTabHolder(new Identifier(Init.MOD_ID, "jta_signs"), () -> new ItemStack(new ItemConvertible(BlockInit.RUSTY_POLE_NO_ENTRY.get().data)));
    public static final CreativeModeTabHolder JTA_ITEMS = Init.REGISTRY.createCreativeModeTabHolder(new Identifier(Init.MOD_ID, "jta_items"), () -> new ItemStack(new ItemConvertible(ItemInit.FIVE_POUND_NOTE.get().data)));
    public static final CreativeModeTabHolder JTA_POSTERS = Init.REGISTRY.createCreativeModeTabHolder(new Identifier(Init.MOD_ID, "jta_posters"), () -> new ItemStack(new ItemConvertible(BlockInit.POSTER_MINDTHECLOSINGDOORS.get().data)));

    public static void INIT() {
        Init.LOGGER.debug("Registering JTA Creative Tabs");
    }
}
