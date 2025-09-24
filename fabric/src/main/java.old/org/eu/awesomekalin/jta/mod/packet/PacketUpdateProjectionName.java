package org.eu.awesomekalin.jta.mod.packet;

import org.eu.awesomekalin.jta.mod.blocks.directional.rail.ProjectionName;
import org.mtr.mapping.holder.BlockEntity;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.MinecraftServer;
import org.mtr.mapping.holder.ServerPlayerEntity;
import org.mtr.mapping.registry.PacketHandler;
import org.mtr.mapping.tool.PacketBufferReceiver;
import org.mtr.mapping.tool.PacketBufferSender;

public final class PacketUpdateProjectionName extends PacketHandler {
    private final BlockPos blockPos;
    private final boolean isUppercase;
    private final String prefix;
    private final String suffix;
    private final float maxWidth;
    private final float maxScale;
    public PacketUpdateProjectionName(PacketBufferReceiver receiver) {
        blockPos = BlockPos.fromLong(receiver.readLong());
        isUppercase = receiver.readBoolean();
        prefix = receiver.readString();
        suffix = receiver.readString();
        maxWidth = receiver.readFloat();
        maxScale = receiver.readFloat();
    }

    public PacketUpdateProjectionName(BlockPos blockPos, boolean isUppercase, String prefix, String suffix, float maxWidth, float maxScale) {
        this.blockPos = blockPos;
        this.isUppercase = isUppercase;
        this.prefix = prefix;
        this.suffix = suffix;
        this.maxWidth = maxWidth;
        this.maxScale = maxScale;
    }

    @Override
    public void write(PacketBufferSender packetBufferSender) {
        packetBufferSender.writeLong(blockPos.asLong());
        packetBufferSender.writeBoolean(isUppercase);
        packetBufferSender.writeString(prefix);
        packetBufferSender.writeString(suffix);
        packetBufferSender.writeFloat(maxWidth);
        packetBufferSender.writeFloat(maxScale);
    }

    @Override
    public void runServer(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity) {
        final BlockEntity entity = serverPlayerEntity.getEntityWorld().getBlockEntity(blockPos);
        if (entity != null && entity.data instanceof ProjectionName.ProjectionNameBlockEntity) {
            ((ProjectionName.ProjectionNameBlockEntity) entity.data).setData(isUppercase, prefix, suffix, maxWidth, maxScale);
        }
    }
}
