package bnmobusinessmanagementsystem.models.customer;

public class Customer {
    private static int numOfCustomer=0;
    private final int idCustomer=numOfCustomer+1;

    public Customer() {
        numOfCustomer++;
    }

    public int getCustomerId() {
        return this.idCustomer;
    }

    public int getNumOfCustomer() {
        return numOfCustomer;
    }

}

