package org.eu.awesomekalin.jta.mod.init;

import org.eu.awesomekalin.jta.mod.Init;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.holder.ItemConvertible;
import org.mtr.mapping.holder.ItemStack;
import org.mtr.mapping.registry.CreativeModeTabHolder;

public class CreativeTabInit {
    public static final CreativeModeTabHolder JTA_BLOCKS = Init.REGISTRY.createCreativeModeTabHolder(new Identifier(Init.MOD_ID, "jta_blocks"), () -> new ItemStack(new ItemConvertible(BlockInit.GREEK_PLATFORM_END.get().data)));
    public static final CreativeModeTabHolder JTA_WALL_SIGNS = Init.REGISTRY.createCreativeModeTabHolder(new Identifier(Init.MOD_ID, "jta_wall_signs"), () -> new ItemStack(new ItemConvertible(WallSignInit.DANGER_ELECTRIC_SHOCK.get().data)));
    public static void INIT() {
        Init.LOGGER.debug("Registering JTA Creative Tabs");
    }
}
