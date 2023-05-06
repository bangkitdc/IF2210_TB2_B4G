package bnmobusinessmanagementsystem.views;

import javafx.collections.ListChangeListener;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.scene.image.Image;


import java.nio.file.Paths;

public class MenuAndTab {
    private Scene scene;
    private TabPane tabPane;

    public MenuAndTab(Scene mainScene) {
        FontManager.loadFonts();

        // Tab pane
        tabPane = new TabPane();

        // Create the two panes
        Pane pane1 = new Pane();
        Pane pane2 = new Pane();

        // Stack them on top of each other in a new StackPane
        StackPane stackPane = new StackPane(pane1, pane2);

        // Set the StackPane as the center node of the BorderPane
        BorderPane borderPane = new BorderPane(stackPane);

        // Add main page
        MainPage mainPage = new MainPage();

        // Set the layout bounds of the VBox to match the size of pane1
        mainPage.layoutBoundsProperty().addListener((obs, oldVal, newVal) -> {
            mainPage.setPrefWidth(pane1.getWidth());
            mainPage.setPrefHeight(pane1.getHeight());
        });

        // Add pane elements
        pane1.getChildren().add(mainPage);
        pane2.getChildren().add(tabPane);

        pane1.setVisible(true);
        pane2.setVisible(false);

        borderPane.setCenter(stackPane);

        // Create menu items
        MenuItem page1MenuItem = new MenuItem("Page 1");
        MenuItem page2MenuItem = new MenuItem("Page 2");
        MenuItem pageSetting = new MenuItem("Setting");

        // Create menus
        Menu pageMenu = new Menu("Menu");
        pageMenu.getItems().addAll(page1MenuItem, page2MenuItem, pageSetting);

        // Create menu bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(pageMenu);

        // Set up event handlers for menu items
        page1MenuItem.setOnAction(e -> {
            pane1.setVisible(false);
            pane2.setVisible(true);
            String pageTitle = "Page 1";
            for (Tab tab : tabPane.getTabs()) {
                if (tab.getText().equals(pageTitle)) {
                    tabPane.getSelectionModel().select(tab);
                    return;
                }
            }
            Page1 page1 = new Page1();
            page1.layoutBoundsProperty().addListener((obs, oldVal, newVal) -> {
                page1.setPrefWidth(pane2.getWidth());
                page1.setPrefHeight(pane2.getHeight());
            });
            Tab tab1 = new Tab(pageTitle);
            tab1.setContent(page1);
            tabPane.getTabs().add(tab1);
        });

        page2MenuItem.setOnAction(e -> {
            pane1.setVisible(false);
            pane2.setVisible(true);
            String pageTitle = "Page 2";
            for (Tab tab : tabPane.getTabs()) {
                if (tab.getText().equals(pageTitle)) {
                    tabPane.getSelectionModel().select(tab);
                    return;
                }
            }
            Page2 page2 = new Page2();
            page2.layoutBoundsProperty().addListener((obs, oldVal, newVal) -> {
                page2.setPrefWidth(pane2.getWidth());
                page2.setPrefHeight(pane2.getHeight());
            });
            Tab tab2 = new Tab(pageTitle);
            tab2.setContent(page2);
            tabPane.getTabs().add(tab2);
        });

        pageSetting.setOnAction(e -> {
            pane1.setVisible(false);
            pane2.setVisible(true);
            String pageTitle = "Settings";
            for (Tab tab : tabPane.getTabs()) {
                if (tab.getText().equals(pageTitle)) {
                    tabPane.getSelectionModel().select(tab);
                    return;
                }
            }
            SettingPage settingPage = new SettingPage();
            settingPage.layoutBoundsProperty().addListener((obs, oldVal, newVal) -> {
                settingPage.setPrefWidth(pane2.getWidth());
                settingPage.setPrefHeight(pane2.getHeight());
            });
            Tab tab3 = new Tab(pageTitle);
            tab3.setContent(settingPage);
            tabPane.getTabs().add(tab3);
        });

        tabPane.getTabs().addListener((ListChangeListener<Tab>) change -> {
            if (tabPane.getTabs().isEmpty()) {
                pane1.setVisible(true);
                pane2.setVisible(false);
            }
        });

        // Set menuBar and new scene
        borderPane.setTop(menuBar);

        scene = new Scene(borderPane, 1080, 660);
        scene.getRoot().setStyle("-fx-font-family: Helvetica");
    }

    public Scene getScene() {
        return scene;
    }
}
