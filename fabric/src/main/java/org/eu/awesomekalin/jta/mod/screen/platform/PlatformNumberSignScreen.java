package org.eu.awesomekalin.jta.mod.screen.platform;

import org.eu.awesomekalin.jta.mod.InitClient;
import org.eu.awesomekalin.jta.mod.blocks.directional.UndergroundWhiteboard;
import org.eu.awesomekalin.jta.mod.blocks.directional.rail.PlatformNumberSign;
import org.eu.awesomekalin.jta.mod.packet.PacketUpdatePlatformNumber;
import org.eu.awesomekalin.jta.mod.packet.PacketUpdateUndergroundWhiteboard;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mapping.mapper.GuiDrawing;
import org.mtr.mapping.mapper.ScreenExtension;
import org.mtr.mapping.mapper.TextFieldWidgetExtension;
import org.mtr.mapping.tool.TextCase;
import org.mtr.mod.client.IDrawing;
import org.mtr.mod.data.IGui;

public class PlatformNumberSignScreen extends ScreenExtension implements IGui {
    private final TextFieldWidgetExtension platformNumber;
    private BlockPos pos;
    private final PlatformNumberSign.PlatfornNumberSignEntity cast;

    // pass the blockstate in?
    public PlatformNumberSignScreen(BlockPos pos) {
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
        cast = (PlatformNumberSign.PlatfornNumberSignEntity) entity.data;

        platformNumber = new TextFieldWidgetExtension(0, 0, 0, SQUARE_SIZE, 30, TextCase.DEFAULT, "", cast.getPlatformNumber().getString());
    }

    public static PlatformNumberSignScreen handle(BlockPos pos) {
        System.out.println("main handle stack");
        PlatformNumberSignScreen screen = new PlatformNumberSignScreen(pos);
        MinecraftClient.getInstance().openScreen(new Screen(screen));
        return screen;
    }

    @Override
    protected void init2() {
        super.init2();
        platformNumber.setText2(cast.getPlatformNumber().getString());

        IDrawing.setPositionAndWidth(platformNumber, getWidthMapped() / 2, 10, PANEL_WIDTH - (TEXT_FIELD_PADDING * 4));

        addChild(new ClickableWidget(platformNumber));
    }

    @Override
    public void render(GraphicsHolder graphicsHolder, int mouseX, int mouseY, float delta) {
        platformNumber.render(graphicsHolder, mouseX, mouseY, delta);

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
        InitClient.REGISTRY_CLIENT.sendPacketToServer(new PacketUpdatePlatformNumber(
                pos,
                platformNumber.getText2()
        ));

        // yooyi  think its almoist donme yk :D
        // what do you really wanna work on?
        // like whats a cool think u think we should add
    }

    @Override
    public void tick2() {
        super.tick2();
        platformNumber.tick2();
    }

    @Override
    public boolean isPauseScreen2() {
        return false;
    }
}
