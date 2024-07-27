package org.eu.awesomekalin.jta.mod;

import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.eu.awesomekalin.jta.mod.init.BlockInit;
import org.eu.awesomekalin.jta.mod.render.*;
import org.eu.awesomekalin.jta.mod.screen.FirstLoadScreen;
import org.mtr.mapping.holder.RenderLayer;
import org.mtr.mapping.holder.Style;
import org.mtr.mapping.registry.RegistryClient;

public final class InitClient {
    public static final RegistryClient REGISTRY_CLIENT = new RegistryClient(Init.REGISTRY);

    public static void init() {
        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.MANCHESTER_SIGN_SQUARE,
                dispatcher -> new ManchesterSignSquareRender<>(
                        dispatcher,
                        14 / 16F,
                        0.2F / 8,
                        0,
                        0F,
                        .5F,
                        0,
                        0xFFFFFFFF,
                        Style.getDefaultFontIdMapped()
                ));
        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.MERSEYSIDE_SIGN_SQUARE,
                dispatcher -> new MerseysideSignSquareRender<>(
                        dispatcher,
                        14 / 16F,
                        0.2F / 8,
                        0,
                        -.25F,
                        .5F,
                        0,
                        0xFFFFFFFF,
                        Style.getDefaultFontIdMapped()
                ));
        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.BEE_BUS_STOP,
                dispatcher -> new BeeBusStopSignRender<>(
                        dispatcher,
                        8 / 16F,
                        0.2F / 8,
                        -.025f,
                        -0.05f,
                        .005F,
                        0,
                        0xFF0F0F0F,
                        Style.getDefaultFontIdMapped()
                ));
        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.LONDON_BUS_STOP,
                dispatcher -> new LondonBusStopSignRender<>(
                        dispatcher,
                        8 / 16F,
                        0.2F / 8,
                        -.025f,
                        -0.05f,
                        .005F,
                        0,
                        0xFF0F0F0F,
                        Style.getDefaultFontIdMapped()
                ));
        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.METROLINK_SIGN_ENTITY,
                dispatcher -> new MetrolinkSignRender<>(
                        dispatcher,
                        16 / 16F,
                        0.2F / 8,
                        .8f,
                        1.325f,
                        .08F,
                        0,
                        0xFF0F0F0F,
                        true,
                        Style.getDefaultFontIdMapped()
                ));
        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.BEE_STATION_SIGN_ENTITY,
                dispatcher -> new BeeStationRender<>(
                        dispatcher,
                        8 / 16F,
                        0.2F / 8,
                        0,
                        -.1f,
                        .1F,
                        0,
                        0xFF0F0F0F,
                        true,
                        Style.getDefaultFontIdMapped()
                ));
        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.DISPATCH_SIGNAL,
                dispatcher -> new DispatchSignalRender<>(
                        dispatcher,
                        32 / 16F,
                        0.2F / 6,
                        0,
                        .05F,
                        -0.10F,
                        0,
                        0xFFFFFFFF,
                        Style.getDefaultFontIdMapped()
                ));
        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.PEDESTRIAN_SIGNAL,
                dispatcher -> new PedestrianSignalRender<>(
                        dispatcher,
                        false,
                        0x90EE90
                ));
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
                BlockEntityTypeInit.UK_BRITISH_OPERATOR_STATION_SIGN_BASE_BLUE,
                dispatcher -> new SimpleStationOperatorSignRenderBlue<>(
                        dispatcher,
                        32 / 16F,
                        0.2F / 8,
                        0,
                        1F,
                        .05F,
                        0,
                        0x132E6A,
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
                        .05F,
                        0,
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
                        .05F,
                        0,
                        0,
                        0xFFFFFFFF,
                        true,
                        Style.getDefaultFontIdMapped()
                ));
        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.RUSTY_UK_BRITISH_OPERATOR_STATION_SIGN_BASE_BLUE,
                dispatcher -> new SimpleStationOperatorSignRustyRenderBlue<>(
                        dispatcher,
                        32 / 16F,
                        0.2F / 8,
                        0,
                        .05F,
                        0,
                        0,
                        0x132E6A,
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
                        -.4F,
                        0,
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
                        -.4F,
                        0,
                        0,
                        0xFFFFFFFF,
                        Style.getDefaultFontIdMapped()
                ));
        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.WALL_UK_BRITISH_OPERATOR_STATION_SIGN_BASE_BLUE,
                dispatcher -> new SimpleStationOperatorSignWallRenderBlue<>(
                        dispatcher,
                        32 / 16F,
                        0.2F / 8,
                        0,
                        -.4F,
                        0,
                        0,
                        0x132E6A,
                        Style.getDefaultFontIdMapped()
                ));

        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.NSE_CLOCK,
                dispatcher -> new NSERoofClockRender<>(
                        dispatcher,
                        20 / 16F,
                        0.2F / 8,
                        0,
                        -.005f,
                        .2F,
                        0,
                        0xBED058,
                        true,
                        Style.getDefaultFontIdMapped()
                ));

        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.MERSEYSIDE_SIGN_SQUARE_RAIL);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.DRAIN);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.DRAIN_ALT);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.DRAIN_CIRCLE);

        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.AMAZON_PARCEL_COLLECTION_LOCKER);

        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.ADT_ALARM);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.WALL_DEFIBRILATOR);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.WHITE_GARAGE_DOOR_WINDOW);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.BLACK_GARAGE_DOOR);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.STEEL_GARAGE_DOOR_WINDOW);

        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.WHITE_WINDOW);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.WHITE_WINDOW_FULL);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.WHITE_WINDOW_SQUARE);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.WHITE_WINDOW_TOP);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.WHITE_WINDOW_SEGMENTED);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.WHITE_WINDOW_SEGMENTED_TOP);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.CREAMY_ENGLISH_BRICKS_WHITE_WINDOW_4_POINT_EXTRU);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.DARK_ENGLISH_BRICKS_WHITE_WINDOW_4_POINT_EXTRU);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.ENGLISH_BRICKS_WHITE_WINDOW_4_POINT_EXTRU);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.LARGE_DARK_GRAY_BRICKS_WHITE_WINDOW_4_POINT_EXTRU);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.LARGE_SLATE_BRICKS_WHITE_WINDOW_4_POINT_EXTRU);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.SANDY_BRICKS_WHITE_WINDOW_4_POINT_EXTRU);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.SILTY_BRICKS_WHITE_WINDOW_4_POINT_EXTRU);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.LIME_BRICKS_WHITE_WINDOW_4_POINT_EXTRU);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.SMOOTH_MIXED_STONE_WHITE_WINDOW_4_POINT_EXTRU);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.STONE_PAVER_WHITE_WINDOW_4_POINT_EXTRU);

        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.BLACK_BUS_SHELTER_LEFT_RED);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.BLACK_BUS_SHELTER_LEFT_LONG_RED);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.BLACK_BUS_SHELTER_LEFT_YELLOW);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.BLACK_BUS_SHELTER_LEFT_LONG_YELLOW);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.BLACK_BUS_SHELTER_RIGHT_RED);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.BLACK_BUS_SHELTER_RIGHT_WITH_ADVERT_RED);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.BLACK_BUS_SHELTER_RIGHT_WITH_ADVERT_YELLOW);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.BLACK_BUS_SHELTER_RIGHT_YELLOW);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.BLACK_BUS_SHELTER_RIGHT_WITH_ADVERT_OPEN_RED);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.BLACK_BUS_SHELTER_RIGHT_WITH_ADVERT_OPEN_YELLOW);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.GRAY_BUS_SHELTER_LEFT_RED);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.GRAY_BUS_SHELTER_LEFT_LONG_RED);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.GRAY_BUS_SHELTER_LEFT_YELLOW);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.GRAY_BUS_SHELTER_LEFT_LONG_YELLOW);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.GRAY_BUS_SHELTER_RIGHT_RED);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.GRAY_BUS_SHELTER_RIGHT_WITH_ADVERT_RED);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.GRAY_BUS_SHELTER_RIGHT_WITH_ADVERT_YELLOW);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.GRAY_BUS_SHELTER_RIGHT_YELLOW);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.GRAY_BUS_SHELTER_RIGHT_WITH_ADVERT_OPEN_RED);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.GRAY_BUS_SHELTER_RIGHT_WITH_ADVERT_OPEN_YELLOW);

        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.STREET_LIGHT);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.STREET_LIGHT_OFF);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.STREET_LIGHT_FLICKER);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.LED_STREET_LIGHT);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.LED_STREET_LIGHT_OFF);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.LED_STREET_LIGHT_FLICKER);

        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.METROLINK_SIGN);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.BEE_BUS_STOP);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.BEE_BUS_STATION_SIGN);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.BEE_INTERCHANGE_STATION_SIGN);

        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.BRITISH_ROAD_BOLLARD);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.BRITISH_ROAD_BOLLARD_ALT);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.GRAY_BOLLARD);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.BRITISH_ROAD_BOLLARD_ALT_NO_ENTRY);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.BRITISH_ROAD_BOLLARD_ALT_BLACK);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.BLUE_TRASH_BIN);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.BRITISH_TRASH_BIN);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.MANCHESTER_TRASH_BIN);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.MANCHESTER_TRASH_BIN_ALT);

        // Coloured blocks
        // nvmn im slow
        // right so we wardrobve later.
        // for now we gotta like
        // can we make itso. Instead of the regster ike registering reach block the way it is.
        // can it just scan what models exist in the generated and make the items that way?
        // then its less code in the idea. maybe
        // or even just scan the blockstates..
        // thats proababyl a better idea yeah
        // then the script deosnt need to touich the java code.if it just rregisters them all as a list righjt.
        // then it can do tihng?>
        // yes in tuis ckas akk u tas ti do.,
        // yes in this class all it has to do
        // do loop thorugh erach block from
        // brr like that yes? def
        // then our init classes are not like 2k lines long by the time we have all the signs actually inb :(
        // so we make new method and make blockinit registered signs just haev liek
        // a list we do rn
        BlockInit.REGISTERED_SIGNS.forEach(sign -> {
            REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), sign);
        });

        REGISTRY_CLIENT.registerBlockColors((blockState, blockRenderView, blockPos, tintIndex) -> org.mtr.mod.InitClient.getStationColor(blockPos),
                BlockInit.STREET_POLE_BASE_COLORED,
                BlockInit.STREET_POLE_COLORED,
                BlockInit.TINY_POLE_COLORED,
                BlockInit.POLE_COLORED
        );

        REGISTRY_CLIENT.eventRegistryClient.registerStartClientTick(FirstLoadScreen::handle);

        REGISTRY_CLIENT.init();
    }
}
