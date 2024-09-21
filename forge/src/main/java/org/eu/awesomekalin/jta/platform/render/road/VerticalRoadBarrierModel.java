package org.eu.awesomekalin.jta.platform.render.road;

import net.minecraft.resources.ResourceLocation;
import org.eu.awesomekalin.jta.mod.Init;
import org.eu.awesomekalin.jta.mod.entity.block.road.VerticalRoadBarrierBlockEntity;
import software.bernie.geckolib.model.GeoModel;

public class VerticalRoadBarrierModel extends GeoModel<VerticalRoadBarrierBlockEntity> {

    @Override
    public ResourceLocation getModelResource(VerticalRoadBarrierBlockEntity animatable) {
        return new ResourceLocation(Init.MOD_ID, "geo/vertical_road_barrier.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(VerticalRoadBarrierBlockEntity animatable) {
        return new ResourceLocation(Init.MOD_ID, "textures/vertical_road_barrier.png");
    }

    @Override
    public ResourceLocation getAnimationResource(VerticalRoadBarrierBlockEntity animatable) {
        return new ResourceLocation(Init.MOD_ID, "animations/vertical_road_barrier.animation.json");
    }
}
