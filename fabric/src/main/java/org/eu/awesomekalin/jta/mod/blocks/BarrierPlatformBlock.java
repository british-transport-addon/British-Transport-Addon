package org.eu.awesomekalin.jta.mod.blocks;

import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mapping.mapper.BlockWithEntity;
import org.mtr.mod.block.BlockPlatform;

import javax.annotation.Nonnull;

public class BarrierPlatformBlock extends BlockPlatform implements BlockWithEntity {
    public BarrierPlatformBlock() {
        super(BlockHelper.createBlockSettings(false, false).dropsNothing().strength(10), false);
    }

    @Override
    public boolean isSideInvisible2(@Nonnull BlockState state, @Nonnull BlockState stateFrom, @Nonnull Direction direction) {
        return direction != Direction.DOWN;
    }

    @Override
    public boolean isTranslucent2(@Nonnull BlockState state, @Nonnull BlockView world, @Nonnull BlockPos pos) {
        return true;
    }

    @Nonnull
    @Override
    public BlockEntityExtension createBlockEntity(@Nonnull BlockPos blockPos, @Nonnull BlockState blockState) {
        return new BarrierPlatformBlockEntity(blockPos, blockState);
    }

    @Nonnull
    @Override
    public BlockRenderType getRenderType2(@Nonnull BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    @Override
    public float getAmbientOcclusionLightLevel2(@Nonnull BlockState state, @Nonnull BlockView world, @Nonnull BlockPos pos) {
        return 1.0F;
    }

    public static class BarrierPlatformBlockEntity extends BlockEntityExtension {
        public BarrierPlatformBlockEntity(BlockPos blockPos, BlockState blockState) {
            super(BlockEntityTypeInit.BARRIER_PLATFORM.get(), blockPos, blockState);
        }
    }
}
