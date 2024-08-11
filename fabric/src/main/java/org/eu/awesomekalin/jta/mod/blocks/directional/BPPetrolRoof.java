package org.eu.awesomekalin.jta.mod.blocks.directional;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.mtr.mapping.holder.BlockEntityType;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mapping.mapper.BlockWithEntity;

public class BPPetrolRoof extends DirectionalBlockExtension implements BlockWithEntity {
    public BPPetrolRoof() {
        super(BlockHelper.createBlockSettings(false, (blockState) -> {
            return 11;
        }));
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new BPPetrolRoofTileEntity(blockPos, blockState);
    }

    public static class BPPetrolRoofTileEntity extends BlockEntityExtension {

        public BPPetrolRoofTileEntity(BlockPos blockPos, BlockState blockState) {
            super(BlockEntityTypeInit.BP_PETROL_ROOF.get(), blockPos, blockState);
        }
    }
}
