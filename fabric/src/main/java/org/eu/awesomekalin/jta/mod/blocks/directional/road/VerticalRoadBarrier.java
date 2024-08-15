package org.eu.awesomekalin.jta.mod.blocks.directional.road;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.eu.awesomekalin.jta.mod.entity.block.road.VerticalRoadBarrierBlockEntity;
import org.jetbrains.annotations.NotNull;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mapping.mapper.BlockWithEntity;

import javax.annotation.Nonnull;

public class VerticalRoadBarrier extends DirectionalBlockExtension implements BlockWithEntity {

    public VerticalRoadBarrier() {
        super(BlockHelper.createBlockSettings(true));
    }
    // how do the geckolib


    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new VerticalRoadBarrierBlockEntity(blockPos, blockState);
    }

    @Nonnull
    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BlockHelper.shapeUnion(Block.createCuboidShape(6, 0, 6, 10, 16, 10), Block.createCuboidShape(5, 0, 7, 11, 16, 9), Block.createCuboidShape(7, 0, 5, 9, 16, 11));
    }

    @NotNull
    @Override
    public BlockRenderType getRenderType2(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }
}
