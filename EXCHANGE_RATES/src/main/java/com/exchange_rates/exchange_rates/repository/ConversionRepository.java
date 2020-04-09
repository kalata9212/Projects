package com.exchange_rates.exchange_rates.repository;

import com.exchange_rates.exchange_rates.model.Conversion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversionRepository extends JpaRepository<Conversion, Long> { }
