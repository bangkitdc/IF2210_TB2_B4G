package bnmobusinessmanagementsystem;

import bnmobusinessmanagementsystem.models.customer.Customer;
import org.junit.Test;

public class AppTest {
    @Test public void testApp() {
        Customer a=new Customer();
        System.out.println(a.getCustomerId());
    }
}
