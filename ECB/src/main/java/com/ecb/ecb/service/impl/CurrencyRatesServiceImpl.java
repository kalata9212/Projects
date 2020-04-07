package com.ecb.ecb.service.impl;

import com.ecb.ecb.adapter.CurrencyRateAdapter;
import com.ecb.ecb.model.Rate;
import com.ecb.ecb.repository.RateRepository;
import com.ecb.ecb.repository.specification.RateSpecification;
import com.ecb.ecb.service.CurrencyRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CurrencyRatesServiceImpl implements CurrencyRatesService {

    @Autowired
    private RateRepository repository;

    public List getCurrencyRates(Optional<String> date, Optional<String> currency) {

        return repository.findAll(RateSpecification.getByOptionalDateAndCurrency(date, currency))
                .stream()
                .map((Function<Rate, Object>) CurrencyRateAdapter::fromEntityToDto)
                .collect(Collectors.toList());
    }
}
