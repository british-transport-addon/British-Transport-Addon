package org.eu.awesomekalin.jta.init;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.eu.awesomekalin.jta.mod.Init;
import org.eu.awesomekalin.jta.mod.init.ItemInit;
import org.eu.awesomekalin.jta.mod.init.SoundInit;
import org.eu.awesomekalin.jta.platform.util.RadioUtil;

@Mod.EventBusSubscriber(modid = Init.MOD_ID, bus = EventBusSubscriber.Bus.FORGE, value = Dist.DEDICATED_SERVER)
public class PlayerActionHandler {

    public static void register() {
        MinecraftForge.EVENT_BUS.register(PlayerActionHandler.class);
    }

    @SubscribeEvent
    public static void onPlayerSwapHands(PlayerInteractEvent.RightClickEmpty event) {
        // This is a workaround, as Forge does not have a specific swap hands event
        ServerPlayer player = (ServerPlayer) event.getEntity();
        InteractionHand hand = event.getHand();
        
        if (hand != InteractionHand.OFF_HAND) return;

        ItemStack stack = player.getMainHandItem();
        
        if (!stack.getItem().getDescriptionId().equals(ItemInit.MET_POLICE_RADIO.get().getTranslationKey())) return;

        boolean isTransmitting = RadioUtil.isRadioTransmitting(new org.mtr.mapping.holder.ItemStack(stack));
        RadioUtil.setRadioTransmitting(new org.mtr.mapping.holder.ItemStack(stack), !isTransmitting);
        int channel = RadioUtil.getRadioChannel(new org.mtr.mapping.holder.ItemStack(stack));
        player.sendSystemMessage(Component.literal(!isTransmitting ? "Transmitting on " + channel + "mhz." : "Transmission Stopped"), true);

        if (!isTransmitting) {
            player.level().playSound(null, player.blockPosition(), SoundInit.MET_POLICE_RADIO_IN.get().data, net.minecraft.sounds.SoundSource.VOICE, 1f, 1f);
        } else {
            player.level().playSound(null, player.blockPosition(), SoundInit.MET_POLICE_RADIO_OUT.get().data, net.minecraft.sounds.SoundSource.VOICE, 1f, 1f);
        }

        event.setCanceled(true);
    }
}