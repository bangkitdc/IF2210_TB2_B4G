package bnmobusinessmanagementsystem.views;

import javafx.collections.ListChangeListener;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.scene.image.Image;


import javax.print.attribute.standard.Media;
import java.io.File;
import java.nio.file.Paths;

public class MenuAndTab {
    private Scene scene;
    private TabPane tabPane;

    public MenuAndTab(Scene mainScene) {

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

        String currentDir = System.getProperty("user.dir");
        String path = "/IF2210_TB2_B4G/app/src/main/resources/background/bg.png";
        String fullPath = Paths.get(currentDir, path).toString();

        // Image img = new Image("src/main/resources/background/bg.png");
        // BackgroundImage bg_img = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, false, false));
        // mainPage.setBackground(new Background(bg_img));

        pane1.setVisible(true);
        pane2.setVisible(false);

        borderPane.setCenter(stackPane);

        // Create menu items
        MenuItem page1MenuItem = new MenuItem("Page 1");
        MenuItem page2MenuItem = new MenuItem("Page 2");
        MenuItem addCustomer = new MenuItem("Add Customer");
        MenuItem update = new MenuItem("Update");
        MenuItem historyTransaction = new MenuItem("History");

        // Create menus
        Menu pageMenu = new Menu("Menu");
        pageMenu.getItems().addAll(page1MenuItem, page2MenuItem, addCustomer, update, historyTransaction);

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

        addCustomer.setOnAction(e -> {
            pane1.setVisible(false);
            pane2.setVisible(true);
            Tab tab1 = new Tab("Add Customer");
            AddMember customerTab = new AddMember();
            customerTab.setPrefWidth(pane1.getWidth());
            customerTab.setPrefHeight(pane1.getHeight());
            tab1.setContent(customerTab);
            tabPane.getTabs().add(tab1);
        });

        update.setOnAction(e -> {
            pane1.setVisible(false);
            pane2.setVisible(true);
            Tab tab1 = new Tab("Update");
            UpdateCustomer updateCust = new UpdateCustomer();
            updateCust.setPrefWidth(pane1.getWidth());
            updateCust.setPrefHeight(pane1.getHeight());
            tab1.setContent(updateCust);
            tabPane.getTabs().add(tab1);
        });
        
        historyTransaction.setOnAction(e -> {
            pane1.setVisible(false);
            pane2.setVisible(true);
            Tab tab1 = new Tab("Histori");
            CustomerHistoriesPage historyPgae = new CustomerHistoriesPage();
            historyPgae.setPrefWidth(pane1.getWidth());
            historyPgae.setPrefHeight(pane1.getHeight());
            tab1.setContent(historyPgae);
            tabPane.getTabs().add(tab1);
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
