package bnmobusinessmanagementsystem.views.components.Settings;

import bnmobusinessmanagementsystem.models.plugin.PluginManager;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Settings extends VBox {
    private String databasePath;
    public Settings() {
        Label filePathLabel = new Label("No folder selected");
        filePathLabel.setStyle("""
            -fx-font-size: 24px;
            -fx-text-fill: #FEFEA8;
            -fx-background-color: transparent;
            -fx-font-family: "SF Pro Rounded Semibold";
            -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 0);
            -fx-padding: 0 0 20 0;
        """);

        Button chooseFolderButton = new Button("Choose Folder");
        chooseFolderButton.setStyle("""
            -fx-background-color: #8A5760;
            -fx-text-fill: white;
            -fx-font-size: 14px;
            -fx-font-family: "SF Pro Rounded Semibold";
            -fx-padding: 10px 20px;
            -fx-background-radius: 20px;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 0, 1);
        """);

        chooseFolderButton.setOnAction(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Select Folder");

            // Set the initial directory
            String currentDir = System.getProperty("user.dir");
            Path currentPath = Paths.get(currentDir);
            File initialDir = new File(currentDir);
            directoryChooser.setInitialDirectory(initialDir);

            File selectedDirectory = directoryChooser.showDialog(this.getScene().getWindow());
            if (selectedDirectory != null) {
                Path selectedPath = selectedDirectory.toPath();
                Path relativePath = currentPath.relativize(selectedPath);
                filePathLabel.setText("Selected folder: " + relativePath);

                this.databasePath = selectedDirectory.toString();
            }
        });

        PluginManager pluginManager = new PluginManager();

        Label filePluginLabel = new Label("No plugin selected");
        filePluginLabel.setStyle("""
            -fx-font-size: 24px;
            -fx-text-fill: #FEFEA8;
            -fx-background-color: transparent;
            -fx-font-family: "SF Pro Rounded Semibold";
            -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 0);
            -fx-padding: 40 0 20 0;
        """);

        Button choosePluginButton = new Button("Choose Plugin");
        choosePluginButton.setStyle("""
            -fx-background-color: #8A5760;
            -fx-text-fill: white;
            -fx-font-size: 14px;
            -fx-font-family: "SF Pro Rounded Semibold";
            -fx-padding: 10px 20px;
            -fx-background-radius: 20px;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 0, 1);
        """);

        choosePluginButton.setOnAction(e -> {
//            FileChooser fileChooser = new FileChooser();
//            fileChooser.setTitle("Load Plugins");
//
//            // Set the initial directory
//            String currentDir = System.getProperty("user.dir");
//            File initialDir = new File(currentDir);
//            fileChooser.setInitialDirectory(initialDir);
//
//            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JAR files", "*.jar"));
//            File file = fileChooser.showOpenDialog(this.getScene().getWindow());
//            if (file != null) {
//                pluginManager.loadPlugin(file.getAbsolutePath(), "bnmobusinessmanagementsystem.pluginExample.MyPlugin");
//                filePluginLabel.setText("Selected plugin: " + file.getName());
//
//                // Get the loaded plugins
//                List<Object> plugins = pluginManager.getPlugins();
//
//                // Call getName() and run() on each loaded plugin
//                for (Object plugin : plugins) {
//                    try {
//                        // Get the name of the plugin
//                        Method getNameMethod = plugin.getClass().getMethod("getName");
//                        String name = (String) getNameMethod.invoke(plugin);
//                        System.out.println("Plugin name: " + name);
//
//                        // Run the plugin
//                        Method runMethod = plugin.getClass().getMethod("run");
//                        runMethod.invoke(plugin);
//                    } catch (Exception error) {
//                        error.printStackTrace();
//                    }
//                }
//
//                // Unload the plugins
//                pluginManager.unloadPlugin();
//            }
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Load Plugins");

            // Set the initial directory
            String currentDir = System.getProperty("user.dir");
            File initialDir = new File(currentDir);
            directoryChooser.setInitialDirectory(initialDir);

            File folder = directoryChooser.showDialog(this.getScene().getWindow());
            if (folder != null) {
                pluginManager.loadPluginsFromFolder(folder.getAbsolutePath(), "bnmobusinessmanagementsystem.pluginExample.MyPlugin");
                filePluginLabel.setText("Selected folder: " + folder.getName());

                // Get the loaded plugins
                List<Object> plugins = pluginManager.getPlugins();

                // Call getName() and run() on each loaded plugin
                for (Object plugin : plugins) {
                    try {
                        // Get the name of the plugin
                        Method getNameMethod = plugin.getClass().getMethod("getName");
                        String name = (String) getNameMethod.invoke(plugin);
                        System.out.println("Plugin name: " + name);

                        // Run the plugin
                        Method runMethod = plugin.getClass().getMethod("run");
                        runMethod.invoke(plugin);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }

                // Unload the plugins
                pluginManager.unloadPlugin();
            }
        });

        this.getChildren().addAll(filePathLabel, chooseFolderButton, filePluginLabel, choosePluginButton);
        this.setAlignment(Pos.CENTER);
        this.setStyle("""
            -fx-background-color: rgba(0, 0, 0, 0.5);
            -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 10, 0, 0, 0);
            -fx-padding: 120px;
            -fx-background-radius: 20px;
        """);
    }

    public String getDBPath() {
        return this.databasePath;
    }
}
