package org.eu.awesomekalin.jta.mod.items;

import org.eu.awesomekalin.jta.mod.packet.PacketOpenRadioScreen;
import org.eu.awesomekalin.jta.mod.screen.radio.RadioMainScreen;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.ItemExtension;

public class RadioItem extends ItemExtension {

    public RadioItem() {
        super(new ItemSettings().maxCount(1));
    }

    @Override
    public void useWithoutResult(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            PacketOpenRadioScreen.sendDirectlyToServer(ServerWorld.cast(world), ServerPlayerEntity.cast(user));
        }
    }
}
