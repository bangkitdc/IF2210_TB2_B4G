package bnmobusinessmanagementsystem.views;

import javafx.collections.ListChangeListener;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.scene.image.Image;


import java.io.IOException;
import java.nio.file.Paths;

public class MenuAndTab {
    private Scene scene;
    private TabPane tabPane;
    private Menu pageMenu;
    private Pane pane1;
    private Pane pane2;

    public MenuAndTab(Scene mainScene) {
        FontManager.loadFonts();

        // Tab pane
        tabPane = new TabPane();

        // Create the two panes
        this.pane1 = new Pane();
        this.pane2 = new Pane();

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
        MenuItem pageInventaris = new MenuItem("Inventaris");
        MenuItem customerHistori = new MenuItem("Histori");
        MenuItem sistemLaporan = new MenuItem("Laporan");
        MenuItem addMember = new MenuItem("Add Member");
        MenuItem updateCustomer = new MenuItem("Update Customer");

        // Create menus
        this.pageMenu = new Menu("Menu");
        pageMenu.getItems().addAll(page1MenuItem, page2MenuItem, pageSetting, pageInventaris, customerHistori,sistemLaporan, addMember, updateCustomer);

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

        SettingPage settingPage = new SettingPage();
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
            settingPage.layoutBoundsProperty().addListener((obs, oldVal, newVal) -> {
                settingPage.setPrefWidth(pane2.getWidth());
                settingPage.setPrefHeight(pane2.getHeight());
            });
            Tab tab3 = new Tab(pageTitle);
            tab3.setContent(settingPage);
            tabPane.getTabs().add(tab3);
        });

        pageInventaris.setOnAction(e -> {
            pane1.setVisible(false);
            pane2.setVisible(true);
            String pageTitle = "Inventaris";
//            for (Tab tab : tabPane.getTabs()) {
//                if (tab.getText().equals(pageTitle)) {
//                    tabPane.getSelectionModel().select(tab);
//                    return;
//                }
//            }
            Inventaris inventaris = new Inventaris();
//            inventaris.layoutBoundsProperty().addListener((obs, oldVal, newVal) -> {
//                inventaris.setPrefWidth(pane2.getWidth());
//                inventaris.setPrefHeight(pane2.getHeight());
//            });
            Tab tab4 = new Tab(pageTitle);
            tab4.setContent(inventaris);
            tabPane.getTabs().add(tab4);
        });

        customerHistori.setOnAction(e -> {
            pane1.setVisible(false);
            pane2.setVisible(true);
            Tab tab = new Tab("History Customer");
            CustomerHistoriesView histori = null;
            try {
                histori = new CustomerHistoriesView();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            histori.setPrefWidth(pane1.getWidth());
            histori.setPrefHeight(pane1.getHeight());
            tab.setContent(histori);
            tabPane.getTabs().add(tab);
        });

        sistemLaporan.setOnAction(e -> {
            pane1.setVisible(false);
            pane2.setVisible(true);
            Tab tab = new Tab("Sistem Laporan");
            SistemLaporan laporan = null;
            try {
                laporan = new SistemLaporan();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            laporan.setPrefWidth(pane1.getWidth());
            laporan.setPrefHeight(pane1.getHeight());
            tab.setContent(laporan);
            tabPane.getTabs().add(tab);
        });

        addMember.setOnAction(e -> {
            pane1.setVisible(false);
            pane2.setVisible(true);
            Tab tab = new Tab("Add Member");
            AddMember add = new AddMember();
            add.setPrefWidth(pane1.getWidth());
            add.setPrefHeight(pane1.getHeight());
            tab.setContent(add);
            tabPane.getTabs().add(tab);
        });

        updateCustomer.setOnAction(e -> {
            pane1.setVisible(false);
            pane2.setVisible(true);
            Tab tab = new Tab("Update Customer");
            UpdateCustomer update =new UpdateCustomer();
            update.setPrefWidth(pane1.getWidth());
            update.setPrefHeight(pane1.getHeight());
            tab.setContent(update);
            tabPane.getTabs().add(tab);
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

    public void addPageToMenuAndTab(String pageTitle, Node pageContent) {
        MenuItem newPageMenuItem = new MenuItem(pageTitle);
        pageMenu.getItems().add(newPageMenuItem);

        newPageMenuItem.setOnAction(e -> {
            pane1.setVisible(false);
            pane2.setVisible(true);
            for (Tab tab : tabPane.getTabs()) {
                if (tab.getText().equals(pageTitle)) {
                    tabPane.getSelectionModel().select(tab);
                    return;
                }
            }
            pageContent.maxHeight(Double.MAX_VALUE);
            pageContent.maxWidth(Double.MAX_VALUE);
            Tab newTab = new Tab(pageTitle);
            newTab.setContent(pageContent);
            tabPane.getTabs().add(newTab);
        });
    }

    public void replacePageInMenuAndTab(String pageName, Node pageContent) {
        // Find the tab associated with the pageName
        Tab pageTab = null;
        for (Tab tab : tabPane.getTabs()) {
            if (tab.getText().equals(pageName)) {
                pageTab = tab;
                break;
            }
        }

        // If page is found, replace the content with the updated root node
        if (pageTab != null) {
            pageTab.setContent(pageContent);
        }
    }
}
