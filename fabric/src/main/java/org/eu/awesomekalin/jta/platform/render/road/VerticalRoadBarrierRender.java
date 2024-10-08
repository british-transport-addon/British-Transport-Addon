package org.eu.awesomekalin.jta.platform.render.road;

import org.eu.awesomekalin.jta.mod.entity.block.road.VerticalRoadBarrierBlockEntity;
import org.eu.awesomekalin.jta.platform.client.model.road.VerticalRoadBarrierModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class VerticalRoadBarrierRender extends GeoBlockRenderer<VerticalRoadBarrierBlockEntity> {
    public VerticalRoadBarrierRender() { super(null); }
    public VerticalRoadBarrierRender(Object ars) {
        super(new VerticalRoadBarrierModel());
    }
}
