package bnmobusinessmanagementsystem.views.components.Catalog;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class Items extends Pane {

    private Label name;
    private Label pict;
    private Label price;
    private Label category;

    public Items(CashierView cashier, String _name, String _category, String _price){

        // Item image
        pict = new Label("PICTURE");
        pict.setPrefHeight(150);
        pict.setPrefWidth(150);
        pict.setBackground(Background.fill(Color.BLACK));

        // Item name
        this.name = new Label(_name);

        // Item category
        this.category = new Label(_category);

        // Item price
        this.price = new Label("Rp"+_price);
//        this.price = new Label(tos);

//        BorderPane itemInfo = new BorderPane();
//        itemInfo.setPrefWidth(250);
//        itemInfo.setPrefHeight(250);
//        itemInfo.setTop(pict);
//        itemInfo.setCenter(name);
//        itemInfo.setBottom(price);
//        BorderPane.setAlignment(pict, Pos.CENTER);
//        BorderPane.setAlignment(name, Pos.CENTER);
//        BorderPane.setAlignment(price, Pos.CENTER);

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
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                System.out.println("Cell 2 clicked!" + name.getText());
//                cashier.addItems(name.getText());
//                cashier.addItems(pict.getText(), name.getText(), price.getText());
                cashier.addItems(bubbleIt());
            }
        });
    }

    public BorderPane bubbleIt(){
        BorderPane bubble = new BorderPane();

        HBox main = new HBox(150);
        VBox left = new VBox();
        HBox insideLeft = new HBox(60);

        insideLeft.setBackground(Background.fill(Color.RED));

        var bubblePict = new Label(pict.getText());
        var bubbleName = new Label(name.getText());
        var bubblePrice = new Label(price.getText());

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

        bubble.setLeft(bubblePict);
        bubble.setRight(bubblePrice);
        BorderPane.setAlignment(bubblePrice, Pos.CENTER);
        bubble.setCenter(bubbleName);
        bubble.setBottom(new Label("QUANTITIY"));

//        Pane bubble = new Pane();
//        bubble.getChildren().add(main);

        return bubble;
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

    public void setCategory(Label category) {
        this.category = category;
    }

    //    public TilePane itemList(){
//        TilePane tilePane = new TilePane();
//        tilePane.setAlignment(Pos.CENTER);
//        tilePane.setHgap(20);
//        tilePane.setVgap(20);
//        String[] test = {"cabe", "botol","susu","cabe", "bawan","pisang",
//                "kopi", "teh","biji","jeruk", "kecap","apel",
//                "batagor", "siomay", "bakso", "pempek"};
//        for (String n : test){
//            tilePane.getChildren().add(this.item((n)));
//        }
//        return tilePane;
//
//    }
}
