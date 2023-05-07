package bnmobusinessmanagementsystem.views.components.Catalog;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.text.ParseException;
import java.util.Optional;

public class ItemView extends Pane {
    private Label editButton;
    private Label deleteButton;

    private Label name;
    private Label pict;
    private Label price;
    private double priceNum;
    private Label category;
    private DataStore itemDataStore;
    private Item itemTemp;

    public ItemView(CashierView cashier, Item _item, int _height, int _width, DataStore itemDataStore) {
        this.itemDataStore = itemDataStore;
        try{
            this.itemTemp = this.itemDataStore.getItemByName(_item.getName());
        } catch (IOException e){
            e.printStackTrace();
        }

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
                    // Create the custom dialog.
                    Dialog<Item> dialog = new Dialog<>();
                    dialog.setTitle("Update Item");
                    dialog.setHeaderText("Update the item information:");

                    // Set the button types.
                    ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
                    dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

                    // Create the GridPane for the form elements.
                    GridPane grid = new GridPane();
                    grid.setHgap(10);
                    grid.setVgap(10);
                    grid.setPadding(new Insets(20, 150, 10, 10));

                    // Add the form elements to the GridPane.
                    TextField nameTextField = new TextField(_item.getName());
                    grid.add(new Label("Name:"), 0, 0);
                    grid.add(nameTextField, 1, 0);

                    TextField sellPriceTextField = new TextField(Double.toString(_item.getSellPrice()));
                    grid.add(new Label("Sell Price:"), 0, 1);
                    grid.add(sellPriceTextField, 1, 1);

                    TextField buyPriceTextField = new TextField(Double.toString(_item.getBuyPrice()));
                    grid.add(new Label("Sell Price:"), 0, 2);
                    grid.add(buyPriceTextField, 1, 2);

                    TextField quantityTextField = new TextField(Integer.toString(_item.getQuantity()));
                    grid.add(new Label("Quantity:"), 0, 3);
                    grid.add(quantityTextField, 1, 3);

                    TextField categoryTextField = new TextField(_item.getCategory());
                    grid.add(new Label("Category:"), 0, 4);
                    grid.add(categoryTextField, 1, 4);

                    TextField imageTextField = new TextField(_item.getImage());
                    grid.add(new Label("Image path:"), 0, 5);
                    grid.add(imageTextField, 1, 5);

                    // Set the default button disable state.
                    Node updateButton = dialog.getDialogPane().lookupButton(updateButtonType);
                    updateButton.setDisable(true);

                    // Add a listener to enable/disable the default button based on form validation.
                    BooleanBinding isFormValid = Bindings.createBooleanBinding(
                            () -> !nameTextField.getText().isEmpty() && !quantityTextField.getText().isEmpty()
                                    && !sellPriceTextField.getText().isEmpty(),
                            nameTextField.textProperty(), quantityTextField.textProperty(), sellPriceTextField.textProperty());
                    updateButton.disableProperty().bind(isFormValid.not());

                    // Add the GridPane to the DialogPane.
                    dialog.getDialogPane().setContent(grid);

                    // Request focus on the first field.
                    Platform.runLater(() -> nameTextField.requestFocus());

                    // Convert the result to an item object.
                    dialog.setResultConverter(dialogButton -> {
                        if (dialogButton == updateButtonType) {
                            String name = nameTextField.getText();
                            int quantity = Integer.parseInt(quantityTextField.getText());
                            double sellPrice = Double.parseDouble(sellPriceTextField.getText());
                            double buyPrice = Double.parseDouble(buyPriceTextField.getText());
                            String category = categoryTextField.getText();
                            String image = categoryTextField.getText();
                            return new Item(name, sellPrice, buyPrice, quantity, category,image);
                        }
                        return null;
                    });

                    // Show the dialog and wait for the user to update the item or cancel.
                    Optional<Item> result = dialog.showAndWait();
                    result.ifPresent(updatedItem -> {
                        // Update the item in the database.
//                        updateItem(updatedItem);
                        try{
                            itemDataStore.deleteItemByName(itemTemp.getName());
                            itemDataStore.addItem(dialog.getResult());
                        } catch (IOException | ParseException e){
                            e.printStackTrace();
                        }

                        // Update the item in the UI.
                        // ...
                    });
                System.out.println("Edit Item!");
            }
        });

        this.deleteButton = new Label("Delete");
        deleteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try{
                    itemDataStore.deleteItemByName(name.getText());
                } catch (IOException | ParseException e){
                    e.printStackTrace();
                }
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
