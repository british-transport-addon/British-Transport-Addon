package org.eu.awesomekalin.jta.mod.screen;

import org.eu.awesomekalin.jta.mod.InitClient;
import org.eu.awesomekalin.jta.mod.init.CustomResourceLoader;
import org.eu.awesomekalin.jta.mod.packet.entity.PacketUpdateDynamicDisplay;
import org.eu.awesomekalin.jta.mod.resource.DisplayResource;
import org.mtr.libraries.it.unimi.dsi.fastutil.longs.LongArrayList;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.ObjectImmutableList;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.ClickableWidget;
import org.mtr.mapping.holder.ClientPlayerEntity;
import org.mtr.mapping.holder.MinecraftClient;
import org.mtr.mapping.mapper.ButtonWidgetExtension;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mod.screen.DashboardListItem;
import org.mtr.mod.screen.DashboardListSelectorScreen;

public class DisplaySelectorScreen extends DashboardListSelectorScreen {
    private final BlockPos pos;
    private final ObjectImmutableList<DisplayResource> allDisplays = CustomResourceLoader.getDisplays();
    private int width;
    private int height;

    private boolean renderSelectors = false;

    private final ButtonWidgetExtension buttonAddW;
    private final ButtonWidgetExtension buttonMinusW;
    private final ButtonWidgetExtension buttonAddH;
    private final ButtonWidgetExtension buttonMinusH;

    private DisplaySelectorScreen(ObjectImmutableList<DashboardListItem> allData, LongArrayList selectedIds, BlockPos pos, int width, int height) {
        super(allData, selectedIds, false, true, null);
        this.pos = pos;
        this.width = width;
        this.height = height;

        buttonAddW = new ButtonWidgetExtension(150, 100, 20, 20, "+", button -> changeWidth(1));
        buttonMinusW = new ButtonWidgetExtension(50, 100, 20, 20, "-", button -> changeWidth(-1));
        buttonAddH = new ButtonWidgetExtension(150, 200, 20, 20, "+", button -> changeHeight(1));
        buttonMinusH = new ButtonWidgetExtension(50, 200, 20, 20, "-", button -> changeHeight(-1));
    }

    @Override
    public void render(GraphicsHolder graphicsHolder, int mouseX, int mouseY, float delta) {
        super.render(graphicsHolder, mouseX, mouseY, delta);

        if (renderSelectors) {
            graphicsHolder.drawCenteredText(String.valueOf(width), 107, 106, RGB_WHITE);
            graphicsHolder.drawCenteredText(String.valueOf(height), 107, 206, RGB_WHITE);
        }
    }

    @Override
    protected void init2() {
        super.init2();

        if (MinecraftClient.getInstance().getWindow().getWidth() >= 1400) {
            addChild(new ClickableWidget(buttonAddW));
            addChild(new ClickableWidget(buttonMinusW));
            addChild(new ClickableWidget(buttonAddH));
            addChild(new ClickableWidget(buttonMinusH));
            renderSelectors = true;
        }
    }

    @Override
    public void onClose2() {
        super.onClose2();
        final ClientPlayerEntity clientPlayerEntity = MinecraftClient.getInstance().getPlayerMapped();

        ObjectArrayList<String> selectedImages = new ObjectArrayList<>();
        selectedIds.forEach(index -> {
            selectedImages.add(allDisplays.get((int) index).getId());
        });

        if (clientPlayerEntity != null) {
            InitClient.REGISTRY_CLIENT.sendPacketToServer(new PacketUpdateDynamicDisplay(pos, selectedImages, width, height));
        }
    }



    @Override
    protected void updateList() {
        super.updateList();
        final ClientPlayerEntity clientPlayerEntity = MinecraftClient.getInstance().getPlayerMapped();

        ObjectArrayList<String> selectedImages = new ObjectArrayList<>();
        selectedIds.forEach(index -> {
            selectedImages.add(allDisplays.get((int) index).getId());
        });

        if (clientPlayerEntity != null) {
            InitClient.REGISTRY_CLIENT.sendPacketToServer(new PacketUpdateDynamicDisplay(pos, selectedImages, width, height));
        }
    }

    public static DisplaySelectorScreen create(BlockPos pos, ObjectArrayList<String> selectedIds, int width, int height) {
        final ObjectImmutableList<DisplayResource> allDisplays = CustomResourceLoader.getDisplays();
        final ObjectArrayList<DashboardListItem> displaysForList = new ObjectArrayList<>();
        final LongArrayList selectedIndices = new LongArrayList();

        for (int i = 0; i < allDisplays.size(); i++) {
            final DisplayResource display = allDisplays.get(i);
            displaysForList.add(new DashboardListItem(i, display.getName(), display.getColor()));
            if (selectedIds.contains(display.getId())) {
                selectedIndices.add(i);
            }
        }

        return new DisplaySelectorScreen(new ObjectImmutableList<>(displaysForList), selectedIndices, pos, width, height);
    }

    private void changeWidth(int amount) {
        width += amount;

        if (width == 0) {
            width = 1;
        }
    }

    private void changeHeight(int amount) {
        height += amount;

        if (height == 0) {
            height = 1;
        }
    }
}
