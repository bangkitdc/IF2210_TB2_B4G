package bnmobusinessmanagementsystem.views.components.Settings;

import bnmobusinessmanagementsystem.App;
import bnmobusinessmanagementsystem.controllers.PaymentStatesControllers;

import bnmobusinessmanagementsystem.models.plugin.ExchangeRate;
import bnmobusinessmanagementsystem.models.plugin.PaymentStates;
import bnmobusinessmanagementsystem.models.plugin.PluginManager;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.List;
import java.util.function.Consumer;

import bnmobusinessmanagementsystem.controllers.ExchangeRateControllers;


public class Settings extends VBox {
    private String databasePath;

    private List<Object> plugins;

    ExchangeRateControllers exchangeRateControllers = new ExchangeRateControllers();
    PaymentStatesControllers paymentStatesControllers = new PaymentStatesControllers();
    public Settings() {
        plugins = new ArrayList<Object>();

        Label filePathLabel = new Label("No folder selected");
        filePathLabel.setStyle("""
            -fx-font-size: 22px;
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
            -fx-font-size: 22px;
            -fx-text-fill: #FEFEA8;
            -fx-background-color: transparent;
            -fx-font-family: "SF Pro Rounded Semibold";
            -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 0);
            -fx-padding: 20 0 20 0;
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

        Button pluginClearButton = new Button("Clear Plugin");
        pluginClearButton.setStyle("""
            -fx-background-color: #8A5760;
            -fx-text-fill: white;
            -fx-font-size: 14px;
            -fx-font-family: "SF Pro Rounded Semibold";
            -fx-padding: 10px 20px;
            -fx-background-radius: 20px;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 0, 1);
        """);

        pluginClearButton.setOnAction(e -> {
            pluginManager.unloadPlugin();
            filePluginLabel.setText("Plugins successfully deleted");
        });

        HBox btn = new HBox();
        btn.getChildren().addAll(choosePluginButton, pluginClearButton);
        btn.setAlignment(Pos.CENTER);
        btn.setSpacing(10);

        VBox pluginSection = new VBox();

        // Akses ke database
        ArrayList<String> arrayList = new ArrayList<>();

        Consumer<Object> optionsChose = (selectedOption) -> {
            if (selectedOption instanceof String) {
                try {
                    Double rate = exchangeRateControllers.getRate((String) selectedOption);
                    exchangeRateControllers.updateCurrentRate(new ExchangeRate((String) selectedOption, rate));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (selectedOption instanceof ArrayList) {
                try {
                    ArrayList<Integer> list = (ArrayList<Integer>) selectedOption;

                    Integer discount = list.get(0);
                    Integer tax = list.get(1);
                    Integer service = list.get(2);

                    paymentStatesControllers.updateCurrentStates(new PaymentStates(discount, tax, service));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        if (this.plugins.size() > 0) {
            for (Object plugin : this.plugins) {
                GridPane pane = new GridPane();
                try {
                    Method isNeedPage = plugin.getClass().getMethod("isNeedPage");
                    boolean needPage = (boolean) isNeedPage.invoke(plugin);

                    Method getNameMethod = plugin.getClass().getMethod("getName");
                    String name = (String) getNameMethod.invoke(plugin);
                    if (needPage) {
                        if (name.equals("Plugin_Pie_Chart")) {
                            Thread thread = new Thread(() -> {
                                while (App.menuBar == null) {
                                    try {
                                        Thread.sleep(1000); // check every second
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }

                                // Add the page to the menu and tab
                                try {
                                    Method runMethod = plugin.getClass().getMethod("run", String.class);
                                    runMethod.invoke(plugin, "hehe");

                                    Method getSceneMethod = plugin.getClass().getMethod("getScene");
                                    Scene scene = (Scene) getSceneMethod.invoke(plugin);

                                    App.menuBar.addPageToMenuAndTab("Pie Chart", scene.getRoot());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });

                            thread.start();
                        }
                        if (name.equals("Plugin_Line_Bar_Chart")) {
                            ;
                        }
                    } else {
                        Method needDB = plugin.getClass().getMethod("getDBNeeded");
                        String db = (String) needDB.invoke(plugin);

                        Method setupMethod = plugin.getClass().getMethod("setUp", ArrayList.class, Consumer.class, String.class);
                        setupMethod.invoke(plugin, getDatabasePlugins(db), optionsChose, exchangeRateControllers.getCurrentRate().getName());

                        Method runMethod = plugin.getClass().getMethod("run", GridPane.class);
                        runMethod.invoke(plugin, pane);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                pluginSection.getChildren().add(pane);
            }
        }


        this.getChildren().addAll(filePathLabel, chooseFolderButton, filePluginLabel, btn, pluginSection);
        this.setAlignment(Pos.CENTER);
        this.setStyle("""
            -fx-background-color: rgba(0, 0, 0, 0.5);
            -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 10, 0, 0, 0);
            -fx-padding: 40px 100px;
            -fx-background-radius: 20px;
        """);
    }

    public String getDBPath() {
        return this.databasePath;
    }

    public ArrayList<String> getDatabasePlugins(String data) {
        ArrayList<String> arrayList = new ArrayList<String>();
        if (data.equals("exchangerate")) {
            try {
                ArrayList<ExchangeRate> exchangeList = this.exchangeRateControllers.readExchangeRates();

                for (ExchangeRate e : exchangeList) {
                    arrayList.add(e.getName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (data.equals("paymentstates")) {
            try {
                PaymentStates paymentStates = this.paymentStatesControllers.getCurrentStates();

                arrayList.add(paymentStates.getDiscount().toString());
                arrayList.add(paymentStates.getTax().toString());
                arrayList.add(paymentStates.getService().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return arrayList;
    }
}
