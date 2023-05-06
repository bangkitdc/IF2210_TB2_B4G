package bnmobusinessmanagementsystem.pluginsStorage.pluginsInput;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Plugin1 implements Plugin {
    public String getName() {
        return "Plugin1";
    }

    public void run(GridPane pane) {
        // Add label and button to GridPane
        Label label = new Label("This is a label added by Plugin1.");
        pane.add(label, 0, 0);

        Button button = new Button("Click me!");
        pane.add(button, 0, 1);

        // Set action for button
        button.setOnAction(e -> {
            System.out.println("Button clicked!");
        });
    }
}
