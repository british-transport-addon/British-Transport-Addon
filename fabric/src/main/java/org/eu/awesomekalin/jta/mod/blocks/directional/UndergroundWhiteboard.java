package org.eu.awesomekalin.jta.mod.blocks.directional;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.eu.awesomekalin.jta.mod.entity.block.NineLineBlockEntity;
import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.eu.awesomekalin.jta.mod.screen.whiteboard.UndergroundWhiteboardScreen;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mapping.mapper.BlockWithEntity;
import org.mtr.mod.block.IBlock;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

public class UndergroundWhiteboard extends DirectionalBlockExtension implements BlockWithEntity {
    public UndergroundWhiteboard() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
    }

    @Nonnull
    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        final Direction facing = IBlock.getStatePropertySafe(state, FACING);
        return BlockHelper.shapeUnion(
                IBlock.getVoxelShapeByDirection(-1.25, 0, 7, 0, 31.25, 9, facing),
                IBlock.getVoxelShapeByDirection(16, 0, 7, 17.25, 31.25, 9, facing),
                IBlock.getVoxelShapeByDirection(0, 31.25, 7.25, 16, 32, 8.75, facing),
                IBlock.getVoxelShapeByDirection(16, 31.25, 7, 17.25, 32, 9, facing),
                IBlock.getVoxelShapeByDirection(-1.25, 31.25, 7, 0, 32, 9, facing),
                IBlock.getVoxelShapeByDirection(0, 8.25, 7.5, 16, 31.25, 8.5, facing),
                IBlock.getVoxelShapeByDirection(0, 7.75, 7.25, 16, 8.75, 8.75, facing)
        );
    }

    @Override
    public ActionResult onUse2(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        UndergroundWhiteboardScreen.handle(pos);
        return ActionResult.SUCCESS;
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new UndergroundWhiteboardBlockEntity(blockPos, blockState);
    }

    public static class UndergroundWhiteboardBlockEntity extends NineLineBlockEntity {
        public List<MutableText> getMessages() {
            return Arrays.asList(line0, line1, line2, line3, line4, line5, line6, line7, line8);
        }

        public UndergroundWhiteboardBlockEntity(BlockPos pos, BlockState state) {
            super(
                    BlockEntityTypeInit.UNDERGROUND_WHITEBOARD.get(),
                    pos,
                    state,
                    "Underground Whiteboard Example",
                    "There are severe delays on the Victoria line",
                    "please use alternatives routes via the Central line.",
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""
            );
        }
        public boolean shouldRender() {
            return true;
        }
    }
}
