package org.eu.awesomekalin.jta.init;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ShieldItem;
import org.eu.awesomekalin.jta.mod.NativeAPI;
import org.eu.awesomekalin.jta.mod.init.ItemInit;
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

    @Override
    public void registerShieldItemProperties() {
        ItemPropertyFunction blockFn = (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F;
        ItemProperties.register(ItemInit.RIOT_SHIELD.get().data, new ResourceLocation("minecraft:blocking"), blockFn);
    }
}
