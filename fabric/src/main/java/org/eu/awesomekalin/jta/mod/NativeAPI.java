package org.eu.awesomekalin.jta.mod;

import dev.emi.trinkets.api.SlotReference;
import net.minecraft.util.Pair;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.registry.BlockEntityTypeRegistryObject;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public interface NativeAPI {

    public boolean hasEquippedAttachment(PlayerEntity player, ItemStack item);
    public Pair<Integer, ItemStack> getEquippedAttachment(PlayerEntity player, Item item);
    public void registerShieldItemProperties();
}
