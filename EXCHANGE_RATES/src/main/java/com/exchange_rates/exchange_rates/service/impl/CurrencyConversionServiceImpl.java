package com.exchange_rates.exchange_rates.service.impl;

import com.exchange_rates.exchange_rates.dto.conversion.ConversionDTO;
import com.exchange_rates.exchange_rates.exception.CurrencyExchangeException;
import com.exchange_rates.exchange_rates.model.Conversion;
import com.exchange_rates.exchange_rates.repository.ConversionRepository;
import com.exchange_rates.exchange_rates.service.CurrencyConversionService;
import com.exchange_rates.exchange_rates.service.CurrencyExchangeRateService;
import com.exchange_rates.exchange_rates.dto.currency.Rate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CurrencyConversionServiceImpl implements CurrencyConversionService {

    @Autowired
    CurrencyExchangeRateService currencyExchangeRateService;

    @Autowired
    ConversionRepository conversionRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ConversionDTO currencyConversion(Double sourceAmount, String sourceCurrency, String targetCurrency) {
        Rate rate = null;
        ConversionDTO conversionDTO = new ConversionDTO();
        try {
            rate = currencyExchangeRateService.exchangeRate(sourceCurrency, targetCurrency);
            Double amount = sourceAmount * rate.getRate();
            conversionDTO.setAmount(amount);
            conversionDTO.setCurrency(targetCurrency);
            Conversion conversion = fromDtoToEntity(conversionDTO);

            conversionRepository.saveAndFlush(conversion);
        } catch (CurrencyExchangeException e) {
            e.printStackTrace();
        }
        return conversionDTO;
    }

    private Conversion fromDtoToEntity(ConversionDTO conversionDTO){
        Conversion conversion = modelMapper.map(conversionDTO, Conversion.class);
        return conversion;
    }

    private ConversionDTO fromEntityToDto(Conversion conversion) {
        ConversionDTO conversionDTO = modelMapper.map(conversion, ConversionDTO.class);
        return conversionDTO;
    }
}
