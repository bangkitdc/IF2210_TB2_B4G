package bnmobusinessmanagementsystem.models.customer;

import java.util.ArrayList;

public class Customer {
    private static int numOfCustomer=0;
    private String idCustomer;
    private ArrayList<Purchase> transaction;


    public Customer(String id) {
        numOfCustomer++;
        this.idCustomer=id;
        this.transaction=new ArrayList<>();
    }
    public Customer() {
        numOfCustomer++;
        this.idCustomer=Integer.toString(numOfCustomer);
        this.transaction=new ArrayList<>();
    }

    public String getCustomerId() {
        return this.idCustomer;
    }

    public int getNumOfCustomer() {
        return numOfCustomer;
    }
    public int getNumOfTransaction(){return this.transaction.size();}

    public ArrayList<Purchase> getTransaction() {
        return transaction;
    }


    public void setTransaction(ArrayList<Purchase> transaction) {
        this.transaction = transaction;
    }

    public void addTransaction(Purchase a){
        this.transaction.add(a);
    }

    @Override
    public String toString() {
        return "Customer ID: " + this.idCustomer;
    }
}

