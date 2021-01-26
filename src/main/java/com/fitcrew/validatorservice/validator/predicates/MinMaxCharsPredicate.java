package com.fitcrew.validatorservice.validator.predicates;

import java.util.Objects;
import java.util.function.BiPredicate;

import static io.vavr.API.*;

public class MinMaxCharsPredicate implements BiPredicate<String, Integer> {

    @Override
    public boolean test(String value,
                        Integer max) {
        return Match(max).of(
                Case($(20), Objects.nonNull(value) && value.length() >= 2 && value.length() <= 20),
                Case($(50), Objects.nonNull(value) && value.length() >= 2 && value.length() <= 50),
                Case($(200), Objects.nonNull(value) && value.length() >= 2 && value.length() <= 200),
                Case($(400), Objects.nonNull(value) && value.length() >= 2 && value.length() <= 400),
                Case($(), false));
    }
}
