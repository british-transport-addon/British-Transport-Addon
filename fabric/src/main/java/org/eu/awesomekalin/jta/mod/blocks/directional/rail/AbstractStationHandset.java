package org.eu.awesomekalin.jta.mod.blocks.directional.rail;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.jetbrains.annotations.Nullable;
import org.mtr.core.data.Station;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mapping.mapper.BlockWithEntity;
import org.mtr.mod.InitClient;

public abstract class AbstractStationHandset extends DirectionalBlockExtension implements BlockWithEntity {
    public AbstractStationHandset() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
    }


    @Override
    public void scheduledTick2(BlockState state, ServerWorld world, BlockPos pos, Random random) {

        final Station station = InitClient.findStation(pos);


        scheduleBlockTick(World.cast(world), pos, state.getBlock(), 20);
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return null;
    }

    @Override
    public void onPlaced2(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        scheduleBlockTick(world, pos, state.getBlock(), 3 * 20);
    }

    public static class StationHandsetBlockEntity extends BlockEntityExtension {
        public StationHandsetBlockEntity(BlockPos blockPos, BlockState blockState) {
            super(BlockEntityTypeInit.STATION_HANDSET.get(), blockPos, blockState);
        }

        @Override
        public void readCompoundTag(CompoundTag compoundTag) {
            super.readCompoundTag(compoundTag);
        }

        @Override
        public void writeCompoundTag(CompoundTag compoundTag) {
            super.writeCompoundTag(compoundTag);
        }
    }
}
