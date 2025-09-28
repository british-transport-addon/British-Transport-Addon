package org.eu.awesomekalin.jta.fabric;

import net.fabricmc.api.ClientModInitializer;
import org.eu.awesomekalin.jta.JTAClient;

public class JTAFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        JTAClient.init();
    }
}
