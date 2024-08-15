package org.eu.awesomekalin.jta.init;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.entity.EntityType;
import org.eu.awesomekalin.jta.mod.InitClient;
import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.eu.awesomekalin.jta.mod.render.road.VerticalRoadBarrierRender;

public class MainFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.register(BlockEntityTypeInit.VERTICAL_ROAD_BARRIER.get().data, VerticalRoadBarrierRender::new);


        InitClient.init();
    }
}
