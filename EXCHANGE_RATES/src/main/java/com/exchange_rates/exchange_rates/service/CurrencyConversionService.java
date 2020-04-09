package com.exchange_rates.exchange_rates.service;

import com.exchange_rates.exchange_rates.dto.conversion.ConversionDTO;

public interface CurrencyConversionService {

    ConversionDTO currencyConversion(Double sourceAmount, String sourceCurrency, String targetCurrency);
}
