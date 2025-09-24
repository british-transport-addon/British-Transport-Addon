package org.eu.awesomekalin.jta.mod.screen;

import org.eu.awesomekalin.jta.mod.init.CustomResourceLoader;
import org.eu.awesomekalin.jta.mod.resource.DisplayResource;
import org.mtr.libraries.it.unimi.dsi.fastutil.longs.LongArrayList;
import org.mtr.libraries.it.unimi.dsi.fastutil.longs.LongCollection;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.ObjectImmutableList;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mod.screen.DashboardListItem;
import org.mtr.mod.screen.DashboardListSelectorScreen;

public class DisplaySelectorScreen extends DashboardListSelectorScreen {
    private final BlockPos pos;

    private DisplaySelectorScreen(ObjectImmutableList<DashboardListItem> allData, LongCollection selectedIds, BlockPos pos) {
        super(allData, selectedIds, false, true, null);
        this.pos = pos;
    }

    @Override
    public void onClose2() {
        super.onClose2();
        //TODO: Save data to display
    }

    @Override
    protected void updateList() {
        super.updateList();
        //TODO: Save data to display
    }

    public static DisplaySelectorScreen create(BlockPos pos) {
        final ObjectImmutableList<DisplayResource> allDisplays = CustomResourceLoader.getDisplays();
        final ObjectArrayList<DashboardListItem> displaysForList = new ObjectArrayList<>();
        final LongArrayList selectedIds = new LongArrayList();

        for (int i = 0; i < allDisplays.size(); i++) {
            displaysForList.add(new DashboardListItem(i, allDisplays.get(i).getName(), allDisplays.get(i).getColor()));
        }

        return new DisplaySelectorScreen(new ObjectImmutableList<>(displaysForList), selectedIds, pos);
    }
}
