package org.eu.awesomekalin.jta.mod.blocks.directional.rail;

import org.eu.awesomekalin.jta.mod.blocks.directional.DirectionalBlockEntityBase;
import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;

public class PlatformSign extends DirectionalBlockEntityBase {

    public PlatformSign() {
        super(BlockHelper.createBlockSettings(false, false).strength(4.0f).nonOpaque().dynamicBounds());
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new PlatformSignEntity(blockPos, blockState);
    }

    public static class PlatformSignEntity extends BlockEntityExtension {

        public PlatformSignEntity(BlockPos pos, BlockState state) {
            super(BlockEntityTypeInit.PLATFORM_SIGN.get(), pos, state);
        }

        public boolean shouldRender() {
            return true;
        }
    }
}
