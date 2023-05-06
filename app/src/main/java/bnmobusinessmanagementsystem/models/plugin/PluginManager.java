package bnmobusinessmanagementsystem.models.plugin;

import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

public class PluginManager {
    private List<Object> plugins;
    private File pluginFile;

    public PluginManager() {
        plugins = new ArrayList<>();

//        pluginFile = new File("plugins.txt");
//
//        // Load any previously loaded plugins from file
//        loadPluginsFromFolder();
    }

    public void loadPluginsFromFolder(String folderPath, String className) {
        try {
            File folder = new File(folderPath);
            File[] files = folder.listFiles((dir, name) -> name.endsWith(".jar"));
            if (files == null) {
                return;
            }
            for (File file : files) {
                URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{file.toURI().toURL()});
                Class<?> cls = classLoader.loadClass(className);
                Object plugin = cls.getDeclaredConstructor().newInstance();
                plugins.add(plugin);

//                // Save the loaded plugin
//                savePluginToFile(file.getAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void loadPlugin(String filename, String className) {
//        try {
//            File file = new File(filename);
//            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{file.toURI().toURL()});
//            Class<?> cls = classLoader.loadClass(className);
//            Object plugin = cls.getDeclaredConstructor().newInstance();
//            plugins.add(plugin);
//
//            // Save the load plugin
//            savePluginToFile(filename);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void unloadPlugin() {
        plugins.clear();

        // Remove the plugin file
//        pluginFile.delete();
    }

    public List<Object> getPlugins() {
        return plugins;
    }

//    private void loadPluginsFromFolder(String folderPath) {
//        File folder = new File(folderPath);
//
//        if (folder.exists() && folder.isDirectory()) {
//            File[] files = folder.listFiles();
//
//            for (File file : files) {
//                String filename = file.getName();
//
//                // Load only .jar files
//                if (filename.endsWith(".jar")) {
//                    loadPlugin(file.getAbsolutePath(), "bnmobusinessmanagementsystem.pluginExample.MyPlugin");
//                }
//            }
//        }
//    }

//    private void savePluginToFile(String filename) {
//        try (PrintWriter out = new PrintWriter(new FileWriter(pluginFile))) {
//            for (String pluginFilename : pluginFilenames) {
//                out.println(pluginFilename);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}