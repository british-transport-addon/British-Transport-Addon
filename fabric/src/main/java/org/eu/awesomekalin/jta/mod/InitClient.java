package org.eu.awesomekalin.jta.mod;

import org.eu.awesomekalin.jta.mod.init.*;
import org.eu.awesomekalin.jta.mod.render.bus.BeeBusStopSignRender;
import org.eu.awesomekalin.jta.mod.render.bus.BeeStationRender;
import org.eu.awesomekalin.jta.mod.render.bus.LondonBusStopSignRender;
import org.eu.awesomekalin.jta.mod.render.rail.*;
import org.eu.awesomekalin.jta.mod.render.rail.pids.*;
import org.eu.awesomekalin.jta.mod.render.roundel.*;
import org.eu.awesomekalin.jta.mod.render.street.*;
import org.eu.awesomekalin.jta.mod.render.street.fuel.*;
import org.eu.awesomekalin.jta.mod.screen.FirstLoadScreen;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.holder.RenderLayer;
import org.mtr.mapping.holder.Style;
import org.mtr.mapping.registry.RegistryClient;

public final class InitClient {
    public static final RegistryClient REGISTRY_CLIENT = new RegistryClient(Init.REGISTRY);

    public static void init() {
        REGISTRY_CLIENT.setupPackets(new Identifier(Init.MOD_ID, "packet"));

        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.PIDS_NATIONALRAIL3CLOCK,
                dispatcher -> new RenderNationalRail3Clock(
                        dispatcher,
                        20 / 16F,
                        0.2F / 10,
                        .5f,
                        -.5f,
                        .155F,
                        0,
                        0xFF9900
                )
        );

        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.PIDS_NATIONALRAILSINGLEBOARD,
                RenderNationalRailSingleBoard::new
        );
        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.PIDS_NATIONALRAILSINGLEBOAR_DWHITE,
                RenderNationalRailSingleBoardWhite::new
        );
        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.PIDS_NATIONALRAIL3CLOCKWHITE,
                dispatcher -> new RenderNationalRail3ClockWhite(
                        dispatcher,
                        20 / 16F,
                        0.2F / 10,
                        .5f,
                        -.5f,
                        .155F,
                        0,
                        0xFFFFFF
                )
        );
        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.PIDS_NATIONALRAIL10CLOCK,
                dispatcher -> new RenderNationalRail10Clock(
                        dispatcher,
                        ((20 / 16F) / 4) * 3,
                        ((0.2F / 8) / 4) * 3,
                        .25f,
                        -1.2f,
                        .155F,
                        0,
                        0xFF9900
                )
        );
        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.PIDS_NATIONALRAIL10CLOCKWHITE,
                dispatcher -> new RenderNationalRail10ClockWhite(
                        dispatcher,
                        ((20 / 16F) / 4) * 3,
                        ((0.2F / 8) / 4) * 3,
                        .25f,
                        -1.2f,
                        .155F,
                        0,
                        0xFFFFFF
                )
        );


        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.PROJECTION_NAME,
                dispatcher -> new ProjectionNameRender<>(
                        dispatcher,
                        12 / 16F,
                        0.2F / 8,
                        0F,
                        0F,
                        -.5F,
                        0,
                        0xFFFFFFFF,
                        Style.getDefaultFontIdMapped()
                )
        );

        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.UNDERGROUND_WHITEBOARD,
                dispatcher -> new UndergroundWhiteboardRender<>(
                        dispatcher,
                        12 / 16F,
                        0.2F / 8,
                        -.1F,
                        1.1F,
                        .0275F,
                        0,
                        0xFF0F0F0F,
                        Style.getDefaultFontIdMapped()
                )
        );

        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.ROUNDEL,
                dispatcher -> new RoundelBaseRender<>(
                        dispatcher,
                        20 / 16F,
                        0.2F / 8,
                        0,
                        .125F,
                        .325F,
                        0,
                        0xFFFFFFF,
                        Style.getDefaultFontIdMapped()
                )
        );

        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.ROUNDEL_POLE,
                dispatcher -> new RoundelPoleBaseRender<>(
                        dispatcher,
                        20 / 16F,
                        0.2F / 8,
                        0,
                        .125f,
                        -.625F,
                        0,
                        0xFFFFFFF,
                        Style.getDefaultFontIdMapped()
                )
        );

        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.ROUNDEL_WALL,
                dispatcher -> new RoundelWallBaseRender<>(
                        dispatcher,
                        20 / 16F,
                        0.2F / 8,
                        0,
                        .125f,
                        -.35F,
                        0,
                        0xFFFFFFF,
                        Style.getDefaultFontIdMapped()
                )
        );

        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.ROUNDEL_PLATFORM,
                dispatcher -> new RoundelPlatformBaseRender<>(
                        dispatcher,
                        32 / 16F,
                        0.2F / 8,
                        0,
                        -.1f,
                        -.85F,
                        0,
                        0xFF0F0F0F,
                        Style.getDefaultFontIdMapped()
                )
        );

        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.ROUNDEL_SQUARE,
                dispatcher -> new RoundelSquareBaseRender<>(
                        dispatcher,
                        10 / 16F,
                        0.2F / 8,
                        0,
                        .05f,
                        .525F,
                        0,
                        0xFFFFFFFF,
                        Style.getDefaultFontIdMapped()
                )
        );

        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.ROUNDEL_SIGN,
                dispatcher -> new RoundelSignBaseRender<>(
                        dispatcher,
                        16 / 16F,
                        0.2F / 8,
                        0,
                        0,
                        -.81F,
                        0,
                        0xFFFFFFF,
                        Style.getDefaultFontIdMapped()
                )
        );

        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.ROUNDEL_SMALL,
                dispatcher -> new RoundelSmallSignBaseRender<>(
                        dispatcher,
                        8 / 8F,
                        0.2F / 16,
                        0,
                        0,
                        -.81F,
                        0,
                        0xFFFFFFF,
                        Style.getDefaultFontIdMapped()
                )
        );

        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.PLATFORM_NUMBER_SIGN,
                dispatcher -> new PlatformNumberSignRender<>(
                        dispatcher,
                        8 / 16F,
                        0.2F / 2,
                        -.3F,
                        .05F,
                        .075F,
                        0,
                        0xFF0F0F0F,
                        Style.getDefaultFontIdMapped()
                )
        );

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
                BlockEntityTypeInit.PLATFORM_SIGN,
                dispatcher -> new PlatformSignRender<>(
                        dispatcher,
                        32 / 16F,
                        0.2F / 8,
                        0,
                        0,
                        -.85F,
                        0,
                        0xFF0F0F0F,
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

        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.ASDA_FUEL_SIGN_TOP,
                dispatcher -> new AsdaFuelStationPriceBoardTopRender<>(
                        dispatcher,
                        12 / 16F,
                        (0.2F / 8) * 1.4F,
                        -.4F,
                        -.65f,
                        .16F,
                        0,
                        0xFFFFFFFF,
                        Style.getDefaultFontIdMapped()
                ));

        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.SAINSBURYS_FUEL_SIGN_TOP,
                dispatcher -> new SainsburysFuelStationPriceBoardTopRender<>(
                        dispatcher,
                        12 / 16F,
                        (0.2F / 8) * 1.4F,
                        -.4F,
                        -.65f,
                        .16F,
                        0,
                        0xFFFFFFFF,
                        Style.getDefaultFontIdMapped()
                ));


        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.MORRISONS_FUEL_SIGN_TOP,
                dispatcher -> new MorrisonsFuelStationPriceBoardTopRender<>(
                        dispatcher,
                        12 / 16F,
                        (0.2F / 8) * 1.4F,
                        .4F,
                        -.65f,
                        .16F,
                        0,
                        0xFF008000,
                        Style.getDefaultFontIdMapped()
                ));

        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.TESCO_FUEL_SIGN_TOP,
                dispatcher -> new TescoFuelStationPriceBoardTopRender<>(
                        dispatcher,
                        12 / 16F,
                        (0.2F / 8) * 1.4F,
                        .4F,
                        -.65f,
                        .16F,
                        0,
                        0xB33C2D,
                        Style.getDefaultFontIdMapped()
                ));

        REGISTRY_CLIENT.registerBlockEntityRenderer(
                BlockEntityTypeInit.SHELL_FUEL_SIGN_TOP,
                dispatcher -> new ShellFuelStationPriceBoardTopRender<>(
                        dispatcher,
                        12 / 16F,
                        (0.2F / 8) * 1.4F,
                        -.4F,
                        -.65f,
                        .16F,
                        0,
                        0xB33C2D,
                        Style.getDefaultFontIdMapped()
                ));

        REGISTRY_CLIENT.registerBlockEntityRenderer(BlockEntityTypeInit.BP_PETROL_ROOF, BPPetrolRoofRender::new);
        //REGISTRY_CLIENT.registerBlockEntityRenderer(BlockEntityTypeInit.BP_PETROL_ROOF_CORNER, BPPetrolRoofCornerRender::new);

        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.MORRISONS_FUEL_SIGN_TOP);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.MORRISONS_FUEL_SIGN_BOTTOM);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.ASDA_FUEL_SIGN_TOP);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.ASDA_FUEL_SIGN_BOTTOM);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.SHELL_FUEL_SIGN_TOP);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.SHELL_FUEL_SIGN_BOTTOM);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.TESCO_FUEL_SIGN_TOP);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.TESCO_FUEL_SIGN_BOTTOM);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.SAINSBURYS_FUEL_SIGN_TOP);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.SAINSBURYS_FUEL_SIGN_BOTTOM);

        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.MORRISONS_PETROL_ROOF_CORNER);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.SAINSBURYS_PETROL_ROOF_LOGO);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.ESSO_PETROL_ROOF_CORNER);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.ASDA_PETROL_ROOF_CORNER_LOGO);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.ASDA_PETROL_ROOF_CORNER_INFO);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.TESCO_PETROL_ROOF_LOGO);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.TESCO_PETROL_ROOF_LOGO_EXTRA);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.TESCO_PETROL_ROOF_LOGO_EXPRESS);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.BP_PETROL_ROOF_LOGO);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.SHELL_PETROL_ROOF_LOGO);


        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_BUSES);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_COACHES);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_TRAMS);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_UNDERGROUND);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_OVERGROUND);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_TFL);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_ELIZEBETH);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_NLE);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PRIDE);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_DLR);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_RIVER);

        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_SMALL_BUSES);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_SMALL_COACHES);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_SMALL_TRAMS);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_SMALL_UNDERGROUND);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_SMALL_OVERGROUND);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_SMALL_TFL);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_SMALL_ELIZEBETH);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_SMALL_NLE);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_SMALL_PRIDE);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_SMALL_DLR);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_SMALL_RIVER);

        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_SIMPLE_BUSES);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_SIMPLE_COACHES);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_SIMPLE_TRAMS);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_SIMPLE_UNDERGROUND);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_SIMPLE_OVERGROUND);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_SIMPLE_TFL);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_SIMPLE_ELIZEBETH);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_SIMPLE_NLE);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_SIMPLE_PRIDE);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_SIMPLE_DLR);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_SIMPLE_RIVER);

        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PLATFORM_BUSES);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PLATFORM_COACHES);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PLATFORM_TRAMS);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PLATFORM_UNDERGROUND);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PLATFORM_OVERGROUND);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PLATFORM_TFL);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PLATFORM_ELIZEBETH);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PLATFORM_NLE);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PLATFORM_PRIDE);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PLATFORM_DLR);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PLATFORM_RIVER);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_LEFT_BUSES);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_LEFT_COACHES);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_LEFT_TRAMS);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_LEFT_UNDERGROUND);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_LEFT_OVERGROUND);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_LEFT_TFL);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_LEFT_ELIZEBETH);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_LEFT_NLE);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_LEFT_PRIDE);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_LEFT_DLR);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_LEFT_RIVER);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_RIGHT_BUSES);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_RIGHT_COACHES);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_RIGHT_TRAMS);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_RIGHT_UNDERGROUND);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_RIGHT_OVERGROUND);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_RIGHT_TFL);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_RIGHT_ELIZEBETH);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_RIGHT_NLE);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_RIGHT_PRIDE);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_RIGHT_DLR);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_RIGHT_RIVER);

        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_SQUARE_BUSES);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_SQUARE_COACHES);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_SQUARE_TRAMS);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_SQUARE_UNDERGROUND);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_SQUARE_OVERGROUND);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_SQUARE_TFL);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_SQUARE_ELIZEBETH);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_SQUARE_NLE);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_SQUARE_PRIDE);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_SQUARE_DLR);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_SQUARE_RIVER);

        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_POLE_BUSES);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_POLE_COACHES);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_POLE_TRAMS);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_POLE_UNDERGROUND);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_POLE_OVERGROUND);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_POLE_TFL);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_POLE_ELIZEBETH);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_POLE_NLE);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_POLE_PRIDE);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_POLE_DLR);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_POLE_RIVER);

        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_WALL_BUSES);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_WALL_COACHES);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_WALL_TRAMS);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_WALL_UNDERGROUND);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_WALL_OVERGROUND);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_WALL_TFL);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_WALL_ELIZEBETH);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_WALL_NLE);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_WALL_PRIDE);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_WALL_DLR);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), RoundelInit.ROUNDEL_WALL_RIVER);

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
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.BLACK_BRICKS_WHITE_WINDOW_4_POINT_EXTRU);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.WHITE_BRICKS_WHITE_WINDOW_4_POINT_EXTRU);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.SMOOTH_MIXED_STONE_WHITE_WINDOW_4_POINT_EXTRU);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.STONE_PAVER_WHITE_WINDOW_4_POINT_EXTRU);



        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.STREET_LIGHT);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.STREET_LIGHT_OFF);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.STREET_LIGHT_FLICKER);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.LED_STREET_LIGHT);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.LED_STREET_LIGHT_OFF);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), BlockInit.LED_STREET_LIGHT_FLICKER);

        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.UNDERGROUND_SQUARE);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.OVERGROUND_SQUARE);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.METROLINK_SIGN);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.BEE_BUS_STOP);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.BEE_BUS_STATION_SIGN);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.BEE_INTERCHANGE_STATION_SIGN);

        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.UNDERGROUND_PLATFORM_END);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.BRITISH_ROAD_BOLLARD);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.BRITISH_ROAD_BOLLARD_ALT);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.GRAY_BOLLARD);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.BRITISH_ROAD_BOLLARD_ALT_NO_ENTRY);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.BRITISH_ROAD_BOLLARD_ALT_BLACK);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.BLUE_TRASH_BIN);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.BRITISH_TRASH_BIN);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.MANCHESTER_TRASH_BIN);
        REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.MANCHESTER_TRASH_BIN_ALT);

        REGISTRY_CLIENT.eventRegistryClient.registerStartClientTick(FirstLoadScreen::handle);

        REGISTRY_CLIENT.init();
    }

    private static RegistryClient.ModelPredicateProvider checkItemPredicateTag() {
        return (itemStack, clientWorld, entity) -> entity != null && entity.isUsingItem() && entity.getStackInHand(entity.getActiveHand()).equals(itemStack) ? 1.0F : 0.0F;
    }
}
