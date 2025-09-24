package org.eu.awesomekalin.jta.mod.resource;

import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.mapper.TextHelper;
import org.mtr.mod.resource.CustomResourceTools;

public final class DisplayResource {
    private final String id;
    private final String translationKey;
    private final String color;
    private final String textureResource;

    public DisplayResource(String id, String translationKey, String color, String textureResource) {
        this.id = id;
        this.translationKey = translationKey;
        this.color = color;
        this.textureResource = textureResource;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return TextHelper.translatable(translationKey).getString();
    }

    public int getColor() {
        return CustomResourceTools.colorStringToInt(color);
    }

    public Identifier getTexture() {
        return CustomResourceTools.formatIdentifierWithDefault(textureResource, "png");
    }
}
