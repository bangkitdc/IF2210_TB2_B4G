package bnmobusinessmanagementsystem.views.components.Catalog;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.util.ArrayList;

public class CashierView extends VBox {

    private Label addCustomer;
    private ListView items;
    private Label saveBill;
    private Label printBill;
    private Label charge;
    private double sum;
    private ArrayList itemInfoList;
    public CashierView(){
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

        HBox bill = new HBox(saveBill, printBill);
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

        items.setPrefHeight(maxHeight-addCustomer.getPrefHeight()-bill.getPrefHeight()-charge.getPrefHeight());

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


        this.getChildren().addAll(addCustomer, items, bill, charge);
        this.setPrefWidth(maxWidth/4);
        this.setPrefHeight(maxHeight);

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

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
