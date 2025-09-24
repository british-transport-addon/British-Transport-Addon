package org.eu.awesomekalin.jta.mod.blocks.directional.bus;

import org.eu.awesomekalin.jta.mod.Init;
import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.eu.awesomekalin.jta.mod.blocks.directional.PoleBase;
import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockWithEntity;
import org.mtr.mod.block.IBlock;
import org.mtr.mod.packet.PacketOpenBlockEntityScreen;

import javax.annotation.Nonnull;

public class LondonBusStopSign extends PoleBase implements BlockWithEntity {

    public LondonBusStopSign() {
        super();
    }

    @Nonnull
    @Override
    public ActionResult onUse2(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return IBlock.checkHoldingBrush(world, player, () -> {
            final Direction facing = IBlock.getStatePropertySafe(state, DirectionalBlockExtension.FACING);
            final Direction hitSide = hit.getSide();
            if (hitSide == facing || hitSide == facing.getOpposite()) {
                Init.REGISTRY.sendPacketToClient(ServerPlayerEntity.cast(player), new PacketOpenBlockEntityScreen(pos));
            }
        });
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new TileEntityLondonBusSign(blockPos, blockState);
    }

    public static class TileEntityLondonBusSign extends BlockEntityExtension {

        private static final String KEY_TOWARDS_TEXT = "towards_text";
        private static final String KEY_SERVICES_TEXT_ONE = "services_text_one";
        private String towardsText;
        private String servicesTextOne;

        public TileEntityLondonBusSign(BlockPos pos, BlockState state) {
            super(BlockEntityTypeInit.LONDON_BUS_STOP.get(), pos, state);
            this.towardsText = "Example";
            this.servicesTextOne = "69 420 111";
        }

        @Override
        public void readCompoundTag(CompoundTag compoundTag) {
            this.towardsText = compoundTag.getString(KEY_TOWARDS_TEXT);
            this.servicesTextOne = compoundTag.getString(KEY_SERVICES_TEXT_ONE);
        }

        @Override
        public void writeCompoundTag(CompoundTag compoundTag) {
            compoundTag.putString(KEY_TOWARDS_TEXT, towardsText);
            compoundTag.putString(KEY_SERVICES_TEXT_ONE, servicesTextOne);
        }

        public void setData(String towardsText, String servicesTextOne) {
            this.towardsText = towardsText;
            this.servicesTextOne = servicesTextOne;
            markDirty2();
        }

        public String getTowardsText() {
            return towardsText;
        }

        public String getServicesTextOne() {
            return servicesTextOne;
        }

        public boolean shouldRender() {
            return true;
        }
    }//Yes! Spam!
}
