package org.eu.awsomekalin.jta.script;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BusShelterGenerator {
    private static List<String> blockInitLines = new ArrayList<>();

    public static void main(String[] args) {
        String base = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta";
        String baseDir = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta\\textures\\block\\bus_shelter\\base";
        String seatBaseDir = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta\\textures\\block\\bus_shelter\\seat";
        String modelOutputDir = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta\\models\\block\\bus_shelter\\generated";
        String blockstateOutputDir = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta\\blockstates\\bus_shelter\\generated";
        String itemOutputDir = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta\\models\\item\\bus_shelter\\generated";

        processDirectory(new File(baseDir), new File(seatBaseDir), new File(modelOutputDir), new File(blockstateOutputDir), new File(itemOutputDir), "bus_shelter_left");
        processDirectory(new File(baseDir), new File(seatBaseDir), new File(modelOutputDir), new File(blockstateOutputDir), new File(itemOutputDir), "bus_shelter_left_long");
        processDirectory(new File(baseDir), new File(seatBaseDir), new File(modelOutputDir), new File(blockstateOutputDir), new File(itemOutputDir), "bus_shelter_right");

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

                    generateModelFile(file, modelDir, parentType, baseName, seatName);
                    generateBlockstateFile(blockstateDir, parentType, baseName, seatName);
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

    private static void generateModelFile(File pngFile, File outputDir, String parentType, String baseName, String seatName) {
        String jsonFileName = parentType + ".json";
        String jsonContent = String.format(
                "{\n" +
                        "  \"parent\": \"jta:block/bus_shelter/%s\",\n" +
                        "  \"textures\": {\n" +
                        "    \"color\": \"jta:block/bus_shelter/base/%s\",\n" +
                        "    \"seatColor\": \"jta:block/bus_shelter/seat/%s\"\n" +
                        "  }\n" +
                        "}", parentType, baseName, seatName);

        writeFile(new File(outputDir, jsonFileName), jsonContent);
    }

    private static void generateBlockstateFile(File blockstateDir, String parentType, String baseName, String seatName) {
        String modelPath = "jta:block/bus_shelter/generated/" + baseName + "/" + seatName + "/" + parentType;
        String blockstateContent = String.format(
                "{\n" +
                        "  \"multipart\": [\n" +
                        "    {\"when\": {\"facing\": \"north\", \"is_22_5\": \"false\", \"is_45\": \"false\"}, \"apply\": {\"model\": \"%s\"}},\n" +
                        "    {\"when\": {\"facing\": \"east\", \"is_22_5\": \"false\", \"is_45\": \"false\"}, \"apply\": {\"model\": \"%s\", \"y\": 90, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"south\", \"is_22_5\": \"false\", \"is_45\": \"false\"}, \"apply\": {\"model\": \"%s\", \"y\": 180, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"west\", \"is_22_5\": \"false\", \"is_45\": \"false\"}, \"apply\": {\"model\": \"%s\", \"y\": 270, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"north\", \"is_22_5\": \"true\", \"is_45\": \"false\"}, \"apply\": {\"model\": \"%s/angles/22_5\"}},\n" +
                        "    {\"when\": {\"facing\": \"east\", \"is_22_5\": \"true\", \"is_45\": \"false\"}, \"apply\": {\"model\": \"%s/angles/22_5\", \"y\": 90, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"south\", \"is_22_5\": \"true\", \"is_45\": \"false\"}, \"apply\": {\"model\": \"%s/angles/22_5\", \"y\": 180, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"west\", \"is_22_5\": \"true\", \"is_45\": \"false\"}, \"apply\": {\"model\": \"%s/angles/22_5\", \"y\": 270, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"north\", \"is_22_5\": \"false\", \"is_45\": \"true\"}, \"apply\": {\"model\": \"%s/angles/45\"}},\n" +
                        "    {\"when\": {\"facing\": \"east\", \"is_22_5\": \"false\", \"is_45\": \"true\"}, \"apply\": {\"model\": \"%s/angles/45\", \"y\": 90, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"south\", \"is_22_5\": \"false\", \"is_45\": \"true\"}, \"apply\": {\"model\": \"%s/angles/45\", \"y\": 180, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"west\", \"is_22_5\": \"false\", \"is_45\": \"true\"}, \"apply\": {\"model\": \"%s/angles/45\", \"y\": 270, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"north\", \"is_22_5\": \"true\", \"is_45\": \"true\"}, \"apply\": {\"model\": \"%s/angles/67_5\", \"y\": 90, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"east\", \"is_22_5\": \"true\", \"is_45\": \"true\"}, \"apply\": {\"model\": \"%s/angles/67_5\", \"y\": 180, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"south\", \"is_22_5\": \"true\", \"is_45\": \"true\"}, \"apply\": {\"model\": \"%s/angles/67_5\", \"y\": 270, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"west\", \"is_22_5\": \"true\", \"is_45\": \"true\"}, \"apply\": {\"model\": \"%s/angles/67_5\"}}\n" +
                        "  ]\n" +
                        "}",
                modelPath, modelPath, modelPath, modelPath,
                modelPath, modelPath, modelPath, modelPath,
                modelPath, modelPath, modelPath, modelPath,
                modelPath, modelPath, modelPath, modelPath
        );
        writeFile(new File(blockstateDir, parentType + ".json"), blockstateContent);
    }

    private static void generateItemFile(File pngFile, File outputDir, String parentType, String baseName, String seatName) {
        String jsonFileName = parentType + ".json";

        String itemContent = String.format(
                "{\n" +
                        "  \"parent\": \"jta:block/bus_shelter/generated/%s/%s/" + parentType + "\"\n" +
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
        File blockInitFile = new File(baseDir, "BusSehter BlockInit update.txt");

        String blockInitContent = String.join("\n", blockInitLines);

        writeFile(blockInitFile, blockInitContent);
    }
}