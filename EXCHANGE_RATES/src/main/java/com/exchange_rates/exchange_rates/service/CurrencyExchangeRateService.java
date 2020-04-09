package com.exchange_rates.exchange_rates.service;

import com.exchange_rates.exchange_rates.dto.currency.Rate;
import com.exchange_rates.exchange_rates.exception.CurrencyExchangeException;

public interface CurrencyExchangeRateService {

    Rate exchangeRate(String baseCurrency, String targetCurrencies) throws CurrencyExchangeException;
}
