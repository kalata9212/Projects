package com.ecb.ecb.adapter;

import com.ecb.ecb.dto.rates.CurrencyRate;
import com.ecb.ecb.model.Rate;

import java.text.SimpleDateFormat;

public class CurrencyRateAdapter {

    public static CurrencyRate fromEntityToDto(Rate rate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return new CurrencyRate(rate.getId(), sdf.format(rate.getDate()), rate.getCurrency(), rate.getRate());
    }
}
