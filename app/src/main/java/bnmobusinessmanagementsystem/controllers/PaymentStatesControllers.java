package bnmobusinessmanagementsystem.controllers;

import bnmobusinessmanagementsystem.models.plugin.PaymentStates;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PaymentStatesControllers {
    private final String filename;
    private final String path = "paymentStates.json";

    public PaymentStatesControllers() {
        this.filename = "src/main/resources/data/" + path;
    }

    public PaymentStates getCurrentStates() throws IOException {
        PaymentStates currentStates = new PaymentStates();
        try (FileReader reader = new FileReader(filename)) {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONObject currentStateJson = (JSONObject) jsonObject.get("currentStates");

            Integer currentStateDiscount = ((Long) currentStateJson.get("discount")).intValue();
            Integer currentStateTax = ((Long) currentStateJson.get("tax")).intValue();
            Integer currentStateService = ((Long) currentStateJson.get("service")).intValue();

            currentStates.setDiscount(currentStateDiscount);
            currentStates.setTax(currentStateTax);
            currentStates.setService(currentStateService);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return currentStates;
    }

    public void updateCurrentStates(PaymentStates newStates) throws IOException, ParseException {
        // Read the existing JSON data
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(filename);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);

        // Update the current state object with the new current rate
        JSONObject currentStates = (JSONObject) jsonObject.get("currentStates");
        currentStates.put("discount", newStates.getDiscount());
        currentStates.put("tax", newStates.getTax());
        currentStates.put("service", newStates.getService());

        // Write the updated JSON data to the file
        FileWriter fileWriter = new FileWriter(filename);
        fileWriter.write(jsonObject.toJSONString());
        fileWriter.flush();
        fileWriter.close();
    }
}
