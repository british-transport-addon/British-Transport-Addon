package org.eu.awesomekalin.jta.mod.packet;

import org.eu.awesomekalin.jta.mod.screen.DisplaySelectorScreen;
import org.eu.awesomekalin.jta.mod.screen.whiteboard.UndergroundWhiteboardScreen;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.MinecraftClient;
import org.mtr.mapping.holder.Screen;

public class ClientPacketHelper {
    public static void openDisplaySelector(BlockPos pos, ObjectArrayList<String> selectedIds, int width, int height) {
        MinecraftClient.getInstance().openScreen(new Screen(DisplaySelectorScreen.create(pos, selectedIds, width, height)));
    }
}
