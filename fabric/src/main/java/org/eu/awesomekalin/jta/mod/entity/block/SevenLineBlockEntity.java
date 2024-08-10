package org.eu.awesomekalin.jta.mod.entity.block;

import org.mtr.mapping.holder.*;

public abstract class SevenLineBlockEntity extends SixLineBlockEntity {

    public MutableText line6;

    public MutableText getLine6() {
        return line6;
    }

    public void setLine6(MutableText line6) {
        this.line6 = line6;
    }

    public void setLine6(String line6) {
        setLine6(MutableText.cast(Text.of(line6)));
    }

    public SevenLineBlockEntity(BlockEntityType<?> type, BlockPos blockPos, BlockState blockState, String line0, String line1, String line2, String line3, String line4, String line5, String line6) {
        super(type, blockPos, blockState, line0, line1, line2, line3, line4, line5);
        setLine6(line6);
    }

    @Override
    public void readCompoundTag(CompoundTag compoundTag) {
        super.readCompoundTag(compoundTag);
        setLine6(compoundTag.getString("line6"));
    }

    @Override
    public void writeCompoundTag(CompoundTag compoundTag) {
        super.writeCompoundTag(compoundTag);
        compoundTag.putString("line6", line6.getString());
    }
}
