package org.eu.awesomekalin.jta.init;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import org.eu.awesomekalin.jta.mod.Init;
import org.eu.awesomekalin.jta.mod.InitClient;
import org.eu.awesomekalin.jta.platform.NativeAPI;

@Mod(Init.MOD_ID)
public class MainForge {
    public MainForge() {
        ServerTickHandler.register();

        Init.injectNativeAPI(new NativeAPI());
        Init.init();
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> InitClient::init);
    }
}
