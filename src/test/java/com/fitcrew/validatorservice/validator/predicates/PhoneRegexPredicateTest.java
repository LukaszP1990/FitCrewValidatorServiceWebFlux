package com.fitcrew.validatorservice.validator.predicates;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PhoneRegexPredicateTest {

    private final PhoneRegexPredicate predicate = new PhoneRegexPredicate();

    static Stream<PhoneData> phoneDataProvider() {
        return Stream.of(
                new PhoneData("493847503", true),
                new PhoneData("493-847-503", false),
                new PhoneData("493 847 503", false),
                new PhoneData("49384750M", false),
                new PhoneData("4938475033", false),
                new PhoneData("4938475", false)
        );
    }

    @ParameterizedTest
    @MethodSource("phoneDataProvider")
    void testPhoneRegexPredicate(PhoneData phoneData) {
        boolean result = predicate.test(phoneData.getPhone());
        assertEquals(result, phoneData.isResult());
    }

    public static class PhoneData {
        private final String phone;
        private final boolean result;

        public PhoneData(String phone, boolean result) {
            this.phone = phone;
            this.result = result;
        }

        public String getPhone() {
            return phone;
        }

        public boolean isResult() {
            return result;
        }
    }
}