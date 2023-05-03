package bnmobusinessmanagementsystem.views.components.Catalog;

import javafx.collections.ListChangeListener;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;

public class CashierView extends VBox {

    private Label addCustomer;
    private ListView items;
    private Label saveBill;
    private Label printBill;
    private Label charge;
    private double sum;

    public CashierView(){
        Screen primaryScreen = Screen.getPrimary();
        double maxHeight = primaryScreen.getVisualBounds().getHeight();
        double maxWidth = primaryScreen.getVisualBounds().getWidth();

        addCustomer = new Label("Add Customer");
        addCustomer.setPrefHeight(50);
        addCustomer.setPrefWidth(maxWidth);
        addCustomer.setAlignment(Pos.CENTER);
        addCustomer.setFont(Font.font(30));

        items = new ListView();

        sum = 0;

        saveBill = new Label("Save Bill");
        printBill = new Label("Print Bill");
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

        items.setPrefHeight(maxHeight-addCustomer.getPrefHeight()-bill.getPrefHeight()-charge.getPrefHeight());
        items.getItems().addListener((ListChangeListener) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    items.scrollTo(change.getTo());
                    sum = 0;
                    items.getItems().forEach(item -> {
                        if(item instanceof BorderPane){
                            var temp = ((BorderPane) item).getRight();
                            if(temp instanceof Label){
                                var numOnly = ((Label) temp).getText().substring(2);
                                var num = Double.parseDouble(numOnly);
                                sum += num;
                                charge.setText("Charge (Rp" + sum +")");
                            }
                        }

                    });
                }
            }
        });

        this.getChildren().addAll(addCustomer, items, bill, charge);
        this.setPrefWidth(maxWidth/4);
        this.setPrefHeight(maxHeight);

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

    public void addItems(Pane item){
        items.getItems().add(item);
    }

}
