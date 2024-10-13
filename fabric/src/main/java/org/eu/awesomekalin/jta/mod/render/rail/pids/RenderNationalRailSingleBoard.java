package org.eu.awesomekalin.jta.mod.render.rail.pids;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.eu.awesomekalin.jta.mod.blocks.pids.NationalRail3Clock;
import org.eu.awesomekalin.jta.mod.blocks.pids.NationalRailSingleBoard;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mapping.mapper.TextHelper;
import org.mtr.mod.block.IBlock;
import org.mtr.mod.render.MainRenderer;
import org.mtr.mod.render.QueuedRenderLayer;
import org.mtr.mod.render.StoredMatrixTransformations;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RenderNationalRailSingleBoard extends RenderBritishPIDSUpdate<NationalRailSingleBoard.TileEntityNationalRailSingleBoard> {

    public RenderNationalRailSingleBoard(Argument dispatcher) {
        super(dispatcher, -6.25F, 30.5F, 6, 46F, 17, true, 1);
    }
}
