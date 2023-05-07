package bnmobusinessmanagementsystem.views;

import bnmobusinessmanagementsystem.utils.DataStore;
import bnmobusinessmanagementsystem.views.components.Catalog.*;
import bnmobusinessmanagementsystem.views.components.Catalog.CashierView;
import bnmobusinessmanagementsystem.views.components.Catalog.CatalogView;
import bnmobusinessmanagementsystem.models.Item;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Inventaris extends HBox{
    private Scene scene;
    private CatalogView itemList;

    public Inventaris(){
        String currentDir = System.getProperty("user.dir");
        System.out.println(currentDir);
        String path = "src/main/resources/background/storage.png";
        String fullPath = Paths.get(currentDir, path).toString();

        Image img = new Image("file:"+fullPath);
        BackgroundImage bg_img = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, false, false));
        this.setBackground(new Background(bg_img));
        DataStore dataStore = new DataStore("itemDataStore.json");
        DataStore custStore = new DataStore("customer.json");

        VBox catalog = new VBox(19);
        CashierView cashier = new CashierView(custStore, dataStore);
        itemList = new CatalogView();
        ScrollPane catalogs = new ScrollPane();
        catalogs.setContent(itemList);
        catalogs.setFitToWidth(true);
        catalogs.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        catalogs.setMinHeight(560);
        try {
            ArrayList<Item> itemss = dataStore.readItems();
            for(Item n : itemss){
                itemList.addItem(new ItemView(cashier, n, 350, 200, dataStore));
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        SearchBar searchBar = new SearchBar(itemList, dataStore);
        catalog.getChildren().addAll(searchBar, catalogs);
        catalog.setPrefWidth(1080*3/5);
        cashier.setPrefWidth(1080*2/5);
        catalog.setPrefHeight(634);
        this.getChildren().addAll(catalog, cashier);
        this.setPrefSize(1080, 634);
    }
}
