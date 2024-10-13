package org.eu.awesomekalin.jta.mod.blocks.pids;

import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mapping.tool.HolderBase;
import org.mtr.mod.block.BlockPIDSBase;
import org.mtr.mod.block.IBlock;

import javax.annotation.Nonnull;
import java.util.List;

public class NationalRailSingleBoardWhite extends BlockPIDSBase {

    public static final int LINES = 17;

    public NationalRailSingleBoardWhite() {
        super(LINES, NationalRailSingleBoardWhite::canStoreData, NationalRailSingleBoardWhite::getBlockPosWithData);
    }

    @Nonnull
    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BlockHelper.shapeUnion(
                IBlock.getVoxelShapeByDirection(6, 0, 0, 10, 9, 16, IBlock.getStatePropertySafe(state, FACING)),
                IBlock.getVoxelShapeByDirection(7.5, 9, 12.5, 8.5, 16, 13.5, IBlock.getStatePropertySafe(state, FACING))
        );
    }

    @Override
    public void addBlockProperties(List<HolderBase<?>> properties) {
        super.addBlockProperties(properties);
        properties.add(FACING);
    }

    @Override
    public void onPlaced2(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        assert placer != null;
        final Direction facing = placer.getHorizontalFacing().getOpposite().rotateYClockwise().rotateYClockwise();
        world.setBlockState(pos, state.with(new Property<>(FACING.data), facing.data));
    }

    @Nonnull
    @Override
    public BlockEntityExtension createBlockEntity(BlockPos pos, BlockState state) {
        return new TileEntityNationalRailSingleBoardWhite(pos, state);
    }

    private static boolean canStoreData(World world, BlockPos blockPos) {
        return true;
    }

    private static BlockPos getBlockPosWithData(World world, BlockPos blockPos) {
        return blockPos;
    }

    public static class TileEntityNationalRailSingleBoardWhite extends BlockEntityBase {

        public TileEntityNationalRailSingleBoardWhite(BlockPos pos, BlockState state) {
            super(LINES, NationalRailSingleBoardWhite::canStoreData, NationalRailSingleBoardWhite::getBlockPosWithData, BlockEntityTypeInit.PIDS_NATIONALRAILSINGLEBOARD.get(), pos, state);
        }

        @Override
        public boolean showArrivalNumber() {
            return false;
        }


        public boolean alternateLines() {
            return false;
        }

        public int textColorArrived() {
            return 0xFFFFFF;
        }

        public int textColor() {
            return 0xFFFFFF;
        }
    }
}
