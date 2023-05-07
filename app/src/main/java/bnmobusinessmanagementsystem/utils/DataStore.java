package bnmobusinessmanagementsystem.utils;

import java.io.*;

import bnmobusinessmanagementsystem.models.customer.*;
import bnmobusinessmanagementsystem.models.Item;
//import bnmobusinessmanagementsystem.models.customer.VIP;
import org.json.simple.JSONArray;
import org.json.simple.parser.*;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.text.ParseException;
//import bnmobusinessmanagementsystem.models.customer.Customer;


public class DataStore {
    private final String filename;

    public DataStore(String filename) {
        this.filename = "app/src/main/resources/data/" +  filename;
    }


    public void saveCustomer(ArrayList<Customer> customers) {

        JSONArray customerArray = new JSONArray();

        for (Customer customer : customers) {
            JSONObject customerObject = new JSONObject();

            customerObject.put("idCustomer", customer.getCustomerId());
            JSONArray transactionArray = new JSONArray();
            for (Purchase purchase : customer.getTransaction()) {
                JSONObject purchaseObject = new JSONObject();

                purchaseObject.put("customerId", purchase.getCustomerId());
                purchaseObject.put("date", purchase.getDate());
                purchaseObject.put("bill", purchase.getBill());

                JSONArray itemArray = new JSONArray();
                for (Item item : purchase.getItemList()) {
                    JSONObject itemObject = new JSONObject();

                    itemObject.put("name", item.getName());
                    itemObject.put("sellPrice", item.getSellPrice());
                    itemObject.put("buyPrice", item.getBuyPrice());
                    itemObject.put("quantity", item.getQuantity());
                    itemObject.put("category", item.getCategory());
                    itemObject.put("image", item.getImage());

                    itemArray.add(itemObject);
                }

                purchaseObject.put("itemList", itemArray);

                transactionArray.add(purchaseObject);
            }
            customerObject.put("transaction", transactionArray);

            if (customer instanceof Member member) {
                customerObject.put("tipe", "member");
                customerObject.put("nama", member.getNama());
                customerObject.put("noTelp", member.getNoTelp());
                customerObject.put("poin", member.getPoin());
                customerObject.put("isActive", member.isActive());
            } else if (customer instanceof VIP vip) {
                customerObject.put("tipe", "vip");
                customerObject.put("nama", vip.getNama());
                customerObject.put("noTelp", vip.getNoTelp());
                customerObject.put("poin", vip.getPoin());
                customerObject.put("isActive", vip.isActive());
            } else {
                customerObject.put("tipe", "customer");
            }

            customerArray.add(customerObject);
        }

        try (FileWriter file = new FileWriter(filename)) {
            file.write(customerArray.toString());
            System.out.println("Data has been saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error: Unable to save data to " + filename);
            e.printStackTrace();
        }
    }

