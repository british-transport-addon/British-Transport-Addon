package org.eu.awesomekalin.jta.platform;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.eu.awesomekalin.jta.mod.init.ItemInit;
import org.mtr.libraries.kotlin.Pair;
import org.mtr.mapping.holder.PlayerEntity;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.Optional;

public class NativeAPI {

    public boolean hasEquippedAttachment(PlayerEntity player, ItemStack item) {
        return CuriosApi.getCuriosHelper().findEquippedCurio(item.getItem(), player.data).isPresent();
    }

    public ImmutableTriple<String, Integer, ItemStack> getEquippedAttachment(PlayerEntity player, Item item) {
        Optional<ImmutableTriple<String, Integer, ItemStack>> result = CuriosApi.getCuriosHelper().findEquippedCurio(item, player.data);
        if (result.isPresent()) {
            ImmutableTriple<String, Integer, ItemStack> slotResult = result.get();
            return slotResult;
        }
        return null;
    }

    @OnlyIn(Dist.CLIENT)
    public void registerShieldItemProperties() {
        ItemProperties.register(ItemInit.RIOT_SHIELD.get().data, new ResourceLocation("blocking"),
                (ItemStack stack, ClientLevel world, LivingEntity entity, int seed) ->
                        entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F
        );
    }
}