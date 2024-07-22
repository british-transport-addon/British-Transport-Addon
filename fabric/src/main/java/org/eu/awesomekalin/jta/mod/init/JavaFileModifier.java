package org.eu.awesomekalin.jta.mod.init;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class JavaFileModifier {

    public static void main(String[] args) {
        // Specify the file path to BlockInit.java
        //String filePath = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\java\\org\\eu\\awesomekalin\\jta\\mod\\InitClient.java";
        String filePath = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\java\\org\\eu\\awesomekalin\\jta\\mod\\init\\BlockInit.java";

        try {
            // Read the content of the file into a List
            List<String> lines = readLines(filePath);
            List<String> newLines = new ArrayList<>();

            AtomicReference<String> lastLine = new AtomicReference<>("");
            lines.forEach(line -> {
                if (!Objects.equals(line, lastLine.get())) {
                    newLines.add(line);
                    if (line.contains("RUSTY_")) {
                        newLines.add(line.replace("RUSTY_", "STEEL_").replace("rusty_", "steel_"));
                    }
                }
                lastLine.set(line);
            });
            newLines.forEach(System.out::println);

            // Uncomment below to write modified content back to the file
            writeLines(filePath, newLines);
            System.out.println("Lines starting with RUSTY_ duplicated with STEEL_ prefix in " + filePath + ".");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read lines from a file into a List
    private static List<String> readLines(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();
        return lines;
    }

    // Method to write lines to a file
    private static void writeLines(String filePath, List<String> lines) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (String line : lines) {
            writer.write(line);
            writer.newLine();
        }
        writer.close();
    }
}
