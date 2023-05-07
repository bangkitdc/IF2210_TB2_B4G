package bnmobusinessmanagementsystem.views.components.Catalog;

import bnmobusinessmanagementsystem.controllers.ExchangeRateControllers;
import bnmobusinessmanagementsystem.controllers.PaymentStatesControllers;
import bnmobusinessmanagementsystem.models.customer.*;
import bnmobusinessmanagementsystem.models.Item;
import bnmobusinessmanagementsystem.models.plugin.ExchangeRate;
import bnmobusinessmanagementsystem.models.plugin.PaymentStates;
import bnmobusinessmanagementsystem.utils.DataStore;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.io.IOException;
import java.text.ParseException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CashierView extends VBox {

    private String currentCustomer;
    private Label addCustomer;
    private ComboBox<String> dropdown;
    private HBox customerType;
    private ListView items;
    private HBox discountBox;
    private HBox taxBox;
    private HBox serviceBox;
    private Label saveBill;
    private Label printBill;
    private HBox bill;
    private Label charge;
    private double sum;
    private ArrayList itemInfoList;
    private ArrayList<Item> itemPurchased;
    private ArrayList<Customer> custs;
    private DataStore custStore;
    private Customer customer;
    public CashierView(DataStore customerDataStore, DataStore itemDataStore){
        this.custStore = customerDataStore;
        customer = new Customer();



        dropdown = new ComboBox<>();
        itemInfoList = new ArrayList<>();
        Screen primaryScreen = Screen.getPrimary();
        double maxHeight = primaryScreen.getVisualBounds().getHeight();
        double maxWidth = primaryScreen.getVisualBounds().getWidth();

        addCustomer = new Label("Add Customer");
        addCustomer.setPrefHeight(20);
        addCustomer.setPrefWidth(1080*2/5);
        addCustomer.setAlignment(Pos.CENTER);
        addCustomer.setFont(Font.font(13));
        addCustomer.setBackground(Background.fill(Color.valueOf("#BFCB7E")));
        addCustomer.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                System.out.println("Add Customer!");
            }
        });

        try{
            custs = custStore.readCustomer();
            for (Customer cust : custs){
                if(cust instanceof Member){
                    dropdown.getItems().add(((Member) cust).getNama());
                }
                if(cust instanceof VIP){
                    dropdown.getItems().add(((VIP) cust).getNama());
                }
            }
        } catch (IOException e){
            e.printStackTrace();;
        }


//        dropdown.getItems().addAll("Customer", "Member", "VIP");
        dropdown.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if(newValue.equals("Customer")) {
                this.currentCustomer = "CUSTOMER";
            }
            else{
                for (Customer n : custs){
                    if(n instanceof Member){
                        if(newValue.equals(((Member) n).getNama())){
                            System.out.println(((Member) n).getNama());
                            this.currentCustomer = "MEMBER";
                        }
                    }
                    if(n instanceof VIP){
                        if(newValue.equals(((VIP) n).getNama())){
                            System.out.println(((VIP) n).getNama());
                            this.currentCustomer = "VIP";
                        }
                    }
                }
            }
        });
        customerType = new HBox(20);
        customerType.getChildren().addAll(dropdown);
        customerType.setBackground(Background.fill(Color.valueOf("#BFCB7E")));

        items = new ListView();

        discountBox = new HBox(20);
