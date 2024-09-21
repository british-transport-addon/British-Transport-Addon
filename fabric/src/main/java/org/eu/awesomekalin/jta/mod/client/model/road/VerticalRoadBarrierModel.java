package org.eu.awesomekalin.jta.mod.client.model.road;

import net.minecraft.util.Identifier;
import org.eu.awesomekalin.jta.mod.Init;
import org.eu.awesomekalin.jta.mod.entity.block.road.VerticalRoadBarrierBlockEntity;
import software.bernie.geckolib.model.GeoModel;

public class VerticalRoadBarrierModel extends GeoModel<VerticalRoadBarrierBlockEntity> {

    @Override
    public Identifier getModelResource(VerticalRoadBarrierBlockEntity animatable) {
        return new Identifier(Init.MOD_ID, "geo/vertical_road_barrier.geo.json");
    }

    @Override
    public Identifier getTextureResource(VerticalRoadBarrierBlockEntity animatable) {
        return new Identifier(Init.MOD_ID, "textures/vertical_road_barrier.png");
    }

    @Override
    public Identifier getAnimationResource(VerticalRoadBarrierBlockEntity animatable) {
        return new Identifier(Init.MOD_ID, "animations/vertical_road_barrier.animation.json");
    }
}
