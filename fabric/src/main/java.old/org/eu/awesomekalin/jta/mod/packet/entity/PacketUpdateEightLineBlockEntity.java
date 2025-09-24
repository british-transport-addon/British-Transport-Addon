package org.eu.awesomekalin.jta.mod.packet.entity;

import org.eu.awesomekalin.jta.mod.entity.block.EightLineBlockEntity;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.tool.PacketBufferReceiver;
import org.mtr.mapping.tool.PacketBufferSender;

public class PacketUpdateEightLineBlockEntity extends PacketUpdateSevenLineBlockEntity {
    public final String line7;

    public PacketUpdateEightLineBlockEntity(PacketBufferReceiver receiver) {
        super(receiver);
        line7 = receiver.readString();
    }

    public PacketUpdateEightLineBlockEntity(BlockPos blockPos, String line0, String line1, String line2, String line3, String line4, String line5, String line6, String line7) {
        super(blockPos, line0, line1, line2, line3, line4, line5, line6);
        this.line7 = line7;
    }

    @Override
    public void write(PacketBufferSender packetBufferSender) {
        super.write(packetBufferSender);
        packetBufferSender.writeString(line7);
    }

    @Override
    public void runServer(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity) {
        final BlockEntity entity = serverPlayerEntity.getEntityWorld().getBlockEntity(blockPos);
        if (entity != null && entity.data instanceof EightLineBlockEntity) {
            ((EightLineBlockEntity) entity.data).setLine7(MutableText.cast(Text.of(line7)));
        }
        super.runServer(minecraftServer, serverPlayerEntity);
    }
}
