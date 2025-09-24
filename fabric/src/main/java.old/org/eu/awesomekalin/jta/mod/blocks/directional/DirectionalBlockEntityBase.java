package org.eu.awesomekalin.jta.mod.blocks.directional;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.mtr.mapping.holder.BlockEntityType;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockSettings;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockWithEntity;

public abstract class DirectionalBlockEntityBase extends DirectionalBlockExtension implements BlockWithEntity {
    protected DirectionalBlockEntityBase(BlockSettings settings) {
        super(settings);
    }

    public abstract static class TileEntityDirectionalBlockEntityBase extends BlockEntityExtension {
        public TileEntityDirectionalBlockEntityBase(BlockEntityType<?> type, BlockPos pos, BlockState state) {
            super(type, pos, state);
        }

        public abstract boolean shouldRender();
    }
}
