package org.eu.awesomekalin.jta.mod;

import org.mtr.mapping.holder.ItemStack;
import org.mtr.mapping.holder.PlayerEntity;

public interface NativeAPI {

    public boolean hasEquippedAttachment(PlayerEntity player, ItemStack item);
}
