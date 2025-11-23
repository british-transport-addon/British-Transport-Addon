package org.eu.awesomekalin.jta.init;

import it.unimi.dsi.fastutil.objects.ObjectAVLTreeSet;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import org.eu.awesomekalin.jta.JTA;
import org.eu.awesomekalin.jta.blocks.BarrierPlatformBlock;
import org.eu.awesomekalin.jta.blocks.directional.rail.AWSRailDecoration;
import org.eu.awesomekalin.jta.blocks.signal.BannerRepeaterSignal;
import org.mtr.registry.ObjectHolder;

import java.util.function.BiFunction;
import java.util.function.Function;

public final class BlockInit {
    private static final ObjectAVLTreeSet<String> REGISTERED_IDENTIFIERS = new ObjectAVLTreeSet<>();

    public static final ObjectHolder<Block> ASPHALT = registerBlockWithBlockItem("asphalt", settings -> new Block(settings.strength(4.0f).nonOpaque().dynamicBounds()),true, CreativeTabs.JTA_BLOCKS);
    public static final ObjectHolder<Block> AWS_DECORATION = registerBlockWithBlockItem("aws", AWSRailDecoration::new, false, CreativeTabs.JTA_BLOCKS);

    public static final ObjectHolder<Block> BANNER_REPEATER_SIGNAL = registerBlockWithBlockItem("banner_repeater_signal", BannerRepeaterSignal::new, false, CreativeTabs.JTA_RAILWAYS);
    public static final ObjectHolder<Block> BARRIER_PLATFORM = registerBlockWithBlockItem("barrier_platform", BarrierPlatformBlock::new, false, CreativeTabs.JTA_BLOCKS);

    public static void init() {
        JTA.LOGGER.info("Initialising Blocks");
    }

    private static AbstractBlock.Settings createDefaultBlockSettings(AbstractBlock.Settings settings, boolean blockPiston) {
        return settings.pistonBehavior(blockPiston ? PistonBehavior.BLOCK : PistonBehavior.NORMAL).strength(3);
    }

    private static ObjectHolder<Block> registerBlock(String registryName, Function<AbstractBlock.Settings, Block> factory) {
        REGISTERED_IDENTIFIERS.add(registryName);
        return Registry.registerBlock(registryName, settings -> factory.apply(createDefaultBlockSettings(settings, true)));
    }

    private static ObjectHolder<Block> registerBlockWithBlockItem(String registryName, Function<AbstractBlock.Settings, Block> factory, boolean blockPiston, String itemGroupRegistryName) {
        return registerBlockWithBlockItem(registryName, factory, blockPiston, BlockItem::new, itemGroupRegistryName);
    }

    private static ObjectHolder<Block> registerBlockWithBlockItem(String registryName, Function<AbstractBlock.Settings, Block> blockFactory, boolean blockPiston, BiFunction<Block, Item.Settings, BlockItem> blockItemFactory, String itemGroupRegistryName) {
        REGISTERED_IDENTIFIERS.add(registryName);
        final ObjectHolder<Block> objectHolder = Registry.registerBlock(registryName, settings -> blockFactory.apply(createDefaultBlockSettings(settings, blockPiston)));
        Registry.registerItem(registryName, settings -> blockItemFactory.apply(objectHolder.createAndGet(), settings), itemGroupRegistryName);
        return objectHolder;
    }
}
