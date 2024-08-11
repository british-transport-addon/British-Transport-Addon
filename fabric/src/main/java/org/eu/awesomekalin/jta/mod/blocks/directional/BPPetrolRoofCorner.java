package org.eu.awesomekalin.jta.mod.blocks.directional;

import net.minecraft.state.property.IntProperty;
import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.holder.IntegerProperty;
import org.mtr.mapping.holder.Property;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mapping.mapper.BlockWithEntity;
import org.mtr.mod.block.BlockTicketBarrier;

public class BPPetrolRoofCorner extends DirectionalBlockExtension implements BlockWithEntity {
    public BPPetrolRoofCorner() {
        super(BlockHelper.createBlockSettings(false, (blockState) -> {
            return 5;
        }));
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new BPPetrolRoofTileEntity(blockPos, blockState);
    }

    public static class BPPetrolRoofTileEntity extends BlockEntityExtension {

        public BPPetrolRoofTileEntity(BlockPos blockPos, BlockState blockState) {
            super(BlockEntityTypeInit.BP_PETROL_ROOF_CORNER.get(), blockPos, blockState);
        }
    }
}
