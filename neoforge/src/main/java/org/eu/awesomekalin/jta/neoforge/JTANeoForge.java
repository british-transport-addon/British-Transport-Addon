package org.eu.awesomekalin.jta.neoforge;

import net.neoforged.fml.common.Mod;
import org.eu.awesomekalin.jta.JTA;
import net.neoforged.bus.api.IEventBus;

@Mod(JTA.MOD_ID)
public final class JTANeoForge {
    public JTANeoForge(IEventBus eventBus) {
        JTA.init();
    }
}
