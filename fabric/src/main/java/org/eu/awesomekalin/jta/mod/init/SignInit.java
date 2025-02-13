package org.eu.awesomekalin.jta.mod.init;

import org.eu.awesomekalin.jta.mod.Init;
import org.eu.awesomekalin.jta.mod.blocks.directional.rail.PlatformSign;
import org.mtr.mapping.holder.Block;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.registry.BlockRegistryObject;

public class SignInit {

    public static final BlockRegistryObject PLATFORM_SIGN_BEE_YELLOW = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "sign/platform_sign/bee_yellow/bee_yellow_platform_sign"), () -> new Block(new PlatformSign()), CreativeTabInit.JTA_SIGNS);
    public static final BlockRegistryObject PLATFORM_SIGN_BLACK = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "sign/platform_sign/black/black_platform_sign"), () -> new Block(new PlatformSign()), CreativeTabInit.JTA_SIGNS);
    public static final BlockRegistryObject PLATFORM_SIGN_BLUE = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "sign/platform_sign/blue/blue_platform_sign"), () -> new Block(new PlatformSign()), CreativeTabInit.JTA_SIGNS);
    public static final BlockRegistryObject PLATFORM_SIGN_DARK_BLUE = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "sign/platform_sign/dark_blue/dark_blue_platform_sign"), () -> new Block(new PlatformSign()), CreativeTabInit.JTA_SIGNS);
    public static final BlockRegistryObject PLATFORM_SIGN_DARK_GRAY = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "sign/platform_sign/dark_gray/dark_gray_platform_sign"), () -> new Block(new PlatformSign()), CreativeTabInit.JTA_SIGNS);
    public static final BlockRegistryObject PLATFORM_SIGN_DARK_GREEN = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "sign/platform_sign/dark_green/dark_green_platform_sign"), () -> new Block(new PlatformSign()), CreativeTabInit.JTA_SIGNS);
    public static final BlockRegistryObject PLATFORM_SIGN_GRAY = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "sign/platform_sign/gray/gray_platform_sign"), () -> new Block(new PlatformSign()), CreativeTabInit.JTA_SIGNS);
    public static final BlockRegistryObject PLATFORM_SIGN_GREEN = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "sign/platform_sign/green/green_platform_sign"), () -> new Block(new PlatformSign()), CreativeTabInit.JTA_SIGNS);
    public static final BlockRegistryObject PLATFORM_SIGN_LIGHT_BLUE = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "sign/platform_sign/light_blue/light_blue_platform_sign"), () -> new Block(new PlatformSign()), CreativeTabInit.JTA_SIGNS);
    public static final BlockRegistryObject PLATFORM_SIGN_ORANGE = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "sign/platform_sign/orange/orange_platform_sign"), () -> new Block(new PlatformSign()), CreativeTabInit.JTA_SIGNS);
    public static final BlockRegistryObject PLATFORM_SIGN_PINK = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "sign/platform_sign/pink/pink_platform_sign"), () -> new Block(new PlatformSign()), CreativeTabInit.JTA_SIGNS);
    public static final BlockRegistryObject PLATFORM_SIGN_PURPLE = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "sign/platform_sign/purple/purple_platform_sign"), () -> new Block(new PlatformSign()), CreativeTabInit.JTA_SIGNS);
    public static final BlockRegistryObject PLATFORM_SIGN_RED = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "sign/platform_sign/red/red_platform_sign"), () -> new Block(new PlatformSign()), CreativeTabInit.JTA_SIGNS);
    public static final BlockRegistryObject PLATFORM_SIGN_ROYAL_RED = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "sign/platform_sign/royal_red/royal_red_platform_sign"), () -> new Block(new PlatformSign()), CreativeTabInit.JTA_SIGNS);
    public static final BlockRegistryObject PLATFORM_SIGN_RUSTY = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "sign/platform_sign/rusty/rusty_platform_sign"), () -> new Block(new PlatformSign()), CreativeTabInit.JTA_SIGNS);
    public static final BlockRegistryObject PLATFORM_SIGN_STEEL = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "sign/platform_sign/steel/steel_platform_sign"), () -> new Block(new PlatformSign()), CreativeTabInit.JTA_SIGNS);
    public static final BlockRegistryObject PLATFORM_SIGN_YELLOW = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "sign/platform_sign/yellow/yellow_platform_sign"), () -> new Block(new PlatformSign()), CreativeTabInit.JTA_SIGNS);
    public static void INIT() {
        Init.LOGGER.debug("Registering British Transport Addon Signs");
    }
}
