package bnmobusinessmanagementsystem.views.components.DateTime;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Platform;
import javafx.scene.layout.BorderPane;

public class DateTime extends BorderPane {
    private Label dateLabel;
    private Label timeLabel;

    public DateTime() {
        dateLabel = new Label();
        timeLabel = new Label();

        dateLabel.setStyle("""
            -fx-font-size: 14px;
            -fx-font-weight: normal;
            -fx-text-fill: #000000;
            -fx-background-color: transparent;
            -fx-font-family: Consolas;
        """);

        timeLabel.setStyle("""
            -fx-font-size: 36px;
            -fx-font-weight: bold;
            -fx-text-fill: #000000;
            -fx-background-color: transparent;
            -fx-font-family: Consolas;
        """);

        dateLabel.setId("dateLabel");
        timeLabel.setId("timeLabel");

        VBox vbox = new VBox(dateLabel, timeLabel);

        vbox.setStyle("""
            -fx-border-color: #000000;
            -fx-border-width: 1px;
            -fx-border-radius: 20px;
            -fx-padding: 5px;
            -fx-alignment: CENTER;
        """);

        vbox.setAlignment(Pos.CENTER);
        setCenter(vbox);

        Thread updateTimeThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                Platform.runLater(() -> {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMMM yyyy");
                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                    String currentDate = dateFormat.format(new Date());
                    String currentTime = timeFormat.format(new Date());

                    dateLabel.setText(currentDate);
                    timeLabel.setText(currentTime);
                });
            }
        });

        updateTimeThread.setDaemon(true);
        updateTimeThread.start();
    }
}
