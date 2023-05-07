package bnmobusinessmanagementsystem.views;

import java.io.IOException;
import java.text.ParseException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import bnmobusinessmanagementsystem.utils.DataStore;
import bnmobusinessmanagementsystem.models.customer.*;
import bnmobusinessmanagementsystem.models.Item;

public class UpdateCustomer extends VBox{

    private Label titleLabel;
    private ToggleButton button1;
    private ToggleButton button2;
    private Circle circlebutton1;
    private Circle circlebutton2;
    private Label idLabel;
    private Label idField;
    private Label nameLabel;
    private TextField nameField;
    private Label telephoneLabel;
    private TextField telephoneField;
    private Label poinLabel;
    private Label poinField;
    private Button submitButton;
    private TextField displayField;
    private ToggleButton deactivate;
    private Circle deactCircle;

    public UpdateCustomer() {
        FontManager.loadFonts();

        //    Image backgroundImage = new Image("src/main/java/views/bg-add.png");
        //    BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
        //    BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

        // Set the BackgroundImage as the background of the main VBox
        DataStore dataStore = new DataStore("customer.json");
        String nama = "";
        String phone = "";
        String id = "";
        double poin = 0;

        String tipe = "";
        try {
            String idTemp = dataStore.getLatestID();
            Customer c = dataStore.getCustomerById(idTemp);
            if (c instanceof Member){
                Member m = ((Member) c);
                nama = m.getNama();
                phone = m.getNoTelp();
                id = m.getCustomerId();
                poin = m.getPoin();
                tipe = "Member";
            }
            if (c instanceof VIP) {
                VIP v = ((VIP) c);
                nama = v.getNama();
                phone = v.getNoTelp();
                id = v.getCustomerId();
                poin = v.getPoin();
                tipe = "VIP";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(nama);
        System.out.println(id);
        System.out.println(poin);
        System.out.println(phone);

        button1 = new ToggleButton("Member");
        button2 = new ToggleButton("VIP");
        circlebutton1 = new Circle(5, Color.TRANSPARENT);
        circlebutton1.setStroke(Color.WHITE);
        circlebutton1.setStrokeWidth(2);
        circlebutton2 = new Circle(5, Color.TRANSPARENT);
        circlebutton2.setStroke(Color.WHITE);
        circlebutton2.setStrokeWidth(2);

        deactivate = new ToggleButton("Deactivate");
        deactCircle = new Circle(5, Color.TRANSPARENT);
        deactCircle.setStroke(Color.WHITE);
        deactCircle.setStrokeWidth(2);

        titleLabel = new Label("Update Profile");
        // id can not be changed
        idLabel = new Label("ID                       :");
        idField = new Label(id);
        nameLabel = new Label("Name             :");
        nameField = new TextField(nama);
        telephoneLabel = new Label("Telephone  :");
        telephoneField = new TextField(phone);
        poinLabel = new Label("Poin                 :");
        poinField = new Label("" + poin);
        submitButton = new Button("Submit");
        displayField = new TextField();

        button1.setOnAction(event -> {
            if (button1.isSelected()) {
                // Button 1 is selected
                circlebutton1.setFill(Color.PINK);
                circlebutton2.setFill(Color.TRANSPARENT);
                button2.setSelected(false);
            } else {
                circlebutton1.setFill(Color.TRANSPARENT);
                // Button 1 is not selected
            }
        });

        button2.setOnAction(event -> {
            if (button2.isSelected()) {
                // Button 2 is selected
                circlebutton1.setFill(Color.TRANSPARENT);
                circlebutton2.setFill(Color.PINK);
                button1.setSelected(false);
            } else {
                circlebutton2.setFill(Color.TRANSPARENT);
                // Button 2 is not selected
            }
        });

        deactivate.setStyle("""
            -fx-background-color: #f6968A;
            -fx-background-radius: 10;
            -fx-border-color: #f6968A;
            -fx-border-radius: 10;
            -fx-border-width: 2;
            -fx-font-size: 14px;
            -fx-font-family: "Poppins";
            -fx-font-weight: 800;
                """);

        button1.setStyle("""
            -fx-background-color: #ffffff;
            -fx-background-radius: 10;
            -fx-border-color: #ffffff;
            -fx-border-radius: 10;
            -fx-border-width: 2;
            -fx-font-size: 14px;
            -fx-font-family: "Poppins";
            -fx-font-weight: 800;
                """);

        button2.setStyle("""
            -fx-background-color: #ffffff;
            -fx-background-radius: 10;
            -fx-border-color: #ffffff;
            -fx-border-radius: 10;
            -fx-border-width: 2;
            -fx-font-size: 14px;
            -fx-font-family: "Poppins";
            -fx-font-weight: 800;
                """);

        titleLabel.setStyle("""
                -fx-font-size: 32px;
                -fx-font-weight: 800;
                -fx-text-fill: #ffffff;
                -fx-background-color: transparent;
                -fx-font-family: "Poppins";
                -fx-font-weight: 800;
                -fx-tick-label-font: 20px;
        """);

        idLabel.setStyle("""
                -fx-font-size: 16px;
                -fx-font-weight: 800;
                -fx-text-fill: #ffffff;
                -fx-background-color: transparent;
                -fx-font-family: "Poppins";
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

        poinLabel.setStyle("""
                -fx-font-size: 16px;
                -fx-font-weight: 800;
                -fx-text-fill: #ffffff;
                -fx-background-color: transparent;
                -fx-font-family: "Poppins";
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


        deactivate.setOnAction(event -> {
            if (deactivate.isSelected()) {
                deactCircle.setFill(Color.PINK);
            } else{
                deactCircle.setFill(Color.TRANSPARENT);
            }
        });

        // make the field parallel aka sejajar bro
        idField.setPrefWidth(600);
        nameField.setPrefWidth(600);
        telephoneField.setPrefWidth(600);
        poinField.setPrefWidth(600);

        idLabel.setMinWidth(120);
        nameLabel.setMinWidth(100);
        telephoneLabel.setMinWidth(100);
        poinLabel.setMinWidth(120);

        nameField.setPrefHeight(30);
        poinLabel.setPrefHeight(30);

        idField.setStyle("""
            -fx-background-color: #ffffff;
            -fx-background-radius: 5;
            -fx-border-color: #ffffff;
            -fx-border-radius: 5;
            -fx-border-width: 2;
            -fx-font-size: 16px;
            -fx-font-family: "Poppins";
            -fx-text-fill: #808080;
            -fx-text-padding: 10px;
        """);

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

        poinField.setStyle("""
            -fx-background-color: #ffffff;
            -fx-background-radius: 5;
            -fx-border-color: #ffffff;
            -fx-border-radius: 5;
            -fx-border-width: 2;
            -fx-font-size: 14px;
            -fx-font-family: "Poppins";
            -fx-text-fill: #000000;
        """);

        submitButton.setOnAction(event -> {
            String button = "";
            String deactState = "";
            if (button1.isSelected()){
                button = button1.getText();
            } else if (button2.isSelected()){
                button = button2.getText();
            }

            if (deactivate.isSelected()){
                deactState = "deact";
            }
            String idText = idField.getText();
            String name = nameField.getText();
            String telephone = telephoneField.getText();
            String contact = button + ", "+ name + ", " + telephone;

            if (button.equals("") | name.equals("") | telephone.equals("")){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("You submit blank input!");
                alert.show();
            } else {
                try{
                    String idTemp = dataStore.getLatestID();
                    Customer c = dataStore.getCustomerById(idTemp);
    
                    dataStore.deleteCustomerById(idTemp);
    
                    if (button == "Member"){
                        Customer temp = new Member(name, telephone, idText);
                        if (deactState == "deact"){
                            ((Member) temp).statusOff();
                        }
                        dataStore.addCustomer(temp);
                    } else if (button == "VIP"){
                        Customer temp = new VIP(name, telephone, idText);
                        if (deactState == "deact"){
                            ((VIP) temp).statusOff();
                        }
                        dataStore.addCustomer(temp);
                    }
    
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            displayField.setText(contact);
        });

        HBox b1 = new HBox(circlebutton1, button1);
        b1.setAlignment(Pos.CENTER);
        HBox b2 = new HBox(circlebutton2, button2);
        b2.setAlignment(Pos.CENTER);
        HBox buttonBox2 = new HBox(b1, b2);
        HBox titleBox = new HBox(titleLabel);
        HBox nameBox = new HBox(nameLabel, nameField);
        HBox idBox = new HBox(idLabel, idField);
        HBox telephoneBox = new HBox(telephoneLabel, telephoneField);
        HBox pointBox = new HBox(poinLabel, poinField);
        HBox deactBox = new HBox(deactCircle, deactivate);
        HBox buttonBox = new HBox(submitButton);
        HBox displayBox = new HBox(displayField);

        VBox inputBox = new VBox(buttonBox2,idBox, nameBox, telephoneBox, pointBox);
        VBox all = new VBox(titleBox, inputBox, deactBox,  buttonBox, displayBox);

        titleBox.setAlignment(Pos.CENTER);
        buttonBox2.setAlignment(Pos.CENTER);
        idBox.setAlignment(Pos.CENTER_LEFT);
        nameBox.setAlignment(Pos.TOP_LEFT);
        telephoneBox.setAlignment(Pos.CENTER_LEFT);
        inputBox.setAlignment(Pos.CENTER);
        pointBox.setAlignment(Pos.CENTER_LEFT);
        buttonBox.setAlignment(Pos.CENTER);
        deactBox.setAlignment(Pos.CENTER_LEFT);
        displayBox.setAlignment(Pos.CENTER);

        nameBox.setSpacing(20);
        telephoneBox.setSpacing(20);
        displayBox.setSpacing(20);
        b1.setSpacing(5);
        b2.setSpacing(5);
        deactBox.setSpacing(5);
        buttonBox2.setSpacing(20);

        buttonBox2.setPadding(new Insets(30, 0, 20, 0));
        inputBox.setPadding(new Insets(10));
        idBox.setPadding(new Insets(15));
        nameBox.setPadding(new Insets(15));
        telephoneBox.setPadding(new Insets(15));
        deactBox.setPadding(new Insets(15, 15, 15, 40));
        pointBox.setPadding(new Insets(15));
        displayBox.setPadding(new Insets(10));
        all.setPadding(new Insets(20,0,20,60));
        all.setStyle("""
            -fx-background-color : #F6968A;
            """);
        all.setPrefSize(1080, 660);

        getChildren().addAll(all);
        
    }
}
