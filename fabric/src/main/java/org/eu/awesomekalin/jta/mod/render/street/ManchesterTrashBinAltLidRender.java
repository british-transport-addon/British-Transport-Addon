package org.eu.awesomekalin.jta.mod.render.street;

import org.eu.awesomekalin.jta.mod.client.model.street.ManchesterTrashBinAltLidModel;
import org.eu.awesomekalin.jta.mod.entity.block.street.ManchesterTrashBinAltLidEntity;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ManchesterTrashBinAltLidRender extends GeoBlockRenderer<ManchesterTrashBinAltLidEntity> {
    public ManchesterTrashBinAltLidRender() { super(null); }
    public ManchesterTrashBinAltLidRender(Object ars) {
        super(new ManchesterTrashBinAltLidModel());
    }
}