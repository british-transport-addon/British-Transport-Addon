package org.eu.awesomekalin.jta;

import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eu.awesomekalin.jta.init.BlockEntityTypeInit;
import org.eu.awesomekalin.jta.init.BlockInit;
import org.eu.awesomekalin.jta.init.CreativeTabs;
import org.eu.awesomekalin.jta.init.Registry;
import org.mtr.packet.CustomPacketC2S;
import org.mtr.packet.CustomPacketS2C;

public class JTA {
    public static final String MOD_ID = "jta";
    public static final Logger LOGGER = LogManager.getLogger("British-Transit-Addon");
    public static final CustomPayload.Id<CustomPacketS2C> PACKET_IDENTIFIER_S2C = new CustomPayload.Id<>(Identifier.of(MOD_ID, "packet_s2c"));
    public static final CustomPayload.Id<CustomPacketC2S> PACKET_IDENTIFIER_C2S = new CustomPayload.Id<>(Identifier.of(MOD_ID, "packet_c2s"));

    public static void init() {
        LOGGER.info("Loading BTA");
        BlockInit.init();
        BlockEntityTypeInit.init();
        CreativeTabs.init();

        Registry.init();
    }
}
