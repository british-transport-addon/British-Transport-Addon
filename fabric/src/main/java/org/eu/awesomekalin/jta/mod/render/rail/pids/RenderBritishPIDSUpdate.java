package org.eu.awesomekalin.jta.mod.render.rail.pids;

import org.eu.awesomekalin.jta.mod.blocks.pids.NationalRailSingleBoard;
import org.mtr.core.data.Platform;
import org.mtr.core.data.RoutePlatformData;
import org.mtr.core.data.Station;
import org.mtr.core.operation.ArrivalResponse;
import org.mtr.core.tool.Utilities;
import org.mtr.libraries.it.unimi.dsi.fastutil.longs.LongArrayList;
import org.mtr.libraries.it.unimi.dsi.fastutil.longs.LongCollection;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.Direction;
import org.mtr.mapping.holder.Vector3d;
import org.mtr.mapping.holder.World;
import org.mtr.mapping.mapper.BlockEntityRenderer;
import org.mtr.mapping.mapper.DirectionHelper;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mod.InitClient;
import org.mtr.mod.block.BlockArrivalProjectorBase;
import org.mtr.mod.block.BlockPIDSBase;
import org.mtr.mod.block.BlockPIDSHorizontalBase;
import org.mtr.mod.block.IBlock;
import org.mtr.mod.client.MinecraftClientData;
import org.mtr.mod.data.ArrivalsCacheClient;
import org.mtr.mod.data.IGui;
import org.mtr.mod.generated.lang.TranslationProvider;
import org.mtr.mod.render.MainRenderer;
import org.mtr.mod.render.QueuedRenderLayer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class RenderBritishPIDSUpdate<T extends BlockPIDSBase.BlockEntityBase> extends BlockEntityRenderer<T> implements IGui, Utilities {

    private final float startX;
    private final float startY;
    private final float startZ;
    private final float maxHeight;
    public final float maxWidth;
    private final boolean rotate90;
    private final float textPadding;

    public static final int SWITCH_LANGUAGE_TICKS = 60;

    public RenderBritishPIDSUpdate(Argument dispatcher, float startX, float startY, float startZ, float maxHeight, int maxWidth, boolean rotate90, float textPadding) {
        super(dispatcher);
        this.startX = startX;
        this.startY = startY;
        this.startZ = startZ;
        this.maxHeight = maxHeight;
        this.maxWidth = maxWidth;
        this.rotate90 = rotate90;
        this.textPadding = textPadding;
    }

    private static final int PLATFORMS_PER_PAGE = 9; // 9 lines for platforms to fill up to 12 lines total
    private static final int PAGE_SWITCH_INTERVAL = 20 * 1000; // 20 seconds in milliseconds
    private int currentPage = 0; // Start on the first page
    private long lastPageSwitchTime = System.currentTimeMillis(); // Track last page switch time


    @Override
    public void render(T entity, float tickDelta, GraphicsHolder graphicsHolder, int light, int overlay) {
        final World world = entity.getWorld2();
        if (world == null) {
            return;
        }

        final BlockPos blockPos = entity.getPos2();
        if (!entity.canStoreData.test(world, blockPos)) {
            return;
        }

        final Direction facing = IBlock.getStatePropertySafe(world, blockPos, DirectionHelper.FACING);

        if (entity.getPlatformIds().isEmpty()) {
            final LongArrayList platformIds = new LongArrayList();
            if (entity instanceof BlockArrivalProjectorBase.BlockEntityArrivalProjectorBase) {
                final Station station = InitClient.findStation(blockPos);
                if (station != null) {
                    station.savedRails.forEach(platform -> platformIds.add(platform.getId()));
                }
            } else {
                InitClient.findClosePlatform(entity.getPos2().down(4), 5, platform -> platformIds.add(platform.getId()));
            }
            getArrivalsAndRender(entity, blockPos, facing, platformIds);
        } else {
            getArrivalsAndRender(entity, blockPos, facing, entity.getPlatformIds());
        }
    }

    private void getArrivalsAndRender(T entity, BlockPos blockPos, Direction facing, LongCollection platformIds) {
        final ObjectArrayList<ArrivalResponse> arrivalResponseList = ArrivalsCacheClient.INSTANCE.requestArrivals(platformIds);
        MainRenderer.scheduleRender(QueuedRenderLayer.TEXT, (graphicsHolder, offset) -> {
            render(entity, blockPos, facing, arrivalResponseList, graphicsHolder, offset);
            if (entity instanceof BlockPIDSHorizontalBase.BlockEntityHorizontalBase) {
                render(entity, blockPos.offset(facing), facing.getOpposite(), arrivalResponseList, graphicsHolder, offset);
            }
        });
    }

    private void render(T entity, BlockPos blockPos, Direction facing, ObjectArrayList<ArrivalResponse> arrivalResponseList, GraphicsHolder graphicsHolder, Vector3d offset) {
        final float scale = 130 * entity.maxArrivals / maxHeight * textPadding;
        //final boolean hasDifferentCarLengths = hasDifferentCarLengths(arrivalResponseList);
        int arrivalIndex;
        try {
            arrivalIndex = Integer.parseInt(entity.getMessage(0).trim());
        } catch (Exception exception) {
            arrivalIndex = 0;
        }

        ArrivalResponse arrivalResponse = null;
        try {
            arrivalResponse = arrivalResponseList.get(arrivalIndex);
        } catch (Exception exception) {
            return;
        }
        int color = entity.textColor();

        final String destinationFormatted = arrivalResponse.getDestination();

        int languageIndex;
        if (new Date(System.currentTimeMillis()).getMinutes() % 2 == 0) {
            languageIndex = 1;
        } else {
            languageIndex = 0;
        }

        String arrivalString;

        arrivalString = "On time";
        if (arrivalResponse.getDeviation() > 60_000) {
            arrivalString = "Exp " + new SimpleDateFormat("HH:mm").format(new Date(arrivalResponse.getArrival()));
        }

        for (int i = 0; i < entity.maxArrivals; i++) {
            graphicsHolder.push();
            graphicsHolder.translate(blockPos.getX() - offset.getXMapped() + 0.5, blockPos.getY() - offset.getYMapped(), blockPos.getZ() - offset.getZMapped() + 0.5);
            graphicsHolder.rotateYDegrees((rotate90 ? 90 : 0) - facing.asRotation());
            graphicsHolder.rotateZDegrees(180);
            graphicsHolder.translate((startX - 8) / 16, -startY / 16 + i * maxHeight / entity.maxArrivals / 16, (startZ - 8) / 16 - SMALL_OFFSET * 2);
            graphicsHolder.scale(1 / scale, 1 / scale, 1 / scale);


            Station station = InitClient.findStation(entity.getPos2());

            if (station == null || arrivalResponse == null || MinecraftClientData.getDashboardInstance() == null ||
                    MinecraftClientData.getDashboardInstance().routeIdMap == null ||
                    !MinecraftClientData.getDashboardInstance().routeIdMap.containsKey(arrivalResponse.getRouteId())) {

                // Define the middle line of the board based on total lines
                int totalLines = entity.maxArrivals; // Assuming this is the total number of lines to render
                int middleLine = totalLines / 2;

                String stationName = (station != null) ? station.getName() : "Unknown";
                String welcomeMessage = "Welcome to " + stationName + " station.";

                // Calculate the padding required to center the message
                int totalPadding = (int) (maxWidth * scale - welcomeMessage.length());
                int paddingOnEachSide = totalPadding / 2;

                // Create a centered message by adding padding spaces
                String centeredMessage = " ".repeat(Math.max(0, paddingOnEachSide)) + welcomeMessage;

                // Render the centered message on the middle line
                renderText(graphicsHolder, centeredMessage, color, maxWidth * scale, middleLine == i);

                graphicsHolder.pop();
                return;
            }

            // Get route information (list of all stations and platforms)
            List<RoutePlatformData> platformsList = MinecraftClientData.getDashboardInstance()
                    .routeIdMap.get(arrivalResponse.getRouteId()).getRoutePlatforms();

            // Find the index of the current station in the platforms list
            int currentIndex = -1;
            for (int x = 0; x < platformsList.size(); x++) {
                if (platformsList.get(x).platform.area.getName().equals(station.getName())) {
                    currentIndex = x;
                    break;
                }
            }

            // If current station is not found, or it's the last station, display termination message
            if (currentIndex == -1 || currentIndex == platformsList.size() - 1) {
                renderText(graphicsHolder, "This train terminates here.", color, maxWidth, false);
                graphicsHolder.pop();
                return;
            }

            // Get upcoming platforms from the current station
            List<RoutePlatformData> upcomingPlatforms = platformsList.subList(currentIndex + 1, platformsList.size());

            int totalPlatforms = upcomingPlatforms.size();
            int totalPages = (int) Math.ceil((double) totalPlatforms / PLATFORMS_PER_PAGE);

            int startIndex = currentPage * PLATFORMS_PER_PAGE;
            int endIndex = Math.min(startIndex + PLATFORMS_PER_PAGE, totalPlatforms);

            // Render different lines based on the value of i
            if (i == 0) {
                renderText(graphicsHolder, new SimpleDateFormat("HH:mm").format(new Date(arrivalResponse.getArrival() - arrivalResponse.getDeviation())), entity.textColor(), maxWidth * scale / 2, false);
                renderText(graphicsHolder, languageIndex == 0 ? arrivalString : "Plat. " + arrivalResponse.getPlatformName(), entity.textColor(), ((maxWidth * scale) / 8) - 15, true);
            } else if (i == 1) {
                renderText(graphicsHolder, destinationFormatted, color, ((maxWidth * scale) / 8) - 15, false);
            } else if (i == 2) {
                renderText(graphicsHolder, "Calling At:  (Page " + (currentPage + 1) + " of " + totalPages + ")", color, ((maxWidth * scale) / 8) - 54, false);
            } else if (i == 15) {
                renderText(graphicsHolder, "ScotRail", color, ((maxWidth * scale) / 8) - 15, false);
            } else if (i == 16) {
                renderText(graphicsHolder, "This train is formed of " + arrivalResponse.getCarCount() + " coaches.", color, ((maxWidth * scale) / 8) - 15, false);
            } else {
                int platformIndex = i - 3 + startIndex;
                if (platformIndex >= startIndex && platformIndex < endIndex) {
                    Platform platform = upcomingPlatforms.get(platformIndex).platform;
                    String platformName = platform.area.getName();

                    renderText(graphicsHolder, "| " + platformName, color, ((maxWidth * scale) / 8) - 40, false);
                }
            }

            //renderText(graphicsHolder, "-".repeat((int) maxWidth), color,maxWidth * scale, false);

            graphicsHolder.pop();
        }

        graphicsHolder.pop();
    }

    private static void renderText(GraphicsHolder graphicsHolder, String text, int color, float availableWidth, boolean rightAlign) {
        graphicsHolder.push();
        final int textWidth = GraphicsHolder.getTextWidth(text);
        if (availableWidth < textWidth) {
            graphicsHolder.scale(textWidth == 0 ? 1 : availableWidth / textWidth, 1, 1);
        }
        graphicsHolder.drawText(text, rightAlign ? Math.max(0, (int) availableWidth - textWidth) : 0, 0, color | ARGB_BLACK, false, GraphicsHolder.getDefaultLight());
        graphicsHolder.pop();
    }

    private static boolean hasDifferentCarLengths(ObjectArrayList<ArrivalResponse> arrivalResponseList) {
        int carCount = 0;
        for (final ArrivalResponse arrivalResponse : arrivalResponseList) {
            final int currentCarCount = arrivalResponse.getCarCount();
            if (carCount > 0 && currentCarCount != carCount) {
                return true;
            }
            carCount = currentCarCount;
        }
        return false;
    }
}