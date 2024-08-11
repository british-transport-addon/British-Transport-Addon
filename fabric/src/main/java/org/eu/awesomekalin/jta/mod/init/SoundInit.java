package org.eu.awesomekalin.jta.mod.init;

import org.eu.awesomekalin.jta.mod.Init;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.registry.SoundEventRegistryObject;

public class SoundInit {
    public static final SoundEventRegistryObject ELECTRICAL_HUM_1 = Init.REGISTRY.registerSoundEvent(new Identifier(Init.MOD_ID, "electrical_hum_1"));
    public static final SoundEventRegistryObject AXON_BODY_3 = Init.REGISTRY.registerSoundEvent(new Identifier(Init.MOD_ID, "axon_body_3"));
    public static final SoundEventRegistryObject MET_POLICE_RADIO_IN = Init.REGISTRY.registerSoundEvent(new Identifier(Init.MOD_ID, "met_police_radio_in"));
    public static final SoundEventRegistryObject MET_POLICE_RADIO_INCOMING = Init.REGISTRY.registerSoundEvent(new Identifier(Init.MOD_ID, "met_police_radio_incoming"));
    public static final SoundEventRegistryObject MET_POLICE_RADIO_OUT = Init.REGISTRY.registerSoundEvent(new Identifier(Init.MOD_ID, "met_police_radio_out"));
    public static final SoundEventRegistryObject MET_POLICE_RADIO_INTERACT = Init.REGISTRY.registerSoundEvent(new Identifier(Init.MOD_ID, "met_police_radio_interact"));
    public static void INIT() {
        Init.LOGGER.debug("Registering JTA Sound Events.");
    }
}
