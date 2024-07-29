package org.eu.awesomekalin.jta.mod.init;

import org.eu.awesomekalin.jta.mod.Init;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.registry.SoundEventRegistryObject;

public class SoundInit {
    public static final SoundEventRegistryObject AXON_BODY_3 = Init.REGISTRY.registerSoundEvent(new Identifier(Init.MOD_ID, "axon_body_3"));
    public static final SoundEventRegistryObject MET_POLICE_RADIO_IN = Init.REGISTRY.registerSoundEvent(new Identifier(Init.MOD_ID, "met_police_radio_in"));
    public static final SoundEventRegistryObject MET_POLICE_RADIO_OUT = Init.REGISTRY.registerSoundEvent(new Identifier(Init.MOD_ID, "met_police_radio_out"));
    public static void INIT() {
        Init.LOGGER.debug("Registering JTA Sound Events.");
    }
}
