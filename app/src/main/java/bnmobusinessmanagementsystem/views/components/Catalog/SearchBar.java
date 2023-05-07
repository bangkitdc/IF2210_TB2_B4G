package bnmobusinessmanagementsystem.views.components.Catalog;

import bnmobusinessmanagementsystem.utils.DataStore;
import bnmobusinessmanagementsystem.models.Item;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchBar extends HBox {

    private TextField searchBar;

    private CatalogView catalog;

    private ArrayList<Node> originalCatalog;

    private String nameValue;
    private String sellPriceValue;
    private String buyPriceValue;
    private String quantityValue;
    private String categoryValue;
    private String imageValue;
    private DataStore itemDataStore;

    public SearchBar(CatalogView catalog, DataStore itemDataStore){
        this.itemDataStore = itemDataStore;
        this.catalog = catalog;
        this.originalCatalog = new ArrayList<>(catalog.getChildren());
        searchBar = new TextField();
        searchBar.setPromptText("Search");
        searchBar.setPrefWidth(360);

        searchBar.setOnKeyReleased(e -> { // handle submit on "Enter" key pressed
            String searchText = searchBar.getText();

            if(searchText.equals("")){
                catalog.clearCatalog();
                catalog.getChildren().addAll(originalCatalog);
            }
            else {
                List<Node> temp = new ArrayList<>(originalCatalog);

                catalog.clearCatalog();

                for (Node node : temp) {
                    if (node instanceof ItemView) {
                        if (((ItemView) node).getName().getText().contains(searchText)) { // Search by name
                            catalog.getChildren().add(node);
                        } else if (((ItemView) node).getCategory().getText().contains(searchText)) { // Search by category
                            catalog.getChildren().add(node);
                        } else if (((ItemView) node).getPrice().getText().contains(searchText)) { // Search by price
                            catalog.getChildren().add(node);
                        }
                    }
                }
            }
        });

        Button addItemButton = new Button();
        addItemButton.setText("+");

        addItemButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Dialog<List<String>> dialog = new Dialog<>();
                dialog.setTitle("Multiple Input Form");
                dialog.setHeaderText("Please enter the following information:");

                // Set the button types.
                ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

                // Create the GridPane for the form elements.
                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(20, 150, 10, 10));

                // Add the form elements to the GridPane.
                TextField name = new TextField();
                name.setPromptText("Name");
                TextField sellPrice = new TextField();
                sellPrice.setPromptText("Sell Price");
                TextField buyPrice = new TextField();
                buyPrice.setPromptText("Buy Price");
                TextField quantity = new TextField();
                quantity.setPromptText("Quantity");
                TextField category = new TextField();
                category.setPromptText("Category");
                TextField image = new TextField();
                image.setPromptText("Image Path");
                grid.add(new Label("Name:"), 0, 0);
                grid.add(name, 1, 0);
                grid.add(new Label("Sell Price:"), 0, 1);
                grid.add(sellPrice, 1, 1);
                grid.add(new Label("Buy Price:"), 0, 2);
                grid.add(buyPrice, 1, 2);
                grid.add(new Label("Quantity:"), 0, 3);
                grid.add(quantity, 1, 3);
                grid.add(new Label("Category:"), 0, 4);
                grid.add(category, 1, 4);
                grid.add(new Label("Image path:"), 0, 5);
                grid.add(image, 1, 5);

                // Set the default button disable state.
                Node okButton = dialog.getDialogPane().lookupButton(okButtonType);
                okButton.setDisable(true);

                // Add a listener to enable/disable the default button based on form validation.
                BooleanBinding isFormValid = Bindings.createBooleanBinding(
                        () -> !name.getText().isEmpty() && !sellPrice.getText().isEmpty() && !buyPrice.getText().isEmpty()
                        && !quantity.getText().isEmpty() && !category.getText().isEmpty(),
                        name.textProperty(), sellPrice.textProperty(), buyPrice.textProperty(), quantity.textProperty(),
                        category.textProperty());
                okButton.disableProperty().bind(isFormValid.not());

                // Add the GridPane to the DialogPane.
                dialog.getDialogPane().setContent(grid);

                // Request focus on the first field.
                Platform.runLater(() -> name.requestFocus());

                // Convert the result to a list of strings.
                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == okButtonType) {
                        return Arrays.asList(name.getText(), sellPrice.getText(), buyPrice.getText(),
                                quantity.getText(), category.getText(), image.getText());
                    }
                    return null;
                });

                // Handle the OK button click event.
                okButton.addEventHandler(ActionEvent.ACTION, e -> {
                    List<String> formData = dialog.getResult();
                    if (formData != null) {
                        nameValue = formData.get(0);
                        sellPriceValue = formData.get(1);
                        buyPriceValue = formData.get(2);
                        quantityValue = formData.get(3);
                        categoryValue = formData.get(4);
                        imageValue = formData.get(5);
                        itemDataStore.addItem(new Item(nameValue, Double.parseDouble(sellPriceValue),
                                Double.parseDouble(buyPriceValue), Integer.parseInt(quantityValue), 0,
                                categoryValue, imageValue));
                    }
                });

                dialog.showAndWait();

            }
        });

        this.getChildren().addAll(searchBar, addItemButton);
        this.setAlignment(Pos.CENTER);
    }
}
