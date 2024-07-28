package org.eu.awesomekalin.jta.mod.items;

import net.minecraft.inventory.StackReference;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ClickType;
import org.eu.awesomekalin.jta.mod.Init;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.ItemExtension;

public class RadioItem extends ItemExtension {

    public RadioItem() {
        super(new ItemSettings().maxCount(1));
    }

    @Override
    public boolean onClicked(net.minecraft.item.ItemStack stack, net.minecraft.item.ItemStack otherStack, Slot slot, ClickType clickType, net.minecraft.entity.player.PlayerEntity player, StackReference cursorStackReference) {
        player.sendMessage(Text.of(slot.getIndex() + "").data, false);
        player.sendMessage(Text.of(Init.ACCESORY_API.hasEquippedAttachment(new PlayerEntity(player), new ItemStack(stack)) + "").data, false);
        return super.onClicked(stack, otherStack, slot, clickType, player, cursorStackReference);
    }
}
