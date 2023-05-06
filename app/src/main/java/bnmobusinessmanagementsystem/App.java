package bnmobusinessmanagementsystem;

/* Import library */
import bnmobusinessmanagementsystem.views.Inventaris;
import bnmobusinessmanagementsystem.views.MenuAndTab;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.*;

public class App extends Application {

    private Button newCustomer;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
//            MenuAndTab menuBar = new MenuAndTab(new Scene(new VBox(), 1080, 660));
//            primaryStage.setScene(menuBar.getScene());
//            primaryStage.show();
            Inventaris inventaris = new Inventaris ();
            primaryStage.setScene(inventaris.getScene());
//            primaryStage.setmaximized(true
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
