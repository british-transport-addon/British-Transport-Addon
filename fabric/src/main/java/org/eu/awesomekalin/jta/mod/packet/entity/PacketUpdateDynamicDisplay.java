package org.eu.awesomekalin.jta.mod.packet.entity;

import org.eu.awesomekalin.jta.mod.blocks.directional.DisplayBlock;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.registry.PacketHandler;
import org.mtr.mapping.tool.PacketBufferReceiver;
import org.mtr.mapping.tool.PacketBufferSender;

public class PacketUpdateDynamicDisplay extends PacketHandler {
    public final BlockPos blockPos;
    public ObjectArrayList<String> selectedIds;
    private final int numberOfIds;

    public PacketUpdateDynamicDisplay(PacketBufferReceiver receiver) {
        blockPos = BlockPos.fromLong(receiver.readLong());

        selectedIds = new  ObjectArrayList<>();
        numberOfIds = receiver.readInt();

        for (int i = 0; i < numberOfIds; i++) {
            selectedIds.add(receiver.readString());
        }
    }

    public PacketUpdateDynamicDisplay(BlockPos blockPos, ObjectArrayList<String> selectedIds) {
        this.blockPos = blockPos;
        this.selectedIds = selectedIds;
        this.numberOfIds = selectedIds.size();
    }

    @Override
    public void write(PacketBufferSender packetBufferSender) {
        packetBufferSender.writeLong(blockPos.asLong());
        packetBufferSender.writeInt(numberOfIds);
        for (int i = 0; i < numberOfIds; i++) {
            packetBufferSender.writeString(selectedIds.get(i));
        }
    }

    @Override
    public void runServer(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity) {
        final BlockEntity entity = serverPlayerEntity.getEntityWorld().getBlockEntity(blockPos);
        if (entity != null && entity.data instanceof DisplayBlock.DisplayBlockEntity) {
            ((DisplayBlock.DisplayBlockEntity) entity.data).setSelectedIds(selectedIds);
        }
    }
}
