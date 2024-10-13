package org.eu.awesomekalin.jta.mod.blocks.directional.rail;

import org.eu.awesomekalin.jta.mod.blocks.directional.DirectionalBlockEntityBase;
import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.eu.awesomekalin.jta.mod.init.BlockInit;
import org.jetbrains.annotations.Nullable;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mod.block.BlockSignalBase;

public class DispatchSignal extends DirectionalBlockEntityBase {

    // Static variable to hold the currently selected BlockSignalBase position
    private static BlockPos selectedSignalBasePosition = null;
    public static Long LAST_UPDATE_TIME = System.currentTimeMillis();

    public DispatchSignal() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        // Create the TileEntityDispatchSignal with the currently selected BlockSignalBase position
        System.out.println(blockState.getBlock().getTranslationKey());
        if (System.currentTimeMillis() < LAST_UPDATE_TIME + 2000L || selectedSignalBasePosition == null) return null;
        System.out.println(selectedSignalBasePosition.toShortString());

        return new TileEntityDispatchSignal(
                blockPos, blockState,
                selectedSignalBasePosition.getX(),
                selectedSignalBasePosition.getY(),
                selectedSignalBasePosition.getZ()
        );
    }

    @Override
    public void onPlaced2(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        // Check if the block below is a BlockSignalBase
        BlockPos blockBelow = pos.down();
        BlockState blockBelowState = world.getBlockState(blockBelow);

        if (PlayerEntity.isInstance(placer)) {
            PlayerEntity player = PlayerEntity.cast(placer);

            if (System.currentTimeMillis() < LAST_UPDATE_TIME + 2000L) {
                DispatchSignal.TileEntityDispatchSignal entity = (DispatchSignal.TileEntityDispatchSignal) world.getBlockEntity(pos).data;
                if (entity.signalX == 0 && entity.signalY == 0 && entity.signalZ == 0) {
                    world.breakBlock(pos, false);
                }
                return;
            }

            LAST_UPDATE_TIME = System.currentTimeMillis();

            if (selectedSignalBasePosition != null) {
                player.sendMessage(Text.of("You have created a new DispatchSignal relative to BlockSignalBase at " + selectedSignalBasePosition.toShortString() + "!"), true);
                selectedSignalBasePosition = null;
                return;
            }

            // Ensure the block below is a BlockSignalBase
            if (!(blockBelowState.getBlock().data instanceof BlockSignalBase)) {
                player.sendMessage(Text.of("You need to click a BlockSignalBase before placing a DispatchSignal!"), true);
                // Remove the DispatchSignal from the world if it fails
                world.breakBlock(pos, false);
                return;
            }

            // Set the selected position to the block below
            selectedSignalBasePosition = blockBelow;
            player.sendMessage(Text.of("You have selected this BlockSignalBase!"), true);
            world.breakBlock(pos, false);
            return;
        }

        // Create the TileEntityDispatchSignal with the selected BlockSignalBase position
        super.onPlaced2(world, pos, state, placer, itemStack);
    }

    public static class TileEntityDispatchSignal extends BlockEntityExtension {

        int signalX, signalY, signalZ;

        public TileEntityDispatchSignal(BlockPos pos, BlockState state) {
            super(BlockEntityTypeInit.DISPATCH_SIGNAL.get(), pos, state);
            this.signalX = 0; // Default value
            this.signalY = 0; // Default value
            this.signalZ = 0; // Default value
        }

        public TileEntityDispatchSignal(BlockPos pos, BlockState state, int signalX, int signalY, int signalZ) {
            super(BlockEntityTypeInit.DISPATCH_SIGNAL.get(), pos, state);
            this.signalX = signalX;
            this.signalY = signalY;
            this.signalZ = signalZ;
        }

        @Override
        public void writeCompoundTag(CompoundTag compoundTag) {
            super.writeCompoundTag(compoundTag);
            compoundTag.putInt("signalX", signalX);
            compoundTag.putInt("signalY", signalY);
            compoundTag.putInt("signalZ", signalZ);
        }

        @Override
        public void readCompoundTag(CompoundTag compoundTag) {
            super.readCompoundTag(compoundTag);
            this.signalX =  compoundTag.getInt("signalX");
            this.signalY =  compoundTag.getInt("signalY");
            this.signalZ =  compoundTag.getInt("signalZ");
        }

        public boolean shouldRender() {
            return true;
        }

        public int getSignalX() {
            return signalX;
        }

        public int getSignalY() {
            return signalY;
        }

        public int getSignalZ() {
            return signalZ;
        }

        public void setSignalX(int signalX) {
            this.signalX = signalX;
        }

        public void setSignalY(int signalY) {
            this.signalY = signalY;
        }

        public void setSignalZ(int signalZ) {
            this.signalZ = signalZ;
        }
    }
}
