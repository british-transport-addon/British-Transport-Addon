package org.eu.awesomekalin.jta.mod.screen.station;

import org.eu.awesomekalin.jta.mod.InitClient;
import org.eu.awesomekalin.jta.mod.blocks.directional.rail.ProjectionName;
import org.eu.awesomekalin.jta.mod.packet.PacketUpdateProjectionName;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.*;
import org.mtr.mapping.tool.TextCase;
import org.mtr.mod.client.IDrawing;
import org.mtr.mod.data.IGui;

import java.util.function.Consumer;

public class ProjectionNameScreen extends ScreenExtension implements IGui {
    private final BlockPos pos;
    private final TextFieldWidgetExtension prefix;
    private final TextFieldWidgetExtension suffix;
    private final TextFieldWidgetExtension maxWidth;
    private final TextFieldWidgetExtension maxScale;
    private final CheckboxWidgetExtension isUppercase;
    private final ProjectionName.ProjectionNameBlockEntity cast;
    public ProjectionNameScreen(BlockPos pos) {
        this.pos = pos;
        ClientPlayerEntity player = MinecraftClient.getInstance().getPlayerMapped();
        World world = player.getEntityWorld();
        BlockEntity entity = world.getBlockEntity(pos);
        cast = (ProjectionName.ProjectionNameBlockEntity) entity.data;
        prefix = new TextFieldWidgetExtension(0, 0, 0, SQUARE_SIZE, 10, TextCase.DEFAULT, "", "");
        suffix = new TextFieldWidgetExtension(0, 0, 0, SQUARE_SIZE, 10, TextCase.DEFAULT, "", "");
        maxWidth = new TextFieldWidgetExtension(0, 0, 0, SQUARE_SIZE, 10, TextCase.DEFAULT, "", "");
        maxScale = new TextFieldWidgetExtension(0, 0, 0, SQUARE_SIZE, 10, TextCase.DEFAULT, "", "");
        isUppercase = new CheckboxWidgetExtension(0, 0, 5, 5, "Make text uppercase", true, new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) {

            }
        });
    }

    public static ProjectionNameScreen handle(BlockPos pos) {
        ProjectionNameScreen screen = new ProjectionNameScreen(pos);
        MinecraftClient.getInstance().openScreen(new Screen(screen));
        return screen;
    }

    @Override
    protected void init2() {
        super.init2();
        prefix.setText2(cast.getPrefix().getString());
        suffix.setText2(cast.getSuffix().getString());
        maxWidth.setText2(cast.getMaxWidth() + "");
        maxScale.setText2(cast.getMaxScale() + "");
        isUppercase.setChecked(cast.isToggleUppercase());

        IDrawing.setPositionAndWidth(prefix, getWidthMapped() / 2, 10, PANEL_WIDTH - (TEXT_FIELD_PADDING * 4));
        IDrawing.setPositionAndWidth(suffix, getWidthMapped() / 2, 30, PANEL_WIDTH - (TEXT_FIELD_PADDING * 4));
        IDrawing.setPositionAndWidth(isUppercase, getWidthMapped() / 2, 45, PANEL_WIDTH - (TEXT_FIELD_PADDING * 4));

        addChild(new ClickableWidget(prefix));
        addChild(new ClickableWidget(suffix));
        addChild(new ClickableWidget(maxWidth));
        addChild(new ClickableWidget(maxScale));
        addChild(new ClickableWidget(isUppercase));
    }

    @Override
    public void render(GraphicsHolder graphicsHolder, int mouseX, int mouseY, float delta) {
        prefix.render(graphicsHolder, mouseX, mouseY, delta);
        suffix.render(graphicsHolder, mouseX, mouseY, delta);
        isUppercase.render(graphicsHolder, mouseX, mouseY, delta);
        maxWidth.render(graphicsHolder, mouseX, mouseY, delta);
        maxScale.render(graphicsHolder, mouseX, mouseY, delta);

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
        InitClient.REGISTRY_CLIENT.sendPacketToServer(new PacketUpdateProjectionName(pos, isUppercase.getActiveMapped(), prefix.getText2(), suffix.getText2(), Float.parseFloat(maxWidth.getText2()), Float.parseFloat(maxScale.getText2())));
    }

    @Override
    public void tick2() {
        super.tick2();
        prefix.tick2();
        suffix.tick2();
        maxWidth.tick2();
        maxScale.tick2();
    }

    @Override
    public boolean isPauseScreen2() {
        return false;
    }
}
