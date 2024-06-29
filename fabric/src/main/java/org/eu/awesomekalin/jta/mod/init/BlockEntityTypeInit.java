package org.eu.awesomekalin.jta.mod.init;

import org.eu.awesomekalin.jta.mod.Init;
import org.eu.awesomekalin.jta.mod.blocks.directional.*;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.registry.BlockEntityTypeRegistryObject;

public class BlockEntityTypeInit {
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

    public static final BlockEntityTypeRegistryObject<UKBritishOperatorStationSignPoleBase.TileEntityBritishRailOperatorSign> RUSTY_UK_BRITISH_OPERATOR_STATION_SIGN_BASE = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "uk_british_operator_station_sign_base_rusty"
            ),
            UKBritishOperatorStationSignPoleBase.TileEntityBritishRailOperatorSign::new,
            BlockInit.RUSTY_POLE_MERSEYRAIL_STATION_SIGN::get,
            BlockInit.RUSTY_POLE_EMR_STATION_SIGN::get
    );

    public static final BlockEntityTypeRegistryObject<UKBritishOperatorStationSignPoleBase.TileEntityBritishRailOperatorSignWhite> RUSTY_UK_BRITISH_OPERATOR_STATION_SIGN_BASE_WHITE = Init.REGISTRY.registerBlockEntityType(
            new Identifier(
                    Init.MOD_ID,
                    "uk_british_operator_station_sign_base_white_rusty"
            ),
            UKBritishOperatorStationSignPoleBase.TileEntityBritishRailOperatorSignWhite::new,
            BlockInit.RUSTY_POLE_AVANTI_WEST_COAST_STATION_SIGN::get,
            BlockInit.RUSTY_POLE_NORTHERN_STATION_SIGN::get
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
    public static void INIT() {
        Init.LOGGER.debug("Registering JTA Block Entities");
    }
}
