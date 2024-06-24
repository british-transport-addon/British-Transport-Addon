package org.eu.awesomekalin.jta.mod;

import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.eu.awesomekalin.jta.mod.init.BlockInit;
import org.eu.awesomekalin.jta.mod.render.SimpleStationOperatorSignRender;
import org.eu.awesomekalin.jta.mod.render.SimpleStationSignRender;
import org.mtr.mapping.holder.RenderLayer;
import org.mtr.mapping.holder.Style;
import org.mtr.mapping.registry.RegistryClient;

public final class InitClient {
    public static final RegistryClient REGISTRY_CLIENT = new RegistryClient(Init.REGISTRY);

    public static void init() {
        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.BRITISH_RAIL_STATION_SIGN_SIMPLE,
                dispatcher -> new SimpleStationSignRender<>(
                        dispatcher,
                        1F,
                        0.2F / 20,
                        -.5F,
                        0f,
                        0.65F,
                        0,
                        0xFF0F0F0F,
                        true,
                        Style.getDefaultFontIdMapped()
                ));
        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.UK_BRITISH_OPERATOR_STATION_SIGN_BASE,
                dispatcher -> new SimpleStationOperatorSignRender<>(
                        dispatcher,
                        1F,
                        0.2F / 20,
                        1.5F,
                        0f,
                        -0.5F,
                        0,
                        0xFFFFFFFF,
                        true,
                        Style.getDefaultFontIdMapped()
                ));
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.BRITISH_RAIL_STATION_SIGN_SIMPLE);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.RUSTY_POLE);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.RUSTY_POLE_ELECTRIC_SHOCK);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.RUSTY_POLE_DO_NOT_CROSS);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.RUSTY_POLE_STOP_LOOK_LISTEN);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.RUSTY_POLE_LEVEL_CROSSING);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.RUSTY_POLE_NO_TRESPASS);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.RUSTY_POLE_NO_PAVEMENT_PARKING);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.WALL_DO_NOT_CROSS);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.WALL_ELECTRIC_SHOCK);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.WALL_STOP_LOOK_LISTEN);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.WALL_LEVEL_CROSSING);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.WALL_NO_TRESPASS);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.WALL_NO_PAVEMENT_PARKING);

        REGISTRY_CLIENT.init();
    }
}
