package bnmobusinessmanagementsystem.views;

import bnmobusinessmanagementsystem.utils.DataStore;
import bnmobusinessmanagementsystem.views.components.Catalog.*;
import bnmobusinessmanagementsystem.views.components.Catalog.CashierView;
import bnmobusinessmanagementsystem.views.components.Catalog.CatalogView;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Screen;

import java.io.IOException;
import java.util.ArrayList;

public class Inventaris extends HBox{
    private Scene scene;
    private CatalogView itemList;

    public Inventaris(){
        Screen primaryScreen = Screen.getPrimary();
        double maxHeight = primaryScreen.getVisualBounds().getHeight();
        double maxWidth = primaryScreen.getVisualBounds().getWidth();
        DataStore dataStore = new DataStore("itemDataStore.json");
        DataStore custStore = new DataStore("customer.json");

        VBox catalog = new VBox(10);
        CashierView cashier = new CashierView(custStore);
        itemList = new CatalogView();
        ScrollPane catalogs = new ScrollPane();
        catalogs.setContent(itemList);
        catalogs.setFitToWidth(true);

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
        catalog.setPrefHeight(maxHeight);
        this.getChildren().addAll(catalog, cashier);
    }
}
