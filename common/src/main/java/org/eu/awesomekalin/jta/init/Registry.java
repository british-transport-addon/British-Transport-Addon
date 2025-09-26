package org.eu.awesomekalin.jta.init;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.mtr.registry.ObjectHolder;

import java.util.function.Function;
import java.util.function.Supplier;

public final class Registry {

    @ExpectPlatform
    public static void init() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static ObjectHolder<Block> registerBlock(String registryName, Function<AbstractBlock.Settings, Block> factory) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static ObjectHolder<Item> registerItem(String registryName, Function<Item.Settings, Item> factory, @Nullable String itemGroupRegistryName) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static String registerItemGroup(String registryName, Supplier<ItemStack> iconSupplier) {
        throw new AssertionError();
    }
}