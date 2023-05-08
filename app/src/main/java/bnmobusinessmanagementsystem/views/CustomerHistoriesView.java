package bnmobusinessmanagementsystem.views;
import bnmobusinessmanagementsystem.models.Item;
import bnmobusinessmanagementsystem.models.customer.*;

import bnmobusinessmanagementsystem.utils.DataStore;
import bnmobusinessmanagementsystem.views.components.CustomerHistories.TransactionView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;


public class CustomerHistoriesView extends VBox{
    private ArrayList<Customer> customers;
    private Customer customer;
    private ArrayList<Purchase> purchaseList;
    private VBox listTransactionView;
    private ScrollPane customerTransactions;
    private TextField customerIdField;
    public CustomerHistoriesView() throws IOException {
        DataStore custStore = new DataStore("customer.json");
        this.customers=custStore.readCustomer();
        this.customer=customers.get(0);
        this.purchaseList=customers.get(0).getTransaction();
        this.customerTransactions = new ScrollPane();
        this.listTransactionView = new VBox();
        this.customerIdField = new TextField();
        customerIdField.setPrefSize(400,30);
        customerIdField.setStyle("""
-fx-font-size: 20;
-fx-border-radius: 20;
-fx-background-radius: 20;
-fx-border-color: #FFFFFF;
""");
        Button searchButton = new Button("Search");
        searchButton.setStyle("""
    -fx-background-color:
            #000000,
            linear-gradient(#7ebcea, #2f4b8f),
            linear-gradient(#426ab7, #263e75),
            linear-gradient(#395cab, #223768);
    -fx-background-insets: 0,1,2,3;
    -fx-background-radius: 3,2,2,2;
    -fx-padding: 12 30 12 30;
    -fx-text-fill: white;
    -fx-font-size: 12px;
                """);
        this.setSpacing(10);
        this.setAlignment(Pos.TOP_CENTER);

        String currentDir = System.getProperty("user.dir");
        String path = "src/main/resources/background/bgHistory2.png";
        String fullPath = Paths.get(currentDir, path).toString();

        Image img = new Image(fullPath);
        BackgroundImage bg_img = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, false, false));
        this.setBackground(new Background(bg_img));
        searchButton.setOnAction(event -> {
            String customerId = customerIdField.getText();
            // Perform the search logic based on the customer ID

            searchCustomer(customerId);
//            this.purchaseList=customer.getTransaction();
            updatePurchaseList();

        });
        Label HistoriesLabel= new Label("Histories");
        HistoriesLabel.setStyle("""
            -fx-font-size: 40px;
            -fx-text-fill: #FEFEA8;
            -fx-background-color: transparent;
            -fx-font-family: "SF Pro Rounded Semibold";
            -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 0);
            -fx-padding: 0 0 0 0;
        """);
        HistoriesLabel.setAlignment(Pos.CENTER);
        HistoriesLabel.setPrefSize(300,100);
        HBox searchBox = new HBox(customerIdField, searchButton);
        searchBox.setSpacing(20);
        searchBox.setAlignment(Pos.CENTER);
        setPadding(new Insets(40));

        for (Purchase purchase : purchaseList
             ) {
            listTransactionView.getChildren().add(new TransactionView(purchase));
        }


        customerTransactions.setStyle("-fx-background-color: #FFFFFF;" + "-fx-background-radius : 20;"+ "-fx-border-color: transparent;");
        customerTransactions.prefWidthProperty().bind(this.widthProperty());
//        customerTransactions.setMaxWidth(1080);
        customerTransactions.setPrefHeight(350);
        customerTransactions.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        customerTransactions.setPadding(new Insets(20));
        customerTransactions.setContent(listTransactionView);



        listTransactionView.setSpacing(15);
        listTransactionView.setPrefSize(956,100);
        listTransactionView.setMaxWidth(944);
        listTransactionView.setStyle("-fx-background-color: #FFFFFF");

//        updatePurchaseList();

        this.getChildren().addAll(HistoriesLabel,searchBox,customerTransactions);




    }

    private void updatePurchaseList() {
        listTransactionView.getChildren().clear();

        for (Purchase purchase : purchaseList) {
            listTransactionView.getChildren().add(new TransactionView(purchase));
        }

        listTransactionView.setSpacing(20);

        // Manually update the layout and refresh the view
        listTransactionView.requestLayout();
        listTransactionView.getParent().layout();
    }
    private void searchCustomer(String customerId) {
        System.out.println("Searching for customer with ID: " + customerId);
        // Add your search logic here
        for (Customer cust : customers) {
            if (cust.getCustomerId().equals(customerId)) {
                this.customer = cust;
                this.purchaseList = cust.getTransaction();
                return;
            }
//            else{
//                this.customer=new Customer();
//                ArrayList<Purchase> temp= new ArrayList<>();
//                ArrayList<Item> tempItem= new ArrayList<>();
//                tempItem.add(new Item("TIDAK ADA TRANSAKSI", 0, 0, 0, "", ""));
//                temp.add(new Purchase(customer.getCustomerId(), "dwadawdaw",tempItem,500000));
//                this.customer.setTransaction(temp);
//                this.purchaseList=temp;
//                return;
//            }
        }
//         Customer not found, clear the purchase list
                Customer tempp= new Customer("-1");
                ArrayList<Purchase> temp= new ArrayList<>();
                ArrayList<Item> tempItem= new ArrayList<>();

                tempItem.add(new Item("TIDAK ADA CUSTOMER", 0, 0, 0, 0, "", ""));
                temp.add(new Purchase(tempp.getCustomerId(), "-",tempItem));

                tempp.setTransaction(temp);
                this.customer=tempp;
                this.purchaseList=tempp.getTransaction();
    }
}
