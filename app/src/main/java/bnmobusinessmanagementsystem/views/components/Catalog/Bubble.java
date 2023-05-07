package bnmobusinessmanagementsystem.views.components.Catalog;

import bnmobusinessmanagementsystem.models.Item;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class Bubble extends BorderPane {
    private Label bubblePict;
    private Label bubbleName;
    private Label bubblePrice;
    private Button removeItem;
    private VBox rightSide;
    private Label quantityLabel;
    private int quantity;
    private Item item;
    public Bubble(Item item){
        this.item = item;
        quantity = 1;
        bubblePict = new Label(item.getImage());
        bubbleName = new Label(item.getName());
        bubblePrice = new Label(Double.toString(item.getSellPrice()));
        removeItem = new Button("X");
        removeItem.setAlignment(Pos.CENTER);
        removeItem.setTextAlignment(TextAlignment.CENTER);

        bubblePict.setPrefHeight(50);
        bubblePict.setPrefWidth(50);
        bubblePict.setBackground(Background.fill(Color.BLACK));

        bubblePrice.setTextAlignment(TextAlignment.RIGHT);

        Button incrementButton = new Button("+");
        incrementButton.setOnAction(event -> {
            quantity++;
            double res = item.getSellPrice()*quantity;
            rightSide.getChildren().setAll(new Label("Rp"+res), removeItem);
            this.setRight(rightSide);
            quantityLabel.setText(Integer.toString(quantity));
            bubblePrice.setText(Double.toString(res));
        });

        Button decrementButton = new Button("-");
        decrementButton.setOnAction(event -> {
            if (quantity > 1) {
                quantity--;
                double res = item.getSellPrice()*quantity;
                rightSide.getChildren().setAll(new Label("Rp"+res), removeItem);
                this.setRight(rightSide);
                quantityLabel.setText(Integer.toString(quantity));
                bubblePrice.setText(Double.toString(res));
            }
        });

        // Create the quantity label
        quantityLabel = new Label(Integer.toString(quantity));

        // Create the HBox containing the buttons and the label
        HBox quantityBox = new HBox();
        quantityBox.setSpacing(10);
        quantityBox.getChildren().addAll(decrementButton, quantityLabel, incrementButton);

        rightSide = new VBox();
        rightSide.setSpacing(20);
        rightSide.getChildren().addAll(bubblePrice, removeItem);
        this.setStyle("-fx-background-color: #f0f0f0;");
        this.setLeft(bubblePict);
        this.setRight(rightSide);
        this.setCenter(bubbleName);
        this.setBottom(quantityBox);
    }

    public void setBubbleName(Label bubbleName) {
        this.bubbleName = bubbleName;
    }

    public Label getBubbleName() {
        return bubbleName;
    }

    public void setBubblePict(Label bubblePict) {
        this.bubblePict = bubblePict;
    }

    public Label getBubblePict() {
        return bubblePict;
    }

    public void setBubblePrice(Label bubblePrice) {
        this.bubblePrice = bubblePrice;
    }

    public Label getBubblePrice() {
        return bubblePrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantityLabel(Label quantityLabel) {
        this.quantityLabel = quantityLabel;
    }

    public Label getQuantityLabel() {
        return quantityLabel;
    }

    public Button getRemoveItem() {
        return removeItem;
    }

    public void setRemoveItem(Button removeItem) {
        this.removeItem = removeItem;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
