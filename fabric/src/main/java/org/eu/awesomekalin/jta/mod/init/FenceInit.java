package org.eu.awesomekalin.jta.mod.init;

import org.eu.awesomekalin.jta.mod.Init;
import org.eu.awesomekalin.jta.mod.blocks.directional.FenceBase;
import org.eu.awesomekalin.jta.mod.blocks.directional.PoleBase;
import org.mtr.mapping.holder.Block;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.registry.BlockRegistryObject;

public class FenceInit {
    public static final BlockRegistryObject WOOD_FENCE_OAK_PLANKS = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "fence/generated/oak_planks/oak_planks_wood_fence"), () -> new Block(new FenceBase()), CreativeTabInit.JTA_FENCES);
    public static final BlockRegistryObject WOOD_FENCE_SPRUCE_PLANKS = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "fence/generated/spruce_planks/spruce_planks_wood_fence"), () -> new Block(new FenceBase()), CreativeTabInit.JTA_FENCES);
    public static final BlockRegistryObject WOOD_FENCE_BIRCH_PLANKS = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "fence/generated/birch_planks/birch_planks_wood_fence"), () -> new Block(new FenceBase()), CreativeTabInit.JTA_FENCES);
    public static final BlockRegistryObject WOOD_FENCE_JUNGLE_PLANKS = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "fence/generated/jungle_planks/jungle_planks_wood_fence"), () -> new Block(new FenceBase()), CreativeTabInit.JTA_FENCES);
    public static final BlockRegistryObject WOOD_FENCE_ACACIA_PLANKS = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "fence/generated/acacia_planks/acacia_planks_wood_fence"), () -> new Block(new FenceBase()), CreativeTabInit.JTA_FENCES);
    public static final BlockRegistryObject WOOD_FENCE_DARK_OAK_PLANKS = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "fence/generated/dark_oak_planks/dark_oak_planks_wood_fence"), () -> new Block(new FenceBase()), CreativeTabInit.JTA_FENCES);
    public static final BlockRegistryObject WOOD_FENCE_MANGROVE_PLANKS = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "fence/generated/mangrove_planks/mangrove_planks_wood_fence"), () -> new Block(new FenceBase()), CreativeTabInit.JTA_FENCES);
    public static final BlockRegistryObject WOOD_FENCE_CHERRY_PLANKS = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "fence/generated/cherry_planks/cherry_planks_wood_fence"), () -> new Block(new FenceBase()), CreativeTabInit.JTA_FENCES);
    public static final BlockRegistryObject WOOD_FENCE_BAMBOO_PLANKS = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "fence/generated/bamboo_planks/bamboo_planks_wood_fence"), () -> new Block(new FenceBase()), CreativeTabInit.JTA_FENCES);
    public static void INIT() {
        Init.LOGGER.debug("Registering JTA Fences");
    }
}
