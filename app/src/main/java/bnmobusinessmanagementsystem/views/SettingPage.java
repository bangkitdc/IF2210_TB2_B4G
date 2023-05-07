package bnmobusinessmanagementsystem.views;

import bnmobusinessmanagementsystem.App;
import bnmobusinessmanagementsystem.views.components.Settings.Settings;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.nio.file.Paths;

public class SettingPage extends HBox {
    public SettingPage() {
        String currentDir = System.getProperty("user.dir");
        String path = "src/main/resources/background/bg_setting.png";
        String fullPath = Paths.get(currentDir, path).toString();

//        Image img = new Image(fullPath);
//        BackgroundImage bg_img = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, false, false));
//        this.setBackground(new Background(bg_img));

        Settings settings = new Settings();
        VBox vbox = new VBox(settings);

        // Set the alignment of the VBox to center
        vbox.setAlignment(Pos.CENTER);

        this.getChildren().add(vbox);
        this.setAlignment(Pos.CENTER);

        this.setPrefSize(1080, 634);
    }
}
