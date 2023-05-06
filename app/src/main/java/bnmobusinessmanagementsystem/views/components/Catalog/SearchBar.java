package bnmobusinessmanagementsystem.views.components.Catalog;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class SearchBar extends HBox {

    private TextField searchBar;

    private CatalogView catalog;

    private ArrayList<Node> originalCatalog;

    public SearchBar(CatalogView catalog){
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

        this.getChildren().addAll(searchBar);
        this.setAlignment(Pos.CENTER);
    }
}
