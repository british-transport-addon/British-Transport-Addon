package org.eu.awesomekalin.jta.mod.blocks;

import org.jetbrains.annotations.NotNull;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockExtension;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mod.block.IBlock;

public class BarrierLayerBlock extends BlockExtension {

    private IntegerProperty LAYER = IntegerProperty.of("layer", 1, 8);

    public BarrierLayerBlock() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque());
    }

    @Override
    public boolean isTranslucent2(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

    @NotNull
    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int layer = 1;

        try {
            layer = IBlock.getStatePropertySafe(state, LAYER);
        } catch (Exception ignored) {
            // ignored
        }
        return Block.createCuboidShape(0, 0, 0, 16, layer * 2, 16);
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
