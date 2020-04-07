package com.ecb.ecb.service;

import java.util.List;
import java.util.Optional;

public interface CurrencyRatesService {

    List getCurrencyRates(Optional<String> date, Optional<String> currency);
}
