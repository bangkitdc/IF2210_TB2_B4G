package bnmobusinessmanagementsystem.models.plugin;

public class PaymentStates {
    private Integer discount;
    private Integer tax;
    private Integer service;

    public PaymentStates() {
        this.discount = 0;
        this.tax = 0;
        this.service = 0;
    }

    public PaymentStates(Integer discount, Integer tax, Integer service) {
        this.discount = discount;
        this.tax = tax;
        this.service = service;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public void setService(Integer service) {
        this.service = service;
    }

    public Integer getDiscount() {
        return discount;
    }

    public Integer getTax() {
        return tax;
    }

    public Integer getService() { return service; }
}
