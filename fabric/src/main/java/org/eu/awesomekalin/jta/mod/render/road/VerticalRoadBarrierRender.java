package org.eu.awesomekalin.jta.mod.render.road;

import org.eu.awesomekalin.jta.mod.client.model.road.VerticalRoadBarrierModel;
import org.eu.awesomekalin.jta.mod.entity.block.road.VerticalRoadBarrierBlockEntity;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class VerticalRoadBarrierRender extends GeoBlockRenderer<VerticalRoadBarrierBlockEntity> {
    public VerticalRoadBarrierRender() { super(null); }
    public VerticalRoadBarrierRender(Object ars) {
        super(new VerticalRoadBarrierModel());
    }
}