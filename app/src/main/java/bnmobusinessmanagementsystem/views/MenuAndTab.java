package bnmobusinessmanagementsystem.views;

import javafx.collections.ListChangeListener;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.layout.*;
import javafx.geometry.Insets;

import bnmobusinessmanagementsystem.views.components.DateTime.DateTime;
import bnmobusinessmanagementsystem.views.components.NimNama.NimNama;

public class MenuAndTab {
    private Scene scene;
    private TabPane tabPane;

    public MenuAndTab(Scene mainScene) {
        tabPane = new TabPane();

        // Create the two panes
        Pane pane1 = new Pane();
        Pane pane2 = new Pane();

        // Stack them on top of each other in a new StackPane
        StackPane stackPane = new StackPane(pane1, pane2);

        // Set the StackPane as the center node of the BorderPane
        BorderPane borderPane = new BorderPane(stackPane);

        // Show or hide the panes as needed
        pane1.setVisible(false);
        pane2.setVisible(true);

        DateTime dateTime = new DateTime();
        dateTime.setMaxSize(200, 100);
        dateTime.setPadding(new Insets(0, 0, 30, 0));
        VBox vBox = new VBox();
        vBox.getChildren().add(dateTime);

        NimNama nimNama = new NimNama();
        nimNama.setMaxSize(400, 200);
        vBox.getChildren().add(nimNama);

        vBox.setAlignment(Pos.CENTER);

        // Set the layout bounds of the VBox to match the size of pane1
        vBox.layoutBoundsProperty().addListener((obs, oldVal, newVal) -> {
            vBox.setPrefWidth(pane1.getWidth());
            vBox.setPrefHeight(pane1.getHeight());
        });

        pane1.getChildren().addAll(vBox);
        pane2.getChildren().add(tabPane);

        pane1.setVisible(true);
        pane2.setVisible(false);

        borderPane.setCenter(stackPane);

        // Create menu items
        MenuItem page1MenuItem = new MenuItem("Page 1");
        MenuItem page2MenuItem = new MenuItem("Page 2");

        // Create menus
        Menu pageMenu = new Menu("Menu");
        pageMenu.getItems().addAll(page1MenuItem, page2MenuItem);

        // Create menu bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(pageMenu);

        // Set up event handlers for menu items
        page1MenuItem.setOnAction(e -> {
            pane1.setVisible(false);
            pane2.setVisible(true);
            Tab tab1 = new Tab("Page 1");
            tab1.setContent(new Label("This is Page 1"));
            tabPane.getTabs().add(tab1);
        });

        page2MenuItem.setOnAction(e -> {
            pane1.setVisible(false);
            pane2.setVisible(true);
            Tab tab2 = new Tab("Page 2");
            tab2.setContent(new Label("This is Page 2"));
            tabPane.getTabs().add(tab2);
        });

        tabPane.getTabs().addListener((ListChangeListener<Tab>) change -> {
            if (tabPane.getTabs().isEmpty()) {
                pane1.setVisible(true);
                pane2.setVisible(false);
            }
        });

        borderPane.setTop(menuBar);

        scene = new Scene(borderPane, 1080, 660);
    }

    public Scene getScene() {
        return scene;
    }
}
