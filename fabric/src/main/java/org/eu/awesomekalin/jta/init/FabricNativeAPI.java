package org.eu.awesomekalin.jta.init;

import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.fabricmc.fabric.impl.registry.sync.FabricRegistryInit;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.eu.awesomekalin.jta.mod.NativeAPI;
import org.eu.awesomekalin.jta.mod.init.ItemInit;
import org.mtr.mapping.holder.ItemStack;
import org.mtr.mapping.holder.PlayerEntity;

import java.util.Optional;

public class FabricNativeAPI implements NativeAPI {

    @Override
    public boolean hasEquippedAttachment(PlayerEntity player, ItemStack item) {
        Optional<TrinketComponent> component = TrinketsApi.getTrinketComponent(player.data);
        return component.map(trinketComponent -> trinketComponent.isEquipped(item.data.getItem())).orElse(false);
    }

    @Override
    public void registerShieldItemProperties() {
        ModelPredicateProviderRegistry.register(ItemInit.RIOT_SHIELD.get().data, new Identifier("blocking"), (net.minecraft.item.ItemStack stack, ClientWorld world, LivingEntity entity, int seed) -> {
            return entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F;
        });
    }
}
