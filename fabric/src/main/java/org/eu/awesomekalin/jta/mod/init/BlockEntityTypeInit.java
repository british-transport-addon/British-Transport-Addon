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
