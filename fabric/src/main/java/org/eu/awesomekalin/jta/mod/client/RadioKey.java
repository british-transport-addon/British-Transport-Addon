package org.eu.awesomekalin.jta.mod.client;

import org.eu.awesomekalin.jta.mod.init.KeyBindingsInit;
import org.mtr.mapping.holder.ItemStack;

public class RadioKey {

	private final ItemStack equippedRadio = null;

	public RadioKey() {
	}

	public void tick() {
		final boolean talkRadio = KeyBindingsInit.TALK_RADIO.isPressed();


	}
}