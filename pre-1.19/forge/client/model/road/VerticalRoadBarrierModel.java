package org.eu.awesomekalin.jta.platform.client.model.road;

import net.minecraft.resources.ResourceLocation;
import org.eu.awesomekalin.jta.mod.Init;
import org.eu.awesomekalin.jta.mod.entity.block.road.VerticalRoadBarrierBlockEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class VerticalRoadBarrierModel extends AnimatedGeoModel<VerticalRoadBarrierBlockEntity> {

    @Override
    public ResourceLocation getModelLocation(VerticalRoadBarrierBlockEntity animatable) {
        return new ResourceLocation(Init.MOD_ID, "geo/vertical_road_barrier.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(VerticalRoadBarrierBlockEntity animatable) {
        return new ResourceLocation(Init.MOD_ID, "textures/vertical_road_barrier.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(VerticalRoadBarrierBlockEntity animatable) {
        return new ResourceLocation(Init.MOD_ID, "animations/vertical_road_barrier.animation.json");
    }
}
