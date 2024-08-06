package org.eu.awesomekalin.jta.mod.screen.radio;

import org.eu.awesomekalin.jta.mod.InitClient;
import org.eu.awesomekalin.jta.mod.packet.PacketUpdateRadioFrequency;
import org.eu.awesomekalin.jta.mod.util.RadioUtil;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.*;
import org.mtr.mapping.tool.TextCase;
import org.mtr.mod.client.IDrawing;
import org.mtr.mod.data.IGui;

public class RadioMainScreen extends ScreenExtension implements IGui {

    private final MutableText frequencyFieldText = TextHelper.translatable("jta.gui.frequency");

    private final TextFieldWidgetExtension frequencyField;
    private ItemStack stack;

    public RadioMainScreen(ItemStack stack) {
        super();
        this.stack = stack;
        frequencyField = new TextFieldWidgetExtension(0, 0, 0, SQUARE_SIZE, 4, TextCase.DEFAULT, "\\D", "1");
    }

    public static RadioMainScreen handle(ItemStack stack) {
        System.out.println("main handle stack");
        RadioMainScreen screen = new RadioMainScreen(stack);
        MinecraftClient.getInstance().openScreen(new Screen(screen));
        return screen;
    }

    @Override
    protected void init2() {
        super.init2();
        frequencyField.setText2(RadioUtil.getRadioChannel(stack) + "");
        IDrawing.setPositionAndWidth(frequencyField, (getWidthMapped() - PANEL_WIDTH) + 8, 100 - SQUARE_SIZE - TEXT_FIELD_PADDING / 2, PANEL_WIDTH - (TEXT_FIELD_PADDING * 4));

        addChild(new ClickableWidget(frequencyField));
    }

    @Override
    public void render(GraphicsHolder graphicsHolder, int mouseX, int mouseY, float delta) {
        graphicsHolder.push();
        graphicsHolder.translate(0, 0, 500);
        final GuiDrawing guiDrawing = new GuiDrawing(graphicsHolder);
        guiDrawing.beginDrawingRectangle();
        guiDrawing.drawRectangle(getWidthMapped() - PANEL_WIDTH, 48, getWidthMapped(), height - 24, ARGB_BLACK_TRANSLUCENT);
        guiDrawing.finishDrawingRectangle();
        graphicsHolder.drawCenteredText(frequencyFieldText, width / 8 * 7, 54, ARGB_WHITE);
        frequencyField.render(graphicsHolder, mouseX, mouseY, delta);
        super.render(graphicsHolder, mouseX, mouseY, delta);
        graphicsHolder.pop();
    }


    @Override
    public void tick2() {
        super.tick2();
        frequencyField.tick2();
    }

    @Override
    public void onClose2() {
        try {
            int channel = Integer.parseInt(frequencyField.getText2());
            RadioUtil.setRadioChannel(stack, channel);

            InitClient.REGISTRY_CLIENT.sendPacketToServer(new PacketUpdateRadioFrequency(channel));
        } catch (Exception e) {

        }
        System.out.println(frequencyField.getText2());
        super.onClose2();
    }

    @Override
    public boolean isPauseScreen2() {
        return false;
    }
}