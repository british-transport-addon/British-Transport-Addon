package org.eu.awsomekalin.jta.script;

import com.google.gson.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class BlockStateModelGenerator {
    private static final String BLOCKSTATES_PATH = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta\\blockstates";
    private static final String MODELS_PATH = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta\\models\\block";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        File blockStatesDir = new File(BLOCKSTATES_PATH);
        File[] blockStateFiles = blockStatesDir.listFiles((dir, name) -> name.endsWith(".json"));

        if (blockStateFiles != null) {
            for (File blockStateFile : blockStateFiles) {
                try {
                    FileReader reader = new FileReader(blockStateFile);
                    JsonObject blockStateJson = new JsonParser().parse(reader).getAsJsonObject();
                    reader.close();

                    if (isMatchingBlockState(blockStateJson)) {
                        String baseName = blockStateFile.getName().replace(".json", "");
                        updateBlockStateFile(blockStateFile, baseName);
                        createRotatedModelFiles(baseName);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static boolean isMatchingBlockState(JsonObject blockStateJson) {
        JsonObject variants = blockStateJson.getAsJsonObject("variants");
        if (variants != null) {
            JsonObject facingNorth = variants.getAsJsonObject("facing=north");
            return facingNorth != null && facingNorth.has("model");
        }
        return false;
    }

    private static void updateBlockStateFile(File blockStateFile, String baseName) throws IOException {
        FileReader reader = new FileReader(new File(BLOCKSTATES_PATH, "aws.json"));
        JsonObject awsBlockStateJson = new JsonParser().parse(reader).getAsJsonObject();
        reader.close();

        String updatedBlockStateContent = GSON.toJson(awsBlockStateJson).replace("aws", baseName);
        FileWriter writer = new FileWriter(blockStateFile);
        writer.write(updatedBlockStateContent);
        writer.close();
    }

    private static void createRotatedModelFiles(String baseName) throws IOException {
        File modelFile = new File(MODELS_PATH, baseName + ".json");
        FileReader reader = new FileReader(modelFile);
        JsonObject modelJson = new JsonParser().parse(reader).getAsJsonObject();
        reader.close();

        createRotatedModelFile(modelJson, baseName + "_22_5", -22.5f);
        createRotatedModelFile(modelJson, baseName + "_45", -45.0f);
        createRotatedModelFile(modelJson, baseName + "_67_5", 22.5f);
    }

    private static void createRotatedModelFile(JsonObject originalModelJson, String newFileName, float rotationAngle) throws IOException {
        JsonObject newModelJson = originalModelJson;

        if (newModelJson.has("elements")) {
            for (JsonElement element : newModelJson.getAsJsonArray("elements")) {
                JsonObject elementObj = element.getAsJsonObject();
                JsonObject rotation = elementObj.getAsJsonObject("rotation");
                if (rotation == null) {
                    rotation = new JsonObject();
                    elementObj.add("rotation", rotation);
                }
                rotation.addProperty("angle", rotationAngle);
            }
        }

        File newModelFile = new File(MODELS_PATH, newFileName + ".json");
        FileWriter writer = new FileWriter(newModelFile);
        GSON.toJson(newModelJson, writer);
        writer.close();
    }
}