package bnmobusinessmanagementsystem.views.components.Catalog;

import bnmobusinessmanagementsystem.models.Item;
import bnmobusinessmanagementsystem.models.customer.Customer;
import bnmobusinessmanagementsystem.models.customer.Member;
import bnmobusinessmanagementsystem.models.customer.Purchase;
import bnmobusinessmanagementsystem.models.customer.VIP;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CashierView extends VBox {

    private String currentCustomer;
    private Label addCustomer;
    private ComboBox<String> dropdown;
    private HBox customerType;
    private ListView items;
    private Label saveBill;
    private Label printBill;
    private HBox bill;
    private Label charge;
    private double sum;
    private ArrayList itemInfoList;
    private ArrayList<Item> itemPurchased;
    private ArrayList<Customer> custs;
    private DataStore custStore;
    public CashierView(DataStore customerDataStore){
        this.custStore = customerDataStore;



        dropdown = new ComboBox<>();
        itemInfoList = new ArrayList<>();
        Screen primaryScreen = Screen.getPrimary();
        double maxHeight = primaryScreen.getVisualBounds().getHeight();
        double maxWidth = primaryScreen.getVisualBounds().getWidth();

        addCustomer = new Label("Add Customer");
        addCustomer.setPrefHeight(50);
        addCustomer.setPrefWidth(maxWidth);
        addCustomer.setAlignment(Pos.CENTER);
        addCustomer.setFont(Font.font(30));
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

        items = new ListView();

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

        charge = new Label("Charge");
        charge.setPrefHeight(80);
        charge.setPrefWidth(maxWidth);
        charge.setAlignment(Pos.CENTER);
        charge.setFont(Font.font(30));
        charge.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                // Get today's date
                LocalDate today = LocalDate.now();

                // Format today's date as "dd/MM/yyyy"
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String formattedDate = today.format(formatter);
                itemPurchased = new ArrayList<>();
                System.out.println(charge.getText());
                for(Object item : items.getItems()){
                    if(item instanceof Bubble){
                        itemPurchased.add(((Bubble) item).getItem());
                    }
                }
                DataStore purchase = new DataStore("purchase.json");
                if(currentCustomer.equals("CUSTOMER")){
                    Customer customer = new Customer();
                    ArrayList<Purchase> purchaseArray = new ArrayList<>();
                    purchaseArray.add(new Purchase(customer.getCustomerId(), formattedDate,itemPurchased, sum));
                    customer.setTransaction(purchaseArray);
                    custStore.addCustomer(customer);
                }
                System.out.print(currentCustomer);
            }
        });

        items.setPrefHeight(584-addCustomer.getPrefHeight()-bill.getPrefHeight()-charge.getPrefHeight());

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
            // Code to execute every second
            sum = 0;

            // Update sum of prices
            items.getItems().forEach(item -> {
                if(item instanceof Bubble){
                    var numString = ((Bubble) item).getBubblePrice().getText();
                    var num = Double.parseDouble(numString);
                    sum += num;
                    charge.setText("Charge (Rp" + sum + ")");
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

        this.getChildren().addAll(addCustomer, customerType, items, bill, charge);
        this.setPrefWidth(maxWidth/4);
        this.setPrefHeight(maxHeight);

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
        if(!items.getItems().contains(itemPane)){
            items.getItems().add(itemPane);
        }
    }


}
