package org.eu.awesomekalin.jta.init;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import org.mtr.packet.PacketHandler;
import org.mtr.registry.ObjectHolder;

public class RegistryClient {
    @ExpectPlatform
    public static <T extends BlockEntity, U extends T> void registerBlockEntityRenderer(ObjectHolder<BlockEntityType<U>> blockEntityType, BlockEntityRendererFactory<T> factory) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends Entity, U extends T> void registerEntityRenderer(ObjectHolder<EntityType<U>> entityType, EntityRendererFactory<T> factory) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void registerBlockRenderType(RenderLayer renderLayer, ObjectHolder<Block> block) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void setupPackets() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends PacketHandler> void sendPacketToServer(T data) {
        throw new AssertionError();
    }
}
