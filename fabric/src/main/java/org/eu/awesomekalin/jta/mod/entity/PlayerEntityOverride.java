package org.eu.awesomekalin.jta.mod.entity;

import net.minecraft.entity.EntityType;
import software.bernie.geckolib.GeckoLib;
import software.bernie.geckolib.animatable.GeoReplacedEntity;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.renderer.GeoReplacedEntityRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

public class PlayerEntityOverride implements GeoReplacedEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::pedicate));
    }

    private PlayState pedicate(AnimationState<PlayerEntityOverride> state) {
        state.getController().setAnimation(RawAnimation.begin().then("smoke", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public EntityType<?> getReplacingEntityType() {
        return EntityType.PLAYER;
    }
}
