package org.eu.awesomekalin.jta.mod.init;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.eu.awesomekalin.jta.mod.Init;
import org.eu.awesomekalin.jta.mod.resource.DisplayResource;
import org.mtr.libraries.com.google.gson.JsonObject;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.Object2ObjectAVLTreeMap;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.mapper.ResourceManagerHelper;
import org.mtr.mod.config.Config;

import java.util.List;
import java.util.function.Function;

public class CustomResourceLoader {
    public static final String CUSTOM_RESOURCES_ID = "jta_custom_resources";

    public static final ObjectArrayList<DisplayResource> DISPLAYS = new ObjectArrayList<>();
    public static final Object2ObjectAVLTreeMap<String, DisplayResource> DISPLAYS_CACHE = new Object2ObjectAVLTreeMap<>();

    public static void reload() {
        DISPLAYS.clear();
        DISPLAYS_CACHE.clear();

        ResourceManagerHelper.readAllResources(new Identifier(Init.MOD_ID, CUSTOM_RESOURCES_ID + ".json"), inputStream -> {
            try {
                Config.readResource(inputStream).getAsJsonObject().getAsJsonArray("displays").forEach((entry) -> {
                    final JsonObject jsonObject = entry.getAsJsonObject();
                    final DisplayResource displayResource = new DisplayResource(jsonObject.get("id").getAsString(), jsonObject.get("name").getAsString(), jsonObject.get("color").getAsString(), jsonObject.get("textureResource").getAsString());

                    DISPLAYS.add(displayResource);
                    DISPLAYS_CACHE.put(displayResource.getId(), displayResource);
                });
            } catch (Exception e) {
                Init.LOGGER.error(e);
            }
        });

        validateDataset("Displays", DISPLAYS, DisplayResource::getId);

        Init.LOGGER.info("Loaded {} displays", DISPLAYS.size());
    }

    private static <T> void validateDataset(String dataSetName, List<T> dataSet, Function<T, String> getId) {
        ObjectOpenHashSet<String> addedIds = new ObjectOpenHashSet<>();
        for (T data : dataSet) {
            String id = getId.apply(data);
            if (addedIds.contains(id)) {
                Init.LOGGER.warn("JTA {} resource contains duplicated id {}!", dataSetName, id);
            } else {
                addedIds.add(id);
            }
        }
    }
}
