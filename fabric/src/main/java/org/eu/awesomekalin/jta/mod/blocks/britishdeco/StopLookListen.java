package org.eu.awesomekalin.jta.mod.blocks.britishdeco;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.jetbrains.annotations.NotNull;
import org.mtr.mapping.holder.*;
import org.mtr.mod.block.IBlock;

public class StopLookListen extends DirectionalBlockExtension {
    public StopLookListen(BlockSettings blockSettings) {
        super(blockSettings);
    }

    @Override
    public @NotNull VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction facing = IBlock.getStatePropertySafe(state, DirectionalBlockExtension.FACING).rotateYClockwise();
        return IBlock.getVoxelShapeByDirection(0, 0, 0, 16, 24, 3, facing);
    }
}
