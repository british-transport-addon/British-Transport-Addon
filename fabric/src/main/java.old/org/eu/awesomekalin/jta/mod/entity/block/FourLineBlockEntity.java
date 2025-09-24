package org.eu.awesomekalin.jta.mod.entity.block;

import org.mtr.mapping.holder.*;

public abstract class FourLineBlockEntity extends ThreeLineBlockEntity {

    public MutableText line3;

    public MutableText getLine3() {
        return line3;
    }

    public void setLine3(MutableText line3) {
        this.line3 = line3;
    }

    public void setLine3(String line3) {
        setLine3(MutableText.cast(Text.of(line3)));
    }

    public FourLineBlockEntity(BlockEntityType<?> type, BlockPos blockPos, BlockState blockState, String line0, String line1, String line2, String line3) {
        super(type, blockPos, blockState, line0, line1, line2);
        setLine3(line3);
    }

    @Override
    public void readCompoundTag(CompoundTag compoundTag) {
        super.readCompoundTag(compoundTag);
        setLine3(compoundTag.getString("line3"));
    }

    @Override
    public void writeCompoundTag(CompoundTag compoundTag) {
        super.writeCompoundTag(compoundTag);
        compoundTag.putString("line3", line3.getString());
    }
}
