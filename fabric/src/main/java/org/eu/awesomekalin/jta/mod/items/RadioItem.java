package org.eu.awesomekalin.jta.mod.items;

import org.eu.awesomekalin.jta.mod.Init;
import org.eu.awesomekalin.jta.mod.init.SoundInit;
import org.eu.awesomekalin.jta.mod.packet.PacketOpenRadioScreen;
import org.eu.awesomekalin.jta.mod.util.RadioUtil;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.ItemExtension;

import java.util.List;
import javax.annotation.Nullable;

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
            Init.REGISTRY.sendPacketToClient(ServerPlayerEntity.cast(user), new PacketOpenRadioScreen(null));
            //PacketOpenRadioScreen.sendDirectlyToServer(ServerWorld.cast(world), ServerPlayerEntity.cast(user));
            world.playSound(null, user.getBlockPos(), SoundInit.MET_POLICE_RADIO_INTERACT.get(), SoundCategory.VOICE, 1f, 1f);
        }

    }
}
