package org.eu.awesomekalin.jta.mod.render.street;

import org.eu.awesomekalin.jta.mod.entity.block.street.ManchesterTrashBinAltLidEntity;
import org.eu.awesomekalin.jta.platform.render.street.ManchesterTrashBinAltLidModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ManchesterTrashBinAltLidRender extends GeoBlockRenderer<ManchesterTrashBinAltLidEntity> {
    public ManchesterTrashBinAltLidRender() { super(null); }
    public ManchesterTrashBinAltLidRender(Object ars) {
        super(new ManchesterTrashBinAltLidModel());
    }
}