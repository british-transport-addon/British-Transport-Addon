package org.eu.awesomekalin.jta.platform.render.street;

import org.eu.awesomekalin.jta.mod.entity.block.street.ManchesterTrashBinAltLidEntity;
import org.eu.awesomekalin.jta.platform.client.model.street.ManchesterTrashBinAltLidModel;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class ManchesterTrashBinAltLidRender extends GeoBlockRenderer<ManchesterTrashBinAltLidEntity> {
    public ManchesterTrashBinAltLidRender() { super(null); }
    public ManchesterTrashBinAltLidRender(Object ars) {
        super(new ManchesterTrashBinAltLidModel());
    }
}