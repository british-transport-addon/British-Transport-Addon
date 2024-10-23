package org.eu.awesomekalin.jta.init;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import org.eu.awesomekalin.jta.mod.InitClient;
import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.eu.awesomekalin.jta.platform.render.road.VerticalRoadBarrierRender;
import org.eu.awesomekalin.jta.platform.render.street.ManchesterTrashBinAltLidRender;

public class MainFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.register(BlockEntityTypeInit.VERTICAL_ROAD_BARRIER.get().data, VerticalRoadBarrierRender::new);
        BlockEntityRendererRegistry.register(BlockEntityTypeInit.MANCHESTER_TRASH_BIN_ALT_LID.get().data, ManchesterTrashBinAltLidRender::new);
//Why is this not in the InitClient class?
        // it uses the fabric api directly coz its uses the geckolib shi so its kinda gae and iddnt work prooperly in initclient so
        // should be two methods for forge and fabric i think
        // TODO: come back and fix this shit up.

        InitClient.init();
    }
}
