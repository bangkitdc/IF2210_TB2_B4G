package bnmobusinessmanagementsystem.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

import bnmobusinessmanagementsystem.views.components.DateTime.DateTime;
import bnmobusinessmanagementsystem.views.components.NimNama.NimNama;
public class MainPage extends VBox {

    public MainPage() {
        // Create a DateTime component
        DateTime dateTime = new DateTime();
        dateTime.setMaxSize(200, 100);
        dateTime.setPadding(new Insets(0, 0, 30, 0));

        // Create a NimNama component
        NimNama nimNama = new NimNama();
        nimNama.setMaxSize(400, 200);

        // Add the components to this VBox
        this.getChildren().addAll(dateTime, nimNama);

        // Set the alignment of the VBox to center
        this.setAlignment(Pos.CENTER);
    }
}
