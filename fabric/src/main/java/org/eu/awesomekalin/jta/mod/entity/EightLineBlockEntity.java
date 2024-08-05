package org.eu.awesomekalin.jta.mod.entity;

import org.mtr.mapping.holder.*;

public abstract class EightLineBlockEntity extends SevenLineBlockEntity {

    public MutableText line7;

    public MutableText getLine7() {
        return line7;
    }

    public void setLine7(MutableText line7) {
        this.line7 = line7;
    }

    public void setLine7(String line7) {
        setLine7(MutableText.cast(Text.of(line7)));
    }

    public EightLineBlockEntity(BlockEntityType<?> type, BlockPos blockPos, BlockState blockState, String line0, String line1, String line2, String line3, String line4, String line5, String line6, String line7) {
        super(type, blockPos, blockState, line0, line1, line2, line3, line4, line5, line6);
        setLine7(line7);
    }

    @Override
    public void readCompoundTag(CompoundTag compoundTag) {
        super.readCompoundTag(compoundTag);
        setLine7(compoundTag.getString("line7"));
    }

    @Override
    public void writeCompoundTag(CompoundTag compoundTag) {
        super.writeCompoundTag(compoundTag);
        compoundTag.putString("line7", line7.getString());
    }
}
