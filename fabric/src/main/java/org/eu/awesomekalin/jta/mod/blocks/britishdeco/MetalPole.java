package org.eu.awesomekalin.jta.mod.blocks.britishdeco;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.jetbrains.annotations.NotNull;
import org.mtr.mapping.holder.*;
import org.mtr.mod.block.IBlock;

public class MetalPole extends DirectionalBlockExtension {
    public MetalPole(BlockSettings blockSettings) {
        super(blockSettings);
    }

    @Override
    public @NotNull VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction facing = IBlock.getStatePropertySafe(state, DirectionalBlockExtension.FACING);
        return IBlock.getVoxelShapeByDirection(7, 0, 0, 9, 16, 2, facing);
    }
}
