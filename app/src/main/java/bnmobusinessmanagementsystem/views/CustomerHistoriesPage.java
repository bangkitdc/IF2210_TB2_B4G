package bnmobusinessmanagementsystem.views;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.beans.property.StringProperty;
import bnmobusinessmanagementsystem.models.customer.*;

public class CustomerHistoriesPage extends BorderPane{
    private TableView<Purchase> purchaseTable = new TableView<>();
    private final ObservableList<Purchase> purchaseData =
            FXCollections.observableArrayList(
                    new Purchase("C001", "2019-08-01", "Item A", 10),
                    new Purchase("C001", "2020-01-15", "Item B", 5),
                    new Purchase("C003", "2021-04-14", "Item E", 4)
            );

    public CustomerHistoriesPage() {
//        purchaseTable.setStyle("-fx-background-color: transparent;" +
//                "-fx-background-radius: 10px;"+
//                "-fx-border-radius: 5px;"
//        );


        Image img = new Image("C:\\Users\\ASUS\\IdeaProjects\\IF2210_TB2_B4G\\app\\src\\main\\resources\\background\\bg.png");
        BackgroundImage bg_img = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, false, false));
        setBackground(new Background(bg_img));
        purchaseTable.setFixedCellSize(-1);
//        purchaseTable.setPadding(new Insets(10, 20, 10, 20));
        purchaseTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        setPadding(new Insets(10, 10, 10, 10));
        setPrefSize(1080, 660);
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.setSpacing(10);
        Label customerIdLabel = new Label("Customer ID:");
        TextField customerIdTextField = new TextField();


        Button showHistoryButton = new Button("Show History");
        showHistoryButton.setStyle(
                "-fx-background-color: #FDF2F8;"+
                        "-fx-start-margin: 10px");


        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);


        TableColumn<Purchase, String> customerIdColumn = new TableColumn<>("Customer ID");
        customerIdColumn.setStyle(
                "-fx-alignment: CENTER;"+
                "-fx-font-size: 16px;"+
                "-fx-font-weight: bold;"+
                "-fx-padding: 10px;"
        );
        customerIdColumn.setCellValueFactory(cellData -> cellData.getValue().customerIdProperty());
        TableColumn<Purchase, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        TableColumn<Purchase, String> itemColumn = new TableColumn<>("Item");
        itemColumn.setCellValueFactory(cellData -> cellData.getValue().itemProperty());
        TableColumn<Purchase, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        purchaseTable.getColumns().addAll(customerIdColumn, dateColumn, itemColumn, quantityColumn);
        vBox.getChildren().addAll(customerIdLabel, customerIdTextField, showHistoryButton);
        setTop(vBox);

        setCenter(purchaseTable);
        showHistoryButton.setOnAction(event -> {
            String customerId = customerIdTextField.getText();
            if (!customerId.isEmpty()) {
                ObservableList<Purchase> filteredData = FXCollections.observableArrayList();
                for (Purchase purchase : purchaseData) {
                    if (purchase.getCustomerId().equals(customerId)) {
                        filteredData.add(purchase);
                    }
                }
                purchaseTable.setItems(filteredData);
            } else {
                purchaseTable.setItems(purchaseData);
            }
        });

        purchaseTable.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                Purchase purchase = purchaseTable.getSelectionModel().getSelectedItem();
                if (purchase != null) {
                    // Do something with the selected purchase, e.g. show details in a dialog
                    System.out.println("Selected purchase: " + purchase);
                }
            }
        });
    }
}


