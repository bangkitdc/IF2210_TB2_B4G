package bnmobusinessmanagementsystem.pluginsStorage.pluginsInput;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Plugin1 implements Plugin<GridPane> {
    private final boolean needPage = false;
    private ArrayList<String> arrayOptions;
    private Consumer<String> optionsChose;
    private String current;
    private String db = "exchangerate";
    public String getName() {
        return "Plugin_Mata_Uang";
    }

    public void setUp(ArrayList<String> arrayOptions, Consumer<String> optionsChose, String current) {
        this.arrayOptions = arrayOptions;
        this.optionsChose = optionsChose;
        this.current = current;
    }

    public void run(GridPane pane) {
        // Add label and button to GridPane
        Label label = new Label("Customize Mata Uang: ");
        label.setStyle("""
            -fx-font-size: 18px;
            -fx-text-fill: #FEFEA8;
            -fx-background-color: transparent;
            -fx-font-family: "SF Pro Rounded Regular";
            -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 0);
            -fx-padding: 20 0 0 0;
        """);
        pane.add(label, 0, 0);

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(arrayOptions);
        comboBox.setValue(current);

        comboBox.setOnAction(e -> {
            String selectedOption = comboBox.getSelectionModel().getSelectedItem();
            optionsChose.accept(selectedOption);
        });

        pane.add(comboBox, 0, 1);

        // Center
        pane.setHalignment(label, HPos.CENTER);
        pane.setHalignment(comboBox, HPos.CENTER);

        // Set alignment and gap
        pane.setAlignment(Pos.CENTER);
        pane.setVgap(15);
    }

    public boolean isNeedPage() {
        return needPage;
    }

    public String getDBNeeded() {
        return db;
    }
}
