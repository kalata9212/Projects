package com.exchange_rates.exchange_rates.dto.currency;

public class Rate {

    private String currency;
    private Double rate;

    public Rate(String currency, Double rate){
        this.setCurrency(currency);
        this.setRate(rate);
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
