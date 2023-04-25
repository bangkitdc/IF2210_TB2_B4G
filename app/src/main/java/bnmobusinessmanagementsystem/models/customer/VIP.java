package bnmobusinessmanagementsystem.models.customer;
public class VIP extends Customer {
    private String nama;
    private String noTelp;
    private boolean isActive;
    private double poin;
    private static final double discPrice=0.9;

    public VIP(String nama, String noTelp){
        super();
        this.nama=nama;
        this.noTelp=noTelp;
        this.poin=0;
        this.isActive=true;
    }
    
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public double getPoin() {
        return poin;
    }

    public void getPoin(double price){
        this.poin += getDiscPrice(price)*0.01;
    }
    
    public void setPoin(double poin) {
        this.poin = poin;
    }

    public void statusOff() {
        this.isActive = false;
    }

    public void statusOn() {
        this.isActive = true;
    }
    
    public boolean isActive() {
        return isActive;
    }

    public double getDiscPrice(double price) {
        return price * discPrice;
    }
}
