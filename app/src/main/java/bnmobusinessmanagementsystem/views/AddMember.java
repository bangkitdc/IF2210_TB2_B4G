package bnmobusinessmanagementsystem.views;

import bnmobusinessmanagementsystem.models.Item;
import bnmobusinessmanagementsystem.utils.DataStore;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.layout.*;
import bnmobusinessmanagementsystem.models.customer.*;
import javafx.scene.image.Image;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class AddMember extends VBox{

    private Label titleLabel;
    private Label nameLabel;
    private TextField nameField;
    private Label telephoneLabel;
    private TextField telephoneField;
    private Button submitButton;

    public AddMember() {
        FontManager.loadFonts();

        //    Image backgroundImage = new Image("src/main/java/views/bg-add.png");
        //    BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
        //    BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

        //    Set the BackgroundImage as the background of the main VBox

        titleLabel = new Label("Add Your Membership");
        nameLabel = new Label("Name:");
        nameField = new TextField();
        telephoneLabel = new Label("Telephone:");
        telephoneField = new TextField();
        submitButton = new Button("Submit");

        titleLabel.setStyle("""
                -fx-font-size: 32px;
                -fx-font-weight: 800;
                -fx-text-fill: #ffffff;
                -fx-background-color: transparent;
                -fx-font-family: "Poppins";
                -fx-font-weight: 800;
                -fx-tick-label-font: 20px;
        """);

        telephoneLabel.setStyle("""
                -fx-font-size: 16px;
                -fx-font-weight: 800;
                -fx-text-fill: #ffffff;
                -fx-background-color: transparent;
                -fx-font-family: "Poppins";
        """);

        nameLabel.setStyle("""
                -fx-font-size: 16px;
                -fx-font-weight: 800;
                -fx-text-fill: #ffffff;
                -fx-background-color: transparent;
                -fx-font-family: "Poppins";
        """);

        // make the field parallel aka sejajar bro
        nameField.setPrefWidth(500);
        telephoneField.setPrefWidth(500);
        nameLabel.setMinWidth(100);
        telephoneLabel.setMinWidth(100);

        nameField.setStyle("""
            -fx-background-color: #ffffff;
            -fx-background-radius: 5;
            -fx-border-color: #ffffff;
            -fx-border-radius: 5;
            -fx-border-width: 2;
            -fx-font-size: 14px;
            -fx-font-family: "Poppins";
            -fx-text-fill: #000000;
        """);

        telephoneField.setStyle("""
            -fx-background-color: #ffffff;
            -fx-background-radius: 5;
            -fx-border-color: #ffffff;
            -fx-border-radius: 5;
            -fx-border-width: 2;
            -fx-font-size: 14px;
            -fx-font-family: "Poppins";
            -fx-text-fill: #000000;
        """);

        submitButton.setStyle("""
            -fx-background-color: #F6968A;
            -fx-background-radius: 10;
            -fx-border-color: #ffffff;
            -fx-border-radius: 10;
            -fx-border-width: 2;
            -fx-font-size: 14px;
            -fx-font-family: "Poppins";
            -fx-font-weight: 800;
                """);


        submitButton.setOnAction(event -> {
            String name = nameField.getText();
            String telephone = telephoneField.getText();
            
            if(name.equals("") | telephone.equals("")){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("You submit blank input!");
                alert.show();
            }else {
                DataStore dataStore = new DataStore("customer.json");
                String id = dataStore.getLatestID();
                int len = 0;

                try {
                    Customer c = dataStore.getCustomerById(id);
                    dataStore.deleteCustomerById(id);
                    len = c.getTransaction().size();
                    if (c.getTransaction().size() == 0){
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("The customer didn't have any transaction yet!");
                        alert.show();
                    }
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
                if (len != 0){
                        Customer cust = new Member(name, telephone, id);  
                        dataStore.addCustomer(cust);  
                }
                nameField.setText("");
                telephoneField.setText("");  
            }
            
        });

        HBox titleBox = new HBox(titleLabel);
        HBox nameBox = new HBox(nameLabel, nameField);
        HBox telephoneBox = new HBox(telephoneLabel, telephoneField);
        HBox buttonBox = new HBox(submitButton);


        VBox inputBox = new VBox(nameBox, telephoneBox);
        VBox all = new VBox(titleBox, inputBox, buttonBox);

        titleBox.setAlignment(Pos.CENTER);
        nameBox.setAlignment(Pos.CENTER_LEFT);
        telephoneBox.setAlignment(Pos.CENTER_LEFT);
        inputBox.setAlignment(Pos.CENTER);
        buttonBox.setAlignment(Pos.CENTER);

        nameBox.setSpacing(20);
        telephoneBox.setSpacing(20);
        buttonBox.setSpacing(20);

        titleBox.setPadding(new Insets(20));
        inputBox.setPadding(new Insets(60, 10, 60, 10));
        nameBox.setPadding(new Insets(25));
        telephoneBox.setPadding(new Insets(25));

        buttonBox.setPadding(new Insets(10));
        all.setPadding(new Insets(30,60, 60,60));
        all.setStyle("""
            -fx-background-color : #F6968A;
            """);
        all.setPrefSize(1080, 660);
        getChildren().addAll(all);

    }
}