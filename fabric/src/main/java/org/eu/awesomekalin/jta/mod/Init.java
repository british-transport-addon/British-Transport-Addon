package org.eu.awesomekalin.jta.mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eu.awesomekalin.jta.mod.init.*;
import org.eu.awesomekalin.jta.mod.packet.PacketOpenRadioScreen;
import org.eu.awesomekalin.jta.mod.packet.PacketToggleRadioTransmit;
import org.eu.awesomekalin.jta.mod.packet.PacketUpdateRadioFrequency;
import org.eu.awesomekalin.jta.mod.packet.entity.*;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.registry.Registry;
import org.mtr.mapping.tool.DummyClass;

public class Init {

    public static final String MOD_ID = "jta";
    public static final Logger LOGGER = LogManager.getLogger("Jesses-Transit-Addon");
    public static final Registry REGISTRY = new Registry();
    public static NativeAPI NATIVE_API;

    public static void init() {
        BlockInit.INIT();
        ParentInit.INIT();
        ItemInit.INIT();
        PoleInit.INIT();
        SignInit.INIT();
        FenceInit.INIT();
        BlockEntityTypeInit.INIT();
        EntityTypeInit.INIT();
        CreativeTabInit.INIT();
        SoundInit.INIT();

        DummyClass.enableLogging();
        System.out.println("Setting up packet handlers");
        REGISTRY.setupPackets(new Identifier(MOD_ID, "packet"));
        REGISTRY.registerPacket(PacketOpenRadioScreen.class, PacketOpenRadioScreen::new);
        REGISTRY.registerPacket(PacketUpdateRadioFrequency.class, PacketUpdateRadioFrequency::new);
        REGISTRY.registerPacket(PacketToggleRadioTransmit.class, PacketToggleRadioTransmit::new);
        REGISTRY.registerPacket(PacketUpdateOneLineBlockEntity.class, PacketUpdateOneLineBlockEntity::new);
        REGISTRY.registerPacket(PacketUpdateTwoLineBlockEntity.class, PacketUpdateTwoLineBlockEntity::new);
        REGISTRY.registerPacket(PacketUpdateThreeLineBlockEntity.class, PacketUpdateThreeLineBlockEntity::new);
        REGISTRY.registerPacket(PacketUpdateFourLineBlockEntity.class, PacketUpdateFourLineBlockEntity::new);
        REGISTRY.registerPacket(PacketUpdateFiveLineBlockEntity.class, PacketUpdateFiveLineBlockEntity::new);
        REGISTRY.registerPacket(PacketUpdateSixLineBlockEntity.class, PacketUpdateSixLineBlockEntity::new);
        REGISTRY.registerPacket(PacketUpdateSevenLineBlockEntity.class, PacketUpdateSevenLineBlockEntity::new);
        REGISTRY.registerPacket(PacketUpdateEightLineBlockEntity.class, PacketUpdateEightLineBlockEntity::new);
        REGISTRY.registerPacket(PacketUpdateNineLineBlockEntity.class, PacketUpdateNineLineBlockEntity::new);

        REGISTRY.init();
    }
    public static void injectNativeAPI(NativeAPI nativeAPI) {
        NATIVE_API = nativeAPI;
    }
}
