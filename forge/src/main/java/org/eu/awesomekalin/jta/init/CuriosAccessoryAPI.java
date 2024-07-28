package org.eu.awesomekalin.jta.init;

import org.eu.awesomekalin.jta.mod.AccessoryAPI;
import org.mtr.mapping.holder.Item;
import org.mtr.mapping.holder.PlayerEntity;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

import java.util.Optional;

public class CuriosAccessoryAPI implements AccessoryAPI {

    @Override
    public boolean hasEquipped(PlayerEntity player, Item item) {
        Optional<ICuriosItemHandler> inventory = CuriosApi.getCuriosInventory(player.data).resolve();
        return inventory.map(iCuriosItemHandler -> iCuriosItemHandler.isEquipped(item.data)).orElse(false);

    }
}
