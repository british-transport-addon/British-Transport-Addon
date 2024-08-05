package org.eu.awesomekalin.jta.mod.packet.entity;

import org.eu.awesomekalin.jta.mod.entity.SixLineBlockEntity;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.tool.PacketBufferReceiver;
import org.mtr.mapping.tool.PacketBufferSender;

public class PacketUpdateSixLineBlockEntity extends PacketUpdateFiveLineBlockEntity {
    public final String line5;

    public PacketUpdateSixLineBlockEntity(PacketBufferReceiver receiver) {
        super(receiver);
        line5 = receiver.readString();
    }

    public PacketUpdateSixLineBlockEntity(BlockPos blockPos, String line0, String line1, String line2, String line3, String line4, String line5) {
        super(blockPos, line0, line1, line2, line3, line4);
        this.line5 = line5;
    }

    @Override
    public void write(PacketBufferSender packetBufferSender) {
        super.write(packetBufferSender);
        packetBufferSender.writeString(line5);
    }

    @Override
    public void runServer(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity) {
        final BlockEntity entity = serverPlayerEntity.getEntityWorld().getBlockEntity(blockPos);
        if (entity != null && entity.data instanceof SixLineBlockEntity) {
            ((SixLineBlockEntity) entity.data).setLine5(MutableText.cast(Text.of(line5)));
        }
        super.runServer(minecraftServer, serverPlayerEntity);
    }
}
