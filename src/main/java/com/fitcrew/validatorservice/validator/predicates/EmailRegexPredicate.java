package com.fitcrew.validatorservice.validator.predicates;

import java.util.Objects;
import java.util.function.Predicate;

public class EmailRegexPredicate implements Predicate<String> {
    private static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    @Override
    public boolean test(String email) {
        return Objects.nonNull(email) && email.matches(EMAIL_REGEX);
    }
}
