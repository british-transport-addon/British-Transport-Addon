package org.eu.awesomekalin.jta.mod.packet;

import org.eu.awesomekalin.jta.mod.screen.whiteboard.UndergroundWhiteboardScreen;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.MinecraftClient;
import org.mtr.mapping.holder.Screen;
import org.mtr.mapping.registry.PacketHandler;
import org.mtr.mapping.tool.PacketBufferReceiver;
import org.mtr.mapping.tool.PacketBufferSender;

public class PacketOpenLUWhiteboard extends PacketHandler {
    private BlockPos pos;

    public PacketOpenLUWhiteboard(PacketBufferReceiver packetBufferReceiver) {
        this.pos = BlockPos.fromLong(packetBufferReceiver.readLong());
    }

    public PacketOpenLUWhiteboard(BlockPos pos) {
        this.pos = pos;
    }

    @Override
    public void write(PacketBufferSender packetBufferSender) {
        packetBufferSender.writeLong(pos.asLong());
    }

    @Override
    public void runClient() {
        UndergroundWhiteboardScreen screen = new UndergroundWhiteboardScreen(pos);
        MinecraftClient.getInstance().openScreen(new Screen(screen));
    }
}
