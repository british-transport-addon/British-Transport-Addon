package org.eu.awesomekalin.jta.init;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import org.eu.awesomekalin.jta.mod.Init;
import org.eu.awesomekalin.jta.mod.InitClient;

@Mod(Init.MOD_ID)
public class MainForge {
    public MainForge() {
        Init.injectNativeAPI(new ForgeNativeAPI());
        Init.init();
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> InitClient::init);
    }
}
