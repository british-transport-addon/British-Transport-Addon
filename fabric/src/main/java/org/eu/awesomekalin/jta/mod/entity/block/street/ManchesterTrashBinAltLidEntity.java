package org.eu.awesomekalin.jta.mod.entity.block.street;

import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.holder.CompoundTag;
import org.mtr.mapping.mapper.BlockEntityExtension;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.core.manager.SingletonAnimationFactory;

public class ManchesterTrashBinAltLidEntity extends BlockEntityExtension implements IAnimatable {
    private long open = -1;
    private AnimationFactory cache = new SingletonAnimationFactory(this);

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
    public void registerControllers(final AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::pedicate));
    }

    private <E extends ManchesterTrashBinAltLidEntity> PlayState pedicate(final AnimationEvent<E> state) {
        state.getController().setAnimation(new AnimationBuilder().playAndHold("animation.model." + (isOpen() ? "open" : "close")));

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
    public AnimationFactory getFactory() {
        return cache;
    }
}

