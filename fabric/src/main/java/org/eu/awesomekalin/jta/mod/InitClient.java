package org.eu.awesomekalin.jta.mod;

import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.eu.awesomekalin.jta.mod.init.BlockInit;
import org.eu.awesomekalin.jta.mod.render.*;
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
                        14 / 16F,
                        0.2F / 8,
                        0,
                        -.4F,
                        0.2F,
                        0,
                        0xFF0F0F0F,
                        true,
                        Style.getDefaultFontIdMapped()
                ));
        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.BRITISH_RAIL_STATION_SIGN_SIMPLE_WALL,
                dispatcher -> new SimpleStationSignRenderWall<>(
                        dispatcher,
                        14 / 16F,
                        0.2F / 8,
                        0,
                        -.3F,
                        -.1F,
                        0,
                        0xFF0F0F0F,
                        Style.getDefaultFontIdMapped()
                ));
        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.UK_BRITISH_OPERATOR_STATION_SIGN_BASE,
                dispatcher -> new SimpleStationOperatorSignRender<>(
                        dispatcher,
                        32 / 16F,
                        0.2F / 8,
                        0,
                        1F,
                        .05F,
                        0,
                        0xFF0F0F0F,
                        true,
                        Style.getDefaultFontIdMapped()
                ));
        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.UK_BRITISH_OPERATOR_STATION_SIGN_BASE_WHITE,
                dispatcher -> new SimpleStationOperatorSignRenderWhite<>(
                        dispatcher,
                        32 / 16F,
                        0.2F / 8,
                        0,
                        1F,
                        .05F,
                        0,
                        0xFFFFFFFF,
                        true,
                        Style.getDefaultFontIdMapped()
                ));
        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.RUSTY_UK_BRITISH_OPERATOR_STATION_SIGN_BASE,
                dispatcher -> new SimpleStationOperatorSignRustyRender<>(
                        dispatcher,
                        32 / 16F,
                        0.2F / 8,
                        0,
                        0,
                        .05F,
                        0,
                        0xFF0F0F0F,
                        true,
                        Style.getDefaultFontIdMapped()
                ));
        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.RUSTY_UK_BRITISH_OPERATOR_STATION_SIGN_BASE_WHITE,
                dispatcher -> new SimpleStationOperatorSignRustyRenderWhite<>(
                        dispatcher,
                        32 / 16F,
                        0.2F / 8,
                        0,
                        0,
                        .05F,
                        0,
                        0xFFFFFFFF,
                        true,
                        Style.getDefaultFontIdMapped()
                ));
        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.WALL_UK_BRITISH_OPERATOR_STATION_SIGN_BASE,
                dispatcher -> new SimpleStationOperatorSignWallRender<>(
                        dispatcher,
                        32 / 16F,
                        0.2F / 8,
                        0,
                        0,
                        -.4F,
                        0,
                        0xFF0F0F0F,
                        Style.getDefaultFontIdMapped()
                ));
        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.WALL_UK_BRITISH_OPERATOR_STATION_SIGN_BASE_WHITE,
                dispatcher -> new SimpleStationOperatorSignWallRenderWhite<>(
                        dispatcher,
                        32 / 16F,
                        0.2F / 8,
                        0,
                        0,
                        -.4F,
                        0,
                        0xFFFFFFFF,
                        Style.getDefaultFontIdMapped()
                ));

        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.RUSTY_POLE_SPEED_5);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.RUSTY_POLE_SPEED_10);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.RUSTY_POLE_SPEED_15);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.RUSTY_POLE_SPEED_20);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.RUSTY_POLE_SPEED_30);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.RUSTY_POLE_SPEED_40);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.RUSTY_POLE_SPEED_50);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.RUSTY_POLE_SPEED_60);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.RUSTY_POLE_NO_ENTRY);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.RUSTY_POLE_STOP);
        REGISTRY_CLIENT.init();
    }
}
