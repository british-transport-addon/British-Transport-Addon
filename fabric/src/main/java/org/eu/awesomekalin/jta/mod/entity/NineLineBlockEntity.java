package org.eu.awesomekalin.jta.mod.entity;

import org.mtr.mapping.holder.*;

public abstract class NineLineBlockEntity extends EightLineBlockEntity {

    public MutableText line8;

    public MutableText getLine8() {
        return line8;
    }

    public void setLine8(MutableText line8) {
        this.line8 = line8;
    }

    public void setLine8(String line8) {
        setLine8(MutableText.cast(Text.of(line8)));
    }

    public NineLineBlockEntity(BlockEntityType<?> type, BlockPos blockPos, BlockState blockState, String line0, String line1, String line2, String line3, String line4, String line5, String line6, String line7, String line8) {
        super(type, blockPos, blockState, line0, line1, line2, line3, line4, line5, line6, line7);
        setLine8(line8);
    }

    @Override
    public void readCompoundTag(CompoundTag compoundTag) {
        super.readCompoundTag(compoundTag);
        setLine8(compoundTag.getString("line8"));
    }

    @Override
    public void writeCompoundTag(CompoundTag compoundTag) {
        super.writeCompoundTag(compoundTag);
        compoundTag.putString("line8", line8.getString());
    }
}
