package org.eu.awesomekalin.jta.mod.blocks.directional.bus;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mod.block.IBlock;

import javax.annotation.Nonnull;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BusShelterRight extends DirectionalBlockExtension {
    public BusShelterRight() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
    }


    @Nonnull
    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        final Direction facing = IBlock.getStatePropertySafe(state, FACING).getOpposite();
        return BlockHelper.shapeUnion(
                IBlock.getVoxelShapeByDirection(-8, 25, -2, 32, 27, 16, facing),
                IBlock.getVoxelShapeByDirection(11, -11.5, 14, 32, -9.5, 16, facing),
                IBlock.getVoxelShapeByDirection(11, 2, 14, 32, 4, 16, facing),
                IBlock.getVoxelShapeByDirection(-8, 2, 0, -6, 4, 14, facing),
                IBlock.getVoxelShapeByDirection(-8, -11.5, 0, -6, -9.5, 14, facing),
                IBlock.getVoxelShapeByDirection(10, 4, 14.75, 32, 25, 15.25, facing),
                IBlock.getVoxelShapeByDirection(10, -9.5, 14.75, 32, 2, 15.25, facing),
                IBlock.getVoxelShapeByDirection(-7.5, -9.5, 0, -6.5, 2, 14, facing),
                IBlock.getVoxelShapeByDirection(-7.5, 4, 0, -6.5, 25, 14, facing),
                IBlock.getVoxelShapeByDirection(-8, -15.5, -2, -6, 25, 0, facing),
                IBlock.getVoxelShapeByDirection(-8, -15.5, 14, -6, 25, 16, facing),
                IBlock.getVoxelShapeByDirection(9, -15.5, 14, 11, 25, 16, facing)
        );
    }
}
