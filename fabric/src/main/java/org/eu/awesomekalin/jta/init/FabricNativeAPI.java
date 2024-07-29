package org.eu.awesomekalin.jta.init;

import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import org.eu.awesomekalin.jta.mod.NativeAPI;
import org.mtr.mapping.holder.ItemStack;
import org.mtr.mapping.holder.PlayerEntity;

import java.util.Optional;

public class FabricNativeAPI implements NativeAPI {

    @Override
    public boolean hasEquippedAttachment(PlayerEntity player, ItemStack item) {
        Optional<TrinketComponent> component = TrinketsApi.getTrinketComponent(player.data);
        return component.map(trinketComponent -> trinketComponent.isEquipped(item.data.getItem())).orElse(false);
    }
}
