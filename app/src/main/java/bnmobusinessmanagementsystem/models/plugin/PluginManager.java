package bnmobusinessmanagementsystem.models.plugin;

import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

import bnmobusinessmanagementsystem.pluginsStorage.pluginsInput.Plugin;
public class PluginManager {
    private List<Object> plugins;
    private final String pathPlugin = "src/main/java/bnmobusinessmanagementsystem/pluginsStorage/pluginsApplication";

    public PluginManager(String pluginInterfaceName) {
        plugins = new ArrayList<>();

        String currentDir = System.getProperty("user.dir");
        String fullPath = Paths.get(currentDir, this.pathPlugin).toString();

        if (!hasJarFiles(fullPath).equals("true")) {
            return;
        }

        loadPluginsFromFolder(fullPath, pluginInterfaceName);
    }

    public String hasJarFiles( String folderPath ) {
        File folder = new File(folderPath);

        // Check if the folder exists
        if (!folder.exists() || !folder.isDirectory()) {
            return "The specified path is not a valid directory.";
        }

        // Get all the JAR files in the folder
        File[] jarFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jar"));

        // Check if there are any JAR files in the folder
        if (jarFiles == null || jarFiles.length == 0) {
            return "The specified directory does not contain any JAR files.";
        }

        return "true";
    }

    public void loadPluginsFromFolder(String folderPath, String pluginInterfaceName) {
        String hasJar = hasJarFiles(folderPath);
        if (!hasJar.equals("true")) {
            throw new IllegalArgumentException(hasJar);
        }

        File folder = new File(folderPath);

        // Get all the JAR files in the folder
        File[] jarFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jar"));

        String currentDir = System.getProperty("user.dir");
        String fullPath = Paths.get(currentDir, this.pathPlugin).toString();

        // Load the plugins from each JAR file
        for (File jarFile : jarFiles) {
            String pluginName = jarFile.getName().substring(0, jarFile.getName().lastIndexOf("."));
            loadPlugin(jarFile.getAbsolutePath(), pluginInterfaceName + "." + pluginName);
            try {
                savePluginToFolder(jarFile, fullPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadPlugin(String path, String className) {
        try {
            File file = new File(path);
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{file.toURI().toURL()});
            Class<?> cls = classLoader.loadClass(className);
            Object plugin = cls.getDeclaredConstructor().newInstance();
            plugins.add(plugin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unloadPlugin() {
        String currentDir = System.getProperty("user.dir");
        String folderPath = Paths.get(currentDir, this.pathPlugin).toString();

        plugins.clear();
        File folder = new File(folderPath);

        // Check if the folder exists
        if (!folder.exists() || !folder.isDirectory()) {
            throw new IllegalArgumentException("The specified path is not a valid directory.");
        }

        // Get all the JAR files in the folder
        File[] jarFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jar"));

        // Check if there are any JAR files in the folder
        if (jarFiles == null || jarFiles.length == 0) {
            return;
        }

        // Delete the JAR files
        for (File jarFile : jarFiles) {
            if (!jarFile.delete()) {
                throw new RuntimeException("Failed to delete JAR file: " + jarFile.getAbsolutePath());
            }
        }
    }

    public List<Object> getPlugins() {
        return plugins;
    }

    public void savePluginToFolder(File pluginFile, String folderPath) throws IOException {
        // Create the output folder if it doesn't exist
        File folder = new File(folderPath);

        // Copy the plugin JAR file to the output folder
        Path pluginPath = pluginFile.toPath();
        Path outputPath = Paths.get(folderPath, pluginFile.getName());
        Files.copy(pluginPath, outputPath, StandardCopyOption.REPLACE_EXISTING);
    }
}