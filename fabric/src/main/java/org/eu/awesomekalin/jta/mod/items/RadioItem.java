package org.eu.awesomekalin.jta.mod.items;

import org.eu.awesomekalin.jta.mod.packet.PacketOpenRadioScreen;
import org.eu.awesomekalin.jta.mod.screen.radio.RadioMainScreen;
import org.eu.awesomekalin.jta.mod.util.RadioUtil;
import org.jetbrains.annotations.Nullable;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.ItemExtension;

import java.util.List;

public class RadioItem extends ItemExtension {

    public RadioItem() {
        super(new ItemSettings().maxCount(1));
    }

    @Override
    public void addTooltips(ItemStack stack, @Nullable World world, List<MutableText> tooltip, TooltipContext options) {
        tooltip.add(MutableText.cast(Text.of("Tuned Channel: " + RadioUtil.getRadioChannel(stack))));
        tooltip.add(MutableText.cast(Text.of("Receiving: " + RadioUtil.isRadioReceiving(stack))));
    }

    @Override
    public void useWithoutResult(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            PacketOpenRadioScreen.sendDirectlyToServer(ServerWorld.cast(world), ServerPlayerEntity.cast(user));
        }

    }
}
