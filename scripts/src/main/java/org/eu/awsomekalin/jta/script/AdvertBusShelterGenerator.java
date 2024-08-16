package org.eu.awsomekalin.jta.script;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class AdvertBusShelterGenerator {
    private static List<String> blockInitLines = new ArrayList<>();

    private static Map<String, String> adverts = new HashMap<>();

    public static void main(String[] args) {
        String base = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta";
        String baseDir = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta\\textures\\block\\bus_shelter\\base";
        String seatBaseDir = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta\\textures\\block\\bus_shelter\\seat";
        String modelOutputDir = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta\\models\\block\\bus_shelter\\generated";
        String blockstateOutputDir = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta\\blockstates\\bus_shelter\\generated";
        String itemOutputDir = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta\\models\\item\\bus_shelter\\generated";

        String advertDir = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta\\textures\\block\\adverts";

        String lastKeyMatch = null;
        for (File file : new File(advertDir).listFiles()) {
            if (lastKeyMatch != null) {
                adverts.put(lastKeyMatch, file.getName().replace(".png", ""));
                lastKeyMatch = null;
            } else {
                lastKeyMatch = file.getName().replace(".png", "");
            }
        }

        processDirectory(new File(baseDir), new File(seatBaseDir), new File(modelOutputDir), new File(blockstateOutputDir), new File(itemOutputDir), "bus_shelter_right_advert");
        processDirectory(new File(baseDir), new File(seatBaseDir), new File(modelOutputDir), new File(blockstateOutputDir), new File(itemOutputDir), "bus_shelter_right_advert_open");

        generateTextFiles(new File(base));
    }

    private static void processDirectory(File inputDir, File seatBaseDir, File modelOutputDir, File blockstateOutputDir, File itemOutputDir, String parentType) {
        File[] files = inputDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));
        File[] seatFiles = seatBaseDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));
        if (files != null) {
            for (File file : files) {
                for (File seatFile : seatFiles) {
                    String baseName = file.getName().replace(".png", "");
                    String seatName = seatFile.getName().replace(".png", "");
                    String fullReference = parentType + "/" + baseName + "/" + baseName + "_" + parentType;
                    File modelDir = new File(new File(modelOutputDir, baseName), seatName);
                    File blockstateDir = new File(new File(blockstateOutputDir, baseName), seatName);
                    File itemDir = new File(new File(itemOutputDir, baseName), seatName);

                    if (!modelDir.exists()) {
                        modelDir.mkdirs();
                    }
                    if (!blockstateDir.exists()) {
                        blockstateDir.mkdirs();
                    }
                    if (!itemDir.exists()) {
                        itemDir.mkdirs();
                    }


                    generateBlockstateFile(blockstateDir, parentType, baseName, seatName);
                    AtomicInteger x = new AtomicInteger();
                    adverts.forEach((advert1, advert2) -> {
                        generateModelFile(x.getAndIncrement(), file, modelDir, parentType, baseName, seatName, advert1, advert2);
                    });
                    generateItemFile(file, itemDir, parentType, baseName, seatName);

                    String inputName = "bus_shelter/generated/" + baseName + "/" + seatName + "/" + parentType;
                    String capitalizedFullReference = (parentType + "_" + baseName + "_" + seatName).toUpperCase().replace("/", "_");
                    blockInitLines.add(String.format(
                            "public static final BlockRegistryObject %s = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, \"%s\"), () -> new Block(xx" + parentType + "xx), CreativeTabInit.JTA_SIGNS);",
                            capitalizedFullReference, inputName));
                }
            }
        }
    }

    private static void generateModelFile(int x,File pngFile, File outputDir, String parentType, String baseName, String seatName, String advert1, String advert2) {
        String jsonFileName = parentType + x + ".json";
        String jsonContent = String.format(
                "{\n" +
                        "  \"parent\": \"jta:block/bus_shelter/%s\",\n" +
                        "  \"textures\": {\n" +
                        "    \"color\": \"jta:block/bus_shelter/base/%s\",\n" +
                        "    \"seatColor\": \"jta:block/bus_shelter/seat/%s\",\n" +
                        "    \"advert1\": \"jta:block/adverts/%s\",\n" +
                        "    \"advert2\": \"jta:block/adverts/%s\"\n" +
                        "  }\n" +
                        "}", parentType, baseName, seatName, advert1, advert2);

        writeFile(new File(outputDir, jsonFileName), jsonContent);
    }

    private static void generateBlockstateFile(File blockstateDir, String parentType, String baseName, String seatName) {
        // Initialize a StringBuilder to construct the JSON content
        StringBuilder blockstateContent = new StringBuilder("{\n  \"variants\": {\n");

        // Define the different facing directions
        String[] facings = {"north", "east", "south", "west"};
        int[] rotations = {0, 90, 180, -90};

        // Iterate over each facing direction
        for (int i = 0; i < facings.length; i++) {
            String facing = facings[i];
            int rotation = rotations[i];

            // Add the facing section to the JSON content
            blockstateContent.append(String.format("    \"facing=%s\": [\n", facing));

            // Iterate over the adverts map using an index
            int index = 0;
            for (Map.Entry<String, String> entry : adverts.entrySet()) {
                String modelPath = "jta:block/bus_shelter/generated/" + baseName + "/" + seatName + "/" + parentType + index;

                // Add each model with the appropriate rotation
                blockstateContent.append(String.format("      { \"model\": \"%s\"", modelPath));
                if (rotation != 0) {
                    blockstateContent.append(String.format(", \"y\": %d", rotation));
                }
                blockstateContent.append(" },\n");

                index++;
            }

            // Remove the last comma and close the facing section
            blockstateContent.setLength(blockstateContent.length() - 2); // Remove the last comma
            blockstateContent.append("\n    ],\n");
        }

        // Remove the last comma and close the variants and JSON content
        blockstateContent.setLength(blockstateContent.length() - 2); // Remove the last comma
        blockstateContent.append("\n  }\n}");

        // Write the content to the blockstate file
        writeFile(new File(blockstateDir, parentType + ".json"), blockstateContent.toString());
    }


    private static void generateItemFile(File pngFile, File outputDir, String parentType, String baseName, String seatName) {
        String jsonFileName = parentType + ".json";

        String itemContent = String.format(
                "{\n" +
                        "  \"parent\": \"jta:block/bus_shelter/generated/%s/%s/" + parentType + "0\"\n" +
                        "}", baseName, seatName
        );

        writeFile(new File(outputDir, jsonFileName), itemContent);
    }

    private static void writeFile(File file, String content) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateTextFiles(File baseDir) {
        File blockInitFile = new File(baseDir, "BusSehter advert BlockInit update.txt");

        String blockInitContent = String.join("\n", blockInitLines);

        writeFile(blockInitFile, blockInitContent);
    }
}