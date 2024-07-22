package org.eu.awsomekalin.jta.script;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class ItemModelGenerator {

    private static final String BLOCKSTATES_PATH = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta\\blockstates";
    private static final String MODELS_PATH = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta\\models\\block";
    private static final String ITEM_MODELS_PATH = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta\\models\\item";


    public static void main(String[] args) {
        String blockstatesDir = BLOCKSTATES_PATH;
        String itemModelsDir = ITEM_MODELS_PATH;

        // Ensure the item models directory exists
        createDirectoryIfNotExists(itemModelsDir);

        // Process each file in the blockstates directory
        File blockstatesDirectory = new File(blockstatesDir);
        if (blockstatesDirectory.exists() && blockstatesDirectory.isDirectory()) {
            for (File blockstateFile : Objects.requireNonNull(blockstatesDirectory.listFiles())) {
                if (blockstateFile.isFile()) {
                    String blockstateFilename = blockstateFile.getName();
                    String itemModelFilename = blockstateFilename.replace(".json", ".json");

                    // Check if the corresponding item model file exists
                    Path itemModelPath = Paths.get(itemModelsDir, itemModelFilename);
                    if (!Files.exists(itemModelPath)) {
                        // Generate the item model file
                        generateItemModel(itemModelPath, blockstateFilename);
                    }
                }
            }
        } else {
            System.out.println("Blockstates directory does not exist or is not a directory.");
        }
    }

    private static void createDirectoryIfNotExists(String directory) {
        Path path = Paths.get(directory);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void generateItemModel(Path itemModelPath, String blockstateFilename) {
        String itemModelContent = String.format("{\n  \"parent\": \"jta:block/%s\"\n}", blockstateFilename.replace(".json", ""));
        try (FileWriter writer = new FileWriter(itemModelPath.toFile())) {
            writer.write(itemModelContent);
            System.out.println("Generated item model: " + itemModelPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
