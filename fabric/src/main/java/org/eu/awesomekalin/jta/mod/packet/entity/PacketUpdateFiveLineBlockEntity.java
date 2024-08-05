package org.eu.awesomekalin.jta.mod.packet.entity;

import org.eu.awesomekalin.jta.mod.entity.FiveLineBlockEntity;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.tool.PacketBufferReceiver;
import org.mtr.mapping.tool.PacketBufferSender;

public class PacketUpdateFiveLineBlockEntity extends PacketUpdateFourLineBlockEntity {
    public final String line4;

    public PacketUpdateFiveLineBlockEntity(PacketBufferReceiver receiver) {
        super(receiver);
        line4 = receiver.readString();
    }

    public PacketUpdateFiveLineBlockEntity(BlockPos blockPos, String line0, String line1, String line2, String line3, String line4) {
        super(blockPos, line0, line1, line2, line3);
        this.line4 = line4;
    }

    @Override
    public void write(PacketBufferSender packetBufferSender) {
        super.write(packetBufferSender);
        packetBufferSender.writeString(line4);
    }

    @Override
    public void runServer(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity) {
        final BlockEntity entity = serverPlayerEntity.getEntityWorld().getBlockEntity(blockPos);
        if (entity != null && entity.data instanceof FiveLineBlockEntity) {
            ((FiveLineBlockEntity) entity.data).setLine4(MutableText.cast(Text.of(line4)));
        }
        super.runServer(minecraftServer, serverPlayerEntity);
    }
}
