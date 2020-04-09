package com.exchange_rates.exchange_rates.dto.conversion;

public class ConversionDTO {

    private Long transactionId;
    private Double amount;
    private String currency;

    public ConversionDTO(){}

    public ConversionDTO(Double amount, String currency, Long transactionId){
        this.transactionId = transactionId;
        this.amount = amount;
        this.currency = currency;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
