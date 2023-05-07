package bnmobusinessmanagementsystem.views;

import bnmobusinessmanagementsystem.models.Item;
import bnmobusinessmanagementsystem.models.customer.Customer;
import bnmobusinessmanagementsystem.models.customer.Purchase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.util.UUID;

import java.io.File;
import java.util.ArrayList;

public class SistemLaporan extends VBox {
    ArrayList <String> fixedBill;
    ArrayList <String>  laporanPenjualan;

    ArrayList<Customer> allCustomer;

    private ObservableList<Customer> customers;
    private ComboBox<Customer> customerComboBox;
    private Button printFixedBillButton;
    private Button printLaporanPenjualanButton;
    public SistemLaporan(){
        printFixedBillButton = new Button("Print to PDF Fixed Bill");
        printLaporanPenjualanButton = new Button("Print to PDF Laporan Penjualan");
        this.fixedBill=new ArrayList<>();
        this.laporanPenjualan=new ArrayList<>();
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);

        ArrayList<Item> items=new ArrayList<Item>();
        items.add(new Item("Alpukat",100,100,10,"buah","picture"));
        items.add(new Item("Alpukat",100,100,10,"buah","picture"));
        items.add(new Item("Alpukat",100,100,10,"buah","picture"));
        items.add(new Item("Alpukat",100,100,10,"buah","picture"));
        items.add(new Item("Alpukat",100,100,10,"buah","picture"));
        items.add(new Item("Alpukat",100,100,10,"buah","picture"));
        items.add(new Item("Alpukat",100,100,10,"buah","picture"));
        items.add(new Item("Alpukat",100,100,10,"buah","picture"));
        Customer temp=new Customer("1");
        Customer temp2=new Customer("1");
        temp.addTransaction(new Purchase("1","5/5/2023",items));
        temp.addTransaction(new Purchase("1","5/5/2023",items));
        temp2.addTransaction(new Purchase("2","5/5/2023",items));
        temp2.addTransaction(new Purchase("2","5/5/2023",items));

        allCustomer=new ArrayList<>();
        allCustomer.add(temp);
        allCustomer.add(temp2);
        customers = FXCollections.observableArrayList();
        customers.add(temp);
        customers.add(temp2);
        // Add your customer objects to the customers list

        customerComboBox = new ComboBox<>(customers);
        customerComboBox.setPromptText("Select a customer");

        TextField folderTextField = new TextField();
        folderTextField.setPromptText("Select a folder");
        folderTextField.setEditable(false);

        TextField CustomerField = new TextField();
        CustomerField.setPromptText("Select a Customer ID");
        CustomerField.setEditable(false);

        Button selectFolderButton = new Button("Select Folder");
        selectFolderButton.setOnAction(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            Stage stage = (Stage) getScene().getWindow();
            File selectedFolder = directoryChooser.showDialog(stage);
            if (selectedFolder != null) {
                folderTextField.setText(selectedFolder.getAbsolutePath());
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
                        tempString.add("LAPORAN PDF : ");
                        tempString.add(" ");

                        for (Purchase purchase : selectedCustomer.getTransaction()
                        ) {
                            tempString.add(purchase.toString());
                        }

                        fixedBill.addAll(tempString);

                        if (!folderPath.isEmpty()) {
                            String filePath2 = folderPath + File.separator + "FixedBill" + UUID.randomUUID().toString() + ".pdf";
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

        printLaporanPenjualanButton.setOnAction(event -> {
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {

                    String folderPath = folderTextField.getText();

                    ArrayList<String> tempString = new ArrayList<>();
                    for (Customer customer: customers
                         ) {
                        for (Purchase purchase: customer.getTransaction()
                             ) {
                            tempString.add(purchase.toString());
                        }
                    }

                    tempString.add("LAPORAN PDF : ");
                    tempString.add(" ");


                    laporanPenjualan.addAll(tempString);

                    for (String string :tempString
                         ) {
                        System.out.println(string);
                    }

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

        this.getChildren().addAll(folderTextField,selectFolderButton,customerComboBox,printFixedBillButton,printLaporanPenjualanButton);
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