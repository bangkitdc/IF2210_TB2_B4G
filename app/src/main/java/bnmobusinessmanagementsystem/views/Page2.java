package bnmobusinessmanagementsystem.views;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.nio.file.Paths;

public class Page2 extends HBox {
    public Page2() {
        String currentDir = System.getProperty("user.dir");
        String path = "src/main/resources/background/bg.png";
        String fullPath = Paths.get(currentDir, path).toString();

        Image img = new Image(fullPath);
        BackgroundImage bg_img = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, false, false));
        this.setBackground(new Background(bg_img));

        // Set the alignment of the VBox to center
        this.setAlignment(Pos.CENTER);

        this.setPrefSize(1080, 634);
    }
}
