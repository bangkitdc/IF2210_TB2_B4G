package bnmobusinessmanagementsystem.views;

import bnmobusinessmanagementsystem.views.components.DateTime.DateTime;
import bnmobusinessmanagementsystem.views.components.NimNama.NimNama;
import bnmobusinessmanagementsystem.views.components.Settings.Settings;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.nio.file.Paths;

public class SettingPage extends HBox {
    public SettingPage() {
        String currentDir = System.getProperty("user.dir");
        String path = "src/main/resources/background/bg_setting.png";
        String fullPath = Paths.get(currentDir, path).toString();

        Image img = new Image(fullPath);
        BackgroundImage bg_img = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, false, false));
        this.setBackground(new Background(bg_img));

        Settings settings = new Settings();
        VBox vbox = new VBox(settings);

        // Set the alignment of the VBox to center
        vbox.setAlignment(Pos.CENTER);

        this.getChildren().add(vbox);
        this.setAlignment(Pos.CENTER);

        this.setPrefSize(1080, 634);
    }
}
