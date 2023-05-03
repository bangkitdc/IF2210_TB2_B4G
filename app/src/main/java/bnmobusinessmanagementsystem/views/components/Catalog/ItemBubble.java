package bnmobusinessmanagementsystem.views.components.Catalog;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class ItemBubble extends BorderPane {

    private String pict;
    private String name;
    private String price;
    private String quantiy;
    public  ItemBubble(String pict, String name, String price, String quantity){
        this.pict = pict;
        this.name = name;
        this.price = price;
        this.quantiy = quantity;
//        BorderPane bubble = new BorderPane();

        HBox main = new HBox(150);
        VBox left = new VBox();
        HBox insideLeft = new HBox(60);

        insideLeft.setBackground(Background.fill(Color.RED));

        Label bubblePict = new Label(pict);
        Label bubbleName = new Label(name);
        Label bubblePrice = new Label(price);

        bubblePict.setPrefHeight(50);
        bubblePict.setPrefWidth(50);
        bubblePict.setBackground(Background.fill(Color.BLACK));

        bubblePrice.setTextAlignment(TextAlignment.RIGHT);

//        insideLeft.getChildren().addAll(new Label(bubblePict), new Label(bubbleName));
        insideLeft.getChildren().addAll(bubblePict, bubbleName);
        left.getChildren().addAll(insideLeft, new Label("QUANTITIY"));

        main.getChildren().addAll(left, bubblePrice);

        main.setBackground(Background.fill(Color.BLUE));
        main.setPrefWidth(330);

        this.setLeft(bubblePict);
        this.setRight(bubblePrice);
        BorderPane.setAlignment(bubblePrice, Pos.CENTER);
        this.setCenter(bubbleName);
        this.setBottom(new Label("QUANTITIY"));

//        Pane bubble = new Pane();
//        bubble.getChildren().add(main);
    }
}
