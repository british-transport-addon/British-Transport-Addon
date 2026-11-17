package org.eu.awesomekalin.jta.init;

import net.fabricmc.api.ModInitializer;
import org.eu.awesomekalin.jta.mod.Init;

public class MainFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Init.init();
    }
}
