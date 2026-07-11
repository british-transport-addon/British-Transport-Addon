package org.eu.awesomekalin.jta.mod.packet;

import org.eu.awesomekalin.jta.mod.screen.DisplaySelectorScreen;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.MinecraftClient;
import org.mtr.mapping.holder.Screen;
import org.mtr.mapping.registry.PacketHandler;
import org.mtr.mapping.tool.PacketBufferReceiver;
import org.mtr.mapping.tool.PacketBufferSender;

public class PacketOpenDisplaySelector extends PacketHandler {
    private final BlockPos pos;
    private final ObjectArrayList<String> selectedIds;


    public PacketOpenDisplaySelector(PacketBufferReceiver packetBufferReceiver) {
        this.pos = BlockPos.fromLong(packetBufferReceiver.readLong());
        final int totalSelected =  packetBufferReceiver.readInt();
        this.selectedIds = new ObjectArrayList<>();
        for (int i = 0; i < totalSelected; i++) this.selectedIds.add(packetBufferReceiver.readString());
    }

    public PacketOpenDisplaySelector(BlockPos pos, ObjectArrayList<String> selectedIds) {
        this.pos = pos;
        this.selectedIds = selectedIds;
    }

    @Override
    public void write(PacketBufferSender packetBufferSender) {
        packetBufferSender.writeLong(pos.asLong());
        packetBufferSender.writeInt(selectedIds.size());
        selectedIds.forEach(packetBufferSender::writeString);
    }

    @Override
    public void runClient() {
        ClientPacketHelper.openDisplaySelector(pos, selectedIds);
    }
}
