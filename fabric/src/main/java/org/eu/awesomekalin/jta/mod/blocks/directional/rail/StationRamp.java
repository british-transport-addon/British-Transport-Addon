package org.eu.awesomekalin.jta.mod.blocks.directional.rail;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockHelper;

import javax.annotation.Nonnull;

public class StationRamp extends DirectionalBlockExtension {
    public StationRamp() {
        super(BlockHelper.createBlockSettings(false, false).strength(4.0f).nonOpaque().dynamicBounds());
    }

    @Nonnull
    @Override
    public VoxelShape getOutlineShape2(
            @Nonnull BlockState state,
            @Nonnull BlockView world,
            @Nonnull BlockPos pos,
            @Nonnull ShapeContext context
    ) {
        return BlockHelper.shapeUnion(Block.createCuboidShape(0, 0, 0, 16, 32, 2));
    }
}
