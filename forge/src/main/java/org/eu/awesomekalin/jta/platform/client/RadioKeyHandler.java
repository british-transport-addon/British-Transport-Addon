package org.eu.awesomekalin.jta.platform.client;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.eu.awesomekalin.jta.mod.Init;
import org.eu.awesomekalin.jta.mod.init.ItemInit;
import org.eu.awesomekalin.jta.mod.init.SoundInit;
import org.eu.awesomekalin.jta.platform.util.RadioUtil;
import org.mtr.mapping.holder.*;

import java.util.concurrent.ThreadLocalRandom;

public class RadioKeyHandler {
	public static void toggleRadio(ServerPlayerEntity player) {
		ImmutableTriple<String, Integer, net.minecraft.world.item.ItemStack> referenceItemStackPair = Init.NATIVE_API.getEquippedAttachment(PlayerEntity.cast(player), ItemInit.MET_POLICE_RADIO.get().data);

		if (referenceItemStackPair.getLeft() == "0") {
			ItemStack stack = new ItemStack(referenceItemStackPair.getRight());
			if (!stack.getTranslationKey().equals(ItemInit.MET_POLICE_RADIO.get().getTranslationKey()))
				return;
			System.out.println("test3");
			boolean isTransmitting = RadioUtil.isRadioTransmitting(stack);
			RadioUtil.setRadioTransmitting(stack, !isTransmitting);
			int channel = RadioUtil.getRadioChannel(stack);
			player.sendMessage(Text.of(!isTransmitting ? "Transmitting on " + channel + "mhz." : "Transmission Stopped"), true);
			System.out.println("test4");

			if (!isTransmitting) {
				if (ThreadLocalRandom.current().nextBoolean()) {
					player.getEntityWorld().playSound(null, player.getBlockPos(), SoundInit.MET_POLICE_RADIO_IN.get(), SoundCategory.VOICE, 1f, 1f);
				} else {
					player.getEntityWorld().playSound(null, player.getBlockPos(), SoundInit.MET_POLICE_RADIO_INCOMING.get(), SoundCategory.VOICE, 1f, 1f);
				}
			} else {
				player.getEntityWorld().playSound(null, player.getBlockPos(), SoundInit.MET_POLICE_RADIO_OUT.get(), SoundCategory.VOICE, 1f, 1f);
			}
		}
	}
}