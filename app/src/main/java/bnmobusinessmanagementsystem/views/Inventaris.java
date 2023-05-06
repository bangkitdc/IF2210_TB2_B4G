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
        Item palu = new Item("palu", 12500, 11500, 5, "peralatan", "picture");
        Item paku = new Item("paku", 12500, 11500, 5, "peralatan", "picture");
        Item laptop = new Item("laptop", 50000, 11500, 5, "elektronik", "picture");
        Item handphone = new Item("handphone", 50000, 11500, 5, "elektronik", "picture");
        Item radio = new Item("radio", 50000, 11500, 5, "elektronik", "picture");

        itemList.addItem(new ItemView(cashier,alpukat));
        itemList.addItem(new ItemView(cashier, pisang));
        itemList.addItem(new ItemView(cashier, apel));
        itemList.addItem(new ItemView(cashier, obeng));
        itemList.addItem(new ItemView(cashier, palu));
        itemList.addItem(new ItemView(cashier, paku));
        itemList.addItem(new ItemView(cashier, laptop));
        itemList.addItem(new ItemView(cashier, handphone));
        itemList.addItem(new ItemView(cashier, radio));

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
