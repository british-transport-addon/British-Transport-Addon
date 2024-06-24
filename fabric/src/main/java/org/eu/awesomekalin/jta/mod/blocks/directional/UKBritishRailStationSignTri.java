package org.eu.awesomekalin.jta.mod.blocks.directional;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.mtr.mapping.mapper.BlockHelper;

public class UKBritishRailStationSignTri extends DirectionalBlockExtension {

    public UKBritishRailStationSignTri() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
    }
}
