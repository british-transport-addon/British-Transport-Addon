package org.eu.awesomekalin.jta.platform;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import org.eu.awesomekalin.jta.mod.init.ItemInit;
import org.mtr.mapping.holder.*;

import java.util.Optional;

public class NativeAPI {

    public boolean hasEquippedAttachment(PlayerEntity player, ItemStack item) {
        Optional<TrinketComponent> component = TrinketsApi.getTrinketComponent(player.data);
        return component.map(trinketComponent -> trinketComponent.isEquipped(item.data.getItem())).orElse(false);
    }

    public Pair<Integer, ItemStack> getEquippedAttachment(PlayerEntity player, Item item) {
        Optional<TrinketComponent> component = TrinketsApi.getTrinketComponent(player.data);
        if (component.isPresent()) {
            Pair<SlotReference, net.minecraft.item.ItemStack> referenceItemPair = component.get().getEquipped(item.data).stream().findFirst().get();
            return new Pair<>(referenceItemPair.getLeft().index(), new ItemStack(referenceItemPair.getRight()));
        }

        return null;
    }

    public void registerShieldItemProperties() {
        ModelPredicateProviderRegistry.register(ItemInit.RIOT_SHIELD.get().data, new Identifier("blocking"), (net.minecraft.item.ItemStack stack, ClientWorld world, LivingEntity entity, int seed) -> {
            return entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F;
        });
    }
}
