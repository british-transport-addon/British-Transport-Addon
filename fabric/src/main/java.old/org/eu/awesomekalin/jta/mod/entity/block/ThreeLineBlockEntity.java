package org.eu.awesomekalin.jta.mod.entity.block;

import org.mtr.mapping.holder.*;

public abstract class ThreeLineBlockEntity extends TwoLineBlockEntity {

    public MutableText line2;

    public MutableText getLine2() {
        return line2;
    }

    public void setLine2(MutableText line2) {
        this.line2 = line2;
    }

    public void setLine2(String line2) {
        setLine2(MutableText.cast(Text.of(line2)));
    }

    public ThreeLineBlockEntity(BlockEntityType<?> type, BlockPos blockPos, BlockState blockState, String line0, String line1, String line2) {
        super(type, blockPos, blockState, line0, line1);
        setLine2(line2);
    }

    @Override
    public void readCompoundTag(CompoundTag compoundTag) {
        super.readCompoundTag(compoundTag);
        setLine2(compoundTag.getString("line2"));
    }

    @Override
    public void writeCompoundTag(CompoundTag compoundTag) {
        super.writeCompoundTag(compoundTag);
        compoundTag.putString("line2", line2.getString());
    }
}
