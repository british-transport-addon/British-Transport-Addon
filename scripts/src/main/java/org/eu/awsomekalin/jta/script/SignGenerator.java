package org.eu.awsomekalin.jta.script;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SignGenerator {

    private static final Set<String> blockInitLines = new HashSet<>();
    private static final Set<String> clientCutoutLines = new HashSet<>();
    private static Map<String, String> keyReferences = new HashMap<>();

    public static void main(String[] args) {
        String base = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta";
        String baseDir = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta\\textures\\block\\signs";
        String modelOutputBaseDir = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta\\models\\block\\sign\\generated";
        String blockstateOutputBaseDir = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta\\blockstates\\sign";
        String itemOutputBaseDir = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta\\models\\item\\sign";

        keyReferences.put("sign_half", "halfed");
        keyReferences.put("sign_center", "halfed");
        keyReferences.put("sign_top", "halfed");
        keyReferences.put("sign_large", "large");
        keyReferences.put("sign_all", "simple");
        keyReferences.put("sign_small", "simple");

        processDirectory(new File(baseDir, "halfed"), new File(modelOutputBaseDir, "halfed"), new File(blockstateOutputBaseDir, "halfed"), new File(itemOutputBaseDir, "halfed"), "sign_half");
        processDirectory(new File(baseDir, "halfed"), new File(modelOutputBaseDir, "halfed"), new File(blockstateOutputBaseDir, "halfed"), new File(itemOutputBaseDir, "halfed"), "sign_center");
        processDirectory(new File(baseDir, "halfed"), new File(modelOutputBaseDir, "halfed"), new File(blockstateOutputBaseDir, "halfed"), new File(itemOutputBaseDir, "halfed"), "sign_top");
        processDirectory(new File(baseDir, "large"), new File(modelOutputBaseDir, "large"), new File(blockstateOutputBaseDir, "large"), new File(itemOutputBaseDir, "large"), "sign_large");
        processDirectory(new File(baseDir, "simple"), new File(modelOutputBaseDir, "simple"), new File(blockstateOutputBaseDir, "simple"), new File(itemOutputBaseDir, "simple"), "sign_all");
        processDirectory(new File(baseDir, "simple"), new File(modelOutputBaseDir, "simple"), new File(blockstateOutputBaseDir, "simple"), new File(itemOutputBaseDir, "simple"), "sign_small");

        generateTextFiles(new File(base).getParentFile());
    }

    private static void processDirectory(File inputDir, File modelOutputDir, File blockstateOutputDir, File itemOutputDir, String parentType) {
        File[] files = inputDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));
        if (files != null) {
            for (File file : files) {
                String baseName = file.getName().replace(".png", "");
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
                if (parentType.contains("top") || parentType.contains("center")) {
                    return;
                }
                String inputName = "jta:block/sign/generated/";
                if (parentType.startsWith("sign_half")) {
                    inputName = "sign/halfed";
                    generateHalfedBlockstateFile(file, blockstateDir, parentType, baseName);
                } else {
                    inputName = "sign/simple";
                    if (parentType.contains("large")) {
                        inputName = "sign/large";
                    }
                    generateBlockstateFile(file, blockstateDir, parentType, baseName);
                }
                generateItemFile(file, itemDir, parentType, baseName);

                inputName = inputName + "/" + baseName + '/' + baseName;
                String capitalizedBaseName = baseName.toUpperCase();
                blockInitLines.add(String.format(
                        "public static final BlockRegistryObject SIGN_%s = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, \"%s\"), () -> new Block(new WallSignBase()), CreativeTabInit.JTA_SIGNS);",
                        capitalizedBaseName, inputName));

                clientCutoutLines.add(String.format(
                        "REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), BlockInit.SIGN_%s);",
                        capitalizedBaseName));
            }
        }
    }

    private static String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    private static void generateModelFile(File pngFile, File outputDir, String parentType, String baseName) {
        String jsonFileName = (parentType.replace("sign", baseName) + ".json").replace("_all.json", ".json").replace("_large.json", ".json");

        String jsonContent = String.format(
                "{\n" +
                        "  \"textures\": {\n" +
                        "    \"sign\": \"jta:block/signs/" + keyReferences.get(parentType) + "/%s\",\n" +
                        "    \"sign_back\": \"jta:block/steel_rect\"\n" +
                        "  },\n" +
                        "  \"parent\": \"jta:block/sign/%s\"\n" +
                        "}", baseName, parentType);

        writeFile(new File(outputDir, jsonFileName), jsonContent);
    }

    private static void generateBlockstateFile(File pngFile, File outputDir, String parentType, String baseName) {
        String jsonFileName = parentType.replace("sign", baseName).replace("_all", "").replace("_large", "") + ".json";
        String modelPath = String.format("jta:block/sign/generated/simple/%s/%s", jsonFileName, jsonFileName).replace(".json", "");
        if (parentType.contains("large")) {
            modelPath = modelPath.replace("simple", "large");
        }

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

        writeFile(new File(outputDir, jsonFileName), blockstateContent);
    }

    private static void generateHalfedBlockstateFile(File pngFile, File outputDir, String parentType, String baseName) {
        String jsonFileName = baseName + ".json";
        String modelPath = String.format("jta:block/sign/generated/halfed/%s", parentType).replace("sign", baseName);

        String blockstateContent = String.format(
                "{\n" +
                        "  \"multipart\": [\n" +
                        "    {\"when\": {\"facing\": \"north\", \"is_22_5\": \"false\", \"is_45\": \"false\", \"type\": \"bottom\"}, \"apply\": {\"model\": \"%s_half\"}},\n" +
                        "    {\"when\": {\"facing\": \"east\", \"is_22_5\": \"false\", \"is_45\": \"false\", \"type\": \"bottom\"}, \"apply\": {\"model\": \"%s_half\", \"y\": 90, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"south\", \"is_22_5\": \"false\", \"is_45\": \"false\", \"type\": \"bottom\"}, \"apply\": {\"model\": \"%s_half\", \"y\": 180, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"west\", \"is_22_5\": \"false\", \"is_45\": \"false\", \"type\": \"bottom\"}, \"apply\": {\"model\": \"%s_half\", \"y\": 270, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"north\", \"is_22_5\": \"true\", \"is_45\": \"false\", \"type\": \"bottom\"}, \"apply\": {\"model\": \"%s_half/angles/22_5\"}},\n" +
                        "    {\"when\": {\"facing\": \"east\", \"is_22_5\": \"true\", \"is_45\": \"false\", \"type\": \"bottom\"}, \"apply\": {\"model\": \"%s_half/angles/22_5\", \"y\": 90, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"south\", \"is_22_5\": \"true\", \"is_45\": \"false\", \"type\": \"bottom\"}, \"apply\": {\"model\": \"%s_half/angles/22_5\", \"y\": 180, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"west\", \"is_22_5\": \"true\", \"is_45\": \"false\", \"type\": \"bottom\"}, \"apply\": {\"model\": \"%s_half/angles/22_5\", \"y\": 270, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"north\", \"is_22_5\": \"false\", \"is_45\": \"true\", \"type\": \"bottom\"}, \"apply\": {\"model\": \"%s_half/angles/45\"}},\n" +
                        "    {\"when\": {\"facing\": \"east\", \"is_22_5\": \"false\", \"is_45\": \"true\", \"type\": \"bottom\"}, \"apply\": {\"model\": \"%s_half/angles/45\", \"y\": 90, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"south\", \"is_22_5\": \"false\", \"is_45\": \"true\", \"type\": \"bottom\"}, \"apply\": {\"model\": \"%s_half/angles/45\", \"y\": 180, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"west\", \"is_22_5\": \"false\", \"is_45\": \"true\", \"type\": \"bottom\"}, \"apply\": {\"model\": \"%s_half/angles/45\", \"y\": 270, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"north\", \"is_22_5\": \"true\", \"is_45\": \"true\", \"type\": \"bottom\"}, \"apply\": {\"model\": \"%s_half/angles/67_5\", \"y\": 90, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"east\", \"is_22_5\": \"true\", \"is_45\": \"true\", \"type\": \"bottom\"}, \"apply\": {\"model\": \"%s_half/angles/67_5\", \"y\": 180, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"south\", \"is_22_5\": \"true\", \"is_45\": \"true\", \"type\": \"bottom\"}, \"apply\": {\"model\": \"%s_half/angles/67_5\", \"y\": 270, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"west\", \"is_22_5\": \"true\", \"is_45\": \"true\", \"type\": \"bottom\"}, \"apply\": {\"model\": \"%s_half/angles/67_5\"}},\n" +

                        "    {\"when\": {\"facing\": \"north\", \"is_22_5\": \"false\", \"is_45\": \"false\", \"type\": \"both\"}, \"apply\": {\"model\": \"%s_center\"}},\n" +
                        "    {\"when\": {\"facing\": \"east\", \"is_22_5\": \"false\", \"is_45\": \"false\", \"type\": \"both\"}, \"apply\": {\"model\": \"%s_center\", \"y\": 90, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"south\", \"is_22_5\": \"false\", \"is_45\": \"false\", \"type\": \"both\"}, \"apply\": {\"model\": \"%s_center\", \"y\": 180, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"west\", \"is_22_5\": \"false\", \"is_45\": \"false\", \"type\": \"both\"}, \"apply\": {\"model\": \"%s_center\", \"y\": 270, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"north\", \"is_22_5\": \"true\", \"is_45\": \"false\", \"type\": \"both\"}, \"apply\": {\"model\": \"%s_center/angles/22_5\"}},\n" +
                        "    {\"when\": {\"facing\": \"east\", \"is_22_5\": \"true\", \"is_45\": \"false\", \"type\": \"both\"}, \"apply\": {\"model\": \"%s_center/angles/22_5\", \"y\": 90, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"south\", \"is_22_5\": \"true\", \"is_45\": \"false\", \"type\": \"both\"}, \"apply\": {\"model\": \"%s_center/angles/22_5\", \"y\": 180, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"west\", \"is_22_5\": \"true\", \"is_45\": \"false\", \"type\": \"both\"}, \"apply\": {\"model\": \"%s_center/angles/22_5\", \"y\": 270, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"north\", \"is_22_5\": \"false\", \"is_45\": \"true\", \"type\": \"both\"}, \"apply\": {\"model\": \"%s_center/angles/45\"}},\n" +
                        "    {\"when\": {\"facing\": \"east\", \"is_22_5\": \"false\", \"is_45\": \"true\", \"type\": \"both\"}, \"apply\": {\"model\": \"%s_center/angles/45\", \"y\": 90, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"south\", \"is_22_5\": \"false\", \"is_45\": \"true\", \"type\": \"both\"}, \"apply\": {\"model\": \"%s_center/angles/45\", \"y\": 180, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"west\", \"is_22_5\": \"false\", \"is_45\": \"true\", \"type\": \"both\"}, \"apply\": {\"model\": \"%s_center/angles/45\", \"y\": 270, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"north\", \"is_22_5\": \"true\", \"is_45\": \"true\", \"type\": \"both\"}, \"apply\": {\"model\": \"%s_center/angles/67_5\", \"y\": 90, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"east\", \"is_22_5\": \"true\", \"is_45\": \"true\", \"type\": \"both\"}, \"apply\": {\"model\": \"%s_center/angles/67_5\", \"y\": 180, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"south\", \"is_22_5\": \"true\", \"is_45\": \"true\", \"type\": \"both\"}, \"apply\": {\"model\": \"%s_center/angles/67_5\", \"y\": 270, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"west\", \"is_22_5\": \"true\", \"is_45\": \"true\", \"type\": \"both\"}, \"apply\": {\"model\": \"%s_center/angles/67_5\"}},\n" +

                        "    {\"when\": {\"facing\": \"north\", \"is_22_5\": \"false\", \"is_45\": \"false\", \"type\": \"top\"}, \"apply\": {\"model\": \"%s_top\"}},\n" +
                        "    {\"when\": {\"facing\": \"east\", \"is_22_5\": \"false\", \"is_45\": \"false\", \"type\": \"top\"}, \"apply\": {\"model\": \"%s_top\", \"y\": 90, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"south\", \"is_22_5\": \"false\", \"is_45\": \"false\", \"type\": \"top\"}, \"apply\": {\"model\": \"%s_top\", \"y\": 180, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"west\", \"is_22_5\": \"false\", \"is_45\": \"false\", \"type\": \"top\"}, \"apply\": {\"model\": \"%s_top\", \"y\": 270, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"north\", \"is_22_5\": \"true\", \"is_45\": \"false\", \"type\": \"top\"}, \"apply\": {\"model\": \"%s_top/angles/22_5\"}},\n" +
                        "    {\"when\": {\"facing\": \"east\", \"is_22_5\": \"true\", \"is_45\": \"false\", \"type\": \"top\"}, \"apply\": {\"model\": \"%s_top/angles/22_5\", \"y\": 90, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"south\", \"is_22_5\": \"true\", \"is_45\": \"false\", \"type\": \"top\"}, \"apply\": {\"model\": \"%s_top/angles/22_5\", \"y\": 180, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"west\", \"is_22_5\": \"true\", \"is_45\": \"false\", \"type\": \"top\"}, \"apply\": {\"model\": \"%s_top/angles/22_5\", \"y\": 270, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"north\", \"is_22_5\": \"false\", \"is_45\": \"true\", \"type\": \"top\"}, \"apply\": {\"model\": \"%s_top/angles/45\"}},\n" +
                        "    {\"when\": {\"facing\": \"east\", \"is_22_5\": \"false\", \"is_45\": \"true\", \"type\": \"top\"}, \"apply\": {\"model\": \"%s_top/angles/45\", \"y\": 90, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"south\", \"is_22_5\": \"false\", \"is_45\": \"true\", \"type\": \"top\"}, \"apply\": {\"model\": \"%s_top/angles/45\", \"y\": 180, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"west\", \"is_22_5\": \"false\", \"is_45\": \"true\", \"type\": \"top\"}, \"apply\": {\"model\": \"%s_top/angles/45\", \"y\": 270, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"north\", \"is_22_5\": \"true\", \"is_45\": \"true\", \"type\": \"top\"}, \"apply\": {\"model\": \"%s_top/angles/67_5\", \"y\": 90, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"east\", \"is_22_5\": \"true\", \"is_45\": \"true\", \"type\": \"top\"}, \"apply\": {\"model\": \"%s_top/angles/67_5\", \"y\": 180, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"south\", \"is_22_5\": \"true\", \"is_45\": \"true\", \"type\": \"top\"}, \"apply\": {\"model\": \"%s_top/angles/67_5\", \"y\": 270, \"uvlock\": true}},\n" +
                        "    {\"when\": {\"facing\": \"west\", \"is_22_5\": \"true\", \"is_45\": \"true\", \"type\": \"top\"}, \"apply\": {\"model\": \"%s_top/angles/67_5\"}}\n" +
                        "  ]\n" +
                        "}"
                , modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath
                , modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath, modelPath
        );

        writeFile(new File(outputDir, jsonFileName), blockstateContent);
    }

    private static void generateItemFile(File pngFile, File outputDir, String parentType, String baseName) {
        String jsonFileName = baseName + ".json";

        String itemContent = String.format(
                "{\n" +
                        "  \"parent\": \"jta:block/sign/generated/" + keyReferences.get(parentType) + "/%s/%s\"\n" +
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
        File clientCutoutFile = new File(baseDir, "ClientCutout update.txt");

        String blockInitContent = String.join("\n", blockInitLines);
        String clientCutoutContent = String.join("\n", clientCutoutLines);

        writeFile(blockInitFile, blockInitContent);
        writeFile(clientCutoutFile, clientCutoutContent);
    }
}
