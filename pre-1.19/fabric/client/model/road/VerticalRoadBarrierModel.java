package org.eu.awesomekalin.jta.platform.client.model.road;

import net.minecraft.util.Identifier;
import org.eu.awesomekalin.jta.mod.Init;
import org.eu.awesomekalin.jta.mod.entity.block.road.VerticalRoadBarrierBlockEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class VerticalRoadBarrierModel extends AnimatedGeoModel<VerticalRoadBarrierBlockEntity> {

    @Override
    public Identifier getModelLocation(VerticalRoadBarrierBlockEntity animatable) {
        return new Identifier(Init.MOD_ID, "geo/vertical_road_barrier.geo.json");
    }

    @Override
    public Identifier getTextureLocation(VerticalRoadBarrierBlockEntity animatable) {
        return new Identifier(Init.MOD_ID, "textures/vertical_road_barrier.png");
    }

    @Override
    public Identifier getAnimationFileLocation(VerticalRoadBarrierBlockEntity animatable) {
        return new Identifier(Init.MOD_ID, "animations/vertical_road_barrier.animation.json");
    }
}
