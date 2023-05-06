package bnmobusinessmanagementsystem.models.customer;

import bnmobusinessmanagementsystem.models.Item;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Purchase {
    private final String customerId;
    private final String date;
    private final ArrayList<Item> itemList;
    private final double bill;


    public Purchase(String customerId, String date,ArrayList<Item> Item ,double bill) {
        this.customerId = customerId;
        this.date = date;
        this.bill = bill;
        this.itemList = new ArrayList<>();

        this.itemList.addAll(Item);

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

}

