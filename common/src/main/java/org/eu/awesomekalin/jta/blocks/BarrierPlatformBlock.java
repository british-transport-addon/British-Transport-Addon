package org.eu.awesomekalin.jta.blocks;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import org.eu.awesomekalin.jta.init.BlockEntityTypeInit;
import org.jetbrains.annotations.NotNull;
import org.mtr.block.BlockPlatform;

public class BarrierPlatformBlock extends BlockPlatform implements BlockEntityProvider {
    public BarrierPlatformBlock(AbstractBlock.Settings settings) {
        super(settings.dropsNothing().strength(10), false);
    }

    @Override
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        return direction != Direction.DOWN;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new BarrierPlatformBlockEntity(blockPos, blockState);
    }

    @NotNull
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    @Override
    public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
        return 1.0F;
    }

    public static class BarrierPlatformBlockEntity extends BlockEntity {
        public BarrierPlatformBlockEntity(BlockPos blockPos, BlockState blockState) {
            super(BlockEntityTypeInit.BARRIER_PLATFORM.get(), blockPos, blockState);
        }
    }
}
