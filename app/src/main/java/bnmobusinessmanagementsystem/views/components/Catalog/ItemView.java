package bnmobusinessmanagementsystem.views.components.Catalog;

import bnmobusinessmanagementsystem.models.Item;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ItemView extends Pane {
    private Label editButton;
    private Label deleteButton;

    private Label name;
    private Label pict;
    private Label price;
    private double priceNum;
    private Label category;

    public ItemView(CashierView cashier, Item _item, int _height, int _width) {
        // Item image
//        System.out.println(this.heightProperty());
//        System.out.println(this.heightProperty());
        // ItemView item = new ItemView()
//        this.setHeight(_height);
        this.setWidth(_width);
        pict = new Label("PICTURE");
        pict.setPrefHeight(150);
        pict.setPrefWidth(150);
        pict.setBackground(Background.fill(Color.BLACK));

        // Item name
        this.name = new Label(_item.getName());

        // Item category
        this.category = new Label(_item.getCategory());

        // Item price
        this.price = new Label("Rp" + _item.getSellPrice());

        this.priceNum = _item.getSellPrice();

        this.editButton = new Label("Edit");
        editButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                System.out.println("Edit Item!");
                // TODO : nge-save bill
            }
        });

        this.deleteButton = new Label("Delete");
        deleteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                System.out.println("Delete Item!!");
                // TODO : nge-save bill
            }
        });

        HBox editDelete = new HBox(editButton, deleteButton);
        editDelete.setSpacing(30);
        editDelete.setAlignment(Pos.CENTER);

        VBox itemInfo = new VBox(10);

        itemInfo.getChildren().addAll(editDelete, pict, name, category, price);

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
                cashier.addItems(new Bubble(_item));
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
