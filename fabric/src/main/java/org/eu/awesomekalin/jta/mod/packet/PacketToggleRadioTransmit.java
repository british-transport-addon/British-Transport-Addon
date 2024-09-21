package org.eu.awesomekalin.jta.mod.packet;

import org.eu.awesomekalin.jta.platform.client.RadioKeyHandler;
import org.mtr.mapping.holder.MinecraftServer;
import org.mtr.mapping.holder.ServerPlayerEntity;
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