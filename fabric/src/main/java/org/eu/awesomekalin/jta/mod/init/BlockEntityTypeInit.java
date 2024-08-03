package org.eu.awesomekalin.jta.mod.init;

import org.eu.awesomekalin.jta.mod.Init;
import org.eu.awesomekalin.jta.mod.blocks.directional.UndergroundWhiteboard;
import org.eu.awesomekalin.jta.mod.blocks.directional.bus.BeeBusStopSign;
import org.eu.awesomekalin.jta.mod.blocks.directional.bus.BeeStationSign;
import org.eu.awesomekalin.jta.mod.blocks.directional.bus.LondonBusStopSign;
import org.eu.awesomekalin.jta.mod.blocks.directional.rail.*;
import org.eu.awesomekalin.jta.mod.blocks.directional.street.PedestrianSignal;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.registry.BlockEntityTypeRegistryObject;

public class BlockEntityTypeInit {
    public static void INIT() {
        Init.LOGGER.debug("Registering JTA Block Entities");
    }

    public static final BlockEntityTypeRegistryObject<UndergroundWhiteboard.UndergroundWhiteboardBlockEntity> UNDERGROUND_WHITEBOARD = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "underground_whiteboard"
            ),
            UndergroundWhiteboard.UndergroundWhiteboardBlockEntity::new,
            BlockInit.UNDERGROUND_WHITEBOARD::get
    );

    public static final BlockEntityTypeRegistryObject<PlatformNumberSign.PlatfornNumberSignEntity> PLATFORM_NUMBER_SIGN = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "platform_number_sign"
            ),
            PlatformNumberSign.PlatfornNumberSignEntity::new,
            SignInit.PLATFORM_NUMBER_SIDE_BEE_YELLOW::get,
            SignInit.PLATFORM_NUMBER_SIDE_BLACK::get,
            SignInit.PLATFORM_NUMBER_SIDE_BLUE::get,
            SignInit.PLATFORM_NUMBER_SIDE_DARK_BLUE::get,
            SignInit.PLATFORM_NUMBER_SIDE_DARK_GRAY::get,
            SignInit.PLATFORM_NUMBER_SIDE_YELLOW::get,
            SignInit.PLATFORM_NUMBER_SIDE_DARK_GREEN::get,
            SignInit.PLATFORM_NUMBER_SIDE_LIGHT_BLUE::get,
            SignInit.PLATFORM_NUMBER_SIDE_PINK::get,
            SignInit.PLATFORM_NUMBER_SIDE_GRAY::get,
            SignInit.PLATFORM_NUMBER_SIDE_ORANGE::get,
            SignInit.PLATFORM_NUMBER_SIDE_RUSTY::get,
            SignInit.PLATFORM_NUMBER_SIDE_STEEL::get,
            SignInit.PLATFORM_NUMBER_SIDE_RED::get,
            SignInit.PLATFORM_NUMBER_SIDE_ROYAL_RED::get,
            SignInit.PLATFORM_NUMBER_SIDE_GREEN::get,
            SignInit.PLATFORM_NUMBER_SIDE_PURPLE::get
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

    public static final BlockEntityTypeRegistryObject<PedestrianSignal.TileEntityPedestrianSignal> PEDESTRIAN_SIGNAL = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "pedestrian_signal"
            ),
            PedestrianSignal.TileEntityPedestrianSignal::new,
            BlockInit.PEDESTRIAN_SIGNAL::get
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

    public static final BlockEntityTypeRegistryObject<UKBritishOperatorStationSignBase.TileEntityBritishRailOperatorSign> UK_BRITISH_OPERATOR_STATION_SIGN_BASE = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "uk_british_operator_station_sign_base"
            ),
            UKBritishOperatorStationSignBase.TileEntityBritishRailOperatorSign::new,
            BlockInit.MERSEYRAIL_STATION_SIGN::get,
            BlockInit.EMR_STATION_SIGN::get
    );

    public static final BlockEntityTypeRegistryObject<UKBritishOperatorStationSignBase.TileEntityBritishRailOperatorSignWhite> UK_BRITISH_OPERATOR_STATION_SIGN_BASE_WHITE = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "uk_british_operator_station_sign_base_white"
            ),
            UKBritishOperatorStationSignBase.TileEntityBritishRailOperatorSignWhite::new,
            BlockInit.AVANTI_WEST_COAST_STATION_SIGN::get,
            BlockInit.NORTHERN_STATION_SIGN::get
    );
    public static final BlockEntityTypeRegistryObject<UKBritishOperatorStationSignBase.TileEntityBritishRailOperatorSignBlue> UK_BRITISH_OPERATOR_STATION_SIGN_BASE_BLUE = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "uk_british_operator_station_sign_base_blue"
            ),
            UKBritishOperatorStationSignBase.TileEntityBritishRailOperatorSignBlue::new,
            BlockInit.NSE_STATION_SIGN::get,
            BlockInit.NSE_STATION_SIGN_ALT::get
    );

    public static final BlockEntityTypeRegistryObject<UKBritishOperatorStationSignPoleBase.TileEntityBritishRailOperatorSign> RUSTY_UK_BRITISH_OPERATOR_STATION_SIGN_BASE = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "uk_british_operator_station_sign_base_rusty"
            ),
            UKBritishOperatorStationSignPoleBase.TileEntityBritishRailOperatorSign::new,
            BlockInit.RUSTY_POLE_EMR_STATION_SIGN::get,
            BlockInit.STEEL_POLE_EMR_STATION_SIGN::get,
            BlockInit.BLACK_POLE_EMR_STATION_SIGN::get,
            BlockInit.RUSTY_POLE_MERSEYRAIL_STATION_SIGN::get,
            BlockInit.BLACK_POLE_MERSEYRAIL_STATION_SIGN::get,
            BlockInit.STEEL_POLE_MERSEYRAIL_STATION_SIGN::get
    );

    public static final BlockEntityTypeRegistryObject<UKBritishOperatorStationSignPoleBase.TileEntityBritishRailOperatorSignWhite> RUSTY_UK_BRITISH_OPERATOR_STATION_SIGN_BASE_WHITE = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "uk_british_operator_station_sign_base_white_rusty"
            ),
            UKBritishOperatorStationSignPoleBase.TileEntityBritishRailOperatorSignWhite::new,
            BlockInit.RUSTY_POLE_AVANTI_WEST_COAST_STATION_SIGN::get,
            BlockInit.BLACK_POLE_AVANTI_WEST_COAST_STATION_SIGN::get,
            BlockInit.STEEL_POLE_AVANTI_WEST_COAST_STATION_SIGN::get,
            BlockInit.RUSTY_POLE_NORTHERN_STATION_SIGN::get,
            BlockInit.STEEL_POLE_NORTHERN_STATION_SIGN::get,
            BlockInit.BLACK_POLE_NORTHERN_STATION_SIGN::get
    );

    public static final BlockEntityTypeRegistryObject<UKBritishOperatorStationSignPoleBase.TileEntityBritishRailOperatorSignBlue> RUSTY_UK_BRITISH_OPERATOR_STATION_SIGN_BASE_BLUE = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "uk_british_operator_station_sign_base_blue_rusty"
            ),
            UKBritishOperatorStationSignPoleBase.TileEntityBritishRailOperatorSignBlue::new,
            BlockInit.RUSTY_POLE_NSE_STATION_SIGN::get,
            BlockInit.STEEL_POLE_NSE_STATION_SIGN::get,
            BlockInit.BLACK_POLE_NSE_STATION_SIGN::get
    );

    public static final BlockEntityTypeRegistryObject<UKBritishOperatorStationSignWallBase.TileEntityBritishRailOperatorSign> WALL_UK_BRITISH_OPERATOR_STATION_SIGN_BASE = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "uk_british_operator_station_sign_base_wall"
            ),
            UKBritishOperatorStationSignWallBase.TileEntityBritishRailOperatorSign::new,
            BlockInit.WALL_MERSEYRAIL_STATION_SIGN::get,
            BlockInit.WALL_EMR_STATION_SIGN::get
    );

    public static final BlockEntityTypeRegistryObject<UKBritishOperatorStationSignWallBase.TileEntityBritishRailOperatorSignWhite> WALL_UK_BRITISH_OPERATOR_STATION_SIGN_BASE_WHITE = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "uk_british_operator_station_sign_base_white_wall"
            ),
            UKBritishOperatorStationSignWallBase.TileEntityBritishRailOperatorSignWhite::new,
            BlockInit.WALL_AVANTI_WEST_COAST_STATION_SIGN::get,
            BlockInit.WALL_NORTHERN_STATION_SIGN::get
    );

    public static final BlockEntityTypeRegistryObject<UKBritishOperatorStationSignWallBase.TileEntityBritishRailOperatorSignBlue> WALL_UK_BRITISH_OPERATOR_STATION_SIGN_BASE_BLUE = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "uk_british_operator_station_sign_base_blue_wall"
            ),
            UKBritishOperatorStationSignWallBase.TileEntityBritishRailOperatorSignBlue::new,
            BlockInit.WALL_NSE_STATION_SIGN::get,
            BlockInit.WALL_NSE_STATION_SIGN_ALT::get
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
