package org.eu.awesomekalin.jta.mod.blocks.directional.rail;

import org.eu.awesomekalin.jta.mod.blocks.directional.DirectionalBlockEntityBase;
import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.eu.awesomekalin.jta.mod.util.DyeColor;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;

public class MetrolinkSign extends DirectionalBlockEntityBase {

    public MetrolinkSign() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new MetrolinkSignEntity(blockPos, blockState);
    }

    public static class MetrolinkSignEntity extends BlockEntityExtension {

        public MetrolinkSignEntity(BlockPos pos, BlockState state) {
            super(BlockEntityTypeInit.METROLINK_SIGN_ENTITY.get(), pos, state);
        }

        public boolean shouldRender() {
            return true;
        }
    }
}
