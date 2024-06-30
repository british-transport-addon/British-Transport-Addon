package org.eu.awesomekalin.jta.mod.blocks.directional;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.mtr.mapping.mapper.BlockHelper;

public class MetrolinkSignSquare extends DirectionalBlockExtension {

    public MetrolinkSignSquare() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
    }
}
