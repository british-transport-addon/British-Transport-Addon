package org.eu.awesomekalin.jta.mod.render.road;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import org.eu.awesomekalin.jta.mod.blocks.directional.road.VerticalRoadBarrier;
import org.eu.awesomekalin.jta.mod.blocks.directional.roundel.RoundelPlatformBase;
import org.eu.awesomekalin.jta.mod.entity.block.road.VerticalRoadBarrierBlockEntity;
import org.mtr.libraries.de.javagl.obj.Obj;
import org.mtr.mapping.mapper.BlockEntityRenderer;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mod.client.IDrawing;
import org.mtr.mod.data.IGui;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class VerticalRoadBarrierRender extends GeoBlockRenderer<VerticalRoadBarrierBlockEntity> {
    public VerticalRoadBarrierRender() { super(null); }
    public VerticalRoadBarrierRender(Object ars) {
        super(new VerticalRoadBarrierModel());
    }
}