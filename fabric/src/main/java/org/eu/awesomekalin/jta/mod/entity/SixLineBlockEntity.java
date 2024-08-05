package org.eu.awesomekalin.jta.mod.entity;

import org.mtr.mapping.holder.*;

public abstract class SixLineBlockEntity extends FiveLineBlockEntity {

    public MutableText line5;

    public MutableText getLine5() {
        return line5;
    }

    public void setLine5(MutableText line5) {
        this.line5 = line5;
    }

    public void setLine5(String line5) {
        setLine5(MutableText.cast(Text.of(line5)));
    }

    public SixLineBlockEntity(BlockEntityType<?> type, BlockPos blockPos, BlockState blockState, String line0, String line1, String line2, String line3, String line4, String line5) {
        super(type, blockPos, blockState, line0, line1, line2, line3, line4);
        setLine5(line5);
    }

    @Override
    public void readCompoundTag(CompoundTag compoundTag) {
        super.readCompoundTag(compoundTag);
        setLine5(compoundTag.getString("line5"));
    }

    @Override
    public void writeCompoundTag(CompoundTag compoundTag) {
        super.writeCompoundTag(compoundTag);
        compoundTag.putString("line5", line5.getString());
    }
}
