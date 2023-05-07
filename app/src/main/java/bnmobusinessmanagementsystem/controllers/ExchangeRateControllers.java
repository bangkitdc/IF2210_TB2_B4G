package bnmobusinessmanagementsystem.controllers;

import bnmobusinessmanagementsystem.models.plugin.ExchangeRate;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ExchangeRateControllers {
    private final String filename;
    private final String path = "exchangeRates.json";

    public ExchangeRateControllers() {
        this.filename = "app/src/main/resources/data/" + path;
    }

    public ArrayList<ExchangeRate> readExchangeRates() throws IOException {
        ArrayList<ExchangeRate> exchangeRates = new ArrayList<ExchangeRate>();

        try (FileReader reader = new FileReader(filename)) {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray exchangeRatesArray = (JSONArray) jsonObject.get("rates");

            for (Object obj : exchangeRatesArray) {
                JSONObject customerJson = (JSONObject) obj;

                String name = (String) customerJson.get("name");
                Double rate = (Double) customerJson.get("rate");

                ExchangeRate exchangeRate = new ExchangeRate(name, rate);
                exchangeRates.add(exchangeRate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return exchangeRates;
    }

    public ExchangeRate getCurrentRate() throws IOException {
        ExchangeRate currentRate = new ExchangeRate();
        try (FileReader reader = new FileReader(filename)) {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONObject currentStateJson = (JSONObject) jsonObject.get("currentRates");

            String currentStateName = (String) currentStateJson.get("name");
            Double currentStateRate = (Double) currentStateJson.get("rate");
            currentRate.setName(currentStateName);
            currentRate.setRate(currentStateRate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return currentRate;
    }

    public void updateCurrentRate(ExchangeRate newCurrentRate) throws IOException, ParseException {
        // Read the existing JSON data
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(filename);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);

        // Update the current state object with the new current rate
        JSONObject currentRates = (JSONObject) jsonObject.get("currentRates");
        currentRates.put("name", newCurrentRate.getName());
        currentRates.put("rate", newCurrentRate.getRate());

        // Write the updated JSON data to the file
        FileWriter fileWriter = new FileWriter(filename);
        fileWriter.write(jsonObject.toJSONString());
        fileWriter.flush();
        fileWriter.close();
    }

    public Double getRate(String rateName) throws IOException, ParseException {
        ArrayList<ExchangeRate> exchangeRates = new ArrayList<ExchangeRate>();
        Double rateRes = 0.0;

        try (FileReader reader = new FileReader(filename)) {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray exchangeRatesArray = (JSONArray) jsonObject.get("rates");

            for (Object obj : exchangeRatesArray) {
                JSONObject customerJson = (JSONObject) obj;

                String name = (String) customerJson.get("name");
                Double rate = (Double) customerJson.get("rate");

                if (name.equals(rateName)) {
                    rateRes = rate;
                    break;
                }

                ExchangeRate exchangeRate = new ExchangeRate(name, rate);
                exchangeRates.add(exchangeRate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rateRes;
    }
}
