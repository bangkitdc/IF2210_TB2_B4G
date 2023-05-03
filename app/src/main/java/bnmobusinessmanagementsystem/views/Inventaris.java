package bnmobusinessmanagementsystem.views;

import bnmobusinessmanagementsystem.models.Item;
import bnmobusinessmanagementsystem.views.components.Catalog.*;
import bnmobusinessmanagementsystem.views.components.Catalog.CashierView;
import bnmobusinessmanagementsystem.views.components.Catalog.CatalogView;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Screen;

public class Inventaris{
    private Scene scene;

    public Inventaris(){
        Screen primaryScreen = Screen.getPrimary();
        double maxHeight = primaryScreen.getVisualBounds().getHeight();
        double maxWidth = primaryScreen.getVisualBounds().getWidth();


        VBox catalog = new VBox(10);
        CashierView cashier = new CashierView();
        CatalogView itemList = new CatalogView();
        ScrollPane catalogs = new ScrollPane();
        catalogs.setContent(itemList);
        catalogs.setFitToWidth(true);

        Item alpukat = new Item("alpukat", 20500, 19500, 5, "buah", "picture");
        Item pisang = new Item("pisang", 18500, 15500, 5, "buah", "picture");
        Item apel = new Item("apel", 7500, 6500, 5, "buah", "picture");
        Item obeng = new Item("obeng", 12500, 11500, 5, "peralatan", "picture");

        itemList.addItem(new Items(cashier, alpukat.getName(),  alpukat.getCategory(), String.valueOf(alpukat.getSellPrice())));
        itemList.addItem(new Items(cashier, pisang.getName(), pisang.getCategory(), String.valueOf(pisang.getSellPrice())));
        itemList.addItem(new Items(cashier, apel.getName(), apel.getCategory(), String.valueOf(apel.getSellPrice())));
        itemList.addItem(new Items(cashier, obeng.getName(), obeng.getCategory(), String.valueOf(obeng.getSellPrice())));

//        for(int i = 0; i < 30; i++){
//            itemList.addItem(new Items(cashier, "Test"+i, "200"+i, "2000"));
//        }
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
