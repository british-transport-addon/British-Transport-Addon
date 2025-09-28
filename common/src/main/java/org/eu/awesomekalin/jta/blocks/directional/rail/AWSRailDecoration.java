package org.eu.awesomekalin.jta.blocks.directional.rail;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.eu.awesomekalin.jta.blocks.DirectionalBlock;

public class AWSRailDecoration extends DirectionalBlock {

    public AWSRailDecoration(Settings settings) {
        super(settings.strength(4.0f).nonOpaque().dynamicBounds());
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(6, 0, 6, 10, 2, 10);
    }
}
