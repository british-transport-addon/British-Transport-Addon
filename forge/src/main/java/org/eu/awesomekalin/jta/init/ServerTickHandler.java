package org.eu.awesomekalin.jta.init;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import org.eu.awesomekalin.jta.mod.Init;
import org.eu.awesomekalin.jta.mod.init.ItemInit;
import org.eu.awesomekalin.jta.mod.init.SoundInit;

@Mod.EventBusSubscriber(modid = Init.MOD_ID)
public class ServerTickHandler {

    private static int tickCounter = 0;
    private static final int INTERVAL_TICKS = 100; // Replace with your desired interval

    public static void register() {
        MinecraftForge.EVENT_BUS.register(ServerTickHandler.class);
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            tickCounter++;
            if (tickCounter >= INTERVAL_TICKS) {
                MinecraftServer server = event.getServer();
                for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                    if (hasEquippedAttachment(player, new ItemStack(ItemInit.AXON_BODY_3.get().data))) {
                        playSound(player);
                    }
                }
                tickCounter = 0; // Reset the tick counter
            }
        }
    }

    private static boolean hasEquippedAttachment(ServerPlayer player, ItemStack itemStack) {
        // Implement your condition to check if the player has the specified item equipped
        // This is a placeholder implementation
        return player.getInventory().contains(itemStack);
    }

    private static void playSound(ServerPlayer player) {
        // Play a sound to the player
        player.playNotifySound(SoundInit.AXON_BODY_3.get().data, SoundSource.PLAYERS, 1.0F, 1.0F);
    }
}
