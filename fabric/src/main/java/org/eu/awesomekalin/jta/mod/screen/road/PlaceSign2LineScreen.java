package org.eu.awesomekalin.jta.mod.screen.road;

import org.eu.awesomekalin.jta.mod.InitClient;
import org.eu.awesomekalin.jta.mod.blocks.directional.street.BritishStreetSign2x2;
import org.eu.awesomekalin.jta.mod.packet.entity.PacketUpdateTwoLineBlockEntity;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mapping.mapper.GuiDrawing;
import org.mtr.mapping.mapper.ScreenExtension;
import org.mtr.mapping.mapper.TextFieldWidgetExtension;
import org.mtr.mapping.tool.TextCase;
import org.mtr.mod.client.IDrawing;
import org.mtr.mod.data.IGui;

public class PlaceSign2LineScreen extends ScreenExtension implements IGui {
    private final TextFieldWidgetExtension line0;
    private final TextFieldWidgetExtension line1;

    private BlockPos pos;
    private final BritishStreetSign2x2.BritishStreetSign2x2BlockEntity cast;

    public PlaceSign2LineScreen(BlockPos pos) {
        this.pos = pos;
        ClientPlayerEntity player = MinecraftClient.getInstance().getPlayerMapped();
        assert player != null;
        World world = player.getEntityWorld();
        BlockEntity entity = world.getBlockEntity(pos);
        assert entity != null;
        cast = (BritishStreetSign2x2.BritishStreetSign2x2BlockEntity) entity.data;
        line0 = new TextFieldWidgetExtension(0, 0, 0, SQUARE_SIZE, 30, TextCase.DEFAULT, "", "");
        line1 = new TextFieldWidgetExtension(0, 0, 0, SQUARE_SIZE, 30, TextCase.DEFAULT, "", "");
    }

    public static PlaceSign2LineScreen handle(BlockPos pos) {
        PlaceSign2LineScreen screen = new PlaceSign2LineScreen(pos);
        MinecraftClient.getInstance().openScreen(new Screen(screen));
        return screen;
    }

    @Override
    protected void init2() {
        super.init2();
        line0.setText2(cast.getLine0().getString());
        line1.setText2(cast.getLine1().getString());

        IDrawing.setPositionAndWidth(line0, getWidthMapped() / 2, 10, PANEL_WIDTH - (TEXT_FIELD_PADDING * 4));
        IDrawing.setPositionAndWidth(line1, getWidthMapped() / 2, 30, PANEL_WIDTH - (TEXT_FIELD_PADDING * 4));

        addChild(new ClickableWidget(line0));
        addChild(new ClickableWidget(line1));
    }

    @Override
    public void render(GraphicsHolder graphicsHolder, int mouseX, int mouseY, float delta) {
        line0.render(graphicsHolder, mouseX, mouseY, delta);
        line1.render(graphicsHolder, mouseX, mouseY, delta);

        graphicsHolder.push();
        final GuiDrawing guiDrawing = new GuiDrawing(graphicsHolder);
        guiDrawing.beginDrawingRectangle();
        guiDrawing.drawRectangle(0, 0, getWidthMapped(), getHeightMapped(), ARGB_BLACK_TRANSLUCENT);
        guiDrawing.finishDrawingRectangle();
        super.render(graphicsHolder, mouseX, mouseY, delta);
        graphicsHolder.pop();
    }

    @Override
    public void onClose2() {
        super.onClose2();
        InitClient.REGISTRY_CLIENT.sendPacketToServer(new PacketUpdateTwoLineBlockEntity(
                pos,
                line0.getText2(),
                line1.getText2()
        ));
    }

    @Override
    public void tick2() {
        super.tick2();
        line0.tick2();
        line1.tick2();
    }

    @Override
    public boolean isPauseScreen2() {
        return false;
    }
}
