package org.eu.awesomekalin.jta.mod.render.rail.pids;

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

import java.util.List;
import java.util.stream.Collectors;

public class RenderManchesterPIDS<T extends BlockPIDSBase.BlockEntityBase> extends BlockEntityRenderer<T> implements IGui, Utilities {

	private final float startX;
	private final float startY;
	private final float startZ;
	private final float maxHeight;
	private final float maxWidth;
	private final boolean rotate90;
	private final float textPadding;

	public static final int SWITCH_LANGUAGE_TICKS = 60;

	public RenderManchesterPIDS(Argument dispatcher, float startX, float startY, float startZ, float maxHeight, int maxWidth, boolean rotate90, float textPadding) {
		super(dispatcher);
		this.startX = startX;
		this.startY = startY;
		this.startZ = startZ;
		this.maxHeight = maxHeight;
		this.maxWidth = maxWidth;
		this.rotate90 = rotate90;
		this.textPadding = textPadding;
	}

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
	private int scrollPosition = 0; // Track scroll position
	private static final int MAX_WIDTH = 25; // Maximum width for scrolling text
	private static final int SCROLL_DELAY = 20; // Scroll every 20 calls (slows down scrolling)
	private int scrollCounter = 0; // Counter to control scroll speed
	private static final int SWITCH_INTERVAL = 30000; // Switch every 30 seconds
	private static final int SECOND_MESSAGE_DURATION = 1000; // Show second message for 1 seconds

	public String getServiceInfo(ArrivalResponse response, Station currentStation) {
		if (currentStation == null) return "";
		// Show "This train is formed of X coaches" for 10 seconds every 30 seconds
		long currentTime = System.currentTimeMillis();
		long timeInCurrentCycle = currentTime % (SWITCH_INTERVAL + SECOND_MESSAGE_DURATION);

		if (timeInCurrentCycle >= SWITCH_INTERVAL) {
			return " ";
		}

		String fullMessage = "There are ongoing engineering works across the Metrolink network. Please check before you travel.";
		// Ensure the message length is longer than MAX_WIDTH to scroll
		if (fullMessage.length() <= MAX_WIDTH) {
			return fullMessage; // No scrolling needed
		}

		// Increment the scroll counter and only update scrollPosition every SCROLL_DELAY calls
		scrollCounter++;
		if (scrollCounter >= SCROLL_DELAY) {
			scrollPosition = (scrollPosition + 1) % fullMessage.length(); // Update scroll position
			scrollCounter = 0; // Reset the counter
		}

		// Calculate substring for scrolling part (platforms)
		String displayedMessage;
		if (scrollPosition + MAX_WIDTH > fullMessage.length()) {
			// If near the end, wrap around to the start
			displayedMessage = fullMessage.substring(scrollPosition) + " " +
					fullMessage.substring(0, (scrollPosition + MAX_WIDTH) - fullMessage.length());
		} else {
			// Regular substring slicing
			displayedMessage = fullMessage.substring(scrollPosition, scrollPosition + MAX_WIDTH);
		}

		// Combine static "Calling At:" with the scrolling platforms message
		return displayedMessage;
	}

