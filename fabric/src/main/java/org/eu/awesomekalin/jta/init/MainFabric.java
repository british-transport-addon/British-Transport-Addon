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
    @Override
    public void onInitialize() {
        Init.init();
    }
}
