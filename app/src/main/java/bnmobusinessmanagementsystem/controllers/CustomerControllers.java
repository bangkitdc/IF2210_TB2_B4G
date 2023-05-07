package bnmobusinessmanagementsystem.controllers;

import bnmobusinessmanagementsystem.models.Item;
import bnmobusinessmanagementsystem.models.customer.Customer;
import bnmobusinessmanagementsystem.models.customer.Member;
import bnmobusinessmanagementsystem.models.customer.Purchase;
import bnmobusinessmanagementsystem.models.customer.VIP;
import javafx.util.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerControllers {
    private final String filename;
    private final String path = "customer.json";

    public CustomerControllers() {
        this.filename = "src/main/resources/data/" + path;
    }

    public ArrayList<Pair<String, Double>> getData() throws IOException {
        ArrayList<Customer> custs = this.readCustomer();
        Integer custCount = 0, vipCount = 0, memCount = 0;

        for (Customer cust : custs) {
            if(cust instanceof Member){
                memCount++;
            }
            else if(cust instanceof VIP){
                vipCount++;
            }
            else {
                custCount++;
            }
        }

        ArrayList<Pair<String, Double>> pieData = new ArrayList<Pair<String, Double>>();
        pieData.add(new Pair<String, Double>("customer", custCount.doubleValue()));
        pieData.add(new Pair<String, Double>("vip", vipCount.doubleValue()));
        pieData.add(new Pair<String, Double>("member", memCount.doubleValue()));

        return pieData;
    }

    public ArrayList<Pair<String, Double>> getLineData() {
        ArrayList<Pair<String, Double>> result = new ArrayList<>();
        try {
            ArrayList<Customer> custs = this.readCustomer();
            ArrayList<Pair<String, Double>> lineData = new ArrayList<Pair<String, Double>>();

            for (Customer cust : custs) {
                ArrayList<Purchase> purchases = cust.getTransaction();
                for (Purchase purchase : purchases) {
                    lineData.add(new Pair<>(purchase.getDate(), purchase.getBill()));
                }
            }

            Map<String, Double> map = new HashMap<>();
            for (Pair<String, Double> pair : lineData) {
                String key = pair.getKey();
                double value = pair.getValue();
                if (map.containsKey(key)) {
                    value += map.get(key);
                }
                map.put(key, value);
            }

            for (Map.Entry<String, Double> entry : map.entrySet()) {
                result.add(new Pair<>(entry.getKey(), entry.getValue()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


    public ArrayList<Customer> readCustomer() throws IOException {
        ArrayList<Customer> customers = new ArrayList<Customer>();

        try (FileReader reader = new FileReader(filename)) {
            JSONParser parser = new JSONParser();
            JSONArray customersArray = (JSONArray) parser.parse(reader);

            for (Object obj : customersArray) {
                JSONObject customerJson = (JSONObject) obj;

                String type = (String) customerJson.get("tipe");
                String id = (String) customerJson.get("idCustomer");

                JSONArray purchasesArray = (JSONArray) customerJson.get("transaction");

                ArrayList<Purchase> purchaseArrayList = new ArrayList<>();

                if (purchasesArray != null) {
                    for (Object purchaseList : purchasesArray) {
                        JSONObject purchaseJson = (JSONObject) purchaseList;

                        String customerId = (String) purchaseJson.get("customerId");
                        String date = (String) purchaseJson.get("date");
                        double bill = (Double) purchaseJson.get("bill");

                        JSONArray itemArray = (JSONArray) purchaseJson.get("itemList");

                        ArrayList<Item> itemList = new ArrayList<>();
                        for (Object itemObj : itemArray) {
                            JSONObject itemJson = (JSONObject) itemObj;
                            String name = (String) itemJson.get("name");
                            double sellPrice = (Double) itemJson.get("sellPrice");
                            double buyPrice = (Double) itemJson.get("buyPrice");
                            int quantity = ((Long) itemJson.get("quantity")).intValue();
                            String category = (String) itemJson.get("category");
                            String image = (String) itemJson.get("image");

                            int sold = ((Long) itemJson.get("sold")).intValue();
                            Item item = new Item(name, sellPrice, buyPrice, quantity, sold, category, image);

                            itemList.add(item);
                        }

                        Purchase purchase = new Purchase(customerId, date, itemList);
                        purchaseArrayList.add(purchase);
                    }

                }

                if (type.equals("customer")) {
                    Customer customer = new Customer(id);
                    customer.setTransaction(purchaseArrayList);
                    customers.add(customer);
                }
                else {
                    String name = (String) customerJson.get("nama");
                    String phoneNumber = (String) customerJson.get("noTelp");
                    double points = (Double) customerJson.get("poin");
                    boolean isActive = (Boolean) customerJson.get("isActive");


                    if (type.equals("member")) {
                        Member member = new Member(name, phoneNumber, id);
                        member.setPoin(points);
                        member.setTransaction(purchaseArrayList);
                        if (!isActive) {
                            member.statusOff();
                        }
                        customers.add(member);
                    } else if (type.equals("vip")) {
                        VIP vip = new VIP(name, phoneNumber, id);
                        vip.setPoin(points);
                        vip.setTransaction(purchaseArrayList);
                        if (!isActive) {
                            vip.statusOff();
                        }
                        customers.add(vip);
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }
}
