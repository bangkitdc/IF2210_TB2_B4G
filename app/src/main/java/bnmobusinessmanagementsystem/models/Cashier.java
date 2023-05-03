package bnmobusinessmanagementsystem.models;

import java.util.ArrayList;

public class Cashier {
    private ArrayList<Item> items;

    public Cashier(ArrayList<Item> items){
        this.items = items;
    }

    public double getTotalPrice(){
        double total = 0;
        for (Item item : items){
            total += item.getSellPrice();
        }

        return total;
    }


}
