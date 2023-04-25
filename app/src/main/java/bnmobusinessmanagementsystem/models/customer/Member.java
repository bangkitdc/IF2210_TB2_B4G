package bnmobusinessmanagementsystem.models.customer;
public class Member extends Customer{
    private String nama;
    private String noTelp;
    private double poin;
    private boolean isActive;

    public Member(String nama, String noTelp){
        super();
        this.nama = nama;
        this.noTelp = noTelp;
        this.poin = 0;
        this.isActive = true;
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

    public void addPoin(double price){
        this.poin += price*0.01;
    }

    public void setPoin(double poin) {
        this.poin = poin;
    }

    public boolean isActive() {
        return isActive;
    }

    public void statusOff() {
        this.isActive = false;
    }

    public void statusOn() {
        this.isActive = true;
    }

    public double usePoin(double price){
        double tempPrice=0;
        if(this.poin>price){
            this.poin-=price;
            return tempPrice;
        }else if(this.poin==price){
            this.poin = 0;
            return tempPrice;
        }else{
            tempPrice=price;
            tempPrice-=this.poin;
            this.poin-=price;
            return tempPrice;
        }
    }

}
