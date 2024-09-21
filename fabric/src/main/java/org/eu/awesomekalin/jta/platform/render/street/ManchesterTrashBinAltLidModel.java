package org.eu.awesomekalin.jta.platform.render.street;

import net.minecraft.util.Identifier;
import org.eu.awesomekalin.jta.mod.Init;
import org.eu.awesomekalin.jta.mod.entity.block.street.ManchesterTrashBinAltLidEntity;
import software.bernie.geckolib.model.GeoModel;

public class ManchesterTrashBinAltLidModel extends GeoModel<ManchesterTrashBinAltLidEntity> {

    @Override
    public Identifier getModelResource(ManchesterTrashBinAltLidEntity animatable) {
        return new Identifier(Init.MOD_ID, "geo/manchester_trash_bin_alt_lid.geo.json");
    }

    @Override
    public Identifier getTextureResource(ManchesterTrashBinAltLidEntity animatable) {
        return new Identifier(Init.MOD_ID, "textures/manchester_trash_bin_alt_lid.png");
    }

    @Override
    public Identifier getAnimationResource(ManchesterTrashBinAltLidEntity animatable) {
        return new Identifier(Init.MOD_ID, "animations/manchester_trash_bin_alt_lid.animation.json");
    }
}
