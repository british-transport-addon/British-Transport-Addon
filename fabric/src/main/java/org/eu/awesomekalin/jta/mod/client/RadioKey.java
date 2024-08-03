package org.eu.awesomekalin.jta.mod.client;

import org.eu.awesomekalin.jta.mod.init.KeyBindingsInit;
import org.mtr.core.tool.Utilities;
import org.mtr.mapping.holder.ClientPlayerEntity;
import org.mtr.mapping.holder.ItemStack;
import org.mtr.mapping.holder.MinecraftClient;
import org.mtr.mapping.holder.Text;
import org.mtr.mapping.mapper.TextHelper;
import org.mtr.mod.InitClient;
import org.mtr.mod.KeyBindings;

public class RadioKey {

	private final ItemStack equippedRadio = null;

	public RadioKey() {
	}

	public void tick() {
		final boolean talkRadio = KeyBindingsInit.TALK_RADIO.isPressed();


	}
}