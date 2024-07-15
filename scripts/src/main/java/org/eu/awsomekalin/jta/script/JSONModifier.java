package org.eu.awsomekalin.jta.script;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class JSONModifier {

    public static void main(String[] args) {
        // Specify the file path
        String filePath = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta\\lang\\en_us.json";

        // Read JSON file
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            // Create new entries
            JsonObject newEntries = new JsonObject();
            for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue().getAsString();
                
                if (key.startsWith("block.jta.rusty_")) {
                    // Replace "Rusty" in value with "Black" and add new key-value pair
                    String newValueBlack = value.replace("Rusty", "Black");
                    newEntries.addProperty(key.replace("rusty_", "black_"), newValueBlack);

                    // Replace "Rusty" in value with "Steel" and add new key-value pair
                    String newValueSteel = value.replace("Rusty", "Steel");
                    newEntries.addProperty(key.replace("rusty_", "steel_"), newValueSteel);

                    // Replace "Rusty" in value with "Wall", remove "_Pole" if present in value, and add new key-value pair
                    String newValueWall = value.replace("Rusty", "Wall").replace(" Pole", "");
                    newEntries.addProperty(key.replace("rusty_", "wall_"), newValueWall);
                }
            }

            // Add new entries to the original JSON object
            for (Map.Entry<String, JsonElement> entry : newEntries.entrySet()) {
                jsonObject.add(entry.getKey(), entry.getValue());
            }

            // Write the modified JSON back to the file
            try (FileWriter writer = new FileWriter(filePath)) {
                Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();
                gsonPretty.toJson(jsonObject, writer);
            }

            System.out.println("JSON file has been updated successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
