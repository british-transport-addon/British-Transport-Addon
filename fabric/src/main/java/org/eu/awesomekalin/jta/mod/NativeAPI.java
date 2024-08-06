package org.eu.awesomekalin.jta.mod;

import dev.emi.trinkets.api.SlotReference;
import net.minecraft.util.Pair;
import org.mtr.mapping.holder.Item;
import org.mtr.mapping.holder.ItemStack;
import org.mtr.mapping.holder.PlayerEntity;

public interface NativeAPI {

    public boolean hasEquippedAttachment(PlayerEntity player, ItemStack item);
    public Pair<Integer, ItemStack> getEquippedAttachment(PlayerEntity player, Item item);

    public void registerShieldItemProperties();
}
