package org.eu.awsomekalin.jta.script.fx;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BlockstateGenerator {

    private static final String TEXTURES_PATH = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta\\textures\\block";
    private static final String BLOCKSTATES_PATH = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta\\blockstates";
    private static final String MODELS_PATH = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta\\models\\block";
    private static final String ITEM_MODELS_PATH = "C:\\Users\\emzyy\\Desktop\\Jesses-Transit-Addon\\fabric\\src\\main\\resources\\assets\\jta\\models\\item";

    private File selectedModelFile;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BlockstateGenerator generator = new BlockstateGenerator();
            generator.createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        // Create the main frame
        JFrame frame = new JFrame("Blockstate Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        // Create the file chooser
        JFileChooser fileChooser = new JFileChooser(TEXTURES_PATH);
        fileChooser.setDialogTitle("Select Texture File");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Texture Files", "png"));

        // Create a checkbox for generating a small model
        JCheckBox generateSmallModelCheckbox = new JCheckBox("Generate Small Model");

        // Create a panel to hold the checkbox and add it to the frame
        JPanel panel = new JPanel();
        panel.add(generateSmallModelCheckbox);
        frame.add(panel, BorderLayout.CENTER);

        // Create a button for file selection and add it to the frame
        JButton selectFileButton = new JButton("Select File");
        selectFileButton.addActionListener(e -> {
            int userSelection = fileChooser.showOpenDialog(frame);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                selectedModelFile = fileChooser.getSelectedFile();
                processModelFile();
                if (generateSmallModelCheckbox.isSelected()) {
                    processSmallModelFile();
                }
            } else {
                JOptionPane.showMessageDialog(frame, "File selection cancelled.");
            }
        });

        // Add the button to the frame
        frame.add(selectFileButton, BorderLayout.SOUTH);

        // Display the frame
        frame.setVisible(true);
    }

    private void processModelFile() {
        String modelName = selectedModelFile.getName().replace(".png", "");

        generateBlockstate(modelName, "wall");
        generateBlockstate(modelName, "steel");
        generateBlockstate(modelName, "black");
        generateBlockstate(modelName, "rusty");

        JOptionPane.showMessageDialog(null, "Blockstate JSON files generated successfully.");
    }

    private void processSmallModelFile() {
        String modelName = selectedModelFile.getName().replace(".png", "_small");

        generateBlockstate(modelName, "wall");
        generateBlockstate(modelName, "steel");
        generateBlockstate(modelName, "black");
        generateBlockstate(modelName, "rusty");

        JOptionPane.showMessageDialog(null, "Small Blockstate JSON files generated successfully.");
    }

    private void generateBlockstate(String modelName, String prefix) {
        generateItemModel(prefix + "_" + modelName);
        if (modelName.contains("small")) {
            if (modelName.contains("wall")) {
                generateSmallWallModel(modelName, prefix, "", 0f);
                generateSmallWallModel(modelName, prefix, "_22_5", -22.5f);
                generateSmallWallModel(modelName, prefix, "_45", -45.0f);
                generateSmallWallModel(modelName, prefix, "_67_5", 22.5f);
            } else {
                generateSmallPoleModel(modelName, prefix, "", 0f);
                generateSmallPoleModel(modelName, prefix, "_22_5", -22.5f);
                generateSmallPoleModel(modelName, prefix, "_45", -45.0f);
                generateSmallPoleModel(modelName, prefix, "_67_5", 22.5f);
            }
        } else {
            if (modelName.contains("wall")) {
                generateWallModel(modelName, prefix, "", 0f);
                generateWallModel(modelName, prefix, "_22_5", -22.5f);
                generateWallModel(modelName, prefix, "_45", -45.0f);
                generateWallModel(modelName, prefix, "_67_5", 22.5f);
            } else {
                generatePoleModel(modelName, prefix, "", 0f);
                generatePoleModel(modelName, prefix, "_22_5", -22.5f);
                generatePoleModel(modelName, prefix, "_45", -45.0f);
                generatePoleModel(modelName, prefix, "_67_5", 22.5f);
            }
        }

        try {
            FileWriter writer = new FileWriter(new File(BLOCKSTATES_PATH, prefix + "_" + modelName + ".json"));
            writer.write("{\n");
            writer.write("  \"multipart\": [\n");
            writer.write("    {\n");
            writer.write("      \"when\": {\n");
            writer.write("        \"facing\": \"north\",\n");
            writer.write("        \"is_22_5\": \"false\",\n");
            writer.write("        \"is_45\": \"false\"\n");
            writer.write("      },\n");
            writer.write("      \"apply\": {\n");
            writer.write("        \"model\": \"jta:block/" + prefix + "_" + modelName + "\"\n");
            writer.write("      }\n");
            writer.write("    },\n");
            writer.write("    {\n");
            writer.write("      \"when\": {\n");
            writer.write("        \"facing\": \"east\",\n");
            writer.write("        \"is_22_5\": \"false\",\n");
            writer.write("        \"is_45\": \"false\"\n");
            writer.write("      },\n");
            writer.write("      \"apply\": {\n");
            writer.write("        \"model\": \"jta:block/" + prefix + "_" + modelName + "\",\n");
            writer.write("        \"y\": 90,\n");
            writer.write("        \"uvlock\": true\n");
            writer.write("      }\n");
            writer.write("    },\n");
            writer.write("    {\n");
            writer.write("      \"when\": {\n");
            writer.write("        \"facing\": \"south\",\n");
            writer.write("        \"is_22_5\": \"false\",\n");
            writer.write("        \"is_45\": \"false\"\n");
            writer.write("      },\n");
            writer.write("      \"apply\": {\n");
            writer.write("        \"model\": \"jta:block/" + prefix + "_" + modelName + "\",\n");
            writer.write("        \"y\": 180,\n");
            writer.write("        \"uvlock\": true\n");
            writer.write("      }\n");
            writer.write("    },\n");
            writer.write("    {\n");
            writer.write("      \"when\": {\n");
            writer.write("        \"facing\": \"west\",\n");
            writer.write("        \"is_22_5\": \"false\",\n");
            writer.write("        \"is_45\": \"false\"\n");
            writer.write("      },\n");
            writer.write("      \"apply\": {\n");
            writer.write("        \"model\": \"jta:block/" + prefix + "_" + modelName + "\",\n");
            writer.write("        \"y\": 270,\n");
            writer.write("        \"uvlock\": true\n");
            writer.write("      }\n");
            writer.write("    },\n");
            writer.write("    {\n");
            writer.write("      \"when\": {\n");
            writer.write("        \"facing\": \"north\",\n");
            writer.write("        \"is_22_5\": \"true\",\n");
            writer.write("        \"is_45\": \"false\"\n");
            writer.write("      },\n");
            writer.write("      \"apply\": {\n");
            writer.write("        \"model\": \"jta:block/" + prefix + "_" + modelName + "_22_5\"\n");
            writer.write("      }\n");
            writer.write("    },\n");
            writer.write("    {\n");
            writer.write("      \"when\": {\n");
            writer.write("        \"facing\": \"east\",\n");
            writer.write("        \"is_22_5\": \"true\",\n");
            writer.write("        \"is_45\": \"false\"\n");
            writer.write("      },\n");
            writer.write("      \"apply\": {\n");
            writer.write("        \"model\": \"jta:block/" + prefix + "_" + modelName + "_22_5\",\n");
            writer.write("        \"y\": 90,\n");
            writer.write("        \"uvlock\": true\n");
            writer.write("      }\n");
            writer.write("    },\n");
            writer.write("    {\n");
            writer.write("      \"when\": {\n");
            writer.write("        \"facing\": \"south\",\n");
            writer.write("        \"is_22_5\": \"true\",\n");
            writer.write("        \"is_45\": \"false\"\n");
            writer.write("      },\n");
            writer.write("      \"apply\": {\n");
            writer.write("        \"model\": \"jta:block/" + prefix + "_" + modelName + "_22_5\",\n");
            writer.write("        \"y\": 180,\n");
            writer.write("        \"uvlock\": true\n");
            writer.write("      }\n");
            writer.write("    },\n");
            writer.write("    {\n");
            writer.write("      \"when\": {\n");
            writer.write("        \"facing\": \"west\",\n");
            writer.write("        \"is_22_5\": \"true\",\n");
            writer.write("        \"is_45\": \"false\"\n");
            writer.write("      },\n");
            writer.write("      \"apply\": {\n");
            writer.write("        \"model\": \"jta:block/" + prefix + "_" + modelName + "_22_5\",\n");
            writer.write("        \"y\": 270,\n");
            writer.write("        \"uvlock\": true\n");
            writer.write("      }\n");
            writer.write("    },\n");
            writer.write("    {\n");
            writer.write("      \"when\": {\n");
            writer.write("        \"facing\": \"north\",\n");
            writer.write("        \"is_22_5\": \"false\",\n");
            writer.write("        \"is_45\": \"true\"\n");
            writer.write("      },\n");
            writer.write("      \"apply\": {\n");
            writer.write("        \"model\": \"jta:block/" + prefix + "_" + modelName + "_45\"\n");
            writer.write("      }\n");
            writer.write("    },\n");
            writer.write("    {\n");
            writer.write("      \"when\": {\n");
            writer.write("        \"facing\": \"east\",\n");
            writer.write("        \"is_22_5\": \"false\",\n");
            writer.write("        \"is_45\": \"true\"\n");
            writer.write("      },\n");
            writer.write("      \"apply\": {\n");
            writer.write("        \"model\": \"jta:block/" + prefix + "_" + modelName + "_45\",\n");
            writer.write("        \"y\": 90,\n");
            writer.write("        \"uvlock\": true\n");
            writer.write("      }\n");
            writer.write("    },\n");
            writer.write("    {\n");
            writer.write("      \"when\": {\n");
            writer.write("        \"facing\": \"south\",\n");
            writer.write("        \"is_22_5\": \"false\",\n");
            writer.write("        \"is_45\": \"true\"\n");
            writer.write("      },\n");
            writer.write("      \"apply\": {\n");
            writer.write("        \"model\": \"jta:block/" + prefix + "_" + modelName + "_45\",\n");
            writer.write("        \"y\": 180,\n");
            writer.write("        \"uvlock\": true\n");
            writer.write("      }\n");
            writer.write("    },\n");
            writer.write("    {\n");
            writer.write("      \"when\": {\n");
            writer.write("        \"facing\": \"west\",\n");
            writer.write("        \"is_22_5\": \"false\",\n");
            writer.write("        \"is_45\": \"true\"\n");
            writer.write("      },\n");
            writer.write("      \"apply\": {\n");
            writer.write("        \"model\": \"jta:block/" + prefix + "_" + modelName + "_45\",\n");
            writer.write("        \"y\": 270,\n");
            writer.write("        \"uvlock\": true\n");
            writer.write("      }\n");
            writer.write("    },\n");
            writer.write("    {\n");
            writer.write("      \"when\": {\n");
            writer.write("        \"facing\": \"north\",\n");
            writer.write("        \"is_22_5\": \"true\",\n");
            writer.write("        \"is_45\": \"true\"\n");
            writer.write("      },\n");
            writer.write("      \"apply\": {\n");
            writer.write("        \"model\": \"jta:block/" + prefix + "_" + modelName + "_67_5\",\n");
            writer.write("        \"y\": 90,\n");
            writer.write("        \"uvlock\": true\n");
            writer.write("      }\n");
            writer.write("    },\n");
            writer.write("    {\n");
            writer.write("      \"when\": {\n");
            writer.write("        \"facing\": \"east\",\n");
            writer.write("        \"is_22_5\": \"true\",\n");
            writer.write("        \"is_45\": \"true\"\n");
            writer.write("      },\n");
            writer.write("      \"apply\": {\n");
            writer.write("        \"model\": \"jta:block/" + prefix + "_" + modelName + "_67_5\",\n");
            writer.write("        \"y\": 180,\n");
            writer.write("        \"uvlock\": true\n");
            writer.write("      }\n");
            writer.write("    },\n");
            writer.write("    {\n");
            writer.write("      \"when\": {\n");
            writer.write("        \"facing\": \"south\",\n");
            writer.write("        \"is_22_5\": \"true\",\n");
            writer.write("        \"is_45\": \"true\"\n");
            writer.write("      },\n");
            writer.write("      \"apply\": {\n");
            writer.write("        \"model\": \"jta:block/" + prefix + "_" + modelName + "_67_5\",\n");
            writer.write("        \"y\": 270,\n");
            writer.write("        \"uvlock\": true\n");
            writer.write("      }\n");
            writer.write("    },\n");
            writer.write("    {\n");
            writer.write("      \"when\": {\n");
            writer.write("        \"facing\": \"west\",\n");
            writer.write("        \"is_22_5\": \"true\",\n");
            writer.write("        \"is_45\": \"true\"\n");
            writer.write("      },\n");
            writer.write("      \"apply\": {\n");
            writer.write("        \"model\": \"jta:block/" + prefix + "_" + modelName + "_67_5\"\n");
            writer.write("      }\n");
            writer.write("    }\n");
            writer.write("  ]\n");
            writer.write("}\n");
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to write blockstate file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generateItemModel(String modelName) {
        try {
            FileWriter writer = new FileWriter(new File(ITEM_MODELS_PATH, modelName + ".json"));
            writer.write("{\n");
            writer.write("  \"parent\": \"jta:block/" + modelName + "\"\n");
            writer.write("}\n");
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while generating the item model JSON file: " + e.getMessage());
        }
    }


    private void generateWallModel(String modelName, String prefix, String suffix, float rotation) {
        try {
            FileWriter writer = new FileWriter(new File(MODELS_PATH, prefix + "_" + modelName + suffix + ".json"));
            writer.write("{\n");
            writer.write("  \"credit\": \"Made with Blockbench\",\n");
            writer.write("  \"texture_size\": [128, 193],\n");
            writer.write("  \"textures\": {\n");
            writer.write("    \"0\": \"jta:block/" + modelName + "\",\n");
            writer.write("    \"particle\": \"jta:block/" + modelName + "\"\n");
            writer.write("  },\n");
            writer.write("  \"elements\": [\n");
            writer.write("    {\n");
            writer.write("      \"from\": [0, 0, 0.3],\n");
            writer.write("      \"to\": [16, 16, 0.3],\n");
            writer.write("      \"rotation\": {\n");
            writer.write("        \"angle\": " + rotation + ",\n");
            writer.write("        \"axis\": \"y\",\n");
            writer.write("        \"origin\": [4.8, 3.4, -1.9]\n");
            writer.write("      },\n");
            writer.write("      \"faces\": {\n");
            writer.write("        \"north\": {\n");
            writer.write("          \"uv\": [1.125, 1.16062, 1.925, 1.95648],\n");
            writer.write("          \"texture\": \"#0\"\n");
            writer.write("        },\n");
            writer.write("        \"east\": {\n");
            writer.write("          \"uv\": [0, 0, 0, 0.79585],\n");
            writer.write("          \"texture\": \"#missing\"\n");
            writer.write("        },\n");
            writer.write("        \"south\": {\n");
            writer.write("          \"uv\": [0, 0, 16, 16],\n");
            writer.write("          \"texture\": \"#0\"\n");
            writer.write("        },\n");
            writer.write("        \"west\": {\n");
            writer.write("          \"uv\": [0, 0, 0, 0.79585],\n");
            writer.write("          \"texture\": \"#missing\"\n");
            writer.write("        },\n");
            writer.write("        \"up\": {\n");
            writer.write("          \"uv\": [0, 0, 0.8, 0],\n");
            writer.write("          \"texture\": \"#missing\"\n");
            writer.write("        },\n");
            writer.write("        \"down\": {\n");
            writer.write("          \"uv\": [0, 0, 0.8, 0],\n");
            writer.write("          \"texture\": \"#missing\"\n");
            writer.write("        }\n");
            writer.write("      }\n");
            writer.write("    }\n");
            writer.write("  ],\n");
            writer.write("  \"display\": {\n");
            writer.write("    \"thirdperson_righthand\": {\n");
            writer.write("      \"rotation\": [0, 180, 0]\n");
            writer.write("    },\n");
            writer.write("    \"thirdperson_lefthand\": {\n");
            writer.write("      \"rotation\": [0, 180, 0]\n");
            writer.write("    },\n");
            writer.write("    \"firstperson_righthand\": {\n");
            writer.write("      \"rotation\": [0, 180, 0],\n");
            writer.write("      \"translation\": [0, 0, -2]\n");
            writer.write("    },\n");
            writer.write("    \"firstperson_lefthand\": {\n");
            writer.write("      \"rotation\": [0, 180, 0]\n");
            writer.write("    },\n");
            writer.write("    \"gui\": {\n");
            writer.write("      \"translation\": [1.25, -0.75, 0]\n");
            writer.write("    },\n");
            writer.write("    \"head\": {\n");
            writer.write("      \"rotation\": [0, -180, 0],\n");
            writer.write("      \"translation\": [-1, 14.25, -7.5]\n");
            writer.write("    }\n");
            writer.write("  }\n");
            writer.write("}\n");
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while generating the wall model JSON file: " + e.getMessage());
        }
    }


    private void generatePoleModel(String modelName, String prefix, String suffix, float rotation) {
        try {
            FileWriter writer = new FileWriter(new File(MODELS_PATH, prefix + "_" + modelName + suffix + ".json"));
            writer.write("{\n");
            writer.write("  \"credit\": \"Made with Blockbench\",\n");
            writer.write("  \"texture_size\": [\n");
            writer.write("    128,\n");
            writer.write("    128\n");
            writer.write("  ],\n");
            writer.write("  \"textures\": {\n");
            writer.write("    \"1\": \"jta:block/" + prefix + "_pole\",\n");
            writer.write("    \"3\": \"jta:block/" + modelName + "\"\n");
            writer.write("  },\n");
            writer.write("  \"elements\": [\n");
            writer.write("    {\n");
            writer.write("      \"from\": [\n");
            writer.write("        7.5,\n");
            writer.write("        0,\n");
            writer.write("        7.5\n");
            writer.write("      ],\n");
            writer.write("      \"to\": [\n");
            writer.write("        8.5,\n");
            writer.write("        16,\n");
            writer.write("        8.5\n");
            writer.write("      ],\n");
            writer.write("      \"rotation\": {\n");
            writer.write("        \"angle\": " + rotation + ",\n");
            writer.write("        \"axis\": \"y\",\n");
            writer.write("        \"origin\": [\n");
            writer.write("          7,\n");
            writer.write("          0,\n");
            writer.write("          5\n");
            writer.write("        ]\n");
            writer.write("      },\n");
            writer.write("      \"faces\": {\n");
            writer.write("        \"north\": {\n");
            writer.write("          \"uv\": [\n");
            writer.write("            0,\n");
            writer.write("            0,\n");
            writer.write("            0.5,\n");
            writer.write("            16\n");
            writer.write("          ],\n");
            writer.write("          \"texture\": \"#1\"\n");
            writer.write("        },\n");
            writer.write("        \"east\": {\n");
            writer.write("          \"uv\": [\n");
            writer.write("            7.5,\n");
            writer.write("            0,\n");
            writer.write("            8,\n");
            writer.write("            16\n");
            writer.write("          ],\n");
            writer.write("          \"texture\": \"#1\"\n");
            writer.write("        },\n");
            writer.write("        \"south\": {\n");
            writer.write("          \"uv\": [\n");
            writer.write("            0.5,\n");
            writer.write("            0,\n");
            writer.write("            1,\n");
            writer.write("            16\n");
            writer.write("          ],\n");
            writer.write("          \"texture\": \"#1\"\n");
            writer.write("        },\n");
            writer.write("        \"west\": {\n");
            writer.write("          \"uv\": [\n");
            writer.write("            7,\n");
            writer.write("            0,\n");
            writer.write("            7.5,\n");
            writer.write("            16\n");
            writer.write("          ],\n");
            writer.write("          \"texture\": \"#1\"\n");
            writer.write("        },\n");
            writer.write("        \"up\": {\n");
            writer.write("          \"uv\": [\n");
            writer.write("            1.0625,\n");
            writer.write("            0,\n");
            writer.write("            1.5625,\n");
            writer.write("            1\n");
            writer.write("          ],\n");
            writer.write("          \"texture\": \"#1\"\n");
            writer.write("        },\n");
            writer.write("        \"down\": {\n");
            writer.write("          \"uv\": [\n");
            writer.write("            6.4375,\n");
            writer.write("            0,\n");
            writer.write("            6.9375,\n");
            writer.write("            1\n");
            writer.write("          ],\n");
            writer.write("          \"texture\": \"#1\"\n");
            writer.write("        }\n");
            writer.write("      }\n");
            writer.write("    },\n");
            writer.write("    {\n");
            writer.write("      \"from\": [\n");
            writer.write("        0,\n");
            writer.write("        0,\n");
            writer.write("        7.4\n");
            writer.write("      ],\n");
            writer.write("      \"to\": [\n");
            writer.write("        16,\n");
            writer.write("        16,\n");
            writer.write("        7.4\n");
            writer.write("      ],\n");
            writer.write("      \"rotation\": {\n");
            writer.write("        \"angle\": " + rotation + ",\n");
            writer.write("        \"axis\": \"y\",\n");
            writer.write("        \"origin\": [\n");
            writer.write("          6.5,\n");
            writer.write("          10,\n");
            writer.write("          7.4\n");
            writer.write("        ]\n");
            writer.write("      },\n");
            writer.write("      \"faces\": {\n");
            writer.write("        \"north\": {\n");
            writer.write("          \"uv\": [\n");
            writer.write("            0,\n");
            writer.write("            0,\n");
            writer.write("            16,\n");
            writer.write("            16\n");
            writer.write("          ],\n");
            writer.write("          \"texture\": \"#3\"\n");
            writer.write("        },\n");
            writer.write("        \"east\": {\n");
            writer.write("          \"uv\": [\n");
            writer.write("            0,\n");
            writer.write("            0,\n");
            writer.write("            0,\n");
            writer.write("            0.75\n");
            writer.write("          ],\n");
            writer.write("          \"texture\": \"#3\"\n");
            writer.write("        },\n");
            writer.write("        \"south\": {\n");
            writer.write("          \"uv\": [\n");
            writer.write("            0,\n");
            writer.write("            0,\n");
            writer.write("            16,\n");
            writer.write("            15.9619\n");
            writer.write("          ],\n");
            writer.write("          \"texture\": \"#3\"\n");
            writer.write("        },\n");
            writer.write("        \"west\": {\n");
            writer.write("          \"uv\": [\n");
            writer.write("            0,\n");
            writer.write("            0,\n");
            writer.write("            0,\n");
            writer.write("            0.75\n");
            writer.write("          ],\n");
            writer.write("          \"texture\": \"#3\"\n");
            writer.write("        },\n");
            writer.write("        \"up\": {\n");
            writer.write("          \"uv\": [\n");
            writer.write("            0,\n");
            writer.write("            0,\n");
            writer.write("            0.875,\n");
            writer.write("            0\n");
            writer.write("          ],\n");
            writer.write("          \"texture\": \"#3\"\n");
            writer.write("        },\n");
            writer.write("        \"down\": {\n");
            writer.write("          \"uv\": [\n");
            writer.write("            0,\n");
            writer.write("            0,\n");
            writer.write("            0.875,\n");
            writer.write("            0\n");
            writer.write("          ],\n");
            writer.write("          \"texture\": \"#3\"\n");
            writer.write("        }\n");
            writer.write("      }\n");
            writer.write("    }\n");
            writer.write("  ],\n");
            writer.write("  \"display\": {\n");
            writer.write("    \"thirdperson_righthand\": {\n");
            writer.write("      \"rotation\": [\n");
            writer.write("        0,\n");
            writer.write("        180,\n");
            writer.write("        0\n");
            writer.write("      ]\n");
            writer.write("    },\n");
            writer.write("    \"thirdperson_lefthand\": {\n");
            writer.write("      \"rotation\": [\n");
            writer.write("        0,\n");
            writer.write("        180,\n");
            writer.write("        0\n");
            writer.write("      ]\n");
            writer.write("    },\n");
            writer.write("    \"firstperson_righthand\": {\n");
            writer.write("      \"rotation\": [\n");
            writer.write("        0,\n");
            writer.write("        180,\n");
            writer.write("        0\n");
            writer.write("      ],\n");
            writer.write("      \"translation\": [\n");
            writer.write("        0,\n");
            writer.write("        0,\n");
            writer.write("        -2\n");
            writer.write("      ]\n");
            writer.write("    },\n");
            writer.write("    \"firstperson_lefthand\": {\n");
            writer.write("      \"rotation\": [\n");
            writer.write("        0,\n");
            writer.write("        180,\n");
            writer.write("        0\n");
            writer.write("      ]\n");
            writer.write("    },\n");
            writer.write("    \"gui\": {\n");
            writer.write("      \"rotation\": [\n");
            writer.write("        0,\n");
            writer.write("        180,\n");
            writer.write("        0\n");
            writer.write("      ]\n");
            writer.write("    },\n");
            writer.write("    \"head\": {\n");
            writer.write("      \"translation\": [\n");
            writer.write("        0,\n");
            writer.write("        10,\n");
            writer.write("        0\n");
            writer.write("      ]\n");
            writer.write("    }\n");
            writer.write("  }\n");
            writer.write("}\n");

            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while generating the pole model JSON file: " + e.getMessage());
        }
    }

    private void generateSmallWallModel(String modelName, String prefix, String suffix, float rotation) {
        try {
            FileWriter writer = new FileWriter(new File(MODELS_PATH, prefix + "_" + modelName + suffix + ".json"));
            String contentModelName = modelName.replace("_small", ""); // Replace all instances of _small with nothing in the content
            writer.write("{\n");
            writer.write("  \"credit\": \"Made with Blockbench\",\n");
            writer.write("  \"texture_size\": [128, 193],\n");
            writer.write("  \"textures\": {\n");
            writer.write("    \"0\": \"jta:block/" + contentModelName + "\",\n");
            writer.write("    \"particle\": \"jta:block/" + contentModelName + "\"\n");
            writer.write("  },\n");
            writer.write("  \"elements\": [\n");
            writer.write("    {\n");
            writer.write("      \"from\": [4, 4, 0.3],\n");
            writer.write("      \"to\": [12, 12, 0.3],\n");
            writer.write("      \"rotation\": {\n");
            writer.write("        \"angle\": " + rotation + ",\n");
            writer.write("        \"axis\": \"y\",\n");
            writer.write("        \"origin\": [4.8, 3.4, -1.9]\n");
            writer.write("      },\n");
            writer.write("      \"faces\": {\n");
            writer.write("        \"north\": {\n");
            writer.write("          \"uv\": [1.125, 1.16062, 1.925, 1.95648],\n");
            writer.write("          \"texture\": \"#0\"\n");
            writer.write("        },\n");
            writer.write("        \"east\": {\n");
            writer.write("          \"uv\": [0, 0, 0, 0.79585],\n");
            writer.write("          \"texture\": \"#missing\"\n");
            writer.write("        },\n");
            writer.write("        \"south\": {\n");
            writer.write("          \"uv\": [0, 0, 16, 16],\n");
            writer.write("          \"texture\": \"#0\"\n");
            writer.write("        },\n");
            writer.write("        \"west\": {\n");
            writer.write("          \"uv\": [0, 0, 0, 0.79585],\n");
            writer.write("          \"texture\": \"#missing\"\n");
            writer.write("        },\n");
            writer.write("        \"up\": {\n");
            writer.write("          \"uv\": [0, 0, 0.8, 0],\n");
            writer.write("          \"texture\": \"#missing\"\n");
            writer.write("        },\n");
            writer.write("        \"down\": {\n");
            writer.write("          \"uv\": [0, 0, 0.8, 0],\n");
            writer.write("          \"texture\": \"#missing\"\n");
            writer.write("        }\n");
            writer.write("      }\n");
            writer.write("    }\n");
            writer.write("  ],\n");
            writer.write("  \"display\": {\n");
            writer.write("    \"thirdperson_righthand\": {\n");
            writer.write("      \"rotation\": [0, 180, 0]\n");
            writer.write("    },\n");
            writer.write("    \"thirdperson_lefthand\": {\n");
            writer.write("      \"rotation\": [0, 180, 0]\n");
            writer.write("    },\n");
            writer.write("    \"firstperson_righthand\": {\n");
            writer.write("      \"rotation\": [0, 180, 0],\n");
            writer.write("      \"translation\": [0, 0, -2]\n");
            writer.write("    },\n");
            writer.write("    \"firstperson_lefthand\": {\n");
            writer.write("      \"rotation\": [0, 180, 0]\n");
            writer.write("    },\n");
            writer.write("    \"gui\": {\n");
            writer.write("      \"translation\": [1.25, -0.75, 0]\n");
            writer.write("    },\n");
            writer.write("    \"head\": {\n");
            writer.write("      \"rotation\": [0, -180, 0],\n");
            writer.write("      \"translation\": [-1, 14.25, -7.5]\n");
            writer.write("    }\n");
            writer.write("  }\n");
            writer.write("}\n");
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while generating the wall model JSON file: " + e.getMessage());
        }
    }

    private void generateSmallPoleModel(String modelName, String prefix, String suffix, float rotation) {
        try {
            FileWriter writer = new FileWriter(new File(MODELS_PATH, prefix + "_" + modelName + suffix + ".json"));
            String contentModelName = modelName.replace("_small", ""); // Replace all instances of _small with nothing in the content
            writer.write("{\n");
            writer.write("  \"credit\": \"Made with Blockbench\",\n");
            writer.write("  \"texture_size\": [128, 128],\n");
            writer.write("  \"textures\": {\n");
            writer.write("    \"1\": \"jta:block/" + prefix + "_pole\",\n");
            writer.write("    \"3\": \"jta:block/" + contentModelName + "\"\n");
            writer.write("  },\n");
            writer.write("  \"elements\": [\n");
            writer.write("    {\n");
            writer.write("      \"from\": [7.5, 0, 7.5],\n");
            writer.write("      \"to\": [8.5, 16, 8.5],\n");
            writer.write("      \"rotation\": {\n");
            writer.write("        \"angle\": " + rotation + ",\n");
            writer.write("        \"axis\": \"y\",\n");
            writer.write("        \"origin\": [7, 0, 5]\n");
            writer.write("      },\n");
            writer.write("      \"faces\": {\n");
            writer.write("        \"north\": {\n");
            writer.write("          \"uv\": [0, 0, 0.5, 16],\n");
            writer.write("          \"texture\": \"#1\"\n");
            writer.write("        },\n");
            writer.write("        \"east\": {\n");
            writer.write("          \"uv\": [7.5, 0, 8, 16],\n");
            writer.write("          \"texture\": \"#1\"\n");
            writer.write("        },\n");
            writer.write("        \"south\": {\n");
            writer.write("          \"uv\": [0.5, 0, 1, 16],\n");
            writer.write("          \"texture\": \"#1\"\n");
            writer.write("        },\n");
            writer.write("        \"west\": {\n");
            writer.write("          \"uv\": [7, 0, 7.5, 16],\n");
            writer.write("          \"texture\": \"#1\"\n");
            writer.write("        },\n");
            writer.write("        \"up\": {\n");
            writer.write("          \"uv\": [1.0625, 0, 1.5625, 1],\n");
            writer.write("          \"texture\": \"#1\"\n");
            writer.write("        },\n");
            writer.write("        \"down\": {\n");
            writer.write("          \"uv\": [6.4375, 0, 6.9375, 1],\n");
            writer.write("          \"texture\": \"#1\"\n");
            writer.write("        }\n");
            writer.write("      }\n");
            writer.write("    },\n");
            writer.write("    {\n");
            writer.write("      \"from\": [4, 4, 7.4],\n");
            writer.write("      \"to\": [12, 12, 7.4],\n");
            writer.write("      \"rotation\": {\n");
            writer.write("        \"angle\": " + rotation + ",\n");
            writer.write("        \"axis\": \"y\",\n");
            writer.write("        \"origin\": [6.5, 10, 7.4]\n");
            writer.write("      },\n");
            writer.write("      \"faces\": {\n");
            writer.write("        \"north\": {\n");
            writer.write("          \"uv\": [0, 0, 16, 16],\n");
            writer.write("          \"texture\": \"#3\"\n");
            writer.write("        },\n");
            writer.write("        \"east\": {\n");
            writer.write("          \"uv\": [7.5, 0, 8, 8],\n");
            writer.write("          \"texture\": \"#3\"\n");
            writer.write("        },\n");
            writer.write("        \"south\": {\n");
            writer.write("          \"uv\": [0, 0, 16, 16],\n");
            writer.write("          \"texture\": \"#3\"\n");
            writer.write("        },\n");
            writer.write("        \"west\": {\n");
            writer.write("          \"uv\": [7.5, 0, 8, 8],\n");
            writer.write("          \"texture\": \"#3\"\n");
            writer.write("        },\n");
            writer.write("        \"up\": {\n");
            writer.write("          \"uv\": [1.5, 0, 2, 1],\n");
            writer.write("          \"texture\": \"#3\"\n");
            writer.write("        },\n");
            writer.write("        \"down\": {\n");
            writer.write("          \"uv\": [1.5, 0, 2, 1],\n");
            writer.write("          \"texture\": \"#3\"\n");
            writer.write("        }\n");
            writer.write("      }\n");
            writer.write("    }\n");
            writer.write("  ],\n");
            writer.write("  \"display\": {\n");
            writer.write("    \"thirdperson_righthand\": {\n");
            writer.write("      \"rotation\": [0, 180, 0]\n");
            writer.write("    },\n");
            writer.write("    \"thirdperson_lefthand\": {\n");
            writer.write("      \"rotation\": [0, 180, 0]\n");
            writer.write("    },\n");
            writer.write("    \"firstperson_righthand\": {\n");
            writer.write("      \"rotation\": [0, 180, 0],\n");
            writer.write("      \"translation\": [0, 0, -2]\n");
            writer.write("    },\n");
            writer.write("    \"firstperson_lefthand\": {\n");
            writer.write("      \"rotation\": [0, 180, 0]\n");
            writer.write("    },\n");
            writer.write("    \"gui\": {\n");
            writer.write("      \"translation\": [1.25, -0.75, 0]\n");
            writer.write("    },\n");
            writer.write("    \"head\": {\n");
            writer.write("      \"rotation\": [0, -180, 0],\n");
            writer.write("      \"translation\": [-1, 14.25, -7.5]\n");
            writer.write("    }\n");
            writer.write("  }\n");
            writer.write("}\n");
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while generating the pole model JSON file: " + e.getMessage());
        }
    }
}
