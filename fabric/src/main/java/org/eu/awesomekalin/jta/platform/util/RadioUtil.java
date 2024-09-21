package org.eu.awesomekalin.jta.platform.util;

import com.google.common.collect.ImmutableList;
import de.maxhenkel.voicechat.api.VoicechatServerApi;
import de.maxhenkel.voicechat.api.audiochannel.AudioPlayer;
import de.maxhenkel.voicechat.api.audiochannel.LocationalAudioChannel;
import de.maxhenkel.voicechat.api.opus.OpusDecoder;
import de.maxhenkel.voicechat.api.opus.OpusEncoder;
import de.maxhenkel.voicechat.api.packets.MicrophonePacket;
import net.minecraft.nbt.NbtCompound;
import org.eu.awesomekalin.jta.mod.util.BoxUtil;
import org.eu.awesomekalin.jta.platform.InitVoiceChatPlugin;
import org.eu.awesomekalin.jta.mod.init.ItemInit;
import org.mtr.mapping.holder.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class RadioUtil {
	public static HashMap<UUID, OpusDecoder> playerDecoders = new HashMap<>();
	public static HashMap<UUID, OpusEncoder> playerEncoders = new HashMap<>();

	// You could argue these would make more sense as static methods in RadioItem, and you'd probably be right.
	public static int getRadioChannel(ItemStack stack) {
		NbtCompound nbt = stack.data.getOrCreateNbt();
		int channel = 0;
		if (nbt.contains("channel"))
			channel = nbt.getInt("channel");
		return channel;
	}

	public static boolean isRadioTransmitting(ItemStack stack) {
		NbtCompound nbt = stack.data.getOrCreateNbt();
		boolean transmitting = false;
		if (nbt.contains("isTransmitting"))
			transmitting = nbt.getBoolean("isTransmitting");
		return transmitting;
	}

	public static boolean isRadioReceiving(ItemStack stack) {
		NbtCompound nbt = stack.data.getOrCreateNbt();
		boolean receiving = true;
		if (nbt.contains("isReceiving"))
			receiving = nbt.getBoolean("isReceiving");
		return receiving;
	}

	public static boolean isRadioEnabled(ItemStack stack) {
		NbtCompound nbt = stack.data.getOrCreateNbt();
		boolean receiving = false;
		if (nbt.contains("isEnabled"))
			receiving = nbt.getBoolean("isEnabled");
		return receiving;
	}

	public static void setRadioChannel(ItemStack stack, int channel) {
		NbtCompound nbt = stack.data.getOrCreateNbt();
		nbt.putInt("channel", channel);
		stack.data.setNbt(nbt);
	}

	public static void setRadioTransmitting(ItemStack stack, boolean transmitting) {
		NbtCompound nbt = stack.data.getOrCreateNbt();
		nbt.putBoolean("isTransmitting", transmitting);
		stack.data.setNbt(nbt);
	}

	public static void setRadioReceiving(ItemStack stack, boolean receiving) {
		NbtCompound nbt = stack.data.getOrCreateNbt();
		nbt.putBoolean("isReceiving", receiving);
		stack.data.setNbt(nbt);
	}

	public static void setRadioEnabled(ItemStack stack, boolean enabled) {
		NbtCompound nbt = stack.data.getOrCreateNbt();
		nbt.putBoolean("isEnabled", enabled);
		stack.data.setNbt(nbt);
	}

	public static void transmitOnChannel(VoicechatServerApi serverApi, MicrophonePacket packet, ServerPlayerEntity sender, int senderChannel) {
		MinecraftServer server = sender.getServer();
		ServerWorld world = sender.getServerWorld();
		byte[] encodedData = packet.getOpusEncodedData();
		// Decode data
		OpusDecoder decoder = playerDecoders.getOrDefault(sender.getUuid(), serverApi.createDecoder());
		playerDecoders.putIfAbsent(sender.getUuid(), decoder);
		if (encodedData.length == 0)
			decoder.resetState();
		short[] decodedData = decoder.decode(encodedData);
		// Apply filter
		//RadioFilter.applyFilter(decodedData);
		// Re-Encode data
		OpusEncoder encoder = playerEncoders.getOrDefault(sender.getUuid(), serverApi.createEncoder());
		playerEncoders.putIfAbsent(sender.getUuid(), encoder);
		if (encodedData.length == 0)
			encoder.resetState();
		final byte[] voiceData = encoder.encode(decodedData);
		// Player radios
		for (ServerPlayerEntity player :  server.getPlayerManager().data.getPlayerList().stream().map(ServerPlayerEntity::new).collect(Collectors.toSet())) {
			if (player == sender)
				continue;
			if (!isReceivingChannel(PlayerEntity.cast(player), senderChannel))
				continue;
			// Play voice to nearby players
			int listeningDistance = 20 * 2;
			List<PlayerEntity> playersInRange = world.data.getEntitiesByClass(net.minecraft.entity.player.PlayerEntity.class, BoxUtil.of(player.getPos(), listeningDistance, listeningDistance, listeningDistance).data, (entity) -> true).stream().map(PlayerEntity::new).toList();
			for (PlayerEntity entity : playersInRange) {
				// Prioritize player's handheld radio over another player's radio
				if (!entity.equals(player) && !entity.equals(sender) && isReceivingChannel(entity, senderChannel))
					continue;
				serverApi.sendLocationalSoundPacketTo(serverApi.getConnectionOf(entity.getUuid()), packet.locationalSoundPacketBuilder().opusEncodedData(voiceData).position(serverApi.createPosition(player.getX(), player.getY(), player.getZ())).distance(8f).build());
			}
		}
	}

	public static void transmitDataOnChannel(VoicechatServerApi serverApi, ServerWorld world, short[] audioData, int senderChannel) {
		transmitDataOnChannel(serverApi, world, audioData, senderChannel, null);
	}

	public static void transmitDataOnChannel(VoicechatServerApi serverApi, ServerWorld world, short[] audioData, int senderChannel, Runnable onAudioStopped) {
		MinecraftServer server = world.getServer();
		AtomicBoolean hasSetRunnable = new AtomicBoolean(false);
		for (ServerPlayerEntity player : server.getPlayerManager().data.getPlayerList().stream().map(ServerPlayerEntity::new).collect(Collectors.toSet())) {
			if (!isReceivingChannel(PlayerEntity.cast(player), senderChannel))
				continue;
			// Play voice to nearby players
			LocationalAudioChannel channel = serverApi.createLocationalAudioChannel(UUID.randomUUID(), serverApi.fromServerLevel(world), serverApi.createPosition(player.getX(), player.getY(), player.getZ()));
			if (channel == null)
				continue;
			channel.setDistance(8f);
			channel.setCategory(InitVoiceChatPlugin.RADIO_CATEGORY);
			AudioPlayer audioPlayer = serverApi.createAudioPlayer(channel, serverApi.createEncoder(), audioData);
			if (!hasSetRunnable.get()) {
				audioPlayer.setOnStopped(onAudioStopped);
				hasSetRunnable.set(true);
			}
			audioPlayer.startPlaying();
		}
	}

	public static boolean isReceivingChannel(PlayerEntity player, int channel) {
		List<ItemStack> radios = RadioUtil.getRadios(player);
		for (ItemStack stack : radios) {
			if (!RadioUtil.isRadioEnabled(stack))
				continue;
			if (!RadioUtil.isRadioReceiving(stack))
				continue;
			if (RadioUtil.getRadioChannel(stack) != channel)
				continue;
			return true;
		}
		return false;
	}

	public static List<ItemStack> getRadios(PlayerEntity player) {
		List<List<ItemStack>> inventories = ImmutableList.of(player.data.getInventory().main.stream().map(ItemStack::new).toList(), player.data.getInventory().offHand.stream().map(ItemStack::new).toList());
		List<ItemStack> radios = new ArrayList<>();
		for (List<ItemStack> inventory : inventories) {
			for (ItemStack stack : inventory) {
				if (!stack.getTranslationKey().equals(ItemInit.MET_POLICE_RADIO.get().getTranslationKey()))
					continue;
				radios.add(stack);
			}
		}
		return radios;
	}
}
