package org.eu.awesomekalin.jta.mod.blocks;

import org.jetbrains.annotations.NotNull;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mod.block.BlockPlatform;

public class BarrierPlatformBlock extends BlockPlatform {

    public BarrierPlatformBlock() {
        super(BlockHelper.createBlockSettings(false).dropsNothing().strength(10), false);
    }

    @Override
    public boolean isSideInvisible2(BlockState state, BlockState stateFrom, Direction direction) {
        return true;
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
}
