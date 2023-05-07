package bnmobusinessmanagementsystem.views;

import bnmobusinessmanagementsystem.models.Item;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ItemHistoryView extends Pane {


    private Label name;
    private Label pict;
    private Label price;
    private double priceNum;
    private Label category;

    public ItemHistoryView(Item _item) {

        // Item image
        pict = new Label("PICTURE");
        pict.setPrefHeight(50);
        pict.setPrefWidth(50);
        pict.setBackground(Background.fill(Color.BLACK));

        // Item name
        this.name = new Label(_item.getName());

        // Item category
        this.category = new Label(_item.getCategory());

        // Item price
        this.price = new Label("Rp" + _item.getSellPrice());

        this.priceNum = _item.getSellPrice();


        VBox itemInfo = new VBox(10);

        itemInfo.getChildren().addAll(pict, name, category, price);

        itemInfo.setStyle(
                "-fx-background-color: #eeebe3; " +
                        "-fx-background-radius: 2em; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 16px; " +
                        "-fx-padding: 10px 20px;"
        );

        this.getChildren().add(itemInfo);
        pict.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
//                cashier.addItems(new Bubble(_item));
            }
        });

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
