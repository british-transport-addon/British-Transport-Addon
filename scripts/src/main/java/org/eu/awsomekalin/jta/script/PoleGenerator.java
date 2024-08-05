package org.eu.awsomekalin.jta.script;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PoleGenerator {
    private static List<String> blockInitLines = new ArrayList<>();

    public static void main(String[] args) {
        String base = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta";
        String baseDir = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta\\textures\\block\\pole";
        String modelOutputDir = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta\\models\\block\\pole\\generated";
        String blockstateOutputDir = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta\\blockstates\\pole\\generated";
        String itemOutputDir = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta\\models\\item\\pole\\generated";

        processDirectory(new File(baseDir), new File(modelOutputDir), new File(blockstateOutputDir), new File(itemOutputDir), "street_pole");
        processDirectory(new File(baseDir), new File(modelOutputDir), new File(blockstateOutputDir), new File(itemOutputDir), "street_pole_base");
        processDirectory(new File(baseDir), new File(modelOutputDir), new File(blockstateOutputDir), new File(itemOutputDir), "tiny_pole");
        processDirectory(new File(baseDir), new File(modelOutputDir), new File(blockstateOutputDir), new File(itemOutputDir), "tiny_offset_pole");
        processDirectory(new File(baseDir), new File(modelOutputDir), new File(blockstateOutputDir), new File(itemOutputDir), "pole");

        generateTextFiles(new File(base));
    }

    private static void processDirectory(File inputDir, File modelOutputDir, File blockstateOutputDir, File itemOutputDir, String parentType) {
        File[] files = inputDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));
        if (files != null) {
            for (File file : files) {
                String baseName = file.getName().replace(".png", "");
                String fullReference = parentType + "/" + baseName + "/" + baseName + "_" + parentType;
                File modelDir = new File(modelOutputDir, baseName);
                File blockstateDir = new File(blockstateOutputDir, baseName);
                File itemDir = new File(itemOutputDir, baseName);

                if (!modelDir.exists()) {
                    modelDir.mkdirs();
                }
                if (!blockstateDir.exists()) {
                    blockstateDir.mkdirs();
                }
                if (!itemDir.exists()) {
                    itemDir.mkdirs();
                }

                generateModelFile(file, modelDir, parentType, baseName);
                generateBlockstateFile(blockstateDir, parentType, baseName);
                generateItemFile(file, itemDir, parentType, baseName);

                String inputName = "jta:block/pole/generated/" + baseName + "/" + baseName + "_" + parentType;
                String capitalizedFullReference = (parentType + "_" + baseName).toUpperCase().replace("/", "_");
                blockInitLines.add(String.format(
                        "public static final BlockRegistryObject %s = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, \"%s\"), () -> new Block(new PoleBase()), CreativeTabInit.JTA_SIGNS);",
                        capitalizedFullReference, inputName));
            }
        }
    }

    private static void generateModelFile(File pngFile, File outputDir, String parentType, String baseName) {
        String jsonFileName = baseName + "_" + parentType + ".json";
        String jsonContent = String.format(
                "{\n" +
                        "  \"parent\": \"jta:block/pole/%s\",\n" +
                        "  \"textures\": {\n" +
                        "    \"color\": \"jta:block/pole/%s\"\n" +
                        "  }\n" +
                        "}", parentType, baseName);

        writeFile(new File(outputDir, jsonFileName), jsonContent);
    }

    private static void generateBlockstateFile(File blockstateDir, String parentType, String baseName) {
        String modelPath = "jta:block/pole/generated/" + baseName + "/" + baseName + "_" + parentType;
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
        writeFile(new File(blockstateDir, baseName + "_" + parentType + ".json"), blockstateContent);
    }

    private static void generateItemFile(File pngFile, File outputDir, String parentType, String baseName) {
        String jsonFileName = baseName + "_" + parentType + ".json";

        String itemContent = String.format(
                "{\n" +
                        "  \"parent\": \"jta:block/pole/generated/%s/%s_" + parentType + "\"\n" +
                        "}", baseName, baseName
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
        File blockInitFile = new File(baseDir, "BlockInit update.txt");

        String blockInitContent = String.join("\n", blockInitLines);

        writeFile(blockInitFile, blockInitContent);
    }
}