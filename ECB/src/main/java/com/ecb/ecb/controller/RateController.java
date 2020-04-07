package com.ecb.ecb.controller;

import com.ecb.ecb.service.CurrencyRatesService;
import com.ecb.ecb.service.EcbIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class RateController {

    @Autowired
    private EcbIntegrationService ecbIntegrationService;

    @Autowired
    private CurrencyRatesService currencyRatesService;


    @PutMapping("/sync")
    public ResponseEntity syncOnDemand() {
        ecbIntegrationService.syncData();
        return ResponseEntity.ok("Sync operation has been triggered.");
    }

    @GetMapping("/rates")
    public ResponseEntity getCurrencies(@RequestParam Optional<String> date, @RequestParam Optional<String> currency) {
        List list = currencyRatesService.getCurrencyRates(date, currency);
        return ResponseEntity.ok(list);
    }
}
