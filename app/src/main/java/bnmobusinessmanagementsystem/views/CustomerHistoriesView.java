package bnmobusinessmanagementsystem.views;
import bnmobusinessmanagementsystem.models.customer.*;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.util.ArrayList;


public class CustomerHistoriesView extends VBox{
    private ArrayList<Customer> customers;
    private Customer customer;
    private ArrayList<Purchase> purchaseList;
    private VBox listTransactionView;
    private ScrollPane customerTransactions;
    private TextField customerIdField;
    public CustomerHistoriesView(ArrayList<Customer> customers){
        this.customers=customers;
        this.customer=customers.get(0);
        this.purchaseList=customers.get(0).getTransaction();
        this.customerTransactions = new ScrollPane();
        this.listTransactionView = new VBox();
        this.customerIdField = new TextField();
        customerIdField.setPrefSize(400,50);
        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> {
            String customerId = customerIdField.getText();
            // Perform the search logic based on the customer ID

            searchCustomer(customerId);
//            this.purchaseList=customer.getTransaction();
            updatePurchaseList();

        });

        HBox searchBox = new HBox(customerIdField, searchButton);
        searchBox.setSpacing(20);
        setPadding(new Insets(40));
        setStyle("-fx-background-color: #888");
        setStyle("-fx-border-radius: 10px");

        for (Purchase purchase : purchaseList
             ) {
            listTransactionView.getChildren().add(new TransactionView(purchase));
        }

        listTransactionView.setSpacing(20);

        customerTransactions.setStyle("-fx-background-color: transparent;" + "-fx-background-radius: 20;" + "-fx-border-color: transparent;");
        customerTransactions.prefWidthProperty().bind(this.widthProperty());
        customerTransactions.prefHeightProperty().bind(this.heightProperty());
        customerTransactions.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        customerTransactions.setContent(listTransactionView);


        listTransactionView.prefWidthProperty().bind(customerTransactions.widthProperty());
        listTransactionView.prefHeightProperty().bind(customerTransactions.heightProperty());

//        updatePurchaseList();

        this.getChildren().addAll(searchBox,customerTransactions);




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
                Customer tempp= new Customer();
                ArrayList<Purchase> temp= new ArrayList<>();
                ArrayList<Item> tempItem= new ArrayList<>();
                tempItem.add(new Item("TIDAK ADA TRANSAKSI", 0, 0, 0, "", ""));
                temp.add(new Purchase(customer.getCustomerId(), "dwadawdaw",tempItem,500000));
                tempp.setTransaction(temp);
                this.customer=tempp;
                this.purchaseList=tempp.getTransaction();
    }
}
