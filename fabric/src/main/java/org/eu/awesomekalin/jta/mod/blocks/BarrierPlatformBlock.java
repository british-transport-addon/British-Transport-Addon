package org.eu.awesomekalin.jta.mod.blocks;

import net.minecraft.block.BarrierBlock;
import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.jetbrains.annotations.NotNull;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mapping.mapper.BlockWithEntity;
import org.mtr.mod.block.BlockPlatform;
import org.mtr.mod.render.RenderDrivingOverlay;
import org.mtr.mod.render.RenderEyeCandy;
import org.mtr.mod.render.RenderRails;

public class BarrierPlatformBlock extends BlockPlatform implements BlockWithEntity {

    public BarrierPlatformBlock() {
        super(BlockHelper.createBlockSettings(false).dropsNothing().strength(10), false);
    }

    @Override
    public boolean isSideInvisible2(BlockState state, BlockState stateFrom, Direction direction) {
        if (direction == Direction.DOWN) return false;
        return true;
    }

    @Override
    public boolean isTranslucent2(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new BarrierPlatformBlockEntity(blockPos, blockState);
    }

    @NotNull
    @Override
    public BlockRenderType getRenderType2(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    @Override
    public float getAmbientOcclusionLightLevel2(BlockState state, BlockView world, BlockPos pos) {
        return 1.0F;
    }



    public static class BarrierPlatformBlockEntity extends BlockEntityExtension {
        public BarrierPlatformBlockEntity(BlockPos blockPos, BlockState blockState) {
            super(BlockEntityTypeInit.BARRIER_PLATFORM.get(), blockPos, blockState);
        }
    }
}
