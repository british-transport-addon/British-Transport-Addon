package org.eu.awesomekalin.jta.mod.init;

import org.eu.awesomekalin.jta.mod.Init;
import org.eu.awesomekalin.jta.mod.blocks.GreekPlatformEnd;
import org.eu.awesomekalin.jta.mod.blocks.UKDoNotTresspassWithRustyPole;
import org.mtr.mapping.holder.Block;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.registry.BlockRegistryObject;

public class BlockInit {
    public static final BlockRegistryObject GREEK_PLATFORM_END = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "greek_platform_end"), () -> new Block(new GreekPlatformEnd()), CreativeTabInit.JTA_BLOCKS);
    public static final BlockRegistryObject UK_DO_NOT_TRESSPASS_WITH_RUSTY_POLE = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "uk_do_not_tresspass_rusty_pole"), () -> new Block(new UKDoNotTresspassWithRustyPole()), CreativeTabInit.JTA_BLOCKS);
    public static void INIT() {
        Init.LOGGER.debug("Registering JTA Blocks");
    }
}
