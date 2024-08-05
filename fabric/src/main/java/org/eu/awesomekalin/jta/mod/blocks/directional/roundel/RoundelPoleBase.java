package org.eu.awesomekalin.jta.mod.blocks.directional.roundel;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.eu.awesomekalin.jta.mod.entity.OneLineBlockEntity;
import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mapping.mapper.BlockWithEntity;
import org.mtr.mod.block.IBlock;

import javax.annotation.Nonnull;

public class RoundelPoleBase extends DirectionalBlockExtension implements BlockWithEntity {

    private String defaultText;

    public RoundelPoleBase(String defaultText) {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
        this.defaultText = defaultText;
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new RoundelBaseBlockEntity(blockPos, blockState, defaultText);
    }

    @Nonnull
    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        final Direction facing = IBlock.getStatePropertySafe(state, FACING);
        return BlockHelper.shapeUnion(
                IBlock.getVoxelShapeByDirection(-4, 0, 11.4, 20, 21, 13.4, facing),
                IBlock.getVoxelShapeByDirection(-4, 0, 2.85, 20, 21, 4.85, facing));
    }

    @Override
    public void onPlaced2(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        assert placer != null;
        final Direction facing = placer.getHorizontalFacing().getOpposite().rotateYClockwise().rotateYClockwise();
        world.setBlockState(pos, state.with(new Property<>(FACING.data), facing.data));
    }

    @Override
    public ActionResult onUse2(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        return ActionResult.SUCCESS;
    }

    public static class RoundelBaseBlockEntity extends OneLineBlockEntity {
        public RoundelBaseBlockEntity(BlockPos pos, BlockState state) {
            super(BlockEntityTypeInit.ROUNDEL_POLE.get(), pos, state, "");
        }
        public RoundelBaseBlockEntity(BlockPos pos, BlockState state, String defaultText) {
            super(BlockEntityTypeInit.ROUNDEL_POLE.get(), pos, state, defaultText);
        }

        // YOU FUCKING DICK./
        public boolean shouldRender() {
            return true;
        }
    }
}
