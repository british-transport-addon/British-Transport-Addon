package org.eu.awesomekalin.jta.mod.blocks;

import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mapping.tool.HolderBase;
import org.mtr.mod.block.BlockDirectionalDoubleBlockBase;

import java.util.List;

public class UKDoNotTresspassWithRustyPole extends BlockDirectionalDoubleBlockBase {
    public UKDoNotTresspassWithRustyPole() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque());
    }

    @Override
    public void addBlockProperties(List<HolderBase<?>> properties) {
        properties.add(FACING);
        properties.add(HALF);
    }
}