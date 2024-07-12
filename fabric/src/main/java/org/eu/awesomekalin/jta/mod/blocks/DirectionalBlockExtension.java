package org.eu.awesomekalin.jta.mod.blocks;

import org.jetbrains.annotations.NotNull;
import org.mtr.core.tool.Angle;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockExtension;
import org.mtr.mapping.mapper.DirectionHelper;
import org.mtr.mapping.tool.HolderBase;
import org.mtr.mod.block.IBlock;

import javax.annotation.Nonnull;
import java.util.List;

public abstract class DirectionalBlockExtension extends BlockExtension implements DirectionHelper {

    public static final EnumProperty<EnumBooleanInverted> IS_22_5 = EnumProperty.of("is_22_5", EnumBooleanInverted.class);
    public static final EnumProperty<EnumBooleanInverted> IS_45 = EnumProperty.of("is_45", EnumBooleanInverted.class);
    public static final EnumProperty<EnumBooleanInverted> IS_67_5 = EnumProperty.of("is_67_5", EnumBooleanInverted.class);

    public DirectionalBlockExtension(BlockSettings blockSettings) {
        super(blockSettings);
    }

    @Override
    public void onPlaced2(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        assert placer != null;
        final Direction facing = placer.getHorizontalFacing().getOpposite();
        world.setBlockState(pos, state.with(new Property<>(FACING.data), facing.data));
    }

    @Override
    public BlockState getPlacementState2(ItemPlacementContext ctx) {
        final int quadrant = Angle.getQuadrant(ctx.getPlayerYaw(), true);
        return getDefaultState2()
                .with(new Property<>(FACING.data), Direction.fromHorizontal(quadrant / 8).data)
                .with(new Property<>(IS_45.data), EnumBooleanInverted.fromBoolean((quadrant % 8) >= 4))
                .with(new Property<>(IS_22_5.data), EnumBooleanInverted.fromBoolean((quadrant % 4) >= 2))
                .with(new Property<>(IS_67_5.data), EnumBooleanInverted.fromBoolean((quadrant % 2) == 1));
    }

    @NotNull
    @Override
    public ActionResult onUse2(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        player.sendMessage(Text.of("Facing: " + IBlock.getStatePropertySafe(state, FACING).getName() +
                ", 22.5: " + IBlock.getStatePropertySafe(state, IS_22_5).booleanValue +
                ", 45: " + IBlock.getStatePropertySafe(state, IS_45).booleanValue +
                ", 67.5: " + IBlock.getStatePropertySafe(state, IS_67_5).booleanValue), false);
        return super.onUse2(state, world, pos, player, hand, hit);
    }

    @Override
    public void addBlockProperties(List<HolderBase<?>> properties) {
        properties.add(FACING);
        properties.add(IS_22_5);
        properties.add(IS_45);
        properties.add(IS_67_5);
    }

    public static float getAngle(BlockState state) {
        return IBlock.getStatePropertySafe(state, DirectionHelper.FACING).asRotation() +
                (IBlock.getStatePropertySafe(state, DirectionalBlockExtension.IS_22_5).booleanValue ? 22.5F : 0) +
                (IBlock.getStatePropertySafe(state, DirectionalBlockExtension.IS_45).booleanValue ? 45 : 0) +
                (IBlock.getStatePropertySafe(state, DirectionalBlockExtension.IS_67_5).booleanValue ? 67.5F : 0);
    }

    public enum EnumBooleanInverted implements StringIdentifiable {

        FALSE(false), TRUE(true);
        public final boolean booleanValue;

        EnumBooleanInverted(boolean booleanValue) {
            this.booleanValue = booleanValue;
        }

        @Nonnull
        @Override
        public String asString2() {
            return String.valueOf(booleanValue);
        }

        private static EnumBooleanInverted fromBoolean(boolean value) {
            return value ? TRUE : FALSE;
        }
    }
}
