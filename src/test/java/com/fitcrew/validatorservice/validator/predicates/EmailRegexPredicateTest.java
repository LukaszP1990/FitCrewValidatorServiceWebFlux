package com.fitcrew.validatorservice.validator.predicates;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.fitcrew.validatorservice.validator.util.EmailUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class EmailRegexPredicateTest {

    private final EmailRegexPredicate predicate = new EmailRegexPredicate();

    static Stream<RegexData> regexDataProvider() {
        return Stream.of(
                new RegexData("user@domain.com", true),
                new RegexData("user@domain.co.in", true),
                new RegexData("user.name@domain.com", true),
                new RegexData("user_name@domain.com", true),
                new RegexData("username@yahoo.corporate.in", true),
                new RegexData(".username@yahoo.com", false),
                new RegexData("username@yahoo.com.", false),
                new RegexData("username@yahoo..com", false),
                new RegexData("username@yahoo.c", false),
                new RegexData("username@yahoo.corporate", false),
                new RegexData(null, false)
        );
    }

    @ParameterizedTest
    @MethodSource("regexDataProvider")
    void emailRegexPredicateTest(RegexData regexData) {
        boolean result = predicate.test(regexData.getEmail());
        assertEquals(result, regexData.isResult());
    }
}