package org.eu.awesomekalin.jta;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eu.awesomekalin.jta.init.BlockInit;
import org.eu.awesomekalin.jta.init.CreativeTabs;
import org.eu.awesomekalin.jta.init.Registry;

public class JTA {
    public static final String MOD_ID = "jta";
    public static final Logger LOGGER = LogManager.getLogger("British-Transit-Addon");

    public static void init() {
        LOGGER.info("Loading BTA");
        BlockInit.init();
        CreativeTabs.init();

        Registry.init();
    }
}
