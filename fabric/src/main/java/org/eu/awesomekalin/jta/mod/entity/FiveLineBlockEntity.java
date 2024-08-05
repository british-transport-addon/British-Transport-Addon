package org.eu.awesomekalin.jta.mod.entity;

import org.mtr.mapping.holder.*;

public abstract class FiveLineBlockEntity extends FourLineBlockEntity {

    public MutableText line4;

    public MutableText getLine4() {
        return line4;
    }

    public void setLine4(MutableText line4) {
        this.line4 = line4;
    }

    public void setLine4(String line4) {
        setLine4(MutableText.cast(Text.of(line4)));
    }

    public FiveLineBlockEntity(BlockEntityType<?> type, BlockPos blockPos, BlockState blockState, String line0, String line1, String line2, String line3, String line4) {
        super(type, blockPos, blockState, line0, line1, line2, line3);
        setLine4(line4);
    }

    @Override
    public void readCompoundTag(CompoundTag compoundTag) {
        super.readCompoundTag(compoundTag);
        setLine4(compoundTag.getString("line4"));
    }

    @Override
    public void writeCompoundTag(CompoundTag compoundTag) {
        super.writeCompoundTag(compoundTag);
        compoundTag.putString("line4", line4.getString());
    }
}