    public void addCustomer(Customer customer) {
        try {
            // Membaca file JSON
            FileReader reader = new FileReader(filename);
            JSONParser parser = new JSONParser();
            JSONArray customersArray = (JSONArray) parser.parse(reader);
            int len = customersArray.toArray().length;
            reader.close();

            // Membuat objek JSON dari data customer
            JSONObject customerObject = new JSONObject();


            if (customer instanceof Member member) {
                customerObject.put("tipe", "member");
                customerObject.put("idCustomer", member.getCustomerId() + len);
                customerObject.put("nama", member.getNama());
                customerObject.put("noTelp", member.getNoTelp());
                customerObject.put("poin", member.getPoin());
                customerObject.put("isActive", member.isActive());
            } else if (customer instanceof VIP vip) {
                customerObject.put("tipe", "vip");
                customerObject.put("idCustomer", vip.getCustomerId() + len);
                customerObject.put("nama", vip.getNama());
                customerObject.put("noTelp", vip.getNoTelp());
                customerObject.put("poin", vip.getPoin());
                customerObject.put("isActive", vip.isActive());
            } else {
                customerObject.put("tipe", "customer");
                customerObject.put("id", customer.getCustomerId());
            }

            // Menambahkan objek JSON baru ke dalam array JSON
            customersArray.add(customerObject);

            // Menulis kembali file JSON dengan objek baru yang telah ditambahkan
            FileWriter writer = new FileWriter(filename);
            writer.write(customersArray.toJSONString());
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomerById(String id) throws IOException, ParseException {
        try (FileReader reader = new FileReader(filename)) {
            JSONParser parser = new JSONParser();
            JSONArray customersArray = (JSONArray) parser.parse(reader);

            for (int i = 0; i < customersArray.size(); i++) {
                JSONObject customerJson = (JSONObject) customersArray.get(i);

                if (customerJson.get("idCustomer").equals(id)) {
                    customersArray.remove(i);
                    break;
                }
            }

            try (FileWriter writer = new FileWriter(filename)) {
                writer.write(customersArray.toJSONString());
                System.out.println("Customer with id " + id + " has been deleted.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                        Item item = new Item(name, sellPrice, buyPrice, quantity, category, image);
                        itemList.add(item);
                    }

                    Purchase purchase = new Purchase(customerId, date, itemList);
                    purchasesArray.add(purchase);

                if (type.equals("customer")) {
                    Customer customer = new Customer(id);
                    customer.setTransaction(purchasesArray);
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
                        member.setTransaction(purchasesArray);
                        if (!isActive) {
                            member.statusOff();
                        }
                        customers.add(member);
                    } else if (type.equals("vip")) {
                        VIP vip = new VIP(name, phoneNumber, id);
                        vip.setPoin(points);
                        vip.setTransaction(purchasesArray);
                        if (!isActive) {
                            vip.statusOff();
                        }
                        customers.add(vip);
                    }
                }
            }
        }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    public Customer getCustomerById(String id) throws IOException {
        Customer customer = null;

        try (FileReader reader = new FileReader(filename)) {
            JSONParser parser = new JSONParser();
            JSONArray customersArray = (JSONArray) parser.parse(reader);

            for (Object obj : customersArray) {
                JSONObject customerJson = (JSONObject) obj;

                if ((customerJson.get("idCustomer")).equals(id)) {
                    String type = (String) customerJson.get("tipe");

                    if (type.equals("customer")) {
                        customer = new Customer(id);
                    }
                    else {
                        String name = (String) customerJson.get("nama");
                        String phoneNumber = (String) customerJson.get("noTelp");
                        double points = (Double) customerJson.get("poin");
                        boolean isActive = (Boolean) customerJson.get("isActive");


                        if (type.equals("member")) {
                            Member member = new Member(name, phoneNumber, id);
                            member.setPoin(points);
                            if (!isActive) {
                                member.statusOff();
                            }
                            customer = member;

                        } else if (type.equals("vip")) {
                            VIP vip = new VIP(name, phoneNumber, id);
                            vip.setPoin(points);
                            if (!isActive) {
                                vip.statusOff();
                            }
                            customer = vip;
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customer;
    }

    public void saveItem(ArrayList<Item> items) {

        JSONArray itemsArray = new JSONArray();

        for (Item item : items) {
            JSONObject itemObject = new JSONObject();

            itemObject.put("name", item.getName());
            itemObject.put("sellPrice", item.getSellPrice());
            itemObject.put("buyPrice", item.getBuyPrice());
            itemObject.put("quantity", item.getQuantity());
            itemObject.put("category", item.getCategory());
            itemObject.put("image", item.getImage());

            itemsArray.add(itemObject);
        }

        try (FileWriter file = new FileWriter(filename)) {
            file.write(itemsArray.toString());
            System.out.println("Data has been saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error: Unable to save data to " + filename);
            e.printStackTrace();
        }
    }

    public void addItem(Item item) {
        try {
            // Membaca file JSON
            FileReader reader = new FileReader(filename);
            JSONParser parser = new JSONParser();
            JSONArray itemsArray = (JSONArray) parser.parse(reader);
            reader.close();

            // Membuat objek JSON dari data customer
            JSONObject itemObject = new JSONObject();

            itemObject.put("name", item.getName());
            itemObject.put("sellPrice", item.getSellPrice());
            itemObject.put("buyPrice", item.getBuyPrice());
            itemObject.put("quantity", item.getQuantity());
            itemObject.put("category", item.getCategory());
            itemObject.put("image", item.getImage());

            // Menambahkan objek JSON baru ke dalam array JSON
            itemsArray.add(itemObject);

            // Menulis kembali file JSON dengan objek baru yang telah ditambahkan
            FileWriter writer = new FileWriter(filename);
            writer.write(itemsArray.toJSONString());
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Item getItemByName(String name) throws IOException {
        Item item = null;

        try (FileReader reader = new FileReader(filename)) {
            JSONParser parser = new JSONParser();
            JSONArray itemsArray = (JSONArray) parser.parse(reader);

            for (Object obj : itemsArray) {
                JSONObject itemJson = (JSONObject) obj;

                if ((itemJson.get("name")).equals(name)) {
                    String nama = (String) itemJson.get("name");
                    double sellPrice = (Double) itemJson.get("sellPrice");
                    double buyPrice = (Double) itemJson.get("buyPrice");
                    int quantity = ((Long) itemJson.get("quantity")).intValue();
                    String category = (String) itemJson.get("category");
                    String image = (String) itemJson.get("image");

                    item = new Item(nama, sellPrice, buyPrice, quantity, category, image);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return item;
    }

    public void deleteItemByName(String name) throws IOException, ParseException {
        try (FileReader reader = new FileReader(filename)) {
            JSONParser parser = new JSONParser();
            JSONArray itemsArray = (JSONArray) parser.parse(reader);

            for (int i = 0; i < itemsArray.size(); i++) {
                JSONObject itemJson = (JSONObject) itemsArray.get(i);

                if (itemJson.get("name").equals(name)) {
                    itemsArray.remove(i);
                    break;
                }
            }

            try (FileWriter writer = new FileWriter(filename)) {
                writer.write(itemsArray.toJSONString());
                System.out.println("Item " + name + " has been deleted.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Item> readItems() throws IOException {
        ArrayList<Item> items = new ArrayList<Item>();

        try (FileReader reader = new FileReader(filename)) {
            JSONParser parser = new JSONParser();
            JSONArray itemsArray = (JSONArray) parser.parse(reader);

            for (Object obj : itemsArray) {
                JSONObject itemJson = (JSONObject) obj;

                String name = (String) itemJson.get("name");
                double sellPrice = (Double) itemJson.get("sellPrice");
                double buyPrice = (Double) itemJson.get("buyPrice");
                int quantity = ((Long) itemJson.get("quantity")).intValue();
                String category = (String) itemJson.get("category");
                String image = (String) itemJson.get("image");

                Item item = new Item(name, sellPrice, buyPrice, quantity, category, image);
                items.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }

    public ArrayList<Purchase> readPurchases() throws IOException {
        ArrayList<Purchase> purchases = new ArrayList<Purchase>();

        try (FileReader reader = new FileReader(filename)) {
            JSONParser parser = new JSONParser();
            JSONArray purchasesArray = (JSONArray) parser.parse(reader);

            for (Object obj : purchasesArray) {
                JSONObject purchaseJson = (JSONObject) obj;

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
                    Item item = new Item(name, sellPrice, buyPrice, quantity, category, image);
                    itemList.add(item);
                }

                Purchase purchase = new Purchase(customerId, date, itemList);
                purchases.add(purchase);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return purchases;
    }

    public void savePurchase(ArrayList<Purchase> purchases) {

        JSONArray purchasesArray = new JSONArray();

        for (Purchase purchase : purchases) {
            JSONObject purchaseObject = new JSONObject();

            purchaseObject.put("customerId", purchase.getCustomerId());
            purchaseObject.put("date", purchase.getDate());
            purchaseObject.put("bill", purchase.getBill());

            JSONArray itemArray = new JSONArray();
            for (Item item : purchase.getItemList()) {
                JSONObject itemObject = new JSONObject();

                itemObject.put("name", item.getName());
                itemObject.put("sellPrice", item.getSellPrice());
                itemObject.put("buyPrice", item.getBuyPrice());
                itemObject.put("quantity", item.getQuantity());
                itemObject.put("category", item.getCategory());
                itemObject.put("image", item.getImage());

                itemArray.add(itemObject);
            }

            purchaseObject.put("itemList", itemArray);

            purchasesArray.add(purchaseObject);
        }

        try (FileWriter file = new FileWriter(filename)) {
            file.write(purchasesArray.toString());
            System.out.println("Data has been saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error: Unable to save data to " + filename);
            e.printStackTrace();
        }
    }

}
