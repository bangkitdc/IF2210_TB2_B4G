package bnmobusinessmanagementsystem.models.customer;

import java.util.ArrayList;
import bnmobusinessmanagementsystem.models.Item;

public class Purchase {
    private final String customerId;
    private final String date;
    private final ArrayList<Item> itemList;
    private final double bill;


    public Purchase(String customerId, String date,ArrayList<Item> Items) {
        this.customerId = customerId;
        this.date = date;
        this.itemList = new ArrayList<>();

        this.itemList.addAll(Items);
        double temp=0;
        for (Item item: Items
             ) {
            temp+=item.getTotalPrice();
        }
        this.bill=temp;

    }
    

    public String getCustomerId() {
        return customerId;
    }

    public String getDate() {
        return date;
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public double getBill() {
        return bill;
    }

    @Override
    public String toString() {
        return "( Customer ID : " + customerId + ", Date : " + date + ", Bill : " + bill + " )";
    }

}

