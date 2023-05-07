package bnmobusinessmanagementsystem.models;

public class Item {
    private String name;
    private double sellPrice;
    private double buyPrice;
    private int quantity;
    private String category;
    private String image;

    public Item(){
        this.name = "";
        this.sellPrice = 0.0;
        this.buyPrice = 0.0;
        this.quantity = 0;
        this.category = "";
        this.image = "";
    }
    public Item(String name, double sellPrice, double buyPrice, int quantity, String category, String image){
        this.name = name;
        this.sellPrice = sellPrice;
        this.buyPrice = buyPrice;
        this.quantity = quantity;
        this.category = category;
        this.image = image;
    }

    public Item(Item other) {
        this.name = other.name;
        this.sellPrice = other.sellPrice;
        this.buyPrice = other.buyPrice;
        this.quantity = other.quantity;
        this.category = other.category;
        this.image = other.image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean outOfStock(){
        return this.quantity == 0;
    }

    @Override
    public String toString() {
        return "Name: " + this.getName() + "\nCategory: " + this.getCategory() + "\nPrice: " +
                this.getSellPrice() + "\nQuantity: " + this.getQuantity();
    }

    public double getTotalPrice(){
        return sellPrice*quantity;
    }
}
