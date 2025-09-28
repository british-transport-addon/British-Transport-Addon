package org.eu.awesomekalin.jta.init.fabric;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import org.eu.awesomekalin.jta.JTA;
import org.mtr.packet.CustomPacketC2S;
import org.mtr.packet.PacketBufferReceiver;
import org.mtr.packet.PacketBufferSender;
import org.mtr.packet.PacketHandler;
import org.mtr.registry.ObjectHolder;

import java.util.function.Function;

public class RegistryClientImpl {
    public static <T extends BlockEntity, U extends T> void registerBlockEntityRenderer(ObjectHolder<BlockEntityType<U>> blockEntityType, BlockEntityRendererFactory<T> factory) {
        BlockEntityRendererFactories.register(blockEntityType.createAndGet(), factory);
    }

    public static <T extends Entity, U extends T> void registerEntityRenderer(ObjectHolder<EntityType<U>> entityType, EntityRendererFactory<T> factory) {
        EntityRendererRegistry.register(entityType.createAndGet(), factory);
    }

    public static void registerBlockRenderType(RenderLayer renderLayer, ObjectHolder<Block> block) {
        BlockRenderLayerMap.INSTANCE.putBlock(block.createAndGet(), renderLayer);
    }

    public static void setupPackets() {
        /*ClientPlayNetworking.registerGlobalReceiver(JTA.PACKET_IDENTIFIER_S2C, (customPacketS2C, context) -> PacketBufferReceiver.receive(customPacketS2C.buffer(), packetBufferReceiver -> {
            final Function<PacketBufferReceiver, ? extends PacketHandler> getInstance = MTRFabric.PACKETS.get(packetBufferReceiver.readString());
            if (getInstance != null) {
                getInstance.apply(packetBufferReceiver).runClient();
            }
        }, MinecraftClient.getInstance()::execute));*/
    }

    public static <T extends PacketHandler> void sendPacketToServer(T data) {
        final PacketBufferSender packetBufferSender = new PacketBufferSender();
        packetBufferSender.writeString(data.getClass().getName());
        data.write(packetBufferSender);
        packetBufferSender.send(bytes -> ClientPlayNetworking.send(new CustomPacketC2S(bytes)), MinecraftClient.getInstance()::execute);
    }
}
