package org.eu.awesomekalin.jta.mod.packet.entity;

import org.eu.awesomekalin.jta.mod.entity.block.FourLineBlockEntity;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.tool.PacketBufferReceiver;
import org.mtr.mapping.tool.PacketBufferSender;

public class PacketUpdateFourLineBlockEntity extends PacketUpdateThreeLineBlockEntity {
    public final String line3;

    public PacketUpdateFourLineBlockEntity(PacketBufferReceiver receiver) {
        super(receiver);
        line3 = receiver.readString();
    }

    public PacketUpdateFourLineBlockEntity(BlockPos blockPos, String line0, String line1, String line2, String line3) {
        super(blockPos, line0, line1, line2);
        this.line3 = line3;
    }

    @Override
    public void write(PacketBufferSender packetBufferSender) {
        super.write(packetBufferSender);
        packetBufferSender.writeString(line3);
    }

    @Override
    public void runServer(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity) {
        final BlockEntity entity = serverPlayerEntity.getEntityWorld().getBlockEntity(blockPos);
        if (entity != null && entity.data instanceof FourLineBlockEntity) {
            ((FourLineBlockEntity) entity.data).setLine3(MutableText.cast(Text.of(line3)));
        }
        super.runServer(minecraftServer, serverPlayerEntity);
    }
}
