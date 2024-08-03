package org.eu.awesomekalin.jta.mod.packet;

import org.eu.awesomekalin.jta.mod.client.RadioKeyHandler;
import org.eu.awesomekalin.jta.mod.init.SoundInit;
import org.eu.awesomekalin.jta.mod.util.RadioUtil;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.registry.PacketHandler;
import org.mtr.mapping.tool.PacketBufferReceiver;
import org.mtr.mapping.tool.PacketBufferSender;

public final class PacketToggleRadioTransmit extends PacketHandler  {

	public PacketToggleRadioTransmit() {
	}

	public PacketToggleRadioTransmit(PacketBufferReceiver receiver) {
		super();
	}

	@Override
	public void write(PacketBufferSender packetBufferSender) {
    }

	@Override
	public void runServer(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity) {
		RadioKeyHandler.toggleRadio(serverPlayerEntity);
	}
}