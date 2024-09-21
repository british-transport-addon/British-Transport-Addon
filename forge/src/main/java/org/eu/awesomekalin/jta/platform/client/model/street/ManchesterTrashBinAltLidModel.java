package org.eu.awesomekalin.jta.platform.client.model.street;

import net.minecraft.resources.ResourceLocation;
import org.eu.awesomekalin.jta.mod.Init;
import org.eu.awesomekalin.jta.mod.entity.block.street.ManchesterTrashBinAltLidEntity;
import software.bernie.geckolib.model.GeoModel;

public class ManchesterTrashBinAltLidModel extends GeoModel<ManchesterTrashBinAltLidEntity> {

    @Override
    public ResourceLocation getModelResource(ManchesterTrashBinAltLidEntity animatable) {
        return new ResourceLocation(Init.MOD_ID, "geo/manchester_trash_bin_alt_lid.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ManchesterTrashBinAltLidEntity animatable) {
        return new ResourceLocation(Init.MOD_ID, "textures/manchester_trash_bin_alt_lid.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ManchesterTrashBinAltLidEntity animatable) {
        return new ResourceLocation(Init.MOD_ID, "animations/manchester_trash_bin_alt_lid.animation.json");
    }
}
