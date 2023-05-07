package bnmobusinessmanagementsystem.views.components.Catalog;
import bnmobusinessmanagementsystem.controllers.ExchangeRateControllers;
import bnmobusinessmanagementsystem.models.Item;
import bnmobusinessmanagementsystem.models.plugin.ExchangeRate;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.text.DecimalFormat;

public class Bubble extends BorderPane {
    private Label bubblePict;
    private Label bubbleName;
    private Label bubblePrice;
    private double price;
    private double updatedPrice;
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
        this.price = item.getSellPrice();
        this.updatedPrice = this.price;

        ExchangeRateControllers exchangeRateControllers = new ExchangeRateControllers();

        String currency = "";
        Double rate = 0.0;
        try {
            ExchangeRate exchangeRate = exchangeRateControllers.getCurrentRate();
            currency = exchangeRate.getName();
            rate = exchangeRate.getRate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Double result = item.getSellPrice() * rate;
        DecimalFormat df = new DecimalFormat("#.#####");
        String formattedValue = df.format(result);

        bubblePrice = new Label(currency + " " +formattedValue);
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
            this.updatedPrice = this.price * quantity;

            String currency2 = "";
            Double rate2 = 0.0;
            try {
                ExchangeRate exchangeRate = exchangeRateControllers.getCurrentRate();
                currency2 = exchangeRate.getName();
                rate2 = exchangeRate.getRate();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Double result2 = res * rate2;
            DecimalFormat df2 = new DecimalFormat("#.#####");
            String formattedValue2 = df2.format(result2);

            rightSide.getChildren().setAll(new Label(currency2 + " " + formattedValue2), removeItem);
            this.setRight(rightSide);
            quantityLabel.setText(Integer.toString(quantity));
            bubblePrice.setText(Double.toString(res));
        });

        Button decrementButton = new Button("-");
        decrementButton.setOnAction(event -> {
            if (quantity > 1) {
                quantity--;
                double res = item.getSellPrice()*quantity;
                this.updatedPrice = this.price * quantity;

                String currency2 = "";
                Double rate2 = 0.0;
                try {
                    ExchangeRate exchangeRate = exchangeRateControllers.getCurrentRate();
                    currency2 = exchangeRate.getName();
                    rate2 = exchangeRate.getRate();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Double result2 = res * rate2;
                DecimalFormat df2 = new DecimalFormat("#.#####");
                String formattedValue2 = df2.format(result2);

                rightSide.getChildren().setAll(new Label(currency2 + " " + formattedValue2), removeItem);
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

    public double getPrice() {
        return price;
    }

    public double getUpdatedPrice() {
        return updatedPrice;
    }
}
