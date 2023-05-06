package bnmobusinessmanagementsystem.views;

import bnmobusinessmanagementsystem.models.Item;
import bnmobusinessmanagementsystem.models.customer.Customer;
import bnmobusinessmanagementsystem.util.DataStore;
import bnmobusinessmanagementsystem.views.components.Catalog.*;
import bnmobusinessmanagementsystem.views.components.Catalog.CashierView;
import bnmobusinessmanagementsystem.views.components.Catalog.CatalogView;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Inventaris{
    private Scene scene;

    public Inventaris(){
        Screen primaryScreen = Screen.getPrimary();
        double maxHeight = primaryScreen.getVisualBounds().getHeight();
        double maxWidth = primaryScreen.getVisualBounds().getWidth();
        DataStore dataStore = new DataStore("itemTEMP.json");

        Item[] itemDB = new Item[5];
        itemDB[0] = new Item("alpukat", 2050, 1500, 5, "buah", "picture");
        itemDB[1] = new Item("pisang", 205, 1500, 4, "buah", "picture");
        itemDB[2] = new Item("apel", 20, 1500, 3, "buah", "picture");
        itemDB[3] = new Item("obeng", 2000, 1500, 2, "peralatan", "picture");
        itemDB[4] = new Item("palu", 200, 1500, 1, "peralatan", "picture");

        VBox catalog = new VBox(10);
        CashierView cashier = new CashierView();
        CatalogView itemList = new CatalogView();
        ScrollPane catalogs = new ScrollPane();
        catalogs.setContent(itemList);
        catalogs.setFitToWidth(true);

        ArrayList<Item> itemListDB = new ArrayList<>(Arrays.asList(itemDB));
        dataStore.saveItem(itemListDB);

//        Item alpukat = new Item("alpukat", 20500, 19500, 5, "buah", "picture");
//        Item pisang = new Item("pisang", 18500, 15500, 5, "buah", "picture");
//        Item apel = new Item("apel", 7500, 6500, 5, "buah", "picture");
//        Item obeng = new Item("obeng", 12500, 11500, 5, "peralatan", "picture");
//        Item palu = new Item("palu", 12500, 11500, 5, "peralatan", "picture");
//        Item paku = new Item("paku", 12500, 11500, 5, "peralatan", "picture");
//        Item laptop = new Item("laptop", 50000, 11500, 5, "elektronik", "picture");
//        Item handphone = new Item("handphone", 50000, 11500, 5, "elektronik", "picture");
//        Item radio = new Item("radio", 50000, 11500, 5, "elektronik", "picture");
        try {
            ArrayList<Item> itemss = dataStore.readItems();
            for(Item n : itemss){
                itemList.addItem(new ItemView(cashier, n, 350, 200));
            }
        } catch (IOException e){
            e.printStackTrace();
        }

//        itemList.addItem(new ItemView(cashier,alpukat, 350, 200));
//        itemList.addItem(new ItemView(cashier, pisang,350, 200));
//        itemList.addItem(new ItemView(cashier, apel,350, 200));
//        itemList.addItem(new ItemView(cashier, obeng,350, 200));
//        itemList.addItem(new ItemView(cashier, palu,350, 200));
//        itemList.addItem(new ItemView(cashier, paku,350, 200));
//        itemList.addItem(new ItemView(cashier, laptop,350, 200));
//        itemList.addItem(new ItemView(cashier, handphone,350, 200));
//        itemList.addItem(new ItemView(cashier, radio,350, 200));

//       for(int i = 0; i < 30; i++){
//            itemList.addItem(new ItemView(cashier, "Test"+i, "200"+i, "2000"));
//       }
        SearchBar searchBar = new SearchBar(itemList);

        catalog.getChildren().addAll(searchBar, catalogs);
        catalog.setPrefWidth(maxWidth*3/5);
        cashier.setPrefWidth(maxWidth*2/5);
        catalog.setPrefHeight(maxHeight);
        HBox inventaris = new HBox(catalog, cashier);
        scene = new Scene(inventaris, 1080, 660);
    }
    public Scene getScene() {
        return scene;
    }
}
