package org.eu.awesomekalin.jta.mod.init;

import org.eu.awesomekalin.jta.mod.Init;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.registry.SoundEventRegistryObject;

public class SoundInit {
    public static final SoundEventRegistryObject ELECTRICAL_HUM_1 = Init.REGISTRY.registerSoundEvent(new Identifier(Init.MOD_ID, "electrical_hum_1"));
   public static void INIT() {
        Init.LOGGER.debug("Registering JTA Sound Events.");
    }
}
