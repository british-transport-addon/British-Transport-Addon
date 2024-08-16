package org.eu.awesomekalin.jta.mod.entity.block.street;

import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
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

public class ManchesterTrashBinAltLidEntity extends BlockEntityExtension implements GeoBlockEntity {
    private long open = -1;
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public void setOpen(boolean open) {
        this.open = open ? System.currentTimeMillis() + 2000 : -1;
    }

    public boolean isOpen() {
        return open != -1 && System.currentTimeMillis() < open;
    }

    public ManchesterTrashBinAltLidEntity(BlockPos blockPos, BlockState blockState) {
        super(BlockEntityTypeInit.MANCHESTER_TRASH_BIN_ALT_LID.get(), blockPos, blockState);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::pedicate));
    }

    private PlayState pedicate(AnimationState<GeoAnimatable> state) {
        state.getController().setAnimation(RawAnimation.begin().then("animation.model." + (isOpen() ? "open" : "close"), Animation.LoopType.HOLD_ON_LAST_FRAME));

        return PlayState.CONTINUE;
    }

    @Override
    public void readCompoundTag(CompoundTag compoundTag) {
        super.readCompoundTag(compoundTag);
        this.open = compoundTag.getLong("open");
    }

    @Override
    public void writeCompoundTag(CompoundTag compoundTag) {
        super.writeCompoundTag(compoundTag);
        compoundTag.putLong("open", open);
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

