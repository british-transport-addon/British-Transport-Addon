package org.eu.awesomekalin.jta.init;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import org.eu.awesomekalin.jta.mod.InitClient;
import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;

public class MainFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        InitClient.init();
    }
}
