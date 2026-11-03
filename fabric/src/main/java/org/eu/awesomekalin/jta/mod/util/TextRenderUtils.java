package org.eu.awesomekalin.jta.mod.util;

import org.mtr.mapping.mapper.GraphicsHolder;

import static org.mtr.mod.data.IGui.ARGB_BLACK;

public class TextRenderUtils {
    public static void renderText(GraphicsHolder graphicsHolder, String text, int color, float availableWidth, boolean rightAlign) {
        graphicsHolder.push();
        final int textWidth = GraphicsHolder.getTextWidth(text);

        if (availableWidth < textWidth) {
            graphicsHolder.scale(textWidth == 0 ? 1 : availableWidth / textWidth, 1, 1);
        }

        graphicsHolder.drawText(text, rightAlign ? Math.max(0, (int) availableWidth - textWidth) : 0, 0, color | ARGB_BLACK, false, GraphicsHolder.getDefaultLight());
        graphicsHolder.pop();
    }
}
