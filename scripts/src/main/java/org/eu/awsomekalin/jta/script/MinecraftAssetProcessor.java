package org.eu.awsomekalin.jta.script;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class MinecraftAssetProcessor {

    // Directories to search
    private static final String BLOCKSTATES_PATH = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta\\blockstates";
    private static final String MODELS_PATH = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta\\models\\block";
    private static final String ITEM_MODELS_PATH = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta\\models\\item";

    public static void main(String[] args) {
        String[] directories = { BLOCKSTATES_PATH, MODELS_PATH, ITEM_MODELS_PATH };

        for (String directory : directories) {
            processDirectory(directory);
        }
    }

    private static void processDirectory(String directoryPath) {
        Path startPath = Paths.get(directoryPath);
        try {
            Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.getFileName().toString().contains("_seperated")) {
                        processFile(file);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processFile(Path file) {
        try {
            // Read the content of the file
            List<String> lines = Files.readAllLines(file);
            String content = String.join(System.lineSeparator(), lines);

            // Replace all instances of "_seperated" with ""
            String modifiedContent = content.replace("_seperated", "");

            // Create the new filename by removing "_seperated" from the original filename
            String newFileName = file.getFileName().toString().replace("_seperated", "");
            Path newFilePath = file.getParent().resolve(newFileName);

            // Write the modified content to the new file
            Files.write(newFilePath, modifiedContent.getBytes());

            System.out.println("Processed: " + file + " -> " + newFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
