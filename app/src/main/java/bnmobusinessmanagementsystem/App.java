package bnmobusinessmanagementsystem;

/* Import library */
import bnmobusinessmanagementsystem.views.MenuAndTab;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            MenuAndTab menuBar = new MenuAndTab(new Scene(new VBox(), 1080, 660));
            primaryStage.setScene(menuBar.getScene());
            primaryStage.show();
            primaryStage.setResizable(false);

        } catch (Exception e) {
            System.out.println("Error: ");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}