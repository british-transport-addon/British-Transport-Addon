package org.eu.awesomekalin.jta.neoforge;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.eu.awesomekalin.jta.JTA;
import net.neoforged.bus.api.IEventBus;

@Mod(JTA.MOD_ID)
public final class JTANeoForge {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, JTA.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, JTA.MOD_ID);
    public static final DeferredRegister<ItemGroup> ITEM_GROUPS = DeferredRegister.create(Registries.ITEM_GROUP, JTA.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, JTA.MOD_ID);

    public JTANeoForge(IEventBus eventBus) {
        JTA.init();
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
        ITEM_GROUPS.register(eventBus);
        BLOCK_ENTITY_TYPES.register(eventBus);
    }
}
