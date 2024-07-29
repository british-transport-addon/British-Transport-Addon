package org.eu.awesomekalin.jta.mod.items;

import net.minecraft.block.DispenserBlock;
import net.minecraft.client.render.entity.model.ShieldEntityModel;
import net.minecraft.item.ArmorItem;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.s2c.play.PlayerActionResponseS2CPacket;
import net.minecraft.util.UseAction;
import org.mtr.mapping.holder.ItemSettings;
import org.mtr.mapping.holder.ItemStack;
import org.mtr.mapping.mapper.ItemExtension;

import java.util.List;

public class ShieldItem extends ItemExtension {

    public static final int field_30918 = 5;
    public static final float MIN_DAMAGE_AMOUNT_TO_BREAK = 3.0F;
    public static final String BASE_KEY = "Base";

    public ShieldItem() {
        super(new ItemSettings());
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSER_BEHAVIOR);
    }

    @Override
    public int getMaxUseTime(net.minecraft.item.ItemStack stack) {
        return 72000;
    }

    @Override
    public UseAction getUseAction(net.minecraft.item.ItemStack stack) {
        return UseAction.BLOCK;
    }

    @Override
    public void useWithoutResult(org.mtr.mapping.holder.World world, org.mtr.mapping.holder.PlayerEntity user, org.mtr.mapping.holder.Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.data.setCurrentHand(hand.data);
    }
}
