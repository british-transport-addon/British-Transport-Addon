package org.eu.awesomekalin.jta.mod.packet;

import org.eu.awesomekalin.jta.mod.screen.DisplaySelectorScreen;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.MinecraftClient;
import org.mtr.mapping.holder.Screen;

public class ClientPacketHelper {
    public static void openDisplaySelector(BlockPos pos) {
        MinecraftClient.getInstance().openScreen(new Screen(DisplaySelectorScreen.create(pos)));
    }
}
