package bnmobusinessmanagementsystem.views.components.NimNama;

import bnmobusinessmanagementsystem.views.FontManager;
import javafx.geometry.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class NimNama extends VBox {
    public NimNama() {
        // Create labels for each piece of information
        Label kelLabel = new Label("Mahasiswa Pencari Takjil");
        kelLabel.setStyle("""
            -fx-font-size: 24px;
            -fx-text-fill: #FEFEA8;
            -fx-opacity: 0.8;
            -fx-background-color: transparent;
            -fx-font-family: "Poppins Semibold";
            -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);
        """);

        kelLabel.setPadding(new Insets(0, 0, 10, 0));

        Label[] labelsNIM = new Label[5];
        Label[] labelsName = new Label[5];

        labelsNIM[0] = new Label("13521055"); labelsName[0] = new Label("Muhammad Bangkit Dwi Cahyono");
        labelsNIM[1] = new Label("13521072"); labelsName[1] = new Label("Irsyad Nurwidianto Basuki");
        labelsNIM[2] = new Label("13521081"); labelsName[2] = new Label("Bagas Aryo Seto");
        labelsNIM[3] = new Label("13521103"); labelsName[3] = new Label("Aulia Mey Diva Annandya");
        labelsNIM[4] = new Label("13521104"); labelsName[4] = new Label("Muhammad Zaydan Athallah");

        for (Label label : labelsNIM) {
            label.setStyle("""
                -fx-font-size: 16px;
                -fx-text-fill: #FEFEA8;
                -fx-opacity: 0.8;
                -fx-background-color: transparent;
                -fx-font-family: "Poppins Semibold";
                -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);
            """);
        }

        for (Label label : labelsName) {
            label.setStyle("""
                -fx-font-size: 16px;
                -fx-text-fill: #F1F6F9;
                -fx-opacity: 0.8;
                -fx-background-color: transparent;
                -fx-font-family: "Poppins Semibold";
                -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);
            """);
        }

        // Create a GridPane to hold the labels
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(30));

        // Add the labels to the GridPane
        gridPane.add(kelLabel, 0, 0, 2, 1);
        GridPane.setHalignment(kelLabel, HPos.CENTER); // Set horizontal alignment to center
        for (int i = 0; i < labelsNIM.length; i++) {
            gridPane.add(labelsNIM[i], 0, i + 1);
            GridPane.setHalignment(labelsNIM[i], HPos.RIGHT); // Set horizontal alignment to center
        }

        for (int i = 0; i < labelsName.length; i++) {
            gridPane.add(labelsName[i], 1, i + 1);
            GridPane.setHalignment(labelsName[i], HPos.LEFT); // Set horizontal alignment to center
        }

        // Add the GridPane to this VBox
        this.getChildren().add(gridPane);

        // Set the alignment of the VBox to center
        this.setAlignment(Pos.CENTER);

        this.setStyle("""
            -fx-alignment: CENTER;
        """);
    }
}
