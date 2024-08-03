package org.eu.awesomekalin.jta.mod.client;

import dev.emi.trinkets.api.SlotReference;
import net.minecraft.util.Pair;
import org.eu.awesomekalin.jta.mod.Init;
import org.eu.awesomekalin.jta.mod.init.ItemInit;
import org.eu.awesomekalin.jta.mod.init.KeyBindingsInit;
import org.eu.awesomekalin.jta.mod.init.SoundInit;
import org.eu.awesomekalin.jta.mod.util.RadioUtil;
import org.mtr.mapping.holder.*;

import java.util.concurrent.ThreadLocalRandom;

public class RadioKeyHandler {
	// okay so when teh button is clicked down can u toggle this function for me?
	// like make a function that can t oggle the toggleRadio(player)
	public static void toggleRadio(ServerPlayerEntity player) {
		// mayber?
		// but then what do we do with this function when do we call it idk
		// like we gotta figure out how to make it so it actuall calls this
		//
		// brr :D
		// Do you know if the rest of the code works already?
		Pair<SlotReference, org.mtr.mapping.holder.ItemStack> referenceItemStackPair = Init.NATIVE_API.getEquippedAttachment(PlayerEntity.cast(player), ItemInit.MET_POLICE_RADIO.get());
		System.out.println(referenceItemStackPair.getLeft().index());
		// to get the correct value to put there
		if (referenceItemStackPair.getLeft().index() == 0) {
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