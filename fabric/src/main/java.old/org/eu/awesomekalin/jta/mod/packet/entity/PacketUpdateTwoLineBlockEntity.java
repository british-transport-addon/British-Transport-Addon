package org.eu.awesomekalin.jta.mod.packet.entity;

import org.eu.awesomekalin.jta.mod.entity.block.TwoLineBlockEntity;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.tool.PacketBufferReceiver;
import org.mtr.mapping.tool.PacketBufferSender;

public class PacketUpdateTwoLineBlockEntity extends PacketUpdateOneLineBlockEntity {
    public final String line1;

    public PacketUpdateTwoLineBlockEntity(PacketBufferReceiver receiver) {
        super(receiver);
        line1 = receiver.readString();
    }

    public PacketUpdateTwoLineBlockEntity(BlockPos blockPos, String line0, String line1) {
        super(blockPos, line0);
        this.line1 = line1;
    }

    @Override
    public void write(PacketBufferSender packetBufferSender) {
        super.write(packetBufferSender);
        packetBufferSender.writeString(line1);
    }

    @Override
    public void runServer(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity) {
        final BlockEntity entity = serverPlayerEntity.getEntityWorld().getBlockEntity(blockPos);
        if (entity != null && entity.data instanceof TwoLineBlockEntity) {
            ((TwoLineBlockEntity) entity.data).setLine1(MutableText.cast(Text.of(line1)));
        }
        super.runServer(minecraftServer, serverPlayerEntity);
    }
}
