package com.exchange_rates.exchange_rates.controller;

import com.exchange_rates.exchange_rates.dto.conversion.ConversionDTO;
import com.exchange_rates.exchange_rates.dto.currency.Rate;
import com.exchange_rates.exchange_rates.exception.CurrencyExchangeException;
import com.exchange_rates.exchange_rates.service.CurrencyConversionService;
import com.exchange_rates.exchange_rates.service.CurrencyExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class ExchangeRateController {

    @Autowired
    CurrencyExchangeRateService currencyExchangeRateService;

    @Autowired
    CurrencyConversionService currencyConversionService;

    @GetMapping("/rates")
    public ResponseEntity<Rate> exchangeRates(@RequestParam(name = "fromCurr", required = true) String fromCurr,
                                              @RequestParam(name = "toCurr", required = true) String toCurr) throws CurrencyExchangeException {
        return new ResponseEntity<>(currencyExchangeRateService.exchangeRate(fromCurr, toCurr), HttpStatus.OK);
    }

    @GetMapping("/conversion")
    public ResponseEntity<ConversionDTO> conversion(@RequestParam(name = "sourceAmount", required = true) Double sourceAmount,
                                                    @RequestParam(name = "sourceCurrency", required = true) String sourceCurrency,
                                                    @RequestParam(name = "targetCurrency", required = true) String targetCurrency){
        return new ResponseEntity<>(currencyConversionService.currencyConversion(sourceAmount, sourceCurrency, targetCurrency), HttpStatus.OK);
    }
}
