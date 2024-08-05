package org.eu.awesomekalin.jta.mod.packet.entity;

import org.eu.awesomekalin.jta.mod.entity.SevenLineBlockEntity;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.tool.PacketBufferReceiver;
import org.mtr.mapping.tool.PacketBufferSender;

public class PacketUpdateSevenLineBlockEntity extends PacketUpdateSixLineBlockEntity {
    public final String line6;

    public PacketUpdateSevenLineBlockEntity(PacketBufferReceiver receiver) {
        super(receiver);
        line6 = receiver.readString();
    }

    public PacketUpdateSevenLineBlockEntity(BlockPos blockPos, String line0, String line1, String line2, String line3, String line4, String line5, String line6) {
        super(blockPos, line0, line1, line2, line3, line4, line5);
        this.line6 = line6;
    }

    @Override
    public void write(PacketBufferSender packetBufferSender) {
        super.write(packetBufferSender);
        packetBufferSender.writeString(line6);
    }

    @Override
    public void runServer(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity) {
        final BlockEntity entity = serverPlayerEntity.getEntityWorld().getBlockEntity(blockPos);
        if (entity != null && entity.data instanceof SevenLineBlockEntity) {
            ((SevenLineBlockEntity) entity.data).setLine6(MutableText.cast(Text.of(line6)));
        }
        super.runServer(minecraftServer, serverPlayerEntity);
    }
}
