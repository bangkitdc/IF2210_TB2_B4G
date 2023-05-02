package bnmobusinessmanagementsystem.views;

import javafx.scene.text.Font;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FontManager {
    public static void loadFonts() {
        String fontDir = "src/main/resources/fonts";

        // Recursively search for all .ttf files in the font directory and its subdirectories
        try {
            Files.walk(Paths.get(fontDir))
                    .filter(path -> path.toString().toLowerCase().endsWith(".ttf"))
                    .forEach(path -> {
                        try {
                            Font.loadFont(Files.newInputStream(path), 12);
                        } catch (IOException e) {
                            System.err.println("Failed to load font: " + path);
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File fontDirectory = new File("src/main/resources/fonts/SF-Pro-Rounded");
        File[] fontFiles = fontDirectory.listFiles();

        for (File fontFile : fontFiles) {
            if (fontFile.getName().endsWith(".ttf")) {
                Font font = Font.loadFont(fontFile.toURI().toString(), 12);
                System.out.println(font.toString());
            }
        }
    }
}
