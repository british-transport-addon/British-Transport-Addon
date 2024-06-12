package org.eu.awesomekalin.jta.mod.wallsigns;

import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockExtension;
import org.mtr.mapping.mapper.DirectionHelper;
import org.mtr.mapping.tool.HolderBase;

import javax.annotation.Nonnull;
import java.util.List;

public class WallSignBase extends BlockExtension implements DirectionHelper {
    public WallSignBase(BlockSettings settings) {
        super(settings);
    }

    @Nonnull
    @Override
    public VoxelShape getCollisionShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public void addBlockProperties(List<HolderBase<?>> builder) {
        builder.add(FACING);
    }
}
