package org.eu.awesomekalin.jta.init;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import org.eu.awesomekalin.jta.mod.Init;
import org.eu.awesomekalin.jta.mod.init.ItemInit;
import org.eu.awesomekalin.jta.mod.init.SoundInit;
import org.mtr.mapping.holder.PlayerEntity;
import org.mtr.mapping.holder.SoundCategory;

public class MainFabric implements ModInitializer {
    private int tickCounter = 0;
    private static final int TICKS_PER_SECOND = 20;
    private static final int INTERVAL_SECONDS = 10;
    private static final int INTERVAL_TICKS = INTERVAL_SECONDS * TICKS_PER_SECOND;

    @Override
    public void onInitialize() {
        // Schedule the repeating task
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            tickCounter++;
            if (tickCounter >= INTERVAL_TICKS) {
                server.getPlayerManager().getPlayerList().forEach(player -> {
                    if (player instanceof ServerPlayerEntity) {
                        // Your condition here
                        if (Init.NATIVE_API.hasEquippedAttachment(new PlayerEntity((net.minecraft.entity.player.PlayerEntity) player), ItemInit.AXON_BODY_3.get().getDefaultStack())) {
                            playSound(player);
                        }
                    }
                });
                tickCounter = 0; // Reset the tick counter
            }
        });

        Init.injectNativeAPI(new FabricNativeAPI());
        Init.init();
    }

    private void playSound(ServerPlayerEntity player) {
        player.getWorld().playSound(null, player.getBlockPos(), SoundInit.AXON_BODY_3.get().data, SoundCategory.VOICE.data, 1f, 1f);
    }
}
