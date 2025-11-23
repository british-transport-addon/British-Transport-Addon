package org.eu.awesomekalin.jta;

import org.eu.awesomekalin.jta.init.BlockEntityTypeInit;
import org.eu.awesomekalin.jta.init.RegistryClient;
import org.eu.awesomekalin.jta.render.RenderBarrierPlatform;
import org.eu.awesomekalin.jta.render.rail.signal.RenderBannerRepeaterSignal;

public class JTAClient {
    public static void init() {
        RegistryClient.registerBlockEntityRenderer(BlockEntityTypeInit.BANNER_REPEATER_SIGNAL, context -> new RenderBannerRepeaterSignal<>(0xFF29D28F));
        RegistryClient.registerBlockEntityRenderer(BlockEntityTypeInit.BARRIER_PLATFORM, context -> new RenderBarrierPlatform<>());
    }
}
