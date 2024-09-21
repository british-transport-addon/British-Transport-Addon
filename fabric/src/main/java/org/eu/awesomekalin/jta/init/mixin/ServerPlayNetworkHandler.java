package org.eu.awesomekalin.jta.init.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import org.eu.awesomekalin.jta.mod.init.ItemInit;
import org.eu.awesomekalin.jta.mod.init.SoundInit;
import org.eu.awesomekalin.jta.platform.util.RadioUtil;
import org.mtr.mapping.holder.SoundCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.ThreadLocalRandom;

@Mixin(net.minecraft.server.network.ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandler {
	@Shadow public ServerPlayerEntity player;

	@Inject(method = "onPlayerAction", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;getStackInHand(Lnet/minecraft/util/Hand;)Lnet/minecraft/item/ItemStack;"), cancellable = true)
	public void onPlayerAction(PlayerActionC2SPacket packet, CallbackInfo ci) {
		System.out.println("test");
		if (packet.getAction() != PlayerActionC2SPacket.Action.SWAP_ITEM_WITH_OFFHAND)
			return;

		System.out.println("test2");
		ItemStack stack = player.getStackInHand(Hand.MAIN_HAND);
		if (!stack.getTranslationKey().equals(ItemInit.MET_POLICE_RADIO.get().getTranslationKey()))
			return;
		System.out.println("test3");
		boolean isTransmitting = RadioUtil.isRadioTransmitting(new org.mtr.mapping.holder.ItemStack(stack));
		RadioUtil.setRadioTransmitting(new org.mtr.mapping.holder.ItemStack(stack), !isTransmitting);
		int channel = RadioUtil.getRadioChannel(new org.mtr.mapping.holder.ItemStack(stack));
		player.sendMessage(Text.of(!isTransmitting ? "Transmitting on " + channel + "mhz." : "Transmission Stopped"), true);
		System.out.println("test4");

		if (!isTransmitting) {
			if (ThreadLocalRandom.current().nextBoolean()) {
				player.getWorld().playSound(null, player.getBlockPos(), SoundInit.MET_POLICE_RADIO_IN.get().data, SoundCategory.VOICE.data, 1f, 1f);
			} else {
				player.getWorld().playSound(null, player.getBlockPos(), SoundInit.MET_POLICE_RADIO_INCOMING.get().data, SoundCategory.VOICE.data, 1f, 1f);
			}
		} else {
			player.getWorld().playSound(null, player.getBlockPos(), SoundInit.MET_POLICE_RADIO_OUT.get().data, SoundCategory.VOICE.data, 1f, 1f);
		}
		ci.cancel();
	}
}