package com.exchange_rates.exchange_rates;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ExchangeRatesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExchangeRatesApplication.class, args);
    }

    @Bean
    RestTemplate client() {
        return new RestTemplate();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
