package org.eu.awesomekalin.jta.mod.packet;

import org.eu.awesomekalin.jta.mod.screen.radio.RadioMainScreen;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.registry.PacketHandler;
import org.mtr.mapping.tool.PacketBufferReceiver;
import org.mtr.mapping.tool.PacketBufferSender;
import org.mtr.mod.packet.PacketOpenBlockEntityScreen;

public class PacketOpenPoleEditor extends PacketHandler {

    private final BlockPos pos;

    public PacketOpenPoleEditor(PacketBufferReceiver packetBufferReceiver) {
        super();
        this.pos = BlockPos.fromLong(packetBufferReceiver.readLong());
    }

    public PacketOpenPoleEditor(BlockPos pos) {
        this.pos = pos;
    }

    @Override
    public void write(PacketBufferSender packetBufferSender) {
        packetBufferSender.writeLong(pos.asLong());
    }

    @Override
    public void runClient() {
        // open the screen
    }
}
