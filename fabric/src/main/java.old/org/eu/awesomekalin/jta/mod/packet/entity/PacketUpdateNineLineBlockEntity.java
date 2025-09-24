package org.eu.awesomekalin.jta.mod.packet.entity;

import org.eu.awesomekalin.jta.mod.entity.block.NineLineBlockEntity;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.tool.PacketBufferReceiver;
import org.mtr.mapping.tool.PacketBufferSender;

public class PacketUpdateNineLineBlockEntity extends PacketUpdateEightLineBlockEntity {
    public final String line8;

    public PacketUpdateNineLineBlockEntity(PacketBufferReceiver receiver) {
        super(receiver);
        line8 = receiver.readString();
    }

    public PacketUpdateNineLineBlockEntity(BlockPos blockPos, String line0, String line1, String line2, String line3, String line4, String line5, String line6, String line7, String line8) {
        super(blockPos, line0, line1, line2, line3, line4, line5, line6, line7);
        this.line8 = line8;
    }

    @Override
    public void write(PacketBufferSender packetBufferSender) {
        super.write(packetBufferSender);
        packetBufferSender.writeString(line8);
    }

    @Override
    public void runServer(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity) {
        final BlockEntity entity = serverPlayerEntity.getEntityWorld().getBlockEntity(blockPos);
        if (entity != null && entity.data instanceof NineLineBlockEntity) {
            ((NineLineBlockEntity) entity.data).setLine8(MutableText.cast(Text.of(line8)));
        }
        super.runServer(minecraftServer, serverPlayerEntity);
    }
}
