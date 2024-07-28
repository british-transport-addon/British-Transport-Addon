package org.eu.awesomekalin.jta.mod.items;

import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.text.TextContent;
import org.eu.awesomekalin.jta.mod.init.SoundInit;
import org.eu.awesomekalin.jta.mod.util.RadioUtil;
import org.jetbrains.annotations.NotNull;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.InventoryHelper;
import org.mtr.mapping.mapper.ItemExtension;
import org.mtr.mapping.mapper.PlayerHelper;
import org.mtr.mapping.mapper.TextHelper;

public class RadioItem extends ItemExtension {

    public RadioItem() {
        super(new ItemSettings().maxCount(1));
    }

    @Override
    public void useWithoutResult(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        int channel = RadioUtil.getRadioChannel(stack);
        if (!RadioUtil.isRadioTransmitting(stack)) {
        }
            world.playSound(null, player.getBlockPos(), SoundInit.MET_POLICE_RADIO_IN.get(), SoundCategory.VOICE, 1f, 1f);
        player.sendMessage(Text.of("Transmitting.."), true);
        RadioUtil.setRadioTransmitting(stack, true);

    }


    @NotNull
    @Override
    public ItemStack finishUsing2(ItemStack stack, World world, LivingEntity user) {RadioUtil.setRadioTransmitting(stack, false);
        int channel = RadioUtil.getRadioChannel(stack);
        PlayerEntity.cast(user).sendMessage(Text.of("Transmission Ended."), true);

        world.playSound(null, user.getBlockPos(), SoundInit.MET_POLICE_RADIO_OUT.get(), SoundCategory.VOICE, 1, 1);

        return super.finishUsing2(stack, world, user);
    }
}
