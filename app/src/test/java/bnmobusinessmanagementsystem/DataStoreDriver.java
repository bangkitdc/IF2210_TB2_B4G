package bnmobusinessmanagementsystem.utils;

//import DataStore;
import bnmobusinessmanagementsystem.models.customer.*;
import bnmobusinessmanagementsystem.models.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class DataStoreDriver {
    public static void main(String[] args) {
        DataStore dataStore = new DataStore("tes.json");
//
        Customer[] customers = new Customer[2];
        customers[0] = new VIP("Jane Doe", "987654321", "id1");
        customers[1] = new Member("Joli Diva", "123456789", "id2");
//        dataStore.addCustomer((customers));
        Item[] items = new Item[2];
        items[0] = new Item("KONTOL", 1.0, 2.0, 100, "kontol", "kontoool");
        items[1] = new Item("MEMEK", 1.0, 2.0, 100, "memek", "kontoool");
        ArrayList<Item> itemL = new ArrayList<>(Arrays.asList(items));

        Purchase[] purchases = new Purchase[1];
        purchases[0] = new Purchase("id1", "asdfghjkl", itemL, 69.69);
        ArrayList<Purchase> purchaseL = new ArrayList<>(Arrays.asList(purchases));

        customers[0].setTransaction(purchaseL);

        ArrayList<Customer> customerList = new ArrayList<>(Arrays.asList(customers));

        dataStore.saveCustomer(customerList);


//        System.out.println("Customers saved successfully");

        try {
            // read customer data from the file
//            ArrayList<Customer> custs = dataStore.readCustomer();
//
//            // do something with the customer data (e.g., print it out)
//            for (Customer cust : custs) {
//                if(cust instanceof Member){
//                    System.out.println(((Member) cust).getNama());
//                }
//                if(cust instanceof VIP){
//                    System.out.println(((VIP) cust).getNama());
//                }
////                System.out.println(cust.);
//            }
            Customer AAA = dataStore.getCustomerById("C2");
            System.out.println(((Member) AAA).getNama());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
