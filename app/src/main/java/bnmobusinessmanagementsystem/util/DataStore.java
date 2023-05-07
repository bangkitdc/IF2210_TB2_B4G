package bnmobusinessmanagementsystem.util;

import java.io.*;

import bnmobusinessmanagementsystem.models.customer.Member;
import bnmobusinessmanagementsystem.models.customer.VIP;
import org.json.simple.*;
import org.json.simple.JSONArray;
import org.json.simple.parser.*;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.List;
import bnmobusinessmanagementsystem.models.customer.Customer;
import bnmobusinessmanagementsystem.models.Item;

public class DataStore {
    private final String filename;

    public DataStore(String filename) {
        this.filename = "app/src/main/resources/data/" +  filename;
    }


    public void saveCustomer(ArrayList<Customer> customers) {

        JSONArray customerArray = new JSONArray();

        for (Customer customer : customers) {
            JSONObject customerObject = new JSONObject();

            if (customer instanceof Member member) {
                customerObject.put("tipe", "member");
                customerObject.put("idCustomer", member.getCustomerId());
                customerObject.put("nama", member.getNama());
                customerObject.put("noTelp", member.getNoTelp());
                customerObject.put("poin", member.getPoin());
                customerObject.put("isActive", member.isActive());
            } else if (customer instanceof VIP vip) {
                customerObject.put("tipe", "vip");
                customerObject.put("idCustomer", vip.getCustomerId());
                customerObject.put("nama", vip.getNama());
                customerObject.put("noTelp", vip.getNoTelp());
                customerObject.put("poin", vip.getPoin());
                customerObject.put("isActive", vip.isActive());
            } else {
                customerObject.put("tipe", "customer");
                customerObject.put("id", customer.getCustomerId());
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

    public ArrayList<Customer> readCustomer() throws IOException {
        ArrayList<Customer> customers = new ArrayList<Customer>();

        try (FileReader reader = new FileReader(filename)) {
            JSONParser parser = new JSONParser();
            JSONArray customersArray = (JSONArray) parser.parse(reader);

            for (Object obj : customersArray) {
                JSONObject customerJson = (JSONObject) obj;

                String type = (String) customerJson.get("tipe");
                String id = (String) customerJson.get("idCustomer");

                if (type.equals("customer")) {
                    Customer customer = new Customer(id);
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
                        if (!isActive) {
                            member.statusOff();
                        }
                        customers.add(member);
                    } else if (type.equals("vip")) {
                        VIP vip = new VIP(name, phoneNumber, id);
                        vip.setPoin(points);
                        if (!isActive) {
                            vip.statusOff();
                        }
                        customers.add(vip);
                    }
                }


            }
        } catch (Exception e) {
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

}
