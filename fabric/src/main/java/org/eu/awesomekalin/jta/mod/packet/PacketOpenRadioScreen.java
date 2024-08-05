package org.eu.awesomekalin.jta.mod.packet;

import org.eu.awesomekalin.jta.mod.screen.radio.RadioMainScreen;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.registry.PacketHandler;
import org.mtr.mapping.tool.PacketBufferReceiver;
import org.mtr.mapping.tool.PacketBufferSender;

public final class PacketOpenRadioScreen extends PacketHandler  {

	public PacketOpenRadioScreen(PacketBufferReceiver receiver) {
		super();
	}

	@Override
	public void write(PacketBufferSender packetBufferSender) {
    }

	@Override
	public void runClient() {
		System.out.println("new packet in"); // is is  never ran ong
		MinecraftClient minecraftClient = MinecraftClient.getInstance();
		ClientPlayerEntity player = minecraftClient.getPlayerMapped();
        assert player != null;
        RadioMainScreen.handle(player.getStackInHand(Hand.MAIN_HAND));
	}
}