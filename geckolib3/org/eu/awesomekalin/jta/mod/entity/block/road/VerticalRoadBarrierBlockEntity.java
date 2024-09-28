package org.eu.awesomekalin.jta.mod.entity.block.road;

import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.holder.CompoundTag;
import org.mtr.mapping.mapper.BlockEntityExtension;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.core.manager.SingletonAnimationFactory;
import software.bernie.geckolib3.core.PlayState;

public class VerticalRoadBarrierBlockEntity extends BlockEntityExtension implements IAnimatable {
    private boolean open = false;
    private AnimationFactory cache = new SingletonAnimationFactory(this);

    public VerticalRoadBarrierBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(BlockEntityTypeInit.VERTICAL_ROAD_BARRIER.get(), blockPos, blockState);
    }

    @Override
    public void registerControllers(final AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::pedicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return cache;
    }

    private <E extends VerticalRoadBarrierBlockEntity> PlayState pedicate(final AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().playAndHold("animation.vertical_road_barrier." + (open ? "open" : "closed")));
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
}
