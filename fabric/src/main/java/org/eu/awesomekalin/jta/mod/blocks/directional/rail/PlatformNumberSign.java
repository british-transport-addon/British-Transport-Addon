package org.eu.awesomekalin.jta.mod.blocks.directional.rail;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.eu.awesomekalin.jta.mod.screen.platform.PlatformNumberSignScreen;
import org.eu.awesomekalin.jta.mod.screen.whiteboard.UndergroundWhiteboardScreen;
import org.jetbrains.annotations.NotNull;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mapping.mapper.BlockWithEntity;
import org.mtr.mod.block.IBlock;

public class PlatformNumberSign extends DirectionalBlockExtension implements BlockWithEntity {
    public PlatformNumberSign() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
    }

    @NotNull
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
        PlatformNumberSignScreen.handle(pos);
        return ActionResult.SUCCESS;
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new PlatfornNumberSignEntity(blockPos, blockState);
    }

    public static class PlatfornNumberSignEntity extends BlockEntityExtension {
        private MutableText platformNumber;

        public MutableText getPlatformNumber() {
            return platformNumber;
        }

        public PlatfornNumberSignEntity(BlockPos pos, BlockState state) {
            super(BlockEntityTypeInit.PLATFORM_NUMBER_SIGN.get(), pos, state);

            this.platformNumber = MutableText.cast(Text.of("1"));
        }
        // so we need block nbt for the brr

        @Override
        public void writeCompoundTag(CompoundTag compoundTag) {
            compoundTag.putString("platform", platformNumber.getString());
        }

        @Override
        public void readCompoundTag(CompoundTag compoundTag) {
            platformNumber = MutableText.cast(Text.of(compoundTag.getString("platform")));
            super.readCompoundTag(compoundTag);
        }

        public void setData(String platformNumber) {
            this.platformNumber = MutableText.cast(Text.of(platformNumber));
        }


        public boolean shouldRender() {
            return true;
        }
    }
}
