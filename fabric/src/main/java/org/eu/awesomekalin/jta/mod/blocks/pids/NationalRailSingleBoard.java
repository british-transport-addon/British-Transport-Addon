package org.eu.awesomekalin.jta.mod.blocks.pids;

import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mapping.tool.HolderBase;
import org.mtr.mod.block.*;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class NationalRailSingleBoard extends BlockPIDSBase {

    public static final int LINES = 17;

    public NationalRailSingleBoard() {
        super(LINES, NationalRailSingleBoard::canStoreData, NationalRailSingleBoard::getBlockPosWithData);
    }

    @Nonnull
    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BlockHelper.shapeUnion(
                IBlock.getVoxelShapeByDirection(6, -16, -8, 10, 32, 24, IBlock.getStatePropertySafe(state, FACING)));
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
        return new TileEntityNationalRailSingleBoard(pos, state);
    }

    public static boolean canStoreData(World world, BlockPos blockPos) {
        return true;
    }

    public static BlockPos getBlockPosWithData(World world, BlockPos blockPos) {
        return blockPos;
    }

    public static class TileEntityNationalRailSingleBoard extends BlockPIDSBase.BlockEntityBase {

        public TileEntityNationalRailSingleBoard(int maxArrivals, BiPredicate<World, BlockPos> canStoreData, BiFunction<World, BlockPos, BlockPos> getBlockPosWithData, BlockEntityType<?> type, BlockPos pos, BlockState state) {
            super(maxArrivals, canStoreData, getBlockPosWithData, type, pos, state);
        }

        public TileEntityNationalRailSingleBoard(BlockPos pos, BlockState state) {
            super(LINES, NationalRailSingleBoard::canStoreData, NationalRailSingleBoard::getBlockPosWithData, BlockEntityTypeInit.PIDS_NATIONALRAILSINGLEBOARD.get(), pos, state);
        }

        @Override
        public boolean showArrivalNumber() {
            return false;
        }


        public boolean alternateLines() {
            return false;
        }

        public int textColorArrived() {
            return 16750848;
        }

        public int textColor() {
            return 16750848;
        }
    }
}
