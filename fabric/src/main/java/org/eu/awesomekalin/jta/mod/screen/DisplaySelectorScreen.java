package org.eu.awesomekalin.jta.mod.screen;

import org.eu.awesomekalin.jta.mod.InitClient;
import org.eu.awesomekalin.jta.mod.init.CustomResourceLoader;
import org.eu.awesomekalin.jta.mod.packet.entity.PacketUpdateDynamicDisplay;
import org.eu.awesomekalin.jta.mod.resource.DisplayResource;
import org.mtr.libraries.it.unimi.dsi.fastutil.longs.LongArrayList;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.ObjectImmutableList;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.ClientPlayerEntity;
import org.mtr.mapping.holder.MinecraftClient;
import org.mtr.mod.screen.DashboardListItem;
import org.mtr.mod.screen.DashboardListSelectorScreen;

public class DisplaySelectorScreen extends DashboardListSelectorScreen {
    private final BlockPos pos;
    private final ObjectImmutableList<DisplayResource> allDisplays = CustomResourceLoader.getDisplays();

    private DisplaySelectorScreen(ObjectImmutableList<DashboardListItem> allData, LongArrayList selectedIds, BlockPos pos) {
        super(allData, selectedIds, false, true, null);
        this.pos = pos;
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
            InitClient.REGISTRY_CLIENT.sendPacketToServer(new PacketUpdateDynamicDisplay(pos, selectedImages));
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
            InitClient.REGISTRY_CLIENT.sendPacketToServer(new PacketUpdateDynamicDisplay(pos, selectedImages));
        }
    }

    public static DisplaySelectorScreen create(BlockPos pos, ObjectArrayList<String> selectedIds) {
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

        return new DisplaySelectorScreen(new ObjectImmutableList<>(displaysForList), selectedIndices, pos);
    }
}
