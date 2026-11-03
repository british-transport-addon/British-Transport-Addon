package org.eu.awesomekalin.jta.mod.packet;

import org.eu.awesomekalin.jta.mod.screen.station.ProjectionNameScreen;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.registry.PacketHandler;
import org.mtr.mapping.tool.PacketBufferReceiver;
import org.mtr.mapping.tool.PacketBufferSender;

public class PacketOpenProjectionNameScreen extends PacketHandler {
    private final BlockPos pos;

    public PacketOpenProjectionNameScreen(PacketBufferReceiver packetBufferReceiver) {
        this.pos = BlockPos.fromLong(packetBufferReceiver.readLong());
    }

    public PacketOpenProjectionNameScreen(BlockPos pos) {
        this.pos = pos;
    }

    @Override
    public void write(PacketBufferSender packetBufferSender) {
        packetBufferSender.writeLong(pos.asLong());
    }

    @Override
    public void runClient() {
        ProjectionNameScreen.handle(pos);
    }
}
