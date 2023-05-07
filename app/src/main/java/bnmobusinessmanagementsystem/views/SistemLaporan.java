package bnmobusinessmanagementsystem.views;

import bnmobusinessmanagementsystem.models.Item;
import bnmobusinessmanagementsystem.models.customer.Customer;
import bnmobusinessmanagementsystem.models.customer.Purchase;
import bnmobusinessmanagementsystem.utils.DataStore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

import java.io.File;
import java.util.ArrayList;

public class SistemLaporan extends HBox {
    ArrayList <String> fixedBill;
    ArrayList <String>  laporanPenjualan;

    private ObservableList<Customer> customers;
    private ComboBox<Customer> customerComboBox;
    private Button printFixedBillButton;
    private Button printLaporanPenjualanButton;
    public SistemLaporan() throws IOException {
        printFixedBillButton = new Button("Print to PDF");
        printLaporanPenjualanButton = new Button("Print to PDF");
        Button selectFolderButton = new Button("Select Folder");
        Button selectFolderButton2 = new Button("Select Folder");
        this.fixedBill=new ArrayList<>();
        this.laporanPenjualan=new ArrayList<>();

        DataStore customersData=new DataStore("customer.json");
        this.customers=FXCollections.observableArrayList();
        this.customers.addAll(customersData.readCustomer());

//        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(30));

        String currentDir = System.getProperty("user.dir");
        String path = "src/main/resources/background/bgLaporan2.png";
        String fullPath = Paths.get(currentDir, path).toString();

        Image img = new Image(fullPath);
        BackgroundImage bg_img = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, false, false));
        this.setBackground(new Background(bg_img));



        customerComboBox = new ComboBox<>(customers);
        customerComboBox.setPromptText("Select a customer");
