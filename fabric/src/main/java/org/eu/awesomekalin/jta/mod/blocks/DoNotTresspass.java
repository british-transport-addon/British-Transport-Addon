package org.eu.awesomekalin.jta.mod.blocks;

import java.util.List;

import org.mtr.mapping.holder.DirectionProperty;
import org.mtr.mapping.mapper.BlockExtension;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mapping.mapper.DirectionHelper;
import org.mtr.mapping.tool.HolderBase;

public class DoNotTresspass extends BlockExtension {
    public static final DirectionProperty FACING = DirectionHelper.FACING;

    public DoNotTresspass() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque());
    }

    @Override
    public void addBlockProperties(List<HolderBase<?>> properties) {
        // TODO Auto-generated method stub
        super.addBlockProperties(properties);
        properties.add(FACING);
        properties.add(HALF);
    }
}