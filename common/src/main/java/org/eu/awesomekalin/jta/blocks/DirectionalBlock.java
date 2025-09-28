package org.eu.awesomekalin.jta.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.Direction;
import org.mtr.core.tool.Angle;

import java.util.Objects;

public class DirectionalBlock extends HorizontalFacingBlock {
    public static final BooleanProperty IS_22_5 = BooleanProperty.of("is_22_5");
    public static final BooleanProperty IS_45 = BooleanProperty.of("is_45");
    public static final MapCodec<DirectionalBlock> CODEC = Block.createCodec(DirectionalBlock::new);

    public DirectionalBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return CODEC;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext itemPlacementContext) {
        final int quadrant = Angle.getQuadrant(itemPlacementContext.getPlayerYaw(), true);
        return Objects.requireNonNull(super.getPlacementState(itemPlacementContext))
                .with(Properties.HORIZONTAL_FACING, itemPlacementContext.getHorizontalPlayerFacing().getOpposite())
                .with(IS_45, quadrant % 4 >= 2)
                .with(IS_22_5, quadrant % 2 == 1);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(Properties.HORIZONTAL_FACING);
        builder.add(IS_22_5);
        builder.add(IS_45);
    }
}
