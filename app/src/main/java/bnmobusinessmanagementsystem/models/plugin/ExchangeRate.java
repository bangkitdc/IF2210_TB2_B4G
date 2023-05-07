package bnmobusinessmanagementsystem.models.plugin;

public class ExchangeRate {
    private String name;
    private Double rate;

    public ExchangeRate() {
        this.name = "";
        this.rate = 0.0;
    }

    public ExchangeRate(String name, Double rate) {
        this.name = name;
        this.rate = rate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public Double getRate() {
        return rate;
    }
}
