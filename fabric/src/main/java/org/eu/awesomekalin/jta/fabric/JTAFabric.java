package org.eu.awesomekalin.jta.fabric;

import org.eu.awesomekalin.jta.JTA;
import net.fabricmc.api.ModInitializer;

public class JTAFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        JTA.init();
    }
}
