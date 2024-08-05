package org.eu.awesomekalin.jta.mod.blocks.directional.rail;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.eu.awesomekalin.jta.mod.screen.station.ProjectionNameScreen;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mapping.mapper.BlockWithEntity;
import org.mtr.mod.block.BlockStationNameWallWhite;

import javax.annotation.Nonnull;

public class ProjectionName extends DirectionalBlockExtension implements BlockWithEntity {

    public ProjectionName() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ProjectionNameBlockEntity(blockPos, blockState);
    }

    public BlockState getPlacementState2(ItemPlacementContext ctx) {
        Direction side = ctx.getSide();
        return side != Direction.UP && side != Direction.DOWN ? this.getDefaultState2().with(new Property<>(FACING.data), side.getOpposite().data) : null;
    }

    @Override
    public ActionResult onUse2(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ProjectionNameScreen.handle(pos);
        return ActionResult.SUCCESS;
    }

    public static class ProjectionNameBlockEntity extends BlockEntityExtension {
        private boolean toggleUppercase;
        private MutableText prefix, suffix;
        private float maxWidth, maxScale;


        public ProjectionNameBlockEntity(BlockPos pos, BlockState state) {
            super(BlockEntityTypeInit.PROJECTION_NAME.get(), pos, state);
            this.toggleUppercase = false;
            this.prefix = MutableText.cast(Text.of(""));
            this.suffix = MutableText.cast(Text.of("Station"));
            this.maxWidth = 32 / 16F;
            this.maxScale = 0.2F / 8;
        }

        public MutableText getPrefix() {
            return prefix;
        }

        public MutableText getSuffix() {
            return suffix;
        }

        public float getMaxScale() {
            return maxScale;
        }

        public float getMaxWidth() {
            return maxWidth;
        }

        public boolean isToggleUppercase() {
            return toggleUppercase;
        }

        @Override
        public void readCompoundTag(CompoundTag compoundTag) {
            super.readCompoundTag(compoundTag);
            this.toggleUppercase = compoundTag.getBoolean("toggleUppercase");
            this.prefix = MutableText.cast(Text.of(compoundTag.getString("prefix")));
            this.suffix = MutableText.cast(Text.of(compoundTag.getString("suffix")));
            this.maxWidth = compoundTag.getFloat("maxWidth");
            this.maxScale = compoundTag.getFloat("maxScale");
        }

        @Override
        public void writeCompoundTag(CompoundTag compoundTag) {
            super.writeCompoundTag(compoundTag);
            compoundTag.putBoolean("toggleUppercase", toggleUppercase);
            compoundTag.putString("prefix", prefix.getString());
            compoundTag.putString("suffix", suffix.getString());
            compoundTag.putFloat("maxWidth", maxWidth);
            compoundTag.putFloat("maxScale", maxScale);
        }

        public void setData(boolean isUppercase, String prefix, String suffix, float maxWidth, float maxScale) {
            this.toggleUppercase = isUppercase;
            this.prefix = MutableText.cast(Text.of(prefix));
            this.suffix = MutableText.cast(Text.of(suffix));
            this.maxWidth = maxWidth;
            this.maxScale = maxScale;
        }

        // we need menu fopr brush with two text box and toggle button
        // i shall do packets

        public boolean shouldRender() {
            return true;
        }
    }
}
