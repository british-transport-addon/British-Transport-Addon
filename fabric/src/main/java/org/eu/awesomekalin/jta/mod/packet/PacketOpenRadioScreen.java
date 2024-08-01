package org.eu.awesomekalin.jta.mod.packet;

import org.eu.awesomekalin.jta.mod.screen.radio.RadioMainScreen;
import org.jetbrains.annotations.NotNull;
import org.mtr.core.integration.Response;
import org.mtr.libraries.com.google.gson.JsonObject;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.registry.PacketHandler;
import org.mtr.mapping.tool.PacketBufferReceiver;
import org.mtr.mapping.tool.PacketBufferSender;
import org.mtr.mod.Init;
import org.mtr.mod.packet.PacketRequestResponseBase;

import javax.annotation.Nonnull;

public final class PacketOpenRadioScreen extends PacketRequestResponseBase  {

	public PacketOpenRadioScreen(String test) {
		super(test);
	}

	public PacketOpenRadioScreen(PacketBufferReceiver receiver) {
		super(receiver);
	}

	@Override
	public void write(PacketBufferSender packetBufferSender) {
    }

	@Override
	protected void runClientInbound(Response response) {
		System.out.println("new packet in");
		MinecraftClient minecraftClient = MinecraftClient.getInstance();
		ClientPlayerEntity player = minecraftClient.getPlayerMapped();
		RadioMainScreen.handle(player.getStackInHand(Hand.MAIN_HAND));
	}

	@Override
	protected PacketRequestResponseBase getInstance(String s) {
		return new PacketOpenRadioScreen(s);
	}

	@NotNull
	@Override
	protected String getEndpoint() {
		return "operation/radio";
	}

	@Override
	protected ResponseType responseType() {
		return ResponseType.NONE;
	}

	public static void sendDirectlyToServer(ServerWorld serverWorld, ServerPlayerEntity serverPlayerEntity) {
		System.out.println("new packet out");
		Init.REGISTRY.sendPacketToClient(serverPlayerEntity, new PacketOpenRadioScreen(""));

	}
}