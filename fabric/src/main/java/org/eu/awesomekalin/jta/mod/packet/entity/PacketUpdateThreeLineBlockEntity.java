package org.eu.awesomekalin.jta.mod.packet.entity;

import org.eu.awesomekalin.jta.mod.entity.ThreeLineBlockEntity;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.tool.PacketBufferReceiver;
import org.mtr.mapping.tool.PacketBufferSender;

public class PacketUpdateThreeLineBlockEntity extends PacketUpdateTwoLineBlockEntity {
    public final String line2;

    public PacketUpdateThreeLineBlockEntity(PacketBufferReceiver receiver) {
        super(receiver);
        line2 = receiver.readString();
    }

    public PacketUpdateThreeLineBlockEntity(BlockPos blockPos, String line0, String line1, String line2) {
        super(blockPos, line0, line1);
        this.line2 = line2;
    }

    @Override
    public void write(PacketBufferSender packetBufferSender) {
        super.write(packetBufferSender);
        packetBufferSender.writeString(line2);
    }

    @Override
    public void runServer(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity) {
        final BlockEntity entity = serverPlayerEntity.getEntityWorld().getBlockEntity(blockPos);
        if (entity != null && entity.data instanceof ThreeLineBlockEntity) {
            ((ThreeLineBlockEntity) entity.data).setLine2(MutableText.cast(Text.of(line2)));
        }
        super.runServer(minecraftServer, serverPlayerEntity);
    }
}
