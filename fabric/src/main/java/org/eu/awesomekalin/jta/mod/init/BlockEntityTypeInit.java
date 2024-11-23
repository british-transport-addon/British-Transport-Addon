package org.eu.awesomekalin.jta.mod.init;

import org.eu.awesomekalin.jta.mod.Init;
import org.eu.awesomekalin.jta.mod.blocks.BarrierPlatformBlock;
import org.eu.awesomekalin.jta.mod.blocks.directional.UndergroundWhiteboard;
import org.eu.awesomekalin.jta.mod.blocks.directional.bus.BeeBusStopSign;
import org.eu.awesomekalin.jta.mod.blocks.directional.bus.BeeStationSign;
import org.eu.awesomekalin.jta.mod.blocks.directional.bus.LondonBusStopSign;
import org.eu.awesomekalin.jta.mod.blocks.directional.rail.*;
import org.eu.awesomekalin.jta.mod.blocks.pids.*;
import org.eu.awesomekalin.jta.mod.blocks.directional.roundel.*;
import org.eu.awesomekalin.jta.mod.blocks.signal.BannerRepeaterSignal;
import org.eu.awesomekalin.jta.mod.blocks.signal.DigitalBritishSignal1Aspect;
import org.eu.awesomekalin.jta.mod.blocks.signal.DigitalBritishSignal2Aspect;
import org.eu.awesomekalin.jta.mod.entity.block.road.VerticalRoadBarrierBlockEntity;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.registry.BlockEntityTypeRegistryObject;

public class BlockEntityTypeInit {
    public static void INIT() {
        Init.LOGGER.debug("Registering JTA Block Entities");
    }

