package org.eu.awesomekalin.jta.mod.client;

import net.minecraft.util.Pair;
import org.eu.awesomekalin.jta.mod.Init;
import org.eu.awesomekalin.jta.mod.init.ItemInit;
import org.eu.awesomekalin.jta.mod.init.SoundInit;
import org.eu.awesomekalin.jta.mod.util.RadioUtil;
import org.mtr.mapping.holder.*;

import java.util.concurrent.ThreadLocalRandom;

public class RadioKeyHandler {
	public static void toggleRadio(ServerPlayerEntity player) {
		Pair<Integer, org.mtr.mapping.holder.ItemStack> referenceItemStackPair = Init.NATIVE_API.getEquippedAttachment(PlayerEntity.cast(player), ItemInit.MET_POLICE_RADIO.get());

		if (referenceItemStackPair.getLeft() == 0) {
			ItemStack stack = referenceItemStackPair.getRight();
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