package org.eu.awesomekalin.jta.mod.blocks.directional.rail;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.eu.awesomekalin.jta.mod.init.SoundInit;
import org.jetbrains.annotations.Nullable;
import org.mtr.core.data.Station;
import org.mtr.core.servlet.SystemMapServlet;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mapping.mapper.TextHelper;
import org.mtr.mod.Init;
import org.mtr.mod.InitClient;
import org.mtr.mod.block.BlockPIDSBase;
import org.mtr.mod.block.IBlock;
import org.mtr.mod.client.MinecraftClientData;
import org.mtr.mod.data.IGui;
import org.mtr.mod.render.RenderPIDS;
import org.mtr.mod.screen.DashboardScreen;

public abstract class AbstractStationSpeaker extends DirectionalBlockExtension {
    public AbstractStationSpeaker() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
    }


    @Override
    public void scheduledTick2(BlockState state, ServerWorld world, BlockPos pos, Random random) {

        final Station station = InitClient.findStation(pos);


        scheduleBlockTick(World.cast(world), pos, state.getBlock(), 20);
    }

    @Override
    public void onPlaced2(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        scheduleBlockTick(world, pos, state.getBlock(), 3 * 20);
    }
}
