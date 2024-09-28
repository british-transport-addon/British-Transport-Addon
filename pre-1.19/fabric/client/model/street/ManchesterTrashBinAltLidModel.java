package org.eu.awesomekalin.jta.platform.client.model.street;

import net.minecraft.util.Identifier;
import org.eu.awesomekalin.jta.mod.Init;
import org.eu.awesomekalin.jta.mod.entity.block.street.ManchesterTrashBinAltLidEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ManchesterTrashBinAltLidModel extends AnimatedGeoModel<ManchesterTrashBinAltLidEntity> {

    @Override
    public Identifier getModelLocation(ManchesterTrashBinAltLidEntity animatable) {
        return new Identifier(Init.MOD_ID, "geo/manchester_trash_bin_alt_lid.geo.json");
    }

    @Override
    public Identifier getTextureLocation(ManchesterTrashBinAltLidEntity animatable) {
        return new Identifier(Init.MOD_ID, "textures/manchester_trash_bin_alt_lid.png");
    }

    @Override
    public Identifier getAnimationFileLocation(ManchesterTrashBinAltLidEntity animatable) {
        return new Identifier(Init.MOD_ID, "animations/manchester_trash_bin_alt_lid.animation.json");
    }
}
