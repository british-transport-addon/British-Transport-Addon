package org.eu.awesomekalin.jta.mod.entity.block.road;

import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.mtr.mapping.holder.BlockEntityType;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.holder.CompoundTag;
import org.mtr.mapping.mapper.BlockEntityExtension;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;

public class VerticalRoadBarrierBlockEntity extends BlockEntityExtension implements GeoBlockEntity {
    private boolean open = false;
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public VerticalRoadBarrierBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(BlockEntityTypeInit.VERTICAL_ROAD_BARRIER.get(), blockPos, blockState);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::pedicate));
    }

    private PlayState pedicate(AnimationState<GeoAnimatable> state) {
        state.getController().setAnimation(RawAnimation.begin().then("animation.vertical_road_barrier." + (open ? "open" : "closed"), Animation.LoopType.HOLD_ON_LAST_FRAME));
        return PlayState.CONTINUE;
    }

    @Override
    public void readCompoundTag(CompoundTag compoundTag) {
        super.readCompoundTag(compoundTag);
        this.open = compoundTag.getBoolean("open");
    }

    @Override
    public void writeCompoundTag(CompoundTag compoundTag) {
        super.writeCompoundTag(compoundTag);
        compoundTag.putBoolean("open", open);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object blockEntity) {
        return RenderUtils.getCurrentTick();
    }
}
