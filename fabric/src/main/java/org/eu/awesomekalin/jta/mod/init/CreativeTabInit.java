package org.eu.awesomekalin.jta.mod.init;

import org.eu.awesomekalin.jta.mod.Init;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.holder.ItemConvertible;
import org.mtr.mapping.holder.ItemStack;
import org.mtr.mapping.registry.CreativeModeTabHolder;

public class CreativeTabInit {
    public static final CreativeModeTabHolder JTA_BLOCKS = Init.REGISTRY.createCreativeModeTabHolder(new Identifier(Init.MOD_ID, "jta_blocks"), () -> new ItemStack(new ItemConvertible(BlockInit.BRITISH_RAIL_PLATFORM.get().data)));
    public static final CreativeModeTabHolder JTA_PARENTS = Init.REGISTRY.createCreativeModeTabHolder(new Identifier(Init.MOD_ID, "jta_parents"), () -> new ItemStack(new ItemConvertible(BlockInit.LIME_BRICKS.get().data)));
    public static final CreativeModeTabHolder JTA_FENCES = Init.REGISTRY.createCreativeModeTabHolder(new Identifier(Init.MOD_ID, "jta_fences"), () -> new ItemStack(new ItemConvertible(BlockInit.BLACK_FENCE.get().data)));
    public static final CreativeModeTabHolder JTA_WINDOWS = Init.REGISTRY.createCreativeModeTabHolder(new Identifier(Init.MOD_ID, "jta_windows"), () -> new ItemStack(new ItemConvertible(BlockInit.WHITE_WINDOW_SEGMENTED.get().data)));
    public static final CreativeModeTabHolder JTA_POLES = Init.REGISTRY.createCreativeModeTabHolder(new Identifier(Init.MOD_ID, "jta_poles"), () -> new ItemStack(new ItemConvertible(PoleInit.POLE_BLACK.get().data)));
    public static final CreativeModeTabHolder JTA_ROUNDELS = Init.REGISTRY.createCreativeModeTabHolder(new Identifier(Init.MOD_ID, "jta_roundels"), () -> new ItemStack(new ItemConvertible(RoundelInit.ROUNDEL_TFL.get().data)));
    public static final CreativeModeTabHolder JTA_SIGNS = Init.REGISTRY.createCreativeModeTabHolder(new Identifier(Init.MOD_ID, "jta_signs"), () -> new ItemStack(new ItemConvertible(SignInit.SIGN_NO_ENTRY_ALL.get().data)));
    public static final CreativeModeTabHolder JTA_ROADS = Init.REGISTRY.createCreativeModeTabHolder(new Identifier(Init.MOD_ID, "jta_roads"), () -> new ItemStack(new ItemConvertible(BlockInit.BRITISH_ROAD_BOLLARD.get().data)));
    public static final CreativeModeTabHolder JTA_BUSES = Init.REGISTRY.createCreativeModeTabHolder(new Identifier(Init.MOD_ID, "jta_buses"), () -> new ItemStack(new ItemConvertible(BlockInit.BEE_BUS_STOP.get().data)));
    public static final CreativeModeTabHolder JTA_RAILWAYS = Init.REGISTRY.createCreativeModeTabHolder(new Identifier(Init.MOD_ID, "jta_railways"), () -> new ItemStack(new ItemConvertible(BlockInit.BRITISH_RAIL_PLATFORM.get().data)));
    public static final CreativeModeTabHolder JTA_STREETS = Init.REGISTRY.createCreativeModeTabHolder(new Identifier(Init.MOD_ID, "jta_streets"), () -> new ItemStack(new ItemConvertible(BlockInit.WALL_DEFIBRILATOR.get().data)));
    public static final CreativeModeTabHolder JTA_ITEMS = Init.REGISTRY.createCreativeModeTabHolder(new Identifier(Init.MOD_ID, "jta_items"), () -> new ItemStack(new ItemConvertible(ItemInit.FIVE_POUND_NOTE.get().data)));
    public static final CreativeModeTabHolder JTA_POSTERS = Init.REGISTRY.createCreativeModeTabHolder(new Identifier(Init.MOD_ID, "jta_posters"), () -> new ItemStack(new ItemConvertible(BlockInit.POSTER_MINDTHECLOSINGDOORS.get().data)));

    public static void INIT() {
        Init.LOGGER.debug("Registering JTA Creative Tabs");
    }
}
