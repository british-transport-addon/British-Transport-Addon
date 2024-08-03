package org.eu.awesomekalin.jta.mod.blocks.directional;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.eu.awesomekalin.jta.mod.init.BlockEntityTypeInit;
import org.eu.awesomekalin.jta.mod.screen.whiteboard.UndergroundWhiteboardScreen;
import org.jetbrains.annotations.NotNull;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mapping.mapper.BlockWithEntity;
import org.mtr.mod.block.IBlock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UndergroundWhiteboard extends DirectionalBlockExtension implements BlockWithEntity {
    public UndergroundWhiteboard() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
    }

    @NotNull
    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        final Direction facing = IBlock.getStatePropertySafe(state, FACING);
        return BlockHelper.shapeUnion(
                IBlock.getVoxelShapeByDirection(-1.25, 0, 7, 0, 31.25, 9, facing),
                IBlock.getVoxelShapeByDirection(16, 0, 7, 17.25, 31.25, 9, facing),
                IBlock.getVoxelShapeByDirection(0, 31.25, 7.25, 16, 32, 8.75, facing),
                IBlock.getVoxelShapeByDirection(16, 31.25, 7, 17.25, 32, 9, facing),
                IBlock.getVoxelShapeByDirection(-1.25, 31.25, 7, 0, 32, 9, facing),
                IBlock.getVoxelShapeByDirection(0, 8.25, 7.5, 16, 31.25, 8.5, facing),
                IBlock.getVoxelShapeByDirection(0, 7.75, 7.25, 16, 8.75, 8.75, facing)
        );
    }

    @Override
    public ActionResult onUse2(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        UndergroundWhiteboardScreen.handle(pos);
        return ActionResult.SUCCESS;
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new UndergroundWhiteboardBlockEntity(blockPos, blockState);
    }

    public static class UndergroundWhiteboardBlockEntity extends BlockEntityExtension {
        private MutableText line0;
        private MutableText line1;
        private MutableText line2;
        private MutableText line3;
        private MutableText line4;
        private MutableText line5;
        private MutableText line6;
        private MutableText line7;
        private MutableText line8;

        public MutableText getLine0() {
            return line0;
        }

        public MutableText getLine1() {
            return line1;
        }

        public MutableText getLine2() {
            return line2;
        }

        public MutableText getLine3() {
            return line3;
        }

        public MutableText getLine4() {
            return line4;
        }

        public MutableText getLine5() {
            return line5;
        }

        public MutableText getLine6() {
            return line6;
        }

        public MutableText getLine7() {
            return line7;
        }

        public MutableText getLine8() {
            return line8;
        }

        public List<MutableText> getMessages() {
            return Arrays.asList(line0, line1, line2, line3, line4, line5, line6, line7, line8);
        }

        public UndergroundWhiteboardBlockEntity(BlockPos pos, BlockState state) {
            super(BlockEntityTypeInit.UNDERGROUND_WHITEBOARD.get(), pos, state);

            this.line0 = MutableText.cast(Text.of("Underground Whiteboard Example"));
            this.line1 = MutableText.cast(Text.of("There are severe delays on the Victoria line"));
            this.line2 = MutableText.cast(Text.of("please use alternatives routes via the Central line."));
            this.line3 = MutableText.cast(Text.of(""));
            this.line4 = MutableText.cast(Text.of(""));
            this.line5 = MutableText.cast(Text.of(""));
            this.line6 = MutableText.cast(Text.of(""));
            this.line7 = MutableText.cast(Text.of(""));
            this.line8 = MutableText.cast(Text.of(""));
        }
        // so we need block nbt for the brr

        @Override
        public void writeCompoundTag(CompoundTag compoundTag) {
            compoundTag.putString("line0", line0.getString());
            compoundTag.putString("line1", line1.getString());
            compoundTag.putString("line2", line2.getString());
            compoundTag.putString("line3", line3.getString());
            compoundTag.putString("line4", line4.getString());
            compoundTag.putString("line5", line5.getString());
            compoundTag.putString("line6", line6.getString());
            compoundTag.putString("line7", line7.getString());
            compoundTag.putString("line8", line8.getString());
        }

        @Override
        public void readCompoundTag(CompoundTag compoundTag) {
            line0 = MutableText.cast(Text.of(compoundTag.getString("line0")));
            line1 = MutableText.cast(Text.of(compoundTag.getString("line1")));
            line2 = MutableText.cast(Text.of(compoundTag.getString("line2")));
            line3 = MutableText.cast(Text.of(compoundTag.getString("line3")));
            line4 = MutableText.cast(Text.of(compoundTag.getString("line4")));
            line5 = MutableText.cast(Text.of(compoundTag.getString("line5")));
            line6 = MutableText.cast(Text.of(compoundTag.getString("line6")));
            line7 = MutableText.cast(Text.of(compoundTag.getString("line7")));
            line8 = MutableText.cast(Text.of(compoundTag.getString("line8")));
            super.readCompoundTag(compoundTag);
        }

        public void setData(String line0, String line1, String line2, String line3, String line4, String line5, String line6, String line7, String line8) {
            this.line0 = MutableText.cast(Text.of(line0));
            this.line1 = MutableText.cast(Text.of(line1));
            this.line2 = MutableText.cast(Text.of(line2));
            this.line3 = MutableText.cast(Text.of(line3));
            this.line4 = MutableText.cast(Text.of(line4));
            this.line5 = MutableText.cast(Text.of(line5));
            this.line6 = MutableText.cast(Text.of(line6));
            this.line7 = MutableText.cast(Text.of(line7));
            this.line8 = MutableText.cast(Text.of(line8));

            for (MutableText text : getMessages()) {
                System.out.println(text.getString());
            }
        }


        public boolean shouldRender() {
            return true;
        }
    }
}
