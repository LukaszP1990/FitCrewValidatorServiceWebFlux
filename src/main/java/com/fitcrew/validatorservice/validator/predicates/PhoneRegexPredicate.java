package com.fitcrew.validatorservice.validator.predicates;

import java.util.Objects;
import java.util.function.Predicate;

public class PhoneRegexPredicate implements Predicate<String> {
    private static final String PHONE_REGEX = "[0-9]{9}";

    @Override
    public boolean test(String phoneNumber) {
        return Objects.nonNull(phoneNumber) && phoneNumber.matches(PHONE_REGEX);
    }
}
