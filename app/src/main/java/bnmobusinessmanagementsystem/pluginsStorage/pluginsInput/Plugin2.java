package bnmobusinessmanagementsystem.pluginsStorage.pluginsInput;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.util.converter.IntegerStringConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class Plugin2 implements Plugin<GridPane> {
    private final boolean needPage = false;
    private ArrayList<String> arrayInt;
    private Consumer<ArrayList<Integer>> updatedStates;
    private String current;
    private String db = "paymentstates";
    public String getName() {
        return "Plugin_Payment";
    }

    public void setUp(ArrayList<String> arrayInt, Consumer<ArrayList<Integer>> updatedStates, String current) {
        this.arrayInt = arrayInt;
        this.updatedStates = updatedStates;
        this.current = current;
    }

    public void run(GridPane pane2) {
        Label label = new Label("Customize Disc, Tax, Service: ");
        label.setStyle("""
            -fx-font-size: 18px;
            -fx-text-fill: #FEFEA8;
            -fx-background-color: transparent;
            -fx-font-family: "SF Pro Rounded Regular";
            -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 0);
            -fx-padding: 20 0 0 0;
        """);
        pane2.add(label, 0, 0);

        TextField text1 = new TextField();
        text1.setPromptText("Set Discount ...");
        Pattern validEditingState = Pattern.compile("-?[0-9]*");

        int maxValue = 100;

        UnaryOperator<TextFormatter.Change> filter = c -> {
            String text = c.getControlNewText();
            if (validEditingState.matcher(text).matches()) {
                int intValue = text.isEmpty() ? 0 : Integer.parseInt(text);
                if (intValue <= maxValue) {
                    return c;
                }
            }
            return null;
        };

        TextFormatter<Integer> formatter1 = new TextFormatter<>(new IntegerStringConverter(), Integer.parseInt(arrayInt.get(0)), filter);
        text1.setTextFormatter(formatter1);

        pane2.add(text1, 0, 1);

        TextField text2 = new TextField();
        text2.setPromptText("Set Tax ...");

        TextFormatter<Integer> formatter2 = new TextFormatter<>(new IntegerStringConverter(), Integer.parseInt(arrayInt.get(1)), filter);
        text2.setTextFormatter(formatter2);

        pane2.add(text2, 0, 2);

        TextField text3 = new TextField();
        text3.setPromptText("Set Service Charge ...");

        TextFormatter<Integer> formatter3 = new TextFormatter<>(new IntegerStringConverter(), Integer.parseInt(arrayInt.get(2)), filter);
        text3.setTextFormatter(formatter3);

        pane2.add(text3, 0, 3);

        Button submitBtn = new Button("Submit");
        submitBtn.setStyle("""
            -fx-background-color: #8A5760;
            -fx-text-fill: white;
            -fx-font-size: 12px;
            -fx-font-family: "SF Pro Rounded Semibold";
            -fx-padding: 8px 12px;
            -fx-background-radius: 20px;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 0, 1);
        """);

        submitBtn.setOnAction(e -> {
            Integer discount = Integer.parseInt(text1.getText());
            Integer tax = Integer.parseInt(text2.getText());
            Integer service = Integer.parseInt(text3.getText());
            ArrayList<Integer> statesList = new ArrayList<>(Arrays.asList(discount, tax, service));
            updatedStates.accept(statesList);
        });

        pane2.add(submitBtn, 0, 4);

        text1.setMaxWidth(140);
        text2.setMaxWidth(140);
        text3.setMaxWidth(140);

        // Center
        pane2.setHalignment(label, HPos.CENTER);
        pane2.setHalignment(text1, HPos.CENTER);
        pane2.setHalignment(text2, HPos.CENTER);
        pane2.setHalignment(text3, HPos.CENTER);
        pane2.setHalignment(submitBtn, HPos.CENTER);

        // Set alignment and gap
        pane2.setAlignment(Pos.CENTER);
        pane2.setVgap(15);
    }

    public boolean isNeedPage() {
        return needPage;
    }

    public String getDBNeeded() {
        return db;
    }
}
