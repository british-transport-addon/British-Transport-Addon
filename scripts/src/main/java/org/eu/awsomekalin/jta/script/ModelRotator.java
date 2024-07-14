package org.eu.awsomekalin.jta.script;

import com.google.gson.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ModelRotator {

    private static final String MODELS_PATH = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta\\models\\block";

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        File modelsDir = new File(MODELS_PATH);

        if (!modelsDir.isDirectory()) {
            System.err.println("Invalid models directory.");
            return;
        }

        File[] modelFiles = modelsDir.listFiles();
        if (modelFiles == null) {
            System.err.println("No model files found.");
            return;
        }

        for (File modelFile : modelFiles) {
            if (modelFile.isFile() && modelFile.getName().endsWith(".json")) {
                rotateModelFile(modelFile);
            }
        }
    }

    private static void rotateModelFile(File baseModelFile) {
        try (FileReader reader = new FileReader(baseModelFile)) {
            JsonObject baseModelJson = GSON.fromJson(reader, JsonObject.class);
            String baseModelName = baseModelFile.getName().replace(".json", "");

            // Rotate elements
            rotateElements(baseModelJson, baseModelName, 22.5f);
            rotateElements(baseModelJson, baseModelName, 45f);
            rotateElements(baseModelJson, baseModelName, 67.5f);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void rotateElements(JsonObject baseModelJson, String baseModelName, float angle) {
        JsonObject rotatedModelJson = deepCopyJsonObject(baseModelJson);

        JsonArray elementsArray = baseModelJson.getAsJsonArray("elements");
        JsonArray rotatedElementsArray = new JsonArray();

        for (JsonElement elementElement : elementsArray) {
            JsonObject element = elementElement.getAsJsonObject();

            // Deep copy the element object
            JsonObject rotatedElement = deepCopyJsonObject(element);

            // Rotate the element
            rotateElement(rotatedElement, angle);

            // Add rotated element to the array
            rotatedElementsArray.add(rotatedElement);
        }

        // Replace elements array with rotated elements
        rotatedModelJson.add("elements", rotatedElementsArray);

        // Write rotated model to file
        String rotatedModelFileName = baseModelName + "_" + (int) angle + ".json";
        String rotatedModelFilePath = MODELS_PATH + "\\" + rotatedModelFileName;

        try (FileWriter writer = new FileWriter(rotatedModelFilePath)) {
            writer.write(GSON.toJson(rotatedModelJson));
            System.out.println("Created rotated model: " + rotatedModelFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void rotateElement(JsonObject element, float angle) {
        JsonArray from = element.getAsJsonArray("from");
        JsonArray to = element.getAsJsonArray("to");

        // Ensure rotation object exists
        JsonObject rotation = element.has("rotation") ? element.getAsJsonObject("rotation") : new JsonObject();
        element.add("rotation", rotation);

        // Update rotation properties
        rotation.addProperty("angle", angle);
        rotation.addProperty("axis", "y");

        // Ensure origin array exists
        JsonArray origin = rotation.has("origin") ? rotation.getAsJsonArray("origin") : new JsonArray();
        rotation.add("origin", origin);

        // Update origin coordinates
        if (origin.size() != 3) {
            origin.add(0);
            origin.add(0);
            origin.add(0);
        }
        origin.set(0, new JsonPrimitive(from.get(0).getAsFloat() + (to.get(0).getAsFloat() - from.get(0).getAsFloat()) / 2));
        origin.set(1, new JsonPrimitive(from.get(1).getAsFloat() + (to.get(1).getAsFloat() - from.get(1).getAsFloat()) / 2));
        origin.set(2, new JsonPrimitive(from.get(2).getAsFloat() + (to.get(2).getAsFloat() - from.get(2).getAsFloat()) / 2));
    }

    private static JsonObject deepCopyJsonObject(JsonObject jsonObject) {
        JsonObject copy = new JsonObject();
        for (String key : jsonObject.keySet()) {
            JsonElement value = jsonObject.get(key);
            if (value.isJsonObject()) {
                copy.add(key, deepCopyJsonObject(value.getAsJsonObject()));
            } else if (value.isJsonArray()) {
                copy.add(key, deepCopyJsonArray(value.getAsJsonArray()));
            } else {
                copy.add(key, value);
            }
        }
        return copy;
    }

    private static JsonArray deepCopyJsonArray(JsonArray jsonArray) {
        JsonArray copy = new JsonArray();
        for (JsonElement element : jsonArray) {
            if (element.isJsonObject()) {
                copy.add(deepCopyJsonObject(element.getAsJsonObject()));
            } else if (element.isJsonArray()) {
                copy.add(deepCopyJsonArray(element.getAsJsonArray()));
            } else {
                copy.add(element);
            }
        }
        return copy;
    }
}
