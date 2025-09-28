package org.eu.awesomekalin.jta.init.fabric;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.eu.awesomekalin.jta.JTA;
import org.jetbrains.annotations.Nullable;
import org.mtr.packet.*;
import org.mtr.registry.ObjectHolder;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public final class RegistryImpl {
    private static final Object2ObjectOpenHashMap<String, ObjectArrayList<Supplier<ItemConvertible>>> ITEM_GROUP_ENTRIES = new Object2ObjectOpenHashMap<>();
    private static final ObjectArrayList<Runnable> OBJECTS_TO_REGISTER = new ObjectArrayList<>();

    public static void init() {
        OBJECTS_TO_REGISTER.forEach(Runnable::run);
    }

    public static ObjectHolder<Block> registerBlock(String registryName, Function<AbstractBlock.Settings, Block> factory) {
        return register(Registries.BLOCK, RegistryKeys.BLOCK, registryName, dataRegistryKey -> factory.apply(AbstractBlock.Settings.create().registryKey(dataRegistryKey)));
    }

    public static ObjectHolder<Item> registerItem(String registryName, Function<Item.Settings, Item> factory, @Nullable String itemGroupRegistryName) {
        final ObjectHolder<Item> objectHolder = register(Registries.ITEM, RegistryKeys.ITEM, registryName, dataRegistryKey -> factory.apply(new Item.Settings().registryKey(dataRegistryKey)));
        if (itemGroupRegistryName != null) {
            ITEM_GROUP_ENTRIES.computeIfAbsent(itemGroupRegistryName, key -> new ObjectArrayList<>()).add(objectHolder::createAndGet);
        }
        return objectHolder;
    }

    public static String registerItemGroup(String registryName, Supplier<ItemStack> iconSupplier) {
        register(Registries.ITEM_GROUP, RegistryKeys.ITEM_GROUP, registryName, dataRegistryKey -> FabricItemGroup.builder().icon(iconSupplier).displayName(Text.translatable(String.format("itemGroup.%s.%s", JTA.MOD_ID, registryName))).entries((displayContext, entries) -> ITEM_GROUP_ENTRIES.getOrDefault(registryName, new ObjectArrayList<>()).forEach(itemSupplier -> entries.add(itemSupplier.get()))).build());
        return registryName;
    }

    public static <T extends BlockEntity> ObjectHolder<BlockEntityType<T>> registerBlockEntityType(String registryName, BiFunction<BlockPos, BlockState, T> factory, Supplier<Block> blockSupplier) {
        return register(Registries.BLOCK_ENTITY_TYPE, RegistryKeys.BLOCK_ENTITY_TYPE, registryName, dataRegistryKey -> FabricBlockEntityTypeBuilder.create(factory::apply, blockSupplier.get()).build());
    }

    private static <T extends U, U> ObjectHolder<T> register(Registry<U> registry, RegistryKey<Registry<U>> registryKey, String registryName, Function<RegistryKey<U>, T> factory) {
        final RegistryKey<U> dataRegistryKey = RegistryKey.of(registryKey, Identifier.of(JTA.MOD_ID, registryName));
        final ObjectHolder<T> objectHolder = new ObjectHolder<>(() -> Registry.register(registry, dataRegistryKey, factory.apply(dataRegistryKey)));
        OBJECTS_TO_REGISTER.add(objectHolder::createAndGet);
        return objectHolder;
    }
}