    public static final BlockEntityTypeRegistryObject<BarrierPlatformBlock.BarrierPlatformBlockEntity> BARRIER_PLATFORM = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "barrier_platform"
            ),
            BarrierPlatformBlock.BarrierPlatformBlockEntity::new,
            BlockInit.BARRIER_PLATFORM::get//update
    );

    public static final BlockEntityTypeRegistryObject<VerticalRoadBarrierBlockEntity> VERTICAL_ROAD_BARRIER = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "vertical_road_barrier"
            ),
            VerticalRoadBarrierBlockEntity::new,
            BlockInit.VERTICAL_ROAD_BARRIER::get//update
    );


    public static final BlockEntityTypeRegistryObject<BannerRepeaterSignal.BlockEntity> BANNER_REPEATER_SIGNAL = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "banner_repeater_signal"
            ),
            BannerRepeaterSignal.BlockEntity::new,
            BlockInit.BANNER_REPEATER_SIGNAL::get
    );

    public static final BlockEntityTypeRegistryObject<DigitalBritishSignal1Aspect.BlockEntity> DIGITAL_SIGNAL_LIGHT_1_ASPECT = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "digital_signal_light_1_aspect"
            ),
            DigitalBritishSignal1Aspect.BlockEntity::new,
            BlockInit.DIGITAL_SIGNAL_LIGHT_1_ASPECT::get
    );

    public static final BlockEntityTypeRegistryObject<DigitalBritishSignal2Aspect.BlockEntity> DIGITAL_SIGNAL_LIGHT_2_ASPECT = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "digital_signal_light_2_aspect"
            ),
            DigitalBritishSignal2Aspect.BlockEntity::new,
            BlockInit.DIGITAL_SIGNAL_LIGHT_2_ASPECT::get
    );

    public static final BlockEntityTypeRegistryObject<NationalRailSingleBoard.TileEntityNationalRailSingleBoard> PIDS_NATIONALRAILSINGLEBOARD = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "national_rail_single_board"
            ),
            NationalRailSingleBoard.TileEntityNationalRailSingleBoard::new,
            BlockInit.PIDS_NATIONALRAILSINGLEBOARD::get//update
    );
    public static final BlockEntityTypeRegistryObject<NationalRailSingleBoardWhite.TileEntityNationalRailSingleBoardWhite> PIDS_NATIONALRAILSINGLEBOAR_DWHITE = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "national_rail_single_board_white"
            ),
            NationalRailSingleBoardWhite.TileEntityNationalRailSingleBoardWhite::new,
            BlockInit.PIDS_NATIONALRAILSINGLEBOARD_WHITE::get//update
    );
    public static final BlockEntityTypeRegistryObject<NationalRail3Clock.TileEntityNationalRail3Clock> PIDS_NATIONALRAIL3CLOCK = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "national_rail_3_clock"
            ),
            NationalRail3Clock.TileEntityNationalRail3Clock::new,
            BlockInit.PIDS_NATIONALRAIL3CLOCK::get//update
    );
    public static final BlockEntityTypeRegistryObject<NationalRail3ClockWhite.TileEntityNationalRail3ClockWhite> PIDS_NATIONALRAIL3CLOCKWHITE = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "national_rail_3_clock_white"
            ),
            NationalRail3ClockWhite.TileEntityNationalRail3ClockWhite::new,
            BlockInit.PIDS_NATIONALRAIL3CLOCKWHITE::get//update
    );
    public static final BlockEntityTypeRegistryObject<NationalRail10Clock.TileEntityNationalRail10Clock> PIDS_NATIONALRAIL10CLOCK = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "national_rail_10_clock"
            ),
            NationalRail10Clock.TileEntityNationalRail10Clock::new,
            BlockInit.PIDS_NATIONALRAIL10CLOCK::get//update
    );
    public static final BlockEntityTypeRegistryObject<NationalRail10ClockWhite.TileEntityNationalRail10ClockWhite> PIDS_NATIONALRAIL10CLOCKWHITE = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "national_rail_10_clock_white"
            ),
            NationalRail10ClockWhite.TileEntityNationalRail10ClockWhite::new,
            BlockInit.PIDS_NATIONALRAIL10CLOCKWHITE::get//update
    );

    public static final BlockEntityTypeRegistryObject<RoundelBase.RoundelBaseBlockEntity> ROUNDEL = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "roundel"
            ),
            RoundelBase.RoundelBaseBlockEntity::new,
            RoundelInit.ROUNDEL_UNDERGROUND::get,
            RoundelInit.ROUNDEL_OVERGROUND::get,
            RoundelInit.ROUNDEL_BUSES::get,
            RoundelInit.ROUNDEL_RIVER::get,
            RoundelInit.ROUNDEL_DLR::get,
            RoundelInit.ROUNDEL_COACHES::get,
            RoundelInit.ROUNDEL_TRAMS::get,
            RoundelInit.ROUNDEL_PRIDE::get,
            RoundelInit.ROUNDEL_ELIZEBETH::get,
            RoundelInit.ROUNDEL_TFL::get,
            RoundelInit.ROUNDEL_NLE::get
    );
    public static final BlockEntityTypeRegistryObject<RoundelPoleBase.RoundelBaseBlockEntity> ROUNDEL_POLE = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "roundel_pole"
            ),
            RoundelPoleBase.RoundelBaseBlockEntity::new,
            RoundelInit.ROUNDEL_POLE_UNDERGROUND::get,
            RoundelInit.ROUNDEL_POLE_OVERGROUND::get,
            RoundelInit.ROUNDEL_POLE_BUSES::get,
            RoundelInit.ROUNDEL_POLE_RIVER::get,
            RoundelInit.ROUNDEL_POLE_DLR::get,
            RoundelInit.ROUNDEL_POLE_COACHES::get,
            RoundelInit.ROUNDEL_POLE_TRAMS::get,
            RoundelInit.ROUNDEL_POLE_PRIDE::get,
            RoundelInit.ROUNDEL_POLE_ELIZEBETH::get,
            RoundelInit.ROUNDEL_POLE_TFL::get,
            RoundelInit.ROUNDEL_POLE_NLE::get
    );
    public static final BlockEntityTypeRegistryObject<RoundelWallBase.RoundelBaseBlockEntity> ROUNDEL_WALL = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "roundel_wall"
            ),
            RoundelWallBase.RoundelBaseBlockEntity::new,
            RoundelInit.ROUNDEL_WALL_UNDERGROUND::get,
            RoundelInit.ROUNDEL_WALL_OVERGROUND::get,
            RoundelInit.ROUNDEL_WALL_BUSES::get,
            RoundelInit.ROUNDEL_WALL_RIVER::get,
            RoundelInit.ROUNDEL_WALL_DLR::get,
            RoundelInit.ROUNDEL_WALL_COACHES::get,
            RoundelInit.ROUNDEL_WALL_TRAMS::get,
            RoundelInit.ROUNDEL_WALL_PRIDE::get,
            RoundelInit.ROUNDEL_WALL_ELIZEBETH::get,
            RoundelInit.ROUNDEL_WALL_TFL::get,
            RoundelInit.ROUNDEL_WALL_NLE::get
    );
    public static final BlockEntityTypeRegistryObject<RoundelSignBase.RoundelBaseBlockEntity> ROUNDEL_SIGN = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "roundel_sign"
            ),
            RoundelSignBase.RoundelBaseBlockEntity::new,
            RoundelInit.ROUNDEL_SIMPLE_UNDERGROUND::get,
            RoundelInit.ROUNDEL_SIMPLE_OVERGROUND::get,
            RoundelInit.ROUNDEL_SIMPLE_BUSES::get,
            RoundelInit.ROUNDEL_SIMPLE_RIVER::get,
            RoundelInit.ROUNDEL_SIMPLE_DLR::get,
            RoundelInit.ROUNDEL_SIMPLE_COACHES::get,
            RoundelInit.ROUNDEL_SIMPLE_TRAMS::get,
            RoundelInit.ROUNDEL_SIMPLE_PRIDE::get,
            RoundelInit.ROUNDEL_SIMPLE_ELIZEBETH::get,
            RoundelInit.ROUNDEL_SIMPLE_TFL::get,
            RoundelInit.ROUNDEL_SIMPLE_NLE::get
    );
    public static final BlockEntityTypeRegistryObject<RoundelPlatformBase.RoundelBaseBlockEntity> ROUNDEL_PLATFORM = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "roundel_platform"
            ),
            RoundelPlatformBase.RoundelBaseBlockEntity::new,
            RoundelInit.ROUNDEL_PLATFORM_UNDERGROUND::get,
            RoundelInit.ROUNDEL_PLATFORM_OVERGROUND::get,
            RoundelInit.ROUNDEL_PLATFORM_BUSES::get,
            RoundelInit.ROUNDEL_PLATFORM_RIVER::get,
            RoundelInit.ROUNDEL_PLATFORM_DLR::get,
            RoundelInit.ROUNDEL_PLATFORM_COACHES::get,
            RoundelInit.ROUNDEL_PLATFORM_TRAMS::get,
            RoundelInit.ROUNDEL_PLATFORM_PRIDE::get,
            RoundelInit.ROUNDEL_PLATFORM_ELIZEBETH::get,
            RoundelInit.ROUNDEL_PLATFORM_TFL::get,
            RoundelInit.ROUNDEL_PLATFORM_NLE::get,
            RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_LEFT_UNDERGROUND::get,
            RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_LEFT_OVERGROUND::get,
            RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_LEFT_BUSES::get,
            RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_LEFT_RIVER::get,
            RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_LEFT_DLR::get,
            RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_LEFT_COACHES::get,
            RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_LEFT_TRAMS::get,
            RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_LEFT_PRIDE::get,
            RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_LEFT_ELIZEBETH::get,
            RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_LEFT_TFL::get,
            RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_LEFT_NLE::get,
            RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_RIGHT_UNDERGROUND::get,
            RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_RIGHT_OVERGROUND::get,
            RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_RIGHT_BUSES::get,
            RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_RIGHT_RIVER::get,
            RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_RIGHT_DLR::get,
            RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_RIGHT_COACHES::get,
            RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_RIGHT_TRAMS::get,
            RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_RIGHT_PRIDE::get,
            RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_RIGHT_ELIZEBETH::get,
            RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_RIGHT_TFL::get,
            RoundelInit.ROUNDEL_PLATFORM_WAY_OUT_RIGHT_NLE::get
    );
    public static final BlockEntityTypeRegistryObject<RoundelSquareBase.RoundelBaseBlockEntity> ROUNDEL_SQUARE = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "roundel_square"
            ),
            RoundelSquareBase.RoundelBaseBlockEntity::new,
            RoundelInit.ROUNDEL_SQUARE_UNDERGROUND::get,
            RoundelInit.ROUNDEL_SQUARE_OVERGROUND::get,
            RoundelInit.ROUNDEL_SQUARE_BUSES::get,
            RoundelInit.ROUNDEL_SQUARE_RIVER::get,
            RoundelInit.ROUNDEL_SQUARE_DLR::get,
            RoundelInit.ROUNDEL_SQUARE_COACHES::get,
            RoundelInit.ROUNDEL_SQUARE_TRAMS::get,
            RoundelInit.ROUNDEL_SQUARE_PRIDE::get,
            RoundelInit.ROUNDEL_SQUARE_ELIZEBETH::get,
            RoundelInit.ROUNDEL_SQUARE_TFL::get,
            RoundelInit.ROUNDEL_SQUARE_NLE::get
    );
    public static final BlockEntityTypeRegistryObject<RoundelSmallSignBase.RoundelBaseBlockEntity> ROUNDEL_SMALL = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "roundel_small"
            ),
            RoundelSmallSignBase.RoundelBaseBlockEntity::new,
            RoundelInit.ROUNDEL_SMALL_UNDERGROUND::get,
            RoundelInit.ROUNDEL_SMALL_OVERGROUND::get,
            RoundelInit.ROUNDEL_SMALL_BUSES::get,
            RoundelInit.ROUNDEL_SMALL_RIVER::get,
            RoundelInit.ROUNDEL_SMALL_DLR::get,
            RoundelInit.ROUNDEL_SMALL_COACHES::get,
            RoundelInit.ROUNDEL_SMALL_TRAMS::get,
            RoundelInit.ROUNDEL_SMALL_PRIDE::get,
            RoundelInit.ROUNDEL_SMALL_ELIZEBETH::get,
            RoundelInit.ROUNDEL_SMALL_TFL::get,
            RoundelInit.ROUNDEL_SMALL_NLE::get
    );
    public static final BlockEntityTypeRegistryObject<UndergroundWhiteboard.UndergroundWhiteboardBlockEntity> UNDERGROUND_WHITEBOARD = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "underground_whiteboard"
            ),
            UndergroundWhiteboard.UndergroundWhiteboardBlockEntity::new,
            BlockInit.UNDERGROUND_WHITEBOARD::get
    );

    public static final BlockEntityTypeRegistryObject<ProjectionName.ProjectionNameBlockEntity> PROJECTION_NAME = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "projection_name"
            ),
            ProjectionName.ProjectionNameBlockEntity::new,
            BlockInit.PROJECTION_NAME::get
    );

    public static final BlockEntityTypeRegistryObject<ManchesterSignSquare.TileEntityManchesterStationSign> MANCHESTER_SIGN_SQUARE = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "manchester_sign_square"
            ),
            ManchesterSignSquare.TileEntityManchesterStationSign::new,
            BlockInit.MANCHESTER_SIGN_SQUARE::get
    );

    public static final BlockEntityTypeRegistryObject<MerseysideSignSquare.TileEntityMerseysideStationSign> MERSEYSIDE_SIGN_SQUARE = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "merseyside_sign_square"
            ),
                MerseysideSignSquare.TileEntityMerseysideStationSign::new,
            BlockInit.MERSEYSIDE_SIGN_SQUARE_RAIL::get
    );
    public static final BlockEntityTypeRegistryObject<MetrolinkSign.MetrolinkSignEntity> METROLINK_SIGN_ENTITY = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "metrolink_sign"
            ),
            MetrolinkSign.MetrolinkSignEntity::new,
            BlockInit.METROLINK_SIGN::get,
            BlockInit.METROLINK_SIGN_BARE::get
    );
    public static final BlockEntityTypeRegistryObject<BeeBusStopSign.TileEntityBeeBusSign> BEE_BUS_STOP = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "bee_bus_sign"
            ),
            BeeBusStopSign.TileEntityBeeBusSign::new,
            BlockInit.BEE_BUS_STOP::get
    );
    public static final BlockEntityTypeRegistryObject<LondonBusStopSign.TileEntityLondonBusSign> LONDON_BUS_STOP = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "london_bus_sign"
            ),
            LondonBusStopSign.TileEntityLondonBusSign::new,
            BlockInit.LONDON_BUS_STOP::get
    );
    public static final BlockEntityTypeRegistryObject<BeeStationSign.TileEntityBeeStationSign> BEE_STATION_SIGN_ENTITY = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "bee_station_sign_entity"
            ),
            BeeStationSign.TileEntityBeeStationSign::new,
            BlockInit.BEE_BUS_STATION_SIGN::get,
            BlockInit.BEE_INTERCHANGE_STATION_SIGN::get
    );

    public static final BlockEntityTypeRegistryObject<DispatchSignal.TileEntityDispatchSignal> DISPATCH_SIGNAL = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "dispatch_signal"
            ),
            DispatchSignal.TileEntityDispatchSignal::new,
            BlockInit.DISPATCH_SIGNAL::get
    );

    public static final BlockEntityTypeRegistryObject<UKBritishRailStationSignSimple.TileEntityBritishRailStationSign> BRITISH_RAIL_STATION_SIGN_SIMPLE = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "uk_british_rail_station_sign_simple"
            ),
            UKBritishRailStationSignSimple.TileEntityBritishRailStationSign::new,
            BlockInit.BRITISH_RAIL_STATION_SIGN_SIMPLE::get
    );
    public static final BlockEntityTypeRegistryObject<UKBritishRailStationSignSimpleWall.TileEntityBritishRailStationSign> BRITISH_RAIL_STATION_SIGN_SIMPLE_WALL = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "uk_british_rail_station_sign_simple_wall"
            ),
            UKBritishRailStationSignSimpleWall.TileEntityBritishRailStationSign::new,
            BlockInit.BRITISH_RAIL_STATION_SIGN_SIMPLE_WALL::get
    );

    public static final BlockEntityTypeRegistryObject<PlatformSign.PlatformSignEntity> PLATFORM_SIGN = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "platform_sign"
            ),
            PlatformSign.PlatformSignEntity::new,
            SignInit.PLATFORM_SIGN_BEE_YELLOW::get,
            SignInit.PLATFORM_SIGN_BLACK::get,
            SignInit.PLATFORM_SIGN_BLUE::get,
            SignInit.PLATFORM_SIGN_DARK_BLUE::get,
            SignInit.PLATFORM_SIGN_DARK_GRAY::get,
            SignInit.PLATFORM_SIGN_YELLOW::get,
            SignInit.PLATFORM_SIGN_DARK_GREEN::get,
            SignInit.PLATFORM_SIGN_LIGHT_BLUE::get,
            SignInit.PLATFORM_SIGN_PINK::get,
            SignInit.PLATFORM_SIGN_GRAY::get,
            SignInit.PLATFORM_SIGN_ORANGE::get,
            SignInit.PLATFORM_SIGN_RUSTY::get,
            SignInit.PLATFORM_SIGN_STEEL::get,
            SignInit.PLATFORM_SIGN_RED::get,
            SignInit.PLATFORM_SIGN_ROYAL_RED::get,
            SignInit.PLATFORM_SIGN_GREEN::get,
            SignInit.PLATFORM_SIGN_PURPLE::get
    );

    public static final BlockEntityTypeRegistryObject<NSERoofClock.TileEntityNSERoofClock> NSE_CLOCK = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "nse_clock"
            ),
            NSERoofClock.TileEntityNSERoofClock::new,
            BlockInit.RED_NSE_ROOF_CLOCK::get,
            BlockInit.BLUE_NSE_ROOF_CLOCK::get,
            BlockInit.STEEL_NSE_ROOF_CLOCK::get,
            BlockInit.YELLOW_NSE_ROOF_CLOCK::get
    );
}
