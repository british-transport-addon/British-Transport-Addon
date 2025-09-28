package org.eu.awesomekalin.jta.init.neoforge;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.eu.awesomekalin.jta.JTA;
import org.eu.awesomekalin.jta.neoforge.JTANeoForge;
import org.mtr.registry.ObjectHolder;

import javax.annotation.Nullable;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public final class RegistryImpl {
    private static final Object2ObjectOpenHashMap<String, ObjectArrayList<Supplier<ItemConvertible>>> ITEM_GROUP_ENTRIES = new Object2ObjectOpenHashMap<>();

    public static void init() {

    }

    public static ObjectHolder<Block> registerBlock(String registryName, Function<AbstractBlock.Settings, Block> factory) {
        return new ObjectHolder<>(JTANeoForge.BLOCKS.register(registryName, identifier -> factory.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, identifier)))));
    }

    public static ObjectHolder<Item> registerItem(String registryName, Function<Item.Settings, Item> factory, @Nullable String itemGroupRegistryName) {
        final ObjectHolder<Item> objectHolder = new ObjectHolder<>(JTANeoForge.ITEMS.register(registryName, identifier -> factory.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, identifier)))));
        if (itemGroupRegistryName != null) {
            ITEM_GROUP_ENTRIES.computeIfAbsent(itemGroupRegistryName, key -> new ObjectArrayList<>()).add(objectHolder::createAndGet);
        }
        return objectHolder;
    }

    public static String registerItemGroup(String registryName, Supplier<ItemStack> iconSupplier) {
        JTANeoForge.ITEM_GROUPS.register(registryName, () -> ItemGroup.builder().icon(iconSupplier).displayName(Text.translatable(String.format("itemGroup.%s.%s", JTA.MOD_ID, registryName))).entries((displayContext, entries) -> ITEM_GROUP_ENTRIES.getOrDefault(registryName, new ObjectArrayList<>()).forEach(itemSupplier -> entries.add(itemSupplier.get()))).build());
        return registryName;
    }

    public static <T extends BlockEntity> ObjectHolder<BlockEntityType<T>> registerBlockEntityType(String registryName, BiFunction<BlockPos, BlockState, T> factory, Supplier<Block> blockSupplier) {
        return new ObjectHolder<>(JTANeoForge.BLOCK_ENTITY_TYPES.register(registryName, () -> new BlockEntityType<>(factory::apply, blockSupplier.get())));
    }
}
