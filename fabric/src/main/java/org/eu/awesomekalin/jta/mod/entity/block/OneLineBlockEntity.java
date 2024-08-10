package org.eu.awesomekalin.jta.mod.entity.block;

import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;

public abstract class OneLineBlockEntity extends BlockEntityExtension {

    public MutableText line0;

    public MutableText getLine0() {
        return line0;
    }

    public void setLine0(MutableText line0) {
        this.line0 = line0;
    }

    public void setLine0(String line0) {
        setLine0(MutableText.cast(Text.of(line0)));
    }

    public OneLineBlockEntity(BlockEntityType<?> type, BlockPos blockPos, BlockState blockState, String line0) {
        super(type, blockPos, blockState);
        setLine0(line0);
    }

    @Override
    public void readCompoundTag(CompoundTag compoundTag) {
        super.readCompoundTag(compoundTag);
        setLine0(compoundTag.getString("line0"));
    }

    @Override
    public void writeCompoundTag(CompoundTag compoundTag) {
        super.writeCompoundTag(compoundTag);
        compoundTag.putString("line0", line0.getString());
    }
}
