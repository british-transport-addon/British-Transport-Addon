package org.eu.awesomekalin.jta.mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eu.awesomekalin.jta.mod.init.*;
import org.mtr.mapping.registry.Registry;
import org.mtr.mapping.tool.DummyClass;

public class Init {

    public static final String MOD_ID = "jta";
    public static final Logger LOGGER = LogManager.getLogger("Jesses-Transit-Addon");
    public static final Registry REGISTRY = new Registry();

    public static void init() {
        BlockInit.INIT();
        ItemInit.INIT();
        BlockEntityTypeInit.INIT();
        CreativeTabInit.INIT();
        SoundInit.INIT();

        DummyClass.enableLogging();
        REGISTRY.init();
    }
}
