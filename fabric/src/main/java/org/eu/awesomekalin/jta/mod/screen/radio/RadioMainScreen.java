package org.eu.awesomekalin.jta.mod.screen.radio;

import org.eu.awesomekalin.jta.mod.Init;
import org.eu.awesomekalin.jta.mod.screen.FirstLoadScreen;
import org.eu.awesomekalin.jta.mod.util.RadioUtil;
import org.mtr.core.tool.Utilities;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.*;
import org.mtr.mapping.tool.TextCase;
import org.mtr.mod.client.IDrawing;
import org.mtr.mod.config.Config;
import org.mtr.mod.data.IGui;

public class RadioMainScreen extends ScreenExtension implements IGui {

    private final MutableText frequencyFieldText = TextHelper.translatable("jta.gui.frequency");

    private final TextFieldWidgetExtension frequencyField;
    private ItemStack stack;

    public RadioMainScreen(ItemStack stack) {
        super();
        this.stack = stack;
        frequencyField = new TextFieldWidgetExtension(0, 0, 0, SQUARE_SIZE, 2, TextCase.DEFAULT, "\\D", "1");
    }

    public static RadioMainScreen handle(ItemStack stack) {
        System.out.println("main handle stack");
        RadioMainScreen screen = new RadioMainScreen(stack);
        MinecraftClient.getInstance().openScreen(new Screen(screen));
        return screen;
    }

    @Override
    protected void init2() {
        frequencyField.setText2(RadioUtil.getRadioChannel(stack) + "");
        super.init2();
        IDrawing.setPositionAndWidth(frequencyField, TEXT_FIELD_PADDING / 2 + width / 4, 300, width / 4 - TEXT_FIELD_PADDING);

        addChild(new ClickableWidget(frequencyField));
    }

    @Override
    public void render(GraphicsHolder graphicsHolder, int mouseX, int mouseY, float delta) {

        graphicsHolder.drawCenteredText(frequencyFieldText, width / 8 * 7, TEXT_PADDING, ARGB_WHITE);
        super.render(graphicsHolder, mouseX, mouseY, delta);
    }


    @Override
    public void tick2() {
        super.tick2();
        frequencyField.tick2();
    }

    @Override
    public void onClose2() {
        RadioUtil.setRadioChannel(stack, Integer.parseInt(frequencyField.getMessage2().getString()));
        super.onClose2();
    }

    @Override
    public boolean isPauseScreen2() {
        return false;
    }
}