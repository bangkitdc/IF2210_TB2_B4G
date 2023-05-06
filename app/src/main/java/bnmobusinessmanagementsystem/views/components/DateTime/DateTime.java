package bnmobusinessmanagementsystem.views.components.DateTime;

import bnmobusinessmanagementsystem.views.FontManager;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Platform;
import javafx.scene.paint.Color;

public class DateTime extends BorderPane {
    private Label dateLabel;
    private Label timeLabel;

    public DateTime() {
        dateLabel = new Label();
        timeLabel = new Label();

        dateLabel.setStyle("""
            -fx-font-size: 30px;
            -fx-text-fill: #FEFEA8;
            -fx-background-color: transparent;
            -fx-font-family: "SF Pro Rounded Regular";
            -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 0);
        """);

        timeLabel.setStyle("""
            -fx-font-size: 80px;
            -fx-text-fill: #FEFEA8;
            -fx-background-color: transparent;
            -fx-font-family: "SF Pro Rounded Semibold";
            -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 0);
            -fx-min-width: 350px;
        """);

        dateLabel.setId("dateLabel");
        timeLabel.setId("timeLabel");

        VBox vbox = new VBox(dateLabel, timeLabel);

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
                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
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
