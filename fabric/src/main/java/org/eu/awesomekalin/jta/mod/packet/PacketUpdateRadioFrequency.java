package org.eu.awesomekalin.jta.mod.packet;

import org.eu.awesomekalin.jta.mod.init.SoundInit;
import org.eu.awesomekalin.jta.mod.screen.radio.RadioMainScreen;
import org.eu.awesomekalin.jta.mod.util.RadioUtil;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.registry.PacketHandler;
import org.mtr.mapping.tool.PacketBufferReceiver;
import org.mtr.mapping.tool.PacketBufferSender;

public final class PacketUpdateRadioFrequency extends PacketHandler  {

	private int channel;

	public PacketUpdateRadioFrequency(int channel) {
		this.channel = channel;
	}

	public PacketUpdateRadioFrequency(PacketBufferReceiver receiver) {
		super();
		this.channel = receiver.readInt();
	}

	@Override
	public void write(PacketBufferSender packetBufferSender) {
		packetBufferSender.writeInt(channel);
    }

	@Override
	public void runServer(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity) {
		RadioUtil.setRadioChannel(serverPlayerEntity.getStackInHand(Hand.MAIN_HAND), channel);
		serverPlayerEntity.getServerWorld().playSound(null, serverPlayerEntity.getBlockPos(), SoundInit.MET_POLICE_RADIO_INTERACT.get(), SoundCategory.VOICE, 1f, 1f);
		serverPlayerEntity.sendMessage(Text.of("Your radio channel has been updated to " + channel), true);
	}
}