package com.exchange_rates.exchange_rates.service.impl;

import com.exchange_rates.exchange_rates.dto.currency.Rate;
import com.exchange_rates.exchange_rates.exception.CurrencyExchangeException;
import com.exchange_rates.exchange_rates.service.CurrencyExchangeRateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class CurrencyExchangeRateServiceImpl implements CurrencyExchangeRateService {

    @Value("${currency.exchange.url}")
    private String url;

    @Value("${currency.exchange.key}")
    private String key;

    @Autowired
    RestTemplate client;

    @Override
    public Rate exchangeRate(String baseCurrency, String targetCurrencies) throws CurrencyExchangeException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("q", baseCurrency+"_"+targetCurrencies)
                .queryParam("compact","ultra")
                .queryParam("apiKey",key);

        ResponseEntity<String> resp = client.exchange(builder.build().toUri(), HttpMethod.GET, null, String.class);

        if (HttpStatus.OK == resp.getStatusCode()) {

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode jsonNode = objectMapper.readTree(resp.getBody());
                return new Rate(targetCurrencies,jsonNode.findValue(baseCurrency+"_"+targetCurrencies).asDouble());
            } catch (JsonProcessingException e) {
                throw new CurrencyExchangeException();
            }

        } else {
            throw new CurrencyExchangeException();
        }
    }
}
