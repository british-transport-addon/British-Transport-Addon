package org.eu.awesomekalin.jta.mod.packet;

import org.eu.awesomekalin.jta.mod.blocks.directional.UndergroundWhiteboard;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.registry.PacketHandler;
import org.mtr.mapping.tool.PacketBufferReceiver;
import org.mtr.mapping.tool.PacketBufferSender;

public final class PacketUpdateUndergroundWhiteboard extends PacketHandler {
    private final BlockPos blockPos;
    private final String line0;
    private final String line1;
    private final String line2;
    private final String line3;
    private final String line4;
    private final String line5;
    private final String line6;
    private final String line7;
    private final String line8;
    public PacketUpdateUndergroundWhiteboard(PacketBufferReceiver receiver) {
        blockPos = BlockPos.fromLong(receiver.readLong());
        line0 = receiver.readString();
        line1 = receiver.readString();
        line2 = receiver.readString();
        line3 = receiver.readString();
        line4 = receiver.readString();
        line5 = receiver.readString();
        line6 = receiver.readString();
        line7 = receiver.readString();
        line8 = receiver.readString();
    }

    public PacketUpdateUndergroundWhiteboard(BlockPos blockPos, String line0, String line1, String line2, String line3, String line4, String line5, String line6, String line7, String line8) {
        this.blockPos = blockPos;
        this.line0 = line0;
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        this.line4 = line4;
        this.line5 = line5;
        this.line6 = line6;
        this.line7 = line7;
        this.line8 = line8;
    }

    @Override
    public void write(PacketBufferSender packetBufferSender) {
        packetBufferSender.writeLong(blockPos.asLong());
        packetBufferSender.writeString(line0);
        packetBufferSender.writeString(line1);
        packetBufferSender.writeString(line2);
        packetBufferSender.writeString(line3);
        packetBufferSender.writeString(line4);
        packetBufferSender.writeString(line5);
        packetBufferSender.writeString(line6);
        packetBufferSender.writeString(line7);
        packetBufferSender.writeString(line8);
    }

    @Override
    public void runServer(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity) {
        System.out.println(line0);
        System.out.println("updated whiteboard packet");
        final BlockEntity entity = serverPlayerEntity.getEntityWorld().getBlockEntity(blockPos);
        if (entity != null && entity.data instanceof UndergroundWhiteboard.UndergroundWhiteboardBlockEntity) {
        System.out.println("updated existing whiteboarxd");
            ((UndergroundWhiteboard.UndergroundWhiteboardBlockEntity) entity.data).setData(line0, line1, line2, line3, line4, line5, line6, line7, line8);
        }
    }
}
