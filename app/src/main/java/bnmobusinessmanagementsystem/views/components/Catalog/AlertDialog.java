package bnmobusinessmanagementsystem.views.components.Catalog;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertDialog extends Alert {
    public AlertDialog(String information){
        super(AlertType.INFORMATION, information, ButtonType.OK);
        this.setAlertType(AlertType.INFORMATION);
        this.setTitle("Information Dialog");
        this.setHeaderText(information);
        this.setContentText("Click OK to continue.");

// Show the alert dialog and wait for the user's response
//        ButtonType result = this.showAndWait().orElse(ButtonType.CANCEL);
    }
}
