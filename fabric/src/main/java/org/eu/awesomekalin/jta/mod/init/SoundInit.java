package org.eu.awesomekalin.jta.mod.init;

import org.eu.awesomekalin.jta.mod.Init;
import org.eu.awesomekalin.jta.mod.blocks.directional.HandheldSignBase;
import org.eu.awesomekalin.jta.mod.items.RadioItem;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.registry.BlockRegistryObject;
import org.mtr.mapping.registry.ItemRegistryObject;
import org.mtr.mapping.registry.SoundEventRegistryObject;
import org.mtr.mod.SoundEvents;

public class SoundInit {
    public static final SoundEventRegistryObject MET_POLICE_RADIO_IN = Init.REGISTRY.registerSoundEvent(new Identifier(Init.MOD_ID, "met_police_radio_in"));
    public static final SoundEventRegistryObject MET_POLICE_RADIO_OUT = Init.REGISTRY.registerSoundEvent(new Identifier(Init.MOD_ID, "met_police_radio_out"));
    public static void INIT() {
        Init.LOGGER.debug("Registering JTA Sound Events.");
    }
}