//        discountBox.setPrefHeight(10);
        discountBox.getChildren().add(new Label("Discount : "));
        discountBox.setBackground(Background.fill(Color.valueOf("#BFCB7E")));
        discountBox.setStyle("-fx-font-size: 10px; -fx-text-alignment: center;");
        // TODO: add discount plugin
        PaymentStatesControllers paymentStatesControllers = new PaymentStatesControllers();

        Integer discount = 0;
        try {
            PaymentStates payment = paymentStatesControllers.getCurrentStates();
            discount = payment.getDiscount();
            discountBox.getChildren().add(new Label(discount.toString() + "%"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        taxBox = new HBox(45);
//        taxBox.setPrefHeight();
        taxBox.getChildren().add(new Label("Tax : "));
        taxBox.setBackground(Background.fill(Color.valueOf("#BFCB7E")));
        taxBox.setStyle("-fx-font-size: 10px; -fx-text-alignment: center;");
        // TODO: add tax plugin

        Integer tax = 0;
        try {
            PaymentStates payment = paymentStatesControllers.getCurrentStates();
            tax = payment.getTax();
            taxBox.getChildren().add(new Label(tax.toString() + "%"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        serviceBox = new HBox(20);
        serviceBox.getChildren().add(new Label("Service : "));
        serviceBox.setBackground(Background.fill(Color.valueOf("#BFCB7E")));
        serviceBox.setStyle("-fx-font-size: 15px;-fx-text-alignment: center;");
        // TODO: add service plugin

        Integer service = 0;
        try {
            PaymentStates payment = paymentStatesControllers.getCurrentStates();
            service = payment.getService();
            serviceBox.getChildren().add(new Label(service.toString() + "%"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        sum = 0;

        saveBill = new Label("Save Bill");

        saveBill.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                System.out.println("Save Bill!");
                // TODO : nge-save bill
            }
        });
        printBill = new Label("Print Bill");
        printBill.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                System.out.println("Print Bill!");
                // TODO : nge-print bill
            }
        });
        saveBill.setAlignment(Pos.CENTER);
        saveBill.setFont(Font.font(30));
        printBill.setAlignment(Pos.CENTER);
        printBill.setFont(Font.font(30));
        saveBill.setPrefHeight(30);
        printBill.setPrefHeight(30);
        saveBill.setPrefWidth(maxWidth/2);
        printBill.setPrefWidth(maxWidth/2);

        bill = new HBox(saveBill, printBill);
        bill.setPrefHeight(30);
        bill.setBackground(Background.fill(Color.valueOf("#BFCB7E")));
        bill.setStyle("-fx-font-size: 30px;");

        charge = new Label("Charge");
        charge.setPrefHeight(80);
        charge.setPrefWidth(1080*2/5);
        charge.setAlignment(Pos.CENTER);
        charge.setStyle("-fx-font-size: 30px;");
        Pane chargePane = new Pane();

//        charge.setFont(Font.font(30));
        charge.setBackground(Background.fill(Color.valueOf("#BFCB7E")));
        chargePane.getChildren().add(charge);
        chargePane.setStyle("-fx-font-size: 30px;");
        charge.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                boolean flag = true;
                for(Object item : items.getItems()){
                    if(item instanceof Bubble) {
                        Item check = ((Bubble) item).getItem();
                        if(check.outOfStock() || check.getQuantity() < ((Bubble) item).getQuantity()){
                            flag = false;
                        }
                    }
                }
                if(flag) {
                    // Get today's date
                    LocalDate today = LocalDate.now();

                    // Format today's date as "dd/MM/yyyy"
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String formattedDate = today.format(formatter);
                    itemPurchased = new ArrayList<>();
                    System.out.println(charge.getText());
                    for (Object item : items.getItems()) {
                        if (item instanceof Bubble) {
                            Item temp = ((Bubble) item).getItem();
                            for (int i = 0; i < ((Bubble) item).getQuantity(); i++) {
                                temp.itemSold();
                            }
                            itemPurchased.add(((Bubble) item).getItem());
                            //                        int sold = ((Bubble) item).getQuantity();
                            //                        System.out.println(sold);
                            try {
                                itemDataStore.deleteItemByName(((Bubble) item).getItem().getName());
                                itemDataStore.addItem(temp);
                            } catch (IOException | ParseException e) {
                                e.printStackTrace();
                            }

                        }
                    }
//                    DataStore purchase = new DataStore("purchase.json");
//                    if (currentCustomer.equals("CUSTOMER")) {
                        customer.addTransaction(new Purchase(customer.getCustomerId(), formattedDate, itemPurchased));
                        try {
                            String latestId = custStore.getLatestID();
                            if (customer.getCustomerId().equals(latestId)) {
                                custStore.deleteCustomerById(custStore.getLatestID());
                            }

                        } catch (IOException | ParseException e){
                            e.printStackTrace();
                        }
                        custStore.addCustomer(customer);
//                    }
                    System.out.print(currentCustomer);
                    System.out.println(customer.getCustomerId());
                    items.getItems().clear();
                }
                else{
                    AlertDialog alertDialog = new AlertDialog("Order quantity exceed its stock!");
                    alertDialog.showAndWait();
                }
            }
        });

        items.setPrefHeight(500-addCustomer.getPrefHeight()-discountBox.getPrefHeight() - taxBox.getPrefHeight()- bill.getPrefHeight() - charge.getPrefHeight());

        Integer finalDiscount = discount;
        Integer finalTax = tax;
        Integer finalService = service;
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
            // Code to execute every second
            sum = 0;

            // Update sum of prices
            items.getItems().forEach(item -> {
                if(item instanceof Bubble){
                    sum += ((Bubble) item).getUpdatedPrice();

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

                    Double res = sum * rate;
                    Double resDiscount = res * finalDiscount/100;
                    Double resTax = (res * finalTax/100);
                    Double resService = (res * finalService/100);
                    res += resTax + resService - resDiscount;
                    DecimalFormat df = new DecimalFormat("#.#####");
                    String formattedValue = df.format(res);
                    charge.setText("Charge (" + currency + " " + formattedValue + ")");
                }
            });

            // If list is empty, set to default text
            if(items.getItems().isEmpty()){
                charge.setText("Charge");
            }
        }));

        // Remove item button
        items.getItems().addListener((ListChangeListener) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    items.scrollTo(change.getTo());
                    items.getItems().forEach(item -> {
                        if(item instanceof Bubble){
                            ((Bubble) item).getRemoveItem().setOnMouseClicked(event -> {
                                items.getItems().remove(item);
                            });
                        }
                    });
                }
            }
        });
        addCustomer.setStyle("-fx-border-color: black;");
        customerType.setStyle("-fx-border-color: black;");
        items.setStyle("-fx-border-color: black;");
        saveBill.setStyle("-fx-border-color: black;");
        printBill.setStyle("-fx-border-color: black;");
        charge.setStyle("-fx-border-color: black;");

        HBox.setHgrow(discountBox, Priority.ALWAYS);
        HBox.setHgrow(taxBox, Priority.ALWAYS);
        HBox.setHgrow(serviceBox, Priority.ALWAYS);

        discountBox.setStyle("-fx-font-size: 15px; -fx-alignment: center; -fx-border-color: black;");
        taxBox.setStyle("-fx-font-size: 15px; -fx-alignment: center; -fx-border-color: black;");
        serviceBox.setStyle("-fx-font-size: 15px; -fx-alignment: center; -fx-border-color: black;");

        this.getChildren().addAll(addCustomer, customerType, items, discountBox, taxBox, serviceBox, bill, chargePane);
        this.setPrefWidth(1080*2/5);
        this.setPrefHeight(560);

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        dropdown.getSelectionModel().select("Customer");
    }

    public Label getAddCustomer() {
        return addCustomer;
    }

    public void setAddCustomer(Label addCustomer) {
        this.addCustomer = addCustomer;
    }

    public Label getCharge() {
        return charge;
    }

    public void setCharge(Label charge) {
        this.charge = charge;
    }

    public ListView getItems() {
        return items;
    }

    public void addItems(Pane itemPane) {
        boolean flag = false;
        for(Object item : items.getItems()){
            if(item instanceof Bubble && itemPane instanceof Bubble){
                if(((Bubble) item).getBubbleName().getText() == ((Bubble) itemPane).getBubbleName().getText()){
                    flag = true;
                }
            }
        }
        if(!flag){
            items.getItems().add(itemPane);
        }
    }


}