	private void render(T entity, BlockPos blockPos, Direction facing, ObjectArrayList<ArrivalResponse> arrivalResponseList, GraphicsHolder graphicsHolder, Vector3d offset) {
		final float scale = 160 * entity.maxArrivals / maxHeight * textPadding;
		final boolean hasDifferentCarLengths = hasDifferentCarLengths(arrivalResponseList);
		int arrivalIndex = entity.getDisplayPage() * entity.maxArrivals;

		for (int i = 0; i < entity.maxArrivals; i++) {
			final int languageTicks = (int) Math.floor(InitClient.getGameTick()) / SWITCH_LANGUAGE_TICKS;
			final ArrivalResponse arrivalResponse;
			final String customMessage = entity.getMessage(i);
			final String[] destinationSplit;
			final String[] customMessageSplit = customMessage.split("\\|");
			final boolean renderCustomMessage;
			final int languageIndex;

			if (entity.getHideArrival(i)) {
				if (customMessage.isEmpty()) {
					continue;
				}
				arrivalResponse = null;
				destinationSplit = new String[0];
				renderCustomMessage = true;
				languageIndex = languageTicks % customMessageSplit.length;
			} else {
				arrivalResponse = Utilities.getElement(arrivalResponseList, arrivalIndex);
				if (arrivalResponse == null) {
					if (customMessage.isEmpty() || customMessageSplit.length == 0) {
						continue;
					}
					destinationSplit = new String[0];
					renderCustomMessage = true;
					languageIndex = languageTicks % customMessageSplit.length;
				} else {
					destinationSplit = arrivalResponse.getDestination().split("\\|");
					final int messageCount = destinationSplit.length + (customMessage.isEmpty() ? 0 : customMessageSplit.length);
					renderCustomMessage = languageTicks % messageCount >= destinationSplit.length;
					languageIndex = (languageTicks % messageCount) - (renderCustomMessage ? destinationSplit.length : 0);
					if (!entity.alternateLines() || i % 2 == 1) {
						arrivalIndex++;
					}
				}
			}

			graphicsHolder.push();
			graphicsHolder.translate(blockPos.getX() - offset.getXMapped() + 0.5, blockPos.getY() - offset.getYMapped(), blockPos.getZ() - offset.getZMapped() + 0.5);
			graphicsHolder.rotateYDegrees((rotate90 ? 90 : 0) - facing.asRotation());
			graphicsHolder.rotateZDegrees(180);
			graphicsHolder.translate((startX - 8) / 16, -startY / 16 + i * maxHeight / entity.maxArrivals / 16, (startZ - 8) / 16 - SMALL_OFFSET * 2);
			graphicsHolder.scale(1 / scale, 1 / scale, 1 / scale);

			if (renderCustomMessage) {
				renderText(graphicsHolder, customMessageSplit[languageIndex].replace(
						"%info%",
						getServiceInfo(arrivalResponseList.stream().findFirst().orElse(null), InitClient.findStation(entity.getPos2()))
				), entity.textColor(), maxWidth * scale / 16, false);
			} else {
				final long arrival = (arrivalResponse.getArrival() - ArrivalsCacheClient.INSTANCE.getMillisOffset() - System.currentTimeMillis()) / 1000;
				final int color = arrival <= 0 ? entity.textColorArrived() : entity.textColor();
				final String destination = destinationSplit[languageIndex];
				final boolean isCjk = IGui.isCjk(destination);
				final String destinationFormatted;

				switch (arrivalResponse.getCircularState()) {
					case CLOCKWISE:
						destinationFormatted = (isCjk ? TranslationProvider.GUI_MTR_CLOCKWISE_VIA_CJK : TranslationProvider.GUI_MTR_CLOCKWISE_VIA).getString(destination);
						break;
					case ANTICLOCKWISE:
						destinationFormatted = (isCjk ? TranslationProvider.GUI_MTR_ANTICLOCKWISE_VIA_CJK : TranslationProvider.GUI_MTR_ANTICLOCKWISE_VIA).getString(destination);
						break;
					default:
						destinationFormatted = destination;
						break;
				}
				;
				final String carLengthString = arrivalResponse.getCarCount() > 2 ? "     dbl" : "";
				final String arrivalString;

				if (arrival >= 60) {
					arrivalString = (isCjk ? TranslationProvider.GUI_MTR_ARRIVAL_MIN_CJK : TranslationProvider.GUI_MTR_ARRIVAL_MIN).getString(arrival / 60);
				} else if (arrival > 0) {
					arrivalString = "Due";
				} else {
					arrivalString = "";
				}

				if (entity.alternateLines()) {
					if (i % 2 == 0) {
						renderText(graphicsHolder, destinationFormatted, color, maxWidth * scale / 16, false);
					} else {
						if (hasDifferentCarLengths) {
							renderText(graphicsHolder, carLengthString, color, 32, false);
							graphicsHolder.translate(32, 0, 0);
						}
						renderText(graphicsHolder, arrivalString, color, maxWidth * scale / 16 - (hasDifferentCarLengths ? 32 : 0), true);
					}
				} else {
					final boolean showPlatformNumber = entity instanceof BlockArrivalProjectorBase.BlockEntityArrivalProjectorBase;

					if (entity.showArrivalNumber()) {
						renderText(graphicsHolder, String.valueOf(arrivalIndex), color, 12, false);
						graphicsHolder.translate(12, 0, 0);
					}

					final float destinationWidth = maxWidth * scale / 16 - 40 - (hasDifferentCarLengths || showPlatformNumber ? showPlatformNumber ? 16 : 32 : 0) - (entity.showArrivalNumber() ? 12 : 0);
					renderText(graphicsHolder, destinationFormatted, color, destinationWidth, false);
					graphicsHolder.translate(destinationWidth, 0, 0);

					if (hasDifferentCarLengths || showPlatformNumber) {
						if (showPlatformNumber) {
							renderText(graphicsHolder, arrivalResponse.getPlatformName(), color, 16, false);
							graphicsHolder.translate(16, 0, 0);
						} else {
							renderText(graphicsHolder, carLengthString, color, 32, false);
							graphicsHolder.translate(32, 0, 0);
						}
					}

					renderText(graphicsHolder, arrivalString, color, 40, true);
				}
			}

			graphicsHolder.pop();
		}
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