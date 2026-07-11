package org.eu.awesomekalin.jta.mod.blocks.directional;

import org.eu.awesomekalin.jta.mod.Init;
import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.eu.awesomekalin.jta.mod.init.CustomResourceLoader;
import org.eu.awesomekalin.jta.mod.packet.PacketOpenDisplaySelector;
import org.jetbrains.annotations.NotNull;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockExtension;
import org.mtr.mapping.mapper.BlockWithEntity;
import org.mtr.mapping.mapper.DirectionHelper;
import org.mtr.mapping.tool.HolderBase;
import org.mtr.mod.Blocks;
import org.mtr.mod.block.IBlock;

import java.util.List;
import java.util.Objects;

public class DisplayBlock extends BlockExtension implements DirectionHelper, BlockWithEntity {
    public final int width;
    public final int height;

    public DisplayBlock(int width, int height) {
        super(Blocks.createDefaultBlockSettings(false));
        this.width = width;
        this.height = height;
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

            Init.REGISTRY.sendPacketToClient(ServerPlayerEntity.cast(player), new PacketOpenDisplaySelector(pos, selectedIds));
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
        return new DisplayBlockEntity(blockPos, blockState, width, height);
    }

    public static class DisplayBlockEntity extends BlockEntityExtension {
        private final ObjectArrayList<String> selectedIds;
        private final int width;
        private final int height;
        private int frame = 0;

        public DisplayBlockEntity(BlockPos pos, BlockState state, int width, int height) {
            super(Objects.requireNonNull(getType(width, height)), pos, state);
            selectedIds = new ObjectArrayList<>();
            selectedIds.add(CustomResourceLoader.DEFAULT_DISPLAY);

            this.width = width;
            this.height = height;
        }

        public void setSelectedIds(ObjectArrayList<String> selectedIds) {
            this.selectedIds.clear();
            this.selectedIds.addAll(selectedIds);
        }

        @Override
        public void readCompoundTag(CompoundTag compoundTag) {
            final int totalImages = compoundTag.getInt("totalImages");

            selectedIds.clear();
            for (int i = 0; i < totalImages; i++) {
                selectedIds.add(compoundTag.getString("selected_" + i));
            }

            super.readCompoundTag(compoundTag);
        }

        @Override
        public void writeCompoundTag(CompoundTag compoundTag) {
            final int totalImages = selectedIds.size();
            compoundTag.putInt("totalImages", totalImages);

            for (int i = 0; i < totalImages; i++) {
                compoundTag.putString("selected_" + i, selectedIds.get(i));
            }

            super.writeCompoundTag(compoundTag);
        }

        private static BlockEntityType<?> getType(int width, int height) {
            switch (width + "x" + height) {
                case "1x1":
                    return BlockEntityTypeInit.DISPLAY_1x1.get();

                default:
                    return null;
            }
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
