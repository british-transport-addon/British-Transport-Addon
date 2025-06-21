package org.eu.awesomekalin.jta.mod.packet;

import org.eu.awesomekalin.jta.mod.screen.DisplaySelectorScreen;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.MinecraftClient;
import org.mtr.mapping.holder.Screen;
import org.mtr.mapping.registry.PacketHandler;
import org.mtr.mapping.tool.PacketBufferReceiver;
import org.mtr.mapping.tool.PacketBufferSender;

public class PacketOpenDisplaySelector extends PacketHandler {
    private BlockPos pos;

    public PacketOpenDisplaySelector(PacketBufferReceiver packetBufferReceiver) {
        this.pos = BlockPos.fromLong(packetBufferReceiver.readLong());
    }

    public PacketOpenDisplaySelector(BlockPos pos) {
        this.pos = pos;
    }

    @Override
    public void write(PacketBufferSender packetBufferSender) {
        packetBufferSender.writeLong(pos.asLong());
    }

    @Override
    public void runClient() {
        MinecraftClient.getInstance().openScreen(new Screen(DisplaySelectorScreen.create(pos)));
    }
}
