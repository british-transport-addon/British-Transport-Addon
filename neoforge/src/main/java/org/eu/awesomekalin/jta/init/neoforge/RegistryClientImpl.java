package org.eu.awesomekalin.jta.init.neoforge;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.neoforged.neoforge.network.PacketDistributor;
import org.eu.awesomekalin.jta.neoforge.ModEventBusClient;
import org.mtr.MTRClient;
import org.mtr.packet.CustomPacketS2C;
import org.mtr.packet.PacketBufferSender;
import org.mtr.packet.PacketHandler;
import org.mtr.registry.ObjectHolder;

public class RegistryClientImpl {
    public static <T extends BlockEntity, U extends T> void registerBlockEntityRenderer(ObjectHolder<BlockEntityType<U>> blockEntityType, BlockEntityRendererFactory<T> factory) {
        ModEventBusClient.BLOCK_ENTITY_RENDERERS.add(event -> event.registerBlockEntityRenderer(blockEntityType.createAndGet(), factory));
    }

    public static <T extends Entity, U extends T> void registerEntityRenderer(ObjectHolder<EntityType<U>> entityType, EntityRendererFactory<T> factory) {
        ModEventBusClient.BLOCK_ENTITY_RENDERERS.add(event -> event.registerEntityRenderer(entityType.createAndGet(), factory));
    }

    public static void registerBlockRenderType(RenderLayer renderLayer, ObjectHolder<Block> block) {
        ModEventBusClient.CLIENT_OBJECTS_TO_REGISTER.add(() -> RenderLayers.setRenderLayer(block.createAndGet(), renderLayer));
    }

    public static void setupPackets() {
        /*ModEventBus.PAYLOAD_HANDLERS.add(payloadRegistrar -> payloadRegistrar.playToClient(MTR.PACKET_IDENTIFIER_S2C, PacketCodec.tuple(PacketCodecs.BYTE_ARRAY, CustomPacketS2C::buffer, CustomPacketS2C::new), new DirectionalPayloadHandler<>((customPacketS2C, context) -> PacketBufferReceiver.receive(customPacketS2C.buffer(), packetBufferReceiver -> {
            final Function<PacketBufferReceiver, ? extends PacketHandler> getInstance = ModEventBus.PACKETS.get(packetBufferReceiver.readString());
            if (getInstance != null) {
                getInstance.apply(packetBufferReceiver).runClient();
            }
        }, MinecraftClient.getInstance()::execute), (customPacketS2C, context) -> {
        })));*/
    }

    public static <T extends PacketHandler> void sendPacketToServer(T data) {
        /*final PacketBufferSender packetBufferSender = new PacketBufferSender();
        packetBufferSender.writeString(data.getClass().getName());
        data.write(packetBufferSender);
        packetBufferSender.send(bytes -> PacketDistributor.sendToServer(new CustomPacketS2C(bytes)), MinecraftClient.getInstance()::execute);
    */}

    public static void registerWorldRenderEvent(MTRClient.WorldRenderCallback worldRenderCallback) {
        //MainEventBusClient.worldRenderCallback = worldRenderCallback;
    }
}
