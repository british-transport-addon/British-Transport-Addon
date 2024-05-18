package org.eu.awesomekalin.jta.init;

import net.fabricmc.api.ClientModInitializer;
import org.eu.awesomekalin.jta.mod.InitClient;

public class MainFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        InitClient.init();
    }
}
