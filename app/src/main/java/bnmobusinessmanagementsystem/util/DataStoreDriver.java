package bnmobusinessmanagementsystem.util;

//import DataStore;
import bnmobusinessmanagementsystem.models.customer.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DataStoreDriver {
    public static void main(String[] args) {
        DataStore dataStore = new DataStore("tes.json");
//
        Customer[] customers = new Customer[2];
        customers[0] = new VIP("Jane Doe", "987654321");
        customers[1] = new Member("Joli Diva", "123456789");
//        dataStore.addCustomer((customers));
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
