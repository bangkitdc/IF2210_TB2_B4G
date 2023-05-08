package bnmobusinessmanagementsystem.views.components.CustomerHistories;
import bnmobusinessmanagementsystem.models.customer.*;
import bnmobusinessmanagementsystem.models.*;
import bnmobusinessmanagementsystem.views.components.CustomerHistories.ItemHistoryView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.TilePane;
import javafx.scene.control.Label;


import java.util.ArrayList;

public class TransactionView extends VBox {
    public TransactionView(Purchase a){
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(10);
        setStyle("-fx-background-color: #A7A9D0;"+"-fx-background-radius: 20;");

        setPadding(new Insets(20));

        Label ID=new Label("ID : " + a.getCustomerId());
        Label Date=new Label("Date : " + a.getDate());
        Label TotalBill=new Label("Total : " + a.getBill());
        ID.setStyle("""
            -fx-text-fill: white;
            -fx-font-size: 20px;
            -fx-font-family: "SF Pro Rounded Semibold";
            -fx-padding: 10px 20px;
            -fx-background-radius: 20px;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 0, 1);
""");
        Date.setStyle("""
            -fx-text-fill: white;
            -fx-font-size: 20px;
            -fx-font-family: "SF Pro Rounded Semibold";
            -fx-padding: 10px 20px;
            -fx-background-radius: 20px;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 0, 1);
""");
        TotalBill.setStyle("""
            -fx-text-fill: white;
            -fx-font-size: 20px;
            -fx-font-family: "SF Pro Rounded Semibold";
            -fx-padding: 10px 20px;
            -fx-background-radius: 20px;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 0, 1);
""");
        ID.setAlignment(Pos.CENTER);
        Date.setAlignment(Pos.CENTER);
        HBox labelView=new HBox(ID,Date,TotalBill);
        labelView.setAlignment(Pos.CENTER);
        labelView.setSpacing(60);


        TilePane purchaseItemView = new TilePane();
        purchaseItemView.setAlignment(Pos.CENTER);
        purchaseItemView.setHgap(20);
        purchaseItemView.setVgap(20);
        purchaseItemView.setPrefSize(900,900);

        ArrayList<Item> temp= new ArrayList<>();
        for (Item item: a.getItemList()
             ) {
            purchaseItemView.getChildren().add(new ItemHistoryView(item));
        }
        this.getChildren().addAll(labelView,purchaseItemView);
    }
//    public void addItem(CustomerTransactionView items){
//        this.getChildren().add(items);
//    }

}
