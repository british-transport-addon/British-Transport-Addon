package org.eu.awesomekalin.jta.init;

import org.eu.awesomekalin.jta.mod.NativeAPI;
import org.mtr.mapping.holder.Item;
import org.mtr.mapping.holder.ItemStack;
import org.mtr.mapping.holder.PlayerEntity;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

import java.util.Optional;

public class ForgeNativeAPI implements NativeAPI {

    @Override
    public boolean hasEquippedAttachment(PlayerEntity player, ItemStack item) {
        Optional<ICuriosItemHandler> inventory = CuriosApi.getCuriosInventory(player.data).resolve();
        return inventory.map(iCuriosItemHandler -> iCuriosItemHandler.isEquipped(item.data.getItem())).orElse(false);

    }
}
