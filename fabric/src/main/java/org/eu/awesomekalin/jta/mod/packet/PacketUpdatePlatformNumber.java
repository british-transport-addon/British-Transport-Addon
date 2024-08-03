package org.eu.awesomekalin.jta.mod.packet;

import org.eu.awesomekalin.jta.mod.blocks.directional.UndergroundWhiteboard;
import org.eu.awesomekalin.jta.mod.blocks.directional.rail.PlatformNumberSign;
import org.mtr.mapping.holder.BlockEntity;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.MinecraftServer;
import org.mtr.mapping.holder.ServerPlayerEntity;
import org.mtr.mapping.registry.PacketHandler;
import org.mtr.mapping.tool.PacketBufferReceiver;
import org.mtr.mapping.tool.PacketBufferSender;

public final class PacketUpdatePlatformNumber extends PacketHandler {
    private final BlockPos blockPos;
    private final String number;
    public PacketUpdatePlatformNumber(PacketBufferReceiver receiver) {
        blockPos = BlockPos.fromLong(receiver.readLong());
        number = receiver.readString();
    }

    public PacketUpdatePlatformNumber(BlockPos blockPos, String number) {
        this.blockPos = blockPos;
        this.number = number;
    }

    @Override
    public void write(PacketBufferSender packetBufferSender) {
        packetBufferSender.writeLong(blockPos.asLong());
        packetBufferSender.writeString(number);
    }

    @Override
    public void runServer(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity) {
        System.out.println(number);
        System.out.println("updated whiteboard packet");
        final BlockEntity entity = serverPlayerEntity.getEntityWorld().getBlockEntity(blockPos);
        if (entity != null && entity.data instanceof PlatformNumberSign.PlatfornNumberSignEntity) {
        System.out.println("updated existing whiteboarxd");
            ((PlatformNumberSign.PlatfornNumberSignEntity) entity.data).setData(number);
        }
    }
}
