package org.eu.awesomekalin.jta.platform;

import de.maxhenkel.voicechat.api.VoicechatConnection;
import de.maxhenkel.voicechat.api.VoicechatPlugin;
import de.maxhenkel.voicechat.api.VoicechatServerApi;
import de.maxhenkel.voicechat.api.VolumeCategory;
import de.maxhenkel.voicechat.api.events.EventRegistration;
import de.maxhenkel.voicechat.api.events.MicrophonePacketEvent;
import de.maxhenkel.voicechat.api.events.VoicechatServerStartedEvent;
import net.minecraft.util.math.Box;
import org.eu.awesomekalin.jta.platform.util.RadioUtil;
import org.mtr.mapping.holder.ItemStack;
import org.mtr.mapping.holder.PlayerEntity;
import org.mtr.mapping.holder.ServerPlayerEntity;

import java.util.List;

public class InitVoiceChatPlugin implements VoicechatPlugin {
	public static VoicechatServerApi API;

	public static String RADIO_CATEGORY = "radios";

	@Override
	public void registerEvents(EventRegistration registration) {
		registration.registerEvent(VoicechatServerStartedEvent.class, this::onServerStarted);
		registration.registerEvent(MicrophonePacketEvent.class, this::onMicrophonePacket);
	}

	private void onServerStarted(VoicechatServerStartedEvent event) {
		API = event.getVoicechat();
		// Register radio volume category
		VolumeCategory radios = API.volumeCategoryBuilder()
				.setId(RADIO_CATEGORY)
				.setName("Radios")
				.setDescription("The volume of all radios")
				.build();
		API.registerVolumeCategory(radios);
	}

	private void onMicrophonePacket(MicrophonePacketEvent event) {
		VoicechatConnection connection = event.getSenderConnection();
		VoicechatServerApi serverApi = event.getVoicechat();
		if (connection == null)
			return;
		if (event.getPacket().getOpusEncodedData().length == 0)
			return;
		ServerPlayerEntity sourcePlayer = (ServerPlayerEntity) connection.getPlayer().getPlayer();
		// Find nearby players that might be carrying radios that could transmit
		int listeningDistance = 20 * 2;
		List<ServerPlayerEntity> playersInRange = sourcePlayer.getServerWorld().data.getEntitiesByClass(net.minecraft.server.network.ServerPlayerEntity.class, Box.of(sourcePlayer.getPos().data, listeningDistance, listeningDistance, listeningDistance), (entity) -> true).stream().map(ServerPlayerEntity::new).toList();
		for (ServerPlayerEntity player : playersInRange) {
			List<ItemStack> radios = RadioUtil.getRadios(PlayerEntity.cast(player));
			for (ItemStack stack : radios) {
				if (!RadioUtil.isRadioEnabled(stack))
					continue;
				if (!RadioUtil.isRadioTransmitting(stack))
					continue;
				int channel = RadioUtil.getRadioChannel(stack);
				RadioUtil.transmitOnChannel(serverApi, event.getPacket(), player, channel);
			}
		}
	}

	@Override
	public String getPluginId() {
		return "bta";
	}
}