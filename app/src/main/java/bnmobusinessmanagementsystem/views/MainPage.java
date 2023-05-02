package bnmobusinessmanagementsystem.views;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import bnmobusinessmanagementsystem.views.components.DateTime.DateTime;
import bnmobusinessmanagementsystem.views.components.NimNama.NimNama;
import javafx.stage.Screen;
import javafx.util.Duration;

public class MainPage extends HBox {

    public MainPage() {
        // Create a DateTime component
        DateTime dateTime = new DateTime();

        // Create a NimNama component
        NimNama nimNama = new NimNama();

        // Create left and right VBox containers
        VBox leftVBox = new VBox(dateTime);
        leftVBox.setPadding(new Insets(0, 0, 120, 260));

        VBox rightVBox = new VBox(nimNama);
        rightVBox.setPadding(new Insets(0, 0, 120, 50));

        // Set the alignment of the VBoxes to center
        leftVBox.setAlignment(Pos.CENTER);
        rightVBox.setAlignment(Pos.CENTER);

        // Add the VBoxes to the HBox container
        this.getChildren().addAll(leftVBox, rightVBox);

        // Set the alignment of the VBox to center
        this.setAlignment(Pos.CENTER);

        // Set the initial translation of nimNama to the height of the HBox container
        nimNama.setTranslateY(this.getHeight());

        // Add listener to height property to set initial translation of nimNama
        this.heightProperty().addListener((observable, oldHeight, newHeight) -> {
            nimNama.setTranslateY(newHeight.doubleValue());
        });

        // Animate the scrolling of the NimNama component
        double durationInSeconds = 15.0;
        double distanceToScroll = 660;
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(nimNama.translateYProperty(), 660)),
                new KeyFrame(Duration.seconds(durationInSeconds), new KeyValue(nimNama.translateYProperty(), -distanceToScroll))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(false);
        timeline.play();
    }
}
