package bnmobusinessmanagementsystem.views.components.CustomerHistories;

import bnmobusinessmanagementsystem.controllers.ExchangeRateControllers;
import bnmobusinessmanagementsystem.models.plugin.ExchangeRate;
import bnmobusinessmanagementsystem.utils.DataStore;
import bnmobusinessmanagementsystem.models.Item;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Optional;

public class ItemHistoryView extends Pane {
    private Label editButton;
    private Label deleteButton;

    private Label name;
    private Pane pict;
    private Label price;
    private Label price2;
    private double priceNum;
    private Label category;
    private DataStore itemDataStore;
    private Item itemTemp;

    public ItemHistoryView(Item _item) {
        //#A7A9D0

        String currentDir = System.getProperty("user.dir");
        String path = "src/main/resources/itempict/"+_item.getImage();
        String fullPath = Paths.get(currentDir, path).toString();
        Image img = new Image(fullPath);
        ImageView imgview = new ImageView(img);
        imgview.setFitHeight(150);
        imgview.setFitWidth(150);
        pict = new Pane(imgview);
//        pict.getChildren().add(imgview);
        pict.setPrefHeight(150);
        pict.setPrefWidth(150);
        pict.setStyle("""
-fx-background-radius: 20;

""");
//        pict.setBackground(Background.fill(Color.BLACK));

        // Item name
        this.name = new Label(_item.getName() + " " + _item.getQuantity() + "x" );

        // Item category
        this.category = new Label("Category : "+_item.getCategory());

        ExchangeRateControllers exchangeRateControllers = new ExchangeRateControllers();
        String currency = "";
        Double rate = 0.0;
        try {
            ExchangeRate exchange = exchangeRateControllers.getCurrentRate();
            currency = exchange.getName();
            rate = exchange.getRate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Double res = _item.getSellPrice() * rate;
        DecimalFormat df = new DecimalFormat("#.#####");
        String formattedValue = df.format(res);
        Double res2 = _item.getTotalPrice() * rate;
        DecimalFormat df2 = new DecimalFormat("#.#####");
        String formattedValue2 = df2.format(res2);
        // Item price
        this.price = new Label(currency + " " + formattedValue);
        this.price2 = new Label(currency + " " + formattedValue2);

        this.priceNum = _item.getSellPrice();

        this.editButton = new Label("Edit");

        VBox itemInfo = new VBox(10);

        itemInfo.getChildren().addAll(pict, name, category, price,price2);

        itemInfo.setStyle(
                "-fx-background-color: #7A833c; " +
                        "-fx-background-radius: 2em; " +
                        "-fx-border-color : black;"+
                        "-fx-border-radius: 2em;" +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 16px; " +
                        "-fx-padding: 10px 20px;"
        );

        this.getChildren().add(itemInfo);

        itemInfo.setBackground(Background.fill(Color.valueOf("#A7A9D0")));
    }

    public void setName(String name) {
        this.name = new Label(name);
    }

    public Label getName() {
        return name;
    }
    public void setPrice(String price) {
        this.price = new Label(price);
    }

    public Label getPrice() {
        return price;
    }

    public Label getCategory() {
        return category;
    }

}
