package org.eu.awesomekalin.jta.mod.entity.block;

import org.mtr.mapping.holder.*;

public abstract class TwoLineBlockEntity extends OneLineBlockEntity {

    public MutableText line1;

    public MutableText getLine1() {
        return line1;
    }

    public void setLine1(MutableText line1) {
        this.line1 = line1;
    }

    public void setLine1(String line1) {
        setLine1(MutableText.cast(Text.of(line1)));
    }

    public TwoLineBlockEntity(BlockEntityType<?> type, BlockPos blockPos, BlockState blockState, String line0, String line1) {
        super(type, blockPos, blockState, line0);
        setLine1(line1);
    }

    @Override
    public void readCompoundTag(CompoundTag compoundTag) {
        super.readCompoundTag(compoundTag);
        setLine1(compoundTag.getString("line1"));
    }

    @Override
    public void writeCompoundTag(CompoundTag compoundTag) {
        super.writeCompoundTag(compoundTag);
        compoundTag.putString("line1", line1.getString());
    }
}
