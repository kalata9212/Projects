package com.ecb.ecb.repository.specification;

import com.ecb.ecb.model.Rate;
import org.springframework.data.jpa.domain.Specification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class RateSpecification {

    public static Specification<Rate> getByDate(Date date) {
        return (root, cq, cb) -> cb.equal(root.get("date"), date);
    }

    public static Specification<Rate> getByCurrency(String currency) {
        return (root, cq, cb) -> cb.equal(root.get("currency"), currency);
    }

    public static Specification<Rate> getByOptionalDateAndCurrency(Optional<String> date, Optional<String> currency) {
        try {
            if (date.isPresent() && currency.isPresent()) {

                return getByDate(new SimpleDateFormat("yyyy-MM-dd").parse(date.get())).and(getByCurrency(currency.get()));

            } else if (date.isPresent() && !currency.isPresent()) {
                return getByDate(new SimpleDateFormat("yyyy-MM-dd").parse(date.get()));
            } else if (!date.isPresent() && currency.isPresent()) {
                return getByCurrency(currency.get());
            } else {
                return getByDate(new Date());
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
