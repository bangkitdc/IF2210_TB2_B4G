package bnmobusinessmanagementsystem.views.components.Settings;

import bnmobusinessmanagementsystem.models.plugin.PluginManager;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Settings extends VBox {
    private String databasePath;

    private List<Object> plugins;
    public Settings() {
        plugins = new ArrayList<Object>();

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

        PluginManager pluginManager = new PluginManager("bnmobusinessmanagementsystem.pluginsStorage.pluginsInput");
        this.plugins = pluginManager.getPlugins();

        Label filePluginLabel = new Label("No plugin selected");
        if (this.plugins.size() > 0) {
            filePluginLabel.setText("Plugins successfully added");
        }
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
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Load Plugins");

            // Set the initial directory
            String currentDir = System.getProperty("user.dir");
            File initialDir = new File(currentDir + "/src/main/java/bnmobusinessmanagementsystem/pluginsStorage");
            directoryChooser.setInitialDirectory(initialDir);

            File folder = directoryChooser.showDialog(this.getScene().getWindow());

            if (folder != null) {
                pluginManager.unloadPlugin();
                pluginManager.loadPluginsFromFolder(folder.getAbsolutePath(), "bnmobusinessmanagementsystem.pluginsStorage.pluginsInput");
                filePluginLabel.setText("Selected folder: " + folder.getName());

                // Get the loaded plugins
                this.plugins = pluginManager.getPlugins();


                // Call getName() and run() on each loaded plugin
                for (Object plugin : this.plugins) {
                    try {
                        // Get the name of the plugin
                        Method getNameMethod = plugin.getClass().getMethod("getName");
                        String name = (String) getNameMethod.invoke(plugin);
                        System.out.println("Plugin name: " + name);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
            }
        });

        VBox pluginSection = new VBox();

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        // Add label and button to GridPane
        Label label = new Label("This is a label added by Plugin1.");
        label.setStyle("""
            -fx-font-size: 16px;
            -fx-text-fill: #FEFEA8;
            -fx-background-color: transparent;
            -fx-font-family: "SF Pro Rounded Regular";
            -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 0);
            -fx-padding: 10 10 10 10;
        """);
        pane.add(label, 0, 0);

        Button button = new Button("Click me!");
        pane.add(button, 0, 1);

        // Set action for button
        button.setOnAction(e -> {
            System.out.println("Button clicked!");
        });

        pluginSection.getChildren().addAll(pane);

//        if (this.plugins.size() > 0) {
//            try {
//                Object plugin1 = this.plugins.get(0);
//                Method runMethod = plugin1.getClass().getMethod("run", GridPane.class);
//                runMethod.invoke(plugin1, mainPane);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

        this.getChildren().addAll(filePathLabel, chooseFolderButton, filePluginLabel, choosePluginButton, pluginSection);
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
