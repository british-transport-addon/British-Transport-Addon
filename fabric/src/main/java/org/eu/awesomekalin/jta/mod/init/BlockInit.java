package org.eu.awesomekalin.jta.mod.init;

import org.eu.awesomekalin.jta.mod.Init;
import org.eu.awesomekalin.jta.mod.blocks.*;
import org.eu.awesomekalin.jta.mod.blocks.directional.*;
import org.eu.awesomekalin.jta.mod.blocks.directional.bus.*;
import org.eu.awesomekalin.jta.mod.blocks.directional.rail.*;
import org.eu.awesomekalin.jta.mod.blocks.pids.*;
import org.eu.awesomekalin.jta.mod.blocks.signal.*;
import org.mtr.mapping.holder.Block;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.mapper.BlockExtension;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mapping.registry.BlockRegistryObject;

public class BlockInit {

    public static final BlockRegistryObject BANNER_REPEATER_SIGNAL = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "banner_repeater_signal"), () -> new Block(new BannerRepeaterSignal(BlockHelper.createBlockSettings(false, false))), CreativeTabInit.JTA_RAILWAYS);
    public static final BlockRegistryObject CROSSING_SIGNAL = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "crossing_signal"), () -> new Block(new CrossingBritishSignal(BlockHelper.createBlockSettings(false, false))), CreativeTabInit.JTA_RAILWAYS);
    public static final BlockRegistryObject DIGITAL_SIGNAL_LIGHT_1_ASPECT = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "digital_signal_light_1_aspect"), () -> new Block(new DigitalBritishSignal1Aspect(BlockHelper.createBlockSettings(false, false))), CreativeTabInit.JTA_RAILWAYS);
    public static final BlockRegistryObject DIGITAL_SIGNAL_LIGHT_2_ASPECT = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "digital_signal_light_2_aspect"), () -> new Block(new DigitalBritishSignal2Aspect(BlockHelper.createBlockSettings(false, false))), CreativeTabInit.JTA_RAILWAYS);
    public static final BlockRegistryObject DIRECTION_SIGNAL_LIGHT_5_ASPECT = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "direction_signal_light_5_aspect"), () -> new Block(new DirectionBritishSignal5Aspect(BlockHelper.createBlockSettings(false, false))), CreativeTabInit.JTA_RAILWAYS);
    public static final BlockRegistryObject DIRECTION_SIGNAL_LIGHT_5_ASPECT_RIGHT = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "direction_signal_light_5_aspect_right"), () -> new Block(new DirectionBritishSignal5AspectRight(BlockHelper.createBlockSettings(false, false))), CreativeTabInit.JTA_RAILWAYS);

    public static final BlockRegistryObject PIDS_NATIONALRAILSINGLEBOARD = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "national_rail_single_board"), () -> new Block(new NationalRailSingleBoard()), CreativeTabInit.JTA_RAILWAYS);
    public static final BlockRegistryObject PIDS_NATIONALRAILSINGLEBOARD_WHITE = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "national_rail_single_board_white"), () -> new Block(new NationalRailSingleBoardWhite()), CreativeTabInit.JTA_RAILWAYS);
    public static final BlockRegistryObject PIDS_NATIONALRAIL3CLOCK = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "national_rail_3_clock"), () -> new Block(new NationalRail3Clock()), CreativeTabInit.JTA_RAILWAYS);
    public static final BlockRegistryObject PIDS_NATIONALRAIL3CLOCKWHITE = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "national_rail_3_clock_white"), () -> new Block(new NationalRail3ClockWhite()), CreativeTabInit.JTA_RAILWAYS);
    public static final BlockRegistryObject PIDS_NATIONALRAIL10CLOCK = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "national_rail_10_clock"), () -> new Block(new NationalRail10Clock()), CreativeTabInit.JTA_RAILWAYS);
    public static final BlockRegistryObject PIDS_NATIONALRAIL10CLOCKWHITE = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "national_rail_10_clock_white"), () -> new Block(new NationalRail10ClockWhite()), CreativeTabInit.JTA_RAILWAYS);
    public static final BlockRegistryObject PIDS_METROLINK3CLOCK = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "metrolink_3_clock"), () -> new Block(new Metrolink3Clock()), CreativeTabInit.JTA_RAILWAYS);

    public static final BlockRegistryObject BARRIER_PLATFORM = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "barrier_platform"), () -> new Block(new BarrierPlatformBlock()), CreativeTabInit.JTA_BLOCKS);

    public static final BlockRegistryObject SCOTRAIL_BLUE = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "scotrail_blue"), () -> new Block(new BlockExtension(BlockHelper.createBlockSettings(false, false).strength(4.0f).nonOpaque().dynamicBounds())), CreativeTabInit.JTA_BLOCKS);

    public static final BlockRegistryObject ASPHALT = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "asphalt"), () -> new Block(new BlockExtension(BlockHelper.createBlockSettings(false, false).strength(4.0f).nonOpaque().dynamicBounds())), CreativeTabInit.JTA_BLOCKS);
    public static final BlockRegistryObject LIME_BRICKS = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "lime_bricks"), () -> new Block(new BlockExtension(BlockHelper.createBlockSettings(false, false).strength(4.0f).nonOpaque().dynamicBounds())), CreativeTabInit.JTA_BLOCKS);
    public static final BlockRegistryObject GREEN_BRICKS = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "green_bricks"), () -> new Block(new BlockExtension(BlockHelper.createBlockSettings(false, false).strength(4.0f).nonOpaque().dynamicBounds())), CreativeTabInit.JTA_BLOCKS);
    public static final BlockRegistryObject BLACK_BRICKS = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "black_bricks"), () -> new Block(new BlockExtension(BlockHelper.createBlockSettings(false, false).strength(4.0f).nonOpaque().dynamicBounds())), CreativeTabInit.JTA_BLOCKS);
    public static final BlockRegistryObject WHITE_BRICKS = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "white_bricks"), () -> new Block(new BlockExtension(BlockHelper.createBlockSettings(false, false).strength(4.0f).nonOpaque().dynamicBounds())), CreativeTabInit.JTA_BLOCKS);
    public static final BlockRegistryObject STONE_PAVER = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "stone_paver"), () -> new Block(new BlockExtension(BlockHelper.createBlockSettings(false, false).strength(4.0f).nonOpaque().dynamicBounds())), CreativeTabInit.JTA_BLOCKS);
    public static final BlockRegistryObject SMOOTH_MIXED_STONE = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "smooth_mixed_stone"), () -> new Block(new BlockExtension(BlockHelper.createBlockSettings(false, false).strength(4.0f).nonOpaque().dynamicBounds())), CreativeTabInit.JTA_BLOCKS);
    public static final BlockRegistryObject CREAMY_ENGLISH_BRICKS = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "creamy_english_bricks"), () -> new Block(new BlockExtension(BlockHelper.createBlockSettings(false, false).strength(4.0f).nonOpaque().dynamicBounds())), CreativeTabInit.JTA_BLOCKS);
    public static final BlockRegistryObject ENGLISH_BRICKS = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "english_bricks"), () -> new Block(new BlockExtension(BlockHelper.createBlockSettings(false, false).strength(4.0f).nonOpaque().dynamicBounds())), CreativeTabInit.JTA_BLOCKS);
    public static final BlockRegistryObject SILTY_BRICKS = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "silty_bricks"), () -> new Block(new BlockExtension(BlockHelper.createBlockSettings(false, false).strength(4.0f).nonOpaque().dynamicBounds())), CreativeTabInit.JTA_BLOCKS);
    public static final BlockRegistryObject SANDY_BRICKS = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "sandy_bricks"), () -> new Block(new BlockExtension(BlockHelper.createBlockSettings(false, false).strength(4.0f).nonOpaque().dynamicBounds())), CreativeTabInit.JTA_BLOCKS);
    public static final BlockRegistryObject LARGE_SLATE_BRICKS = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "large_slate_bricks"), () -> new Block(new BlockExtension(BlockHelper.createBlockSettings(false, false).strength(4.0f).nonOpaque().dynamicBounds())), CreativeTabInit.JTA_BLOCKS);
    public static final BlockRegistryObject LARGE_DARK_GRAY_BRICKS = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "large_dark_gray_bricks"), () -> new Block(new BlockExtension(BlockHelper.createBlockSettings(false, false).strength(4.0f).nonOpaque().dynamicBounds())), CreativeTabInit.JTA_BLOCKS);
    public static final BlockRegistryObject DARK_ENGLISH_BRICKS = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "dark_english_bricks"), () -> new Block(new BlockExtension(BlockHelper.createBlockSettings(false, false).strength(4.0f).nonOpaque().dynamicBounds())), CreativeTabInit.JTA_BLOCKS);

    public static final BlockRegistryObject DISPATCH_SIGNAL = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "dispatch_signal"), () -> new Block(new DispatchSignal()), CreativeTabInit.JTA_BLOCKS);
    public static final BlockRegistryObject AWS_DECORATION = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "aws"), () -> new Block(new AWSRailDecoration()), CreativeTabInit.JTA_BLOCKS);
    public static final BlockRegistryObject BRITISH_RAILWAY_GROUT = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "british_railway_grout"), () -> new Block(new BlockExtension(BlockHelper.createBlockSettings(false, false).strength(4.0f).nonOpaque().dynamicBounds())), CreativeTabInit.JTA_BLOCKS);
    public static final BlockRegistryObject UNDERGROUND_PLATFORM_END = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "underground_platform_end"), () -> new Block(new UndergroundPlatformEnd()), CreativeTabInit.JTA_BLOCKS);
    public static final BlockRegistryObject BRITISH_TICKET_BARRIER_ENTRANCE = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "british_ticket_barrier_entrance"), () -> new Block(new BritishTicketBarrier(true)), CreativeTabInit.JTA_RAILWAYS);
    public static final BlockRegistryObject BRITISH_TICKET_BARRIER_EXIT = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "british_ticket_barrier_exit"), () -> new Block(new BritishTicketBarrier(false)), CreativeTabInit.JTA_RAILWAYS);
    //public static final BlockRegistryObject BRITISH_TICKET_BARRIER_FENCE = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "british_ticket_barrier_fence"), () -> new Block(new BritishTicketBarrierFence(BlockHelper.createBlockSettings(false, false).strength(4.0f).nonOpaque().dynamicBounds())), CreativeTabInit.JTA_BLOCKS);
    public static final BlockRegistryObject BRITISH_RAIL_PLATFORM = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "british_rail_platform"), () -> new Block(new BritishRailPlatform(BlockHelper.createBlockSettings(true, false).strength(2), true)), CreativeTabInit.JTA_RAILWAYS);
    public static final BlockRegistryObject BRITISH_RAIL_PLATFORM_ALT = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "british_rail_platform_alt"), () -> new Block(new BritishRailPlatform(BlockHelper.createBlockSettings(true, false).strength(2), true)), CreativeTabInit.JTA_RAILWAYS);
    public static final BlockRegistryObject NORTHERN_TICKET_MACHINE = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "northern_ticket_machine"), () -> new Block(new NorthernTicketMachine()), CreativeTabInit.JTA_RAILWAYS);
    public static final BlockRegistryObject EMR_TICKET_MACHINE = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "emr_ticket_machine"), () -> new Block(new NorthernTicketMachine()), CreativeTabInit.JTA_RAILWAYS);
    public static final BlockRegistryObject METROLINK_TICKET_MACHINE = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "metrolink_ticket_machine"), () -> new Block(new MetrolinkTicketMachine()), CreativeTabInit.JTA_RAILWAYS);
    public static final BlockRegistryObject METROLINK_CONTACTLESS_READER = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "metrolink_contactless_reader"), () -> new Block(new MetrolinkContactactlessReader()), CreativeTabInit.JTA_RAILWAYS);
    public static final BlockRegistryObject RED_NSE_ROOF_CLOCK = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "red_nse_roof_clock"), () -> new Block(new NSERoofClock()), CreativeTabInit.JTA_RAILWAYS);
    public static final BlockRegistryObject YELLOW_NSE_ROOF_CLOCK = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "yellow_nse_roof_clock"), () -> new Block(new NSERoofClock()), CreativeTabInit.JTA_RAILWAYS);
    public static final BlockRegistryObject BLUE_NSE_ROOF_CLOCK = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "blue_nse_roof_clock"), () -> new Block(new NSERoofClock()), CreativeTabInit.JTA_RAILWAYS);
    public static final BlockRegistryObject STEEL_NSE_ROOF_CLOCK = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "steel_nse_roof_clock"), () -> new Block(new NSERoofClock()), CreativeTabInit.JTA_RAILWAYS);
    public static final BlockRegistryObject METROLINK_SIGN = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "metrolink_sign"), () -> new Block(new MetrolinkSign()), CreativeTabInit.JTA_SIGNS);
    public static final BlockRegistryObject METROLINK_SIGN_BARE = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "metrolink_sign_bare"), () -> new Block(new MetrolinkSign()), CreativeTabInit.JTA_SIGNS);

    public static final BlockRegistryObject BRITISH_RAIL_STATION_SIGN_SIMPLE = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "uk_british_rail_sign_simple"), () -> new Block(new UKBritishRailStationSignSimple()), CreativeTabInit.JTA_SIGNS);
    public static final BlockRegistryObject BRITISH_RAIL_STATION_SIGN_SIMPLE_WALL = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "uk_british_rail_sign_simple_wall"), () -> new Block(new UKBritishRailStationSignSimpleWall()), CreativeTabInit.JTA_SIGNS);
    public static final BlockRegistryObject BRITISH_RAIL_STATION_SIGN_SQUARE = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "uk_british_rail_sign_square"), () -> new Block(new UKBritishRailStationSignSquare()), CreativeTabInit.JTA_SIGNS);
    public static final BlockRegistryObject BRITISH_RAIL_STATION_SIGN_SQUARE_MERSEYTRAVEL = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "uk_british_rail_sign_square_merseytravel"), () -> new Block(new UKBritishRailStationSignSquare()), CreativeTabInit.JTA_SIGNS);
    public static final BlockRegistryObject UNDERGROUND_SQUARE = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "underground_sign_square"), () -> new Block(new MetrolinkSignSquare()), CreativeTabInit.JTA_SIGNS);
    public static final BlockRegistryObject OVERGROUND_SQUARE = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "overground_sign_square"), () -> new Block(new MetrolinkSignSquare()), CreativeTabInit.JTA_SIGNS);
    public static final BlockRegistryObject METROLINK_SIGN_SQUARE = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "metrolink_sign_square"), () -> new Block(new MetrolinkSignSquare()), CreativeTabInit.JTA_SIGNS);
    public static final BlockRegistryObject TYNE_AND_WEAR_SQUARE = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "tyne_and_wear_square"), () -> new Block(new MetrolinkSignSquare()), CreativeTabInit.JTA_SIGNS);
    public static final BlockRegistryObject MANCHESTER_SIGN_SQUARE = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "manchester_sign_square"), () -> new Block(new ManchesterSignSquare()), CreativeTabInit.JTA_SIGNS);
    public static final BlockRegistryObject MERSEYSIDE_SIGN_SQUARE_RAIL = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "merseyside_sign_square_rail"), () -> new Block(new MerseysideSignSquare()), CreativeTabInit.JTA_SIGNS);
    public static final BlockRegistryObject BUS_STOP_POLE = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "bus_stop_pole"), () -> new Block(new PoleBase()), CreativeTabInit.JTA_BUSES);
    public static final BlockRegistryObject BEE_BUS_STOP = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "bee_bus_stop"), () -> new Block(new BeeBusStopSign()), CreativeTabInit.JTA_BUSES);
    public static final BlockRegistryObject LONDON_BUS_STOP = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "london_bus_stop"), () -> new Block(new LondonBusStopSign()), CreativeTabInit.JTA_BUSES);
    public static final BlockRegistryObject BEE_BUS_STATION_SIGN = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "bee_bus_station_sign"), () -> new Block(new BeeStationSign()), CreativeTabInit.JTA_BUSES);
    public static final BlockRegistryObject BEE_INTERCHANGE_STATION_SIGN = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "bee_interchange_station_sign"), () -> new Block(new BeeStationSign()), CreativeTabInit.JTA_BUSES);
    public static final BlockRegistryObject UNDERGROUND_WHITEBOARD = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "underground_whiteboard"), () -> new Block(new UndergroundWhiteboard()), CreativeTabInit.JTA_BLOCKS);
    public static final BlockRegistryObject METRO_NEWS = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "metro_news"), () -> new Block(new MetroNews()), CreativeTabInit.JTA_BLOCKS);
    public static final BlockRegistryObject PROJECTION_NAME = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "projection_name"), () -> new Block(new ProjectionName()), CreativeTabInit.JTA_BLOCKS);
    public static final BlockRegistryObject TRAIN_BALLAST = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "ballast"), () -> new Block(new BlockExtension(BlockHelper.createBlockSettings(true, false).strength(4.0f).nonOpaque())), CreativeTabInit.JTA_BLOCKS);
    public static void INIT() {
        Init.LOGGER.debug("Registering JTA Blocks");
    }
}
