package org.eu.awesomekalin.jta.mod.screen.whiteboard;

import org.eu.awesomekalin.jta.mod.InitClient;
import org.eu.awesomekalin.jta.mod.blocks.directional.UndergroundWhiteboard;
import org.eu.awesomekalin.jta.mod.packet.entity.PacketUpdateNineLineBlockEntity;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mapping.mapper.GuiDrawing;
import org.mtr.mapping.mapper.ScreenExtension;
import org.mtr.mapping.mapper.TextFieldWidgetExtension;
import org.mtr.mapping.tool.TextCase;
import org.mtr.mod.client.IDrawing;
import org.mtr.mod.data.IGui;

public class UndergroundWhiteboardScreen extends ScreenExtension implements IGui {
    private final TextFieldWidgetExtension line0;
    private final TextFieldWidgetExtension line1;
    private final TextFieldWidgetExtension line2;
    private final TextFieldWidgetExtension line3;
    private final TextFieldWidgetExtension line4;
    private final TextFieldWidgetExtension line5;
    private final TextFieldWidgetExtension line6;
    private final TextFieldWidgetExtension line7;
    private final TextFieldWidgetExtension line8;

    private BlockPos pos;
    private final UndergroundWhiteboard.UndergroundWhiteboardBlockEntity cast;

    // pass the blockstate in?
    public UndergroundWhiteboardScreen(BlockPos pos) {
        this.pos = pos;
        ClientPlayerEntity player = MinecraftClient.getInstance().getPlayerMapped();
        World world = player.getEntityWorld();
        BlockEntity entity = world.getBlockEntity(pos);
        assert entity != null;
        // thats mpt right tho,.. thats the mojan reference isnt it? or wait that miht b e fiht acc hold on. They do something like that directly in MTR
        // okay cool lets try it..
        // it miht work?
        // can u make the client block renderere
        // it needs to redner the text on the bloc kface tuyk
        // it does not work
        cast = (UndergroundWhiteboard.UndergroundWhiteboardBlockEntity) entity.data;
        for (MutableText text : cast.getMessages()) {
            System.out.println(text.getString());
        }
        line0 = new TextFieldWidgetExtension(0, 0, 0, SQUARE_SIZE, 30, TextCase.DEFAULT, "", cast.getLine0().getString());
        line1 = new TextFieldWidgetExtension(0, 0, 0, SQUARE_SIZE, 30, TextCase.DEFAULT, "", cast.getLine1().getString());
        line2 = new TextFieldWidgetExtension(0, 0, 0, SQUARE_SIZE, 30, TextCase.DEFAULT, "", cast.getLine2().getString());
        line3 = new TextFieldWidgetExtension(0, 0, 0, SQUARE_SIZE, 30, TextCase.DEFAULT, "", cast.getLine3().getString());
        line4 = new TextFieldWidgetExtension(0, 0, 0, SQUARE_SIZE, 30, TextCase.DEFAULT, "", cast.getLine4().getString());
        line5 = new TextFieldWidgetExtension(0, 0, 0, SQUARE_SIZE, 30, TextCase.DEFAULT, "", cast.getLine5().getString());
        line6 = new TextFieldWidgetExtension(0, 0, 0, SQUARE_SIZE, 30, TextCase.DEFAULT, "", cast.getLine6().getString());
        line7 = new TextFieldWidgetExtension(0, 0, 0, SQUARE_SIZE, 30, TextCase.DEFAULT, "", cast.getLine7().getString());
        line8 = new TextFieldWidgetExtension(0, 0, 0, SQUARE_SIZE, 30, TextCase.DEFAULT, "", cast.getLine8().getString());
    }

    public static UndergroundWhiteboardScreen handle(BlockPos pos) {
        System.out.println("main handle stack");
        UndergroundWhiteboardScreen screen = new UndergroundWhiteboardScreen(pos);
        MinecraftClient.getInstance().openScreen(new Screen(screen));
        return screen;
    }