//        customerComboBox.setEditable(true);
//        customerComboBox.getEditor().setOnKeyReleased(event -> filterItems(customerComboBox.getEditor().getText()));

        printFixedBillButton.setStyle("""
    -fx-background-color:
            linear-gradient(#f0ff35,#a9ff00),
            radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%);
    -fx-background-radius: 6,5;
    -fx-background-insets: 0,1;

    -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 5, 0.0, 0, 1);
   -fx-text-fill: #395306;
""");

        TextField folderTextField = new TextField();
        folderTextField.setPromptText("Select a folder");
        folderTextField.setEditable(false);

        Label customerReport= new Label("Customer Report");
        customerReport.setStyle("""
            -fx-font-size: 30px;
            -fx-text-fill: #000000;
            -fx-background-color: transparent;
            -fx-font-family: "SF Pro Rounded Semibold";
            -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 0);
            -fx-padding: 0 0 0 0;
        """);
        selectFolderButton.setStyle("""
    -fx-background-color:
            linear-gradient(#ffd65b, #e68400),
            linear-gradient(#ffef84, #f2ba44),
            linear-gradient(#ffea6a, #efaa22),
            linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),
            linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));
    -fx-background-radius: 30;
    -fx-background-insets: 0,1,2,3,0;
    -fx-text-fill: #654b00;
    -fx-font-weight: bold;
    -fx-font-size: 14px;
    -fx-padding: 10 20 10 20;
""");
        VBox fixedBillContainer=new VBox();
        fixedBillContainer.setSpacing(30);
        fixedBillContainer.setPadding(new Insets(30));
        fixedBillContainer.setStyle("""
        -fx-background-color: #A7A9D0;
        -fx-background-radius: 20;
        -fx-border-width: 1;
        -fx-border-color: #FFFFFF;
        -fx-border-radius: 20;
""");

        folderTextField.setStyle("""
            -fx-background: white;
            -fx-background-color: #FFFFFF;
            -fx-text-fill: -fx-text-base-color;
            -fx-padding: 3 0 2 7;
            -fx-cell-size: 2em;
            -fx-background-radius: 20;
                    -fx-border-width: 1;
        -fx-border-color: #000000;
        -fx-border-radius: 20;
""");
        folderTextField.setPrefSize(200,40);


        customerComboBox.setStyle("""
            -fx-background: white;
            -fx-background-color: #FFFFFF;
            -fx-text-fill: -fx-text-base-color;
            -fx-padding: 3 0 2 7;
            -fx-cell-size: 2em;
            -fx-background-radius: 20;
                    -fx-border-width: 1;
        -fx-border-color: #000000;
        -fx-border-radius: 20;
""");
        customerComboBox.setPrefSize(200,40);
        fixedBillContainer.setMaxSize(400,400);
        fixedBillContainer.setAlignment(Pos.CENTER);
        fixedBillContainer.getChildren().addAll(customerReport,folderTextField,selectFolderButton,customerComboBox,printFixedBillButton);


        TextField CustomerField = new TextField();
        CustomerField.setPromptText("Select a Customer ID");
        CustomerField.setEditable(false);




        selectFolderButton.setOnAction(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            Stage stage = (Stage) getScene().getWindow();
            File selectedFolder = directoryChooser.showDialog(stage);
            if (selectedFolder != null) {
                folderTextField.setText(selectedFolder.getAbsolutePath());
            }
        });


        TextField folderTextField2 = new TextField();
        folderTextField2.setPromptText("Select a folder");
        folderTextField2.setEditable(false);
        folderTextField2.setStyle("""
            -fx-background: white;
            -fx-background-color: #FFFFFF;
            -fx-text-fill: -fx-text-base-color;
            -fx-padding: 3 0 2 7;
            -fx-cell-size: 2em;
            -fx-background-radius: 20;
                    -fx-border-width: 1;
        -fx-border-color: #000000;
        -fx-border-radius: 20;
""");
        folderTextField2.setPrefSize(200,40);
        selectFolderButton2.setStyle("""
    -fx-background-color:
            linear-gradient(#ffd65b, #e68400),
            linear-gradient(#ffef84, #f2ba44),
            linear-gradient(#ffea6a, #efaa22),
            linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),
            linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));
    -fx-background-radius: 30;
    -fx-background-insets: 0,1,2,3,0;
    -fx-text-fill: #654b00;
    -fx-font-weight: bold;
    -fx-font-size: 14px;
    -fx-padding: 10 20 10 20;
""");

        printLaporanPenjualanButton.setStyle("""
    -fx-background-color:
            linear-gradient(#f0ff35,#a9ff00),
            radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%);
    -fx-background-radius: 6,5;
    -fx-background-insets: 0,1;

    -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 5, 0.0, 0, 1);
   -fx-text-fill: #395306;
""");

        selectFolderButton2.setOnAction(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            Stage stage = (Stage) getScene().getWindow();
            File selectedFolder = directoryChooser.showDialog(stage);
            if (selectedFolder != null) {
                folderTextField2.setText(selectedFolder.getAbsolutePath());
            }
        });
        Label allReport= new Label("Transactions Report");
        allReport.setStyle("""
            -fx-font-size: 30px;
            -fx-text-fill: #000000;
            -fx-background-color: transparent;
            -fx-font-family: "SF Pro Rounded Semibold";
            -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 0);
            -fx-padding: 0 0 0 0;
        """);

        VBox laporanPenjualanContainer = new VBox();
        laporanPenjualanContainer.setSpacing(30);
        laporanPenjualanContainer.setPadding(new Insets(30));
        laporanPenjualanContainer.setStyle("""
        -fx-background-color: #A7A9D0;
        -fx-background-radius: 20;
        -fx-border-width: 1;
        -fx-border-color: #FFFFFF;
        -fx-border-radius: 20;
""");
        laporanPenjualanContainer.setMaxSize(400,400);
        laporanPenjualanContainer.setAlignment(Pos.CENTER);

        laporanPenjualanContainer.getChildren().addAll(allReport,folderTextField2,selectFolderButton2,printLaporanPenjualanButton);



        printLaporanPenjualanButton.setOnAction(event -> {
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {

                    String folderPath = folderTextField2.getText();

                    ArrayList<String> tempString = new ArrayList<>();
                    tempString.add("LAPORAN : ");
                    tempString.add(" ");
                    for (Customer customer: customers
                         ) {
                        for (Purchase purchase: customer.getTransaction()
                             ) {
                            tempString.add(purchase.toString());
                        }
                    }



                    laporanPenjualan.addAll(tempString);


                    if (!folderPath.isEmpty()) {
//                        String filePath = folderPath + File.separator + "fixedBill"+UUID.randomUUID().toString()+".pdf";
                        String filePath = folderPath + File.separator + "LaporanPenjualan"+UUID.randomUUID().toString()+".pdf";
                        saveToPDF(filePath, tempString);
                    }else {
                        System.out.println("Folder path or file name is empty.");
                        return null;
                    }
                    return null;
                }
            };

            Thread thread = new Thread(task);
            thread.start();
            try {
                thread.join(); // Wait for the thread to finish
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        });
        printFixedBillButton.setOnAction(event -> {
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    Customer selectedCustomer = customerComboBox.getValue();
                    if (selectedCustomer != null) {
                        String folderPath = folderTextField.getText();

                        ArrayList<String> tempString = new ArrayList<>();
                        tempString.add("LAPORAN Customer"+selectedCustomer.getCustomerId()+": ");
                        tempString.add(" ");

                        for (Purchase purchase : selectedCustomer.getTransaction()
                        ) {
                            tempString.add(purchase.toString());
                        }

                        fixedBill.addAll(tempString);

                        if (!folderPath.isEmpty()) {
                            String filePath2 = folderPath + File.separator + "FixedBill Customer" + selectedCustomer.getCustomerId() +UUID.randomUUID().toString() + ".pdf";
                            saveToPDF(filePath2, tempString);
                        } else {
                            System.out.println("Folder path or file name is empty.");
                            return null;
                        }
                    }
                    return null;
                }
            };

            Thread thread = new Thread(task);
            thread.start();
            try {
                thread.join(); // Wait for the thread to finish
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        });

        this.setAlignment(Pos.CENTER);
        this.setSpacing(200);
        this.getChildren().addAll(fixedBillContainer,laporanPenjualanContainer);
    }


    private void saveToPDF(String filePath, ArrayList<String> data) throws Exception {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.newLineAtOffset(20, 750);
            contentStream.setLeading(14.5f);
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

            int currentPageLineCount = 0;
            for (String line : data) {
                if (currentPageLineCount >= 48) {
                    contentStream.endText();
                    contentStream.close();

                    page = new PDPage();
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(20, 750);
                    contentStream.setLeading(14.5f);
                    contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
                    currentPageLineCount = 0;
                }

                contentStream.showText(line);
                contentStream.newLine();
                currentPageLineCount++;
            }

            contentStream.endText();
            contentStream.close();

            document.save(filePath);
            System.out.println("Data saved to PDF: " + filePath);
        }
    }


}