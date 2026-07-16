package org.eu.awesomekalin.jta.mod.blocks.directional;

import org.eu.awesomekalin.jta.mod.Init;
import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.eu.awesomekalin.jta.mod.init.CustomResourceLoader;
import org.eu.awesomekalin.jta.mod.packet.PacketOpenDisplaySelector;
import org.jetbrains.annotations.NotNull;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.*;
import org.mtr.mapping.tool.HolderBase;
import org.mtr.mod.block.IBlock;

import javax.annotation.Nonnull;
import java.util.List;

public class DisplayBlock extends BlockExtension implements DirectionHelper, BlockWithEntity {

    public DisplayBlock() {
        super(BlockHelper.createBlockSettings(false, false).strength(4.0f).nonOpaque().noCollision().dynamicBounds());
    }

    @NotNull
    @Override
    public ActionResult onUse2(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return IBlock.checkHoldingBrush(world, player, () -> {
            final BlockEntity entity = ServerPlayerEntity.cast(player).getEntityWorld().getBlockEntity(pos);
            ObjectArrayList<String> selectedIds;

            if (entity != null && entity.data instanceof DisplayBlock.DisplayBlockEntity) {
                selectedIds = ((DisplayBlock.DisplayBlockEntity) entity.data).getSelectedIds();
            } else {
                selectedIds = new ObjectArrayList<>();
            }

            if (entity != null && entity.data instanceof DisplayBlock.DisplayBlockEntity) {
                Init.REGISTRY.sendPacketToClient(ServerPlayerEntity.cast(player), new PacketOpenDisplaySelector(pos, selectedIds, ((DisplayBlock.DisplayBlockEntity) entity.data).getWidth(), ((DisplayBlock.DisplayBlockEntity) entity.data).getHeight()));
            } else {
                Init.REGISTRY.sendPacketToClient(ServerPlayerEntity.cast(player), new PacketOpenDisplaySelector(pos, selectedIds, 1, 1));
            }
        });
    }

    @Override
    public void addBlockProperties(List<HolderBase<?>> properties) {
        properties.add(FACING);
    }

    @Override
    public BlockState getPlacementState2(ItemPlacementContext ctx) {
        final Direction facing = ctx.getPlayerFacing();
        return getDefaultState2().with(new Property<>(FACING.data), facing.data);
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new DisplayBlockEntity(blockPos, blockState);
    }

    @Nonnull
    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        final Direction facing = IBlock.getStatePropertySafe(state, FACING);
        return BlockHelper.shapeUnion(IBlock.getVoxelShapeByDirection(0, 0, 0, 16, 16, 1, facing));
    }

    public static class DisplayBlockEntity extends BlockEntityExtension {
        private final ObjectArrayList<String> selectedIds;
        private int width;
        private int height;
        private int frame = 0;

        public DisplayBlockEntity(BlockPos pos, BlockState state) {
            super(BlockEntityTypeInit.DISPLAY.get(), pos, state);
            selectedIds = new ObjectArrayList<>();
            selectedIds.add(CustomResourceLoader.DEFAULT_DISPLAY);

            this.width = 1;
            this.height = 1;
        }

        public void setSelectedIds(ObjectArrayList<String> selectedIds, int width, int height) {
            this.selectedIds.clear();
            this.selectedIds.addAll(selectedIds);
            this.width = width;
            this.height = height;
        }

        @Override
        public void readCompoundTag(CompoundTag compoundTag) {
            final int totalImages = compoundTag.getInt("totalImages");

            selectedIds.clear();
            for (int i = 0; i < totalImages; i++) {
                selectedIds.add(compoundTag.getString("selected_" + i));
            }

            width = compoundTag.getInt("width");
            height = compoundTag.getInt("height");

            super.readCompoundTag(compoundTag);
        }

        @Override
        public void writeCompoundTag(CompoundTag compoundTag) {
            final int totalImages = selectedIds.size();
            compoundTag.putInt("totalImages", totalImages);

            for (int i = 0; i < totalImages; i++) {
                compoundTag.putString("selected_" + i, selectedIds.get(i));
            }

            compoundTag.putInt("width", width);
            compoundTag.putInt("height", height);

            super.writeCompoundTag(compoundTag);
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public int getFrame() {
            return frame;
        }

        public void setFrame(int frame) {
            this.frame = frame;
        }

        public ObjectArrayList<String> getSelectedIds() {
            return selectedIds;
        }
    }
}
