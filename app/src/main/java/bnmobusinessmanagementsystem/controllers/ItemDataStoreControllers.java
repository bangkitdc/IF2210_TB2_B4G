package bnmobusinessmanagementsystem.controllers;

import bnmobusinessmanagementsystem.models.Item;
import javafx.util.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ItemDataStoreControllers {

    private final String filename;
    private final String path = "itemDataStore.json";

    public ItemDataStoreControllers() {
        this.filename = "src/main/resources/data/" + path;
    }
    public ArrayList<Pair<String, Double>> getBarData() throws IOException {
        ArrayList<Item> items = this.readItems();
        ArrayList<Pair<String, Double>> barData = new ArrayList<Pair<String, Double>>();

        for (Item item : items) {
            Integer soldItem = item.getSold();
            barData.add(new Pair<>(item.getName(), soldItem.doubleValue()));
        }

        return barData;
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
                int sold = ((Long) itemJson.get("sold")).intValue();

                Item item = new Item(name, sellPrice, buyPrice, quantity, sold, category, image);
                items.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }
}
