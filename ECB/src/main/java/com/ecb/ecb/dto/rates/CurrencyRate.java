package com.ecb.ecb.dto.rates;

public class CurrencyRate {

    private Long id;

    private String date;

    private String currency;

    private Double rate;

    public CurrencyRate() {}

    public CurrencyRate(Long id, String date, String currency, Double rate) {
        this.id = id;
        this.date = date;
        this.currency = currency;
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
