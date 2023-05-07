package bnmobusinessmanagementsystem.views;

import bnmobusinessmanagementsystem.models.Item;
import bnmobusinessmanagementsystem.util.DataStore;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.layout.*;
import bnmobusinessmanagementsystem.models.customer.*;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;

public class AddMember extends VBox{
    
    private Label titleLabel;
    private Label nameLabel;
    private TextField nameField;
    private Label telephoneLabel;
    private TextField telephoneField;
    private Button submitButton;
    private TextField displayField;

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
        displayField = new TextField();

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
        nameField.setPrefWidth(200);
        telephoneField.setPrefWidth(200);
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
            -fx-text-fill: #F6968A;
        """);

        telephoneField.setStyle("""
            -fx-background-color: #ffffff;
            -fx-background-radius: 5;
            -fx-border-color: #ffffff;
            -fx-border-radius: 5;
            -fx-border-width: 2;
            -fx-font-size: 14px;
            -fx-font-family: "Poppins";
            -fx-text-fill: #F6968A;
        """);

        submitButton.setStyle("""
            -fx-background-color: #ffffff;
            -fx-background-radius: 10;
            -fx-border-color: #ffffff;
            -fx-border-radius: 10;
            -fx-border-width: 2;
            -fx-font-size: 14px;
            -fx-font-weight: 800;
                """);

        submitButton.setOnAction(event -> {
            String name = nameField.getText();
            String telephone = telephoneField.getText();
            String id = "000";
            String contact = id + ", " + name + ", " + telephone;

            DataStore dataStore = new DataStore("add.json");

            Customer c = new Member(name, telephone, id);

            dataStore.addCustomer(c);
            displayField.setText(contact);
        });
        
        HBox titleBox = new HBox(titleLabel);
        HBox nameBox = new HBox(nameLabel, nameField);
        HBox telephoneBox = new HBox(telephoneLabel, telephoneField);
        HBox buttonBox = new HBox(submitButton);
        HBox displayBox = new HBox(displayField);

        VBox inputBox = new VBox(nameBox, telephoneBox);
        VBox all = new VBox(titleBox, inputBox, buttonBox, displayBox);

        titleBox.setAlignment(Pos.CENTER);
        nameBox.setAlignment(Pos.CENTER_LEFT);
        telephoneBox.setAlignment(Pos.CENTER_LEFT);
        inputBox.setAlignment(Pos.CENTER);
        buttonBox.setAlignment(Pos.CENTER);
        displayBox.setAlignment(Pos.CENTER);
        
        nameBox.setSpacing(20);
        telephoneBox.setSpacing(20);
        buttonBox.setSpacing(20);
        displayBox.setSpacing(20);

        titleBox.setPadding(new Insets(20));
        inputBox.setPadding(new Insets(10, 10, 30, 10));
        nameBox.setPadding(new Insets(15));
        telephoneBox.setPadding(new Insets(15));
        
        buttonBox.setPadding(new Insets(10));
        displayBox.setPadding(new Insets(10));
        all.setPadding(new Insets(30,60, 60,60));
        all.setStyle("""
            -fx-background-color : #F6968A;
            """);
        all.setPrefSize(1080, 660);
        getChildren().addAll(all);

    }
}

