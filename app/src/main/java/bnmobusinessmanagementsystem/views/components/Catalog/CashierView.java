package bnmobusinessmanagementsystem.views.components.Catalog;

import bnmobusinessmanagementsystem.models.customer.Customer;
import bnmobusinessmanagementsystem.models.customer.Member;
import bnmobusinessmanagementsystem.models.customer.VIP;
import bnmobusinessmanagementsystem.utils.DataStore;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CashierView extends VBox {

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
    public CashierView(){
        DataStore custStore = new DataStore("customer.json");
        Customer[] customers = new Customer[2];
        customers[0] = new VIP("Jane Doe", "987654321");
        customers[1] = new Member("Joli Diva", "123456789");
        ArrayList<Customer> customerList = new ArrayList<>(Arrays.asList(customers));
        custStore.saveCustomer(customerList);



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

//        customerType = new HBox(20);
        try{
            ArrayList<Customer> custs = custStore.readCustomer();
            for (Customer cust : custs){
                if(cust instanceof Member){
                    dropdown.getItems().add(((Member) cust).getNama());
                }
                if(cust instanceof VIP){
                    dropdown.getItems().add(((VIP) cust).getNama());
                }
//                System.out.println(cust.);
            }
        } catch (IOException e){
            e.printStackTrace();;
        }


//        dropdown.getItems().addAll("Customer", "Member", "VIP");
        dropdown.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {

        });
//        customerType.getChildren().addAll(dropdown, new Label("ID"));
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
                System.out.println(charge.getText());
                for(Object item : items.getItems()){
                    if(item instanceof Bubble){
                        ArrayList<String> itemInfo = new ArrayList<>();
                        itemInfo.add(((Bubble) item).getBubbleName().getText());
                        itemInfo.add(((Bubble) item).getBubblePrice().getText());
                        itemInfo.add(((Bubble) item).getQuantityLabel().getText());
                        System.out.print(itemInfo);
//                        System.out.println(((Bubble) item).getBubbleName().getText());
//                        System.out.println(((Bubble) item).getBubblePrice().getText());
//                        System.out.println(((Bubble) item).getQuantityLabel().getText());
                    }
                }
                //TODO : ngecharge ke customer
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