    @Override
    protected void init2() {
        super.init2();
        line0.setText2(cast.getLine0().getString());
        line1.setText2(cast.getLine1().getString());
        line2.setText2(cast.getLine2().getString());
        line3.setText2(cast.getLine3().getString());
        line4.setText2(cast.getLine4().getString());
        line5.setText2(cast.getLine5().getString());
        line6.setText2(cast.getLine6().getString());
        line7.setText2(cast.getLine7().getString());
        line8.setText2(cast.getLine8().getString());

        IDrawing.setPositionAndWidth(line0, getWidthMapped() / 2, 10, PANEL_WIDTH - (TEXT_FIELD_PADDING * 4));
        IDrawing.setPositionAndWidth(line1, getWidthMapped() / 2, 30, PANEL_WIDTH - (TEXT_FIELD_PADDING * 4));
        IDrawing.setPositionAndWidth(line2, getWidthMapped() / 2, 50, PANEL_WIDTH - (TEXT_FIELD_PADDING * 4));
        IDrawing.setPositionAndWidth(line3, getWidthMapped() / 2, 70, PANEL_WIDTH - (TEXT_FIELD_PADDING * 4));
        IDrawing.setPositionAndWidth(line4, getWidthMapped() / 2, 90, PANEL_WIDTH - (TEXT_FIELD_PADDING * 4));
        IDrawing.setPositionAndWidth(line5, getWidthMapped() / 2, 110, PANEL_WIDTH - (TEXT_FIELD_PADDING * 4));
        IDrawing.setPositionAndWidth(line6, getWidthMapped() / 2, 130, PANEL_WIDTH - (TEXT_FIELD_PADDING * 4));
        IDrawing.setPositionAndWidth(line7, getWidthMapped() / 2, 150, PANEL_WIDTH - (TEXT_FIELD_PADDING * 4));
        IDrawing.setPositionAndWidth(line8, getWidthMapped() / 2, 170, PANEL_WIDTH - (TEXT_FIELD_PADDING * 4));

        addChild(new ClickableWidget(line0));
        addChild(new ClickableWidget(line1));
        addChild(new ClickableWidget(line2));
        addChild(new ClickableWidget(line3));
        addChild(new ClickableWidget(line4));
        addChild(new ClickableWidget(line5));
        addChild(new ClickableWidget(line6));
        addChild(new ClickableWidget(line7));
        addChild(new ClickableWidget(line8));
    }

    @Override
    public void render(GraphicsHolder graphicsHolder, int mouseX, int mouseY, float delta) {
        line0.render(graphicsHolder, mouseX, mouseY, delta);
        line1.render(graphicsHolder, mouseX, mouseY, delta);
        line2.render(graphicsHolder, mouseX, mouseY, delta);
        line3.render(graphicsHolder, mouseX, mouseY, delta);
        line4.render(graphicsHolder, mouseX, mouseY, delta);
        line5.render(graphicsHolder, mouseX, mouseY, delta);
        line6.render(graphicsHolder, mouseX, mouseY, delta);
        line7.render(graphicsHolder, mouseX, mouseY, delta);
        line8.render(graphicsHolder, mouseX, mouseY, delta);

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
        InitClient.REGISTRY_CLIENT.sendPacketToServer(new PacketUpdateNineLineBlockEntity(
                pos,
                line0.getText2(),
                line1.getText2(),
                line2.getText2(),
                line3.getText2(),
                line4.getText2(),
                line5.getText2(),
                line6.getText2(),
                line7.getText2(),
                line8.getText2()
        ));

        // yooyi  think its almoist donme yk :D
        // what do you really wanna work on?
        // like whats a cool think u think we should add
    }

    @Override
    public void tick2() {
        super.tick2();
        line0.tick2();
        line1.tick2();
        line2.tick2();
        line3.tick2();
        line4.tick2();
        line5.tick2();
        line6.tick2();
        line7.tick2();
        line8.tick2();
    }

    @Override
    public boolean isPauseScreen2() {
        return false;
    }
}
