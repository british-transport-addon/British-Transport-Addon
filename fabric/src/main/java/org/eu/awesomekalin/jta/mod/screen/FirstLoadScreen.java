package org.eu.awesomekalin.jta.mod.screen;

import org.eu.awesomekalin.jta.mod.Init;
import org.mtr.core.tool.Utilities;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.*;
import org.mtr.mapping.registry.RegistryClient;
import org.mtr.mod.client.IDrawing;
import org.mtr.mod.config.Config;
import org.mtr.mod.data.IGui;

public class FirstLoadScreen extends ScreenExtension implements IGui, Utilities {

    private static final int BUTTON_WIDTH = SQUARE_SIZE * 8;
    private static final int FORCE_OPEN_DURATION = 5000;
    private static long openTime;
    private static long lastMillis;
    private final ButtonWidgetExtension buttonDiscord;

    public FirstLoadScreen() {
        super();
        buttonDiscord = new ButtonWidgetExtension(0, 0, 0, SQUARE_SIZE, TextHelper.literal("Support this mod on Discord"), button -> Util.getOperatingSystem().open("https://discord.gg/XApeBC8gWV"));
    }

    public static void handle() {
        if (openTime < FORCE_OPEN_DURATION) {
            final Screen screen = MinecraftClient.getInstance().getCurrentScreenMapped();
            if (screen != null && screen.getTitle().data.toString().contains("narrator.screen.title")) {
                MinecraftClient.getInstance().openScreen(new Screen(new FirstLoadScreen()));
            }
        } else {
            openTime = FORCE_OPEN_DURATION;
        }
    }

    @Override
    protected void init2() {
        super.init2();
        IDrawing.setPositionAndWidth(buttonDiscord, width / 2 - SQUARE_SIZE / 2 - BUTTON_WIDTH, height - SQUARE_SIZE * 2, BUTTON_WIDTH);
        addChild(new ClickableWidget(buttonDiscord));
    }

    @Override
    public void render(GraphicsHolder graphicsHolder, int mouseX, int mouseY, float delta) {
        renderBackground(graphicsHolder);
        super.render(graphicsHolder, mouseX, mouseY, delta);

        graphicsHolder.push();
        graphicsHolder.translate(width / 2F, SQUARE_SIZE, 0);
        graphicsHolder.scale(2, 2, 1);
        graphicsHolder.drawCenteredText("Jexes Transit Addon alpha 6", 0, 0, ARGB_WHITE);
        graphicsHolder.pop();

        int i = SQUARE_SIZE * 2;
        graphicsHolder.drawCenteredText("Please back up your worlds before continuing!", width / 2, i += TEXT_HEIGHT, System.currentTimeMillis() % 1000 < 500 ? 0xFFFF00 : 0xFF9900);
        i = wrapAndRender(graphicsHolder, "- To keep up with the latest updates, join our discord!", i += TEXT_HEIGHT);
        graphicsHolder.drawCenteredText("Thank you and enjoy :)", width / 2, i += TEXT_HEIGHT, ARGB_WHITE);

        graphicsHolder.push();
        graphicsHolder.translate(width / 2F, i + TEXT_HEIGHT + TEXT_PADDING / 2F, 0);
        graphicsHolder.scale(0.5F, 0.5F, 1);
        graphicsHolder.drawCenteredText(openTime < FORCE_OPEN_DURATION ? String.format("Please read the above carefully to continue! (%s)", (FORCE_OPEN_DURATION - openTime) / MILLIS_PER_SECOND) : "Press ESC to continue", 0, 0, ARGB_WHITE);
        graphicsHolder.pop();

        final GuiDrawing guiDrawing = new GuiDrawing(graphicsHolder);
        guiDrawing.beginDrawingTexture(new Identifier(Init.MOD_ID, "textures/gui/discord.png"));
        guiDrawing.drawTexture(width / 2F - BUTTON_WIDTH / 2F - SQUARE_SIZE, height - SQUARE_SIZE * 3 - TEXT_PADDING, width / 2F - BUTTON_WIDTH / 2F, height - SQUARE_SIZE * 2 - TEXT_PADDING, 0, 0, 1, 1);
        guiDrawing.finishDrawingTexture();
    }

    @Override
    public void tick2() {
        super.tick2();
        if (openTime < FORCE_OPEN_DURATION) {
            final long currentMillis = System.currentTimeMillis();
            if (lastMillis > 0) {
                openTime += currentMillis - lastMillis;
            }
            lastMillis = currentMillis;
        }
    }

    @Override
    public void onClose2() {
        super.onClose2();
        if (openTime >= FORCE_OPEN_DURATION) {
            Config.getClient().hideBetaWarningScreen();
            Config.save();
        }
    }

    @Override
    public boolean isPauseScreen2() {
        return false;
    }

    private int wrapAndRender(GraphicsHolder graphicsHolder, String text, int y) {
        final int textWidth = Math.min(512, width - SQUARE_SIZE * 2);
        final int[] newY = {y};
        GraphicsHolder.wrapLines(new MutableText(TextHelper.literal(text).data), textWidth).forEach(orderedText -> {
            graphicsHolder.drawText(orderedText, (width - textWidth) / 2 + (newY[0] == y ? 0 : 10), newY[0], ARGB_LIGHT_GRAY, true, GraphicsHolder.getDefaultLight());
            newY[0] += TEXT_HEIGHT + TEXT_PADDING / 2;
        });
        return newY[0] + TEXT_PADDING * 3 / 2;
    }
}