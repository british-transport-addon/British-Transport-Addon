package org.eu.awesomekalin.jta.mod.packet.entity;

import org.eu.awesomekalin.jta.mod.entity.block.OneLineBlockEntity;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.registry.PacketHandler;
import org.mtr.mapping.tool.PacketBufferReceiver;
import org.mtr.mapping.tool.PacketBufferSender;

public class PacketUpdateOneLineBlockEntity extends PacketHandler {
    public final BlockPos blockPos;
    public final String line0;

    public PacketUpdateOneLineBlockEntity(PacketBufferReceiver receiver) {
        blockPos = BlockPos.fromLong(receiver.readLong());
        line0 = receiver.readString();
    }

    public PacketUpdateOneLineBlockEntity(BlockPos blockPos, String line0) {
        this.blockPos = blockPos;
        this.line0 = line0;
    }

    @Override
    public void write(PacketBufferSender packetBufferSender) {
        packetBufferSender.writeLong(blockPos.asLong());
        packetBufferSender.writeString(line0);
    }

    @Override
    public void runServer(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity) {
        final BlockEntity entity = serverPlayerEntity.getEntityWorld().getBlockEntity(blockPos);
        if (entity != null && entity.data instanceof OneLineBlockEntity) {
            ((OneLineBlockEntity) entity.data).setLine0(MutableText.cast(Text.of(line0)));
        }
    }
}
