package bnmobusinessmanagementsystem.models;

import java.util.ArrayList;

public class Catalog {
    private ArrayList<Item> items;

    public Catalog(){
        items = new ArrayList<Item>();
    }

    public int getItemCount(){
        return items.size();
    }
    public void addItem(Item item){
        items.add(item);
    }

    public void removeItem(int idx){
        items.remove(idx);
    }

    public Item getItem(int idx){
        return items.get(idx);
    }

    public ArrayList<Item> getItemsByCategory(String category){
        ArrayList<Item> res = new ArrayList<>();
        for(Item n : items){
            if(n.getCategory().equals(category)){
                res.add(n);
            }
        }

        return res;
    }

    public Item getItemByName(String name){
        for(Item n : items){
            if(n.getName().equals(name)){
                return n;
            }
        }
        return null;
    }

    public ArrayList<Item>  getItemByPrice(double price){
        ArrayList<Item> res = new ArrayList<>();
        for(Item n : items){
            if(n.getSellPrice() == price){
                res.add(n);
            }
        }

        return res;
    }

    public boolean noItem(){
        return items.isEmpty();
    }
}
