package com.fitcrew.validatorservice.validator.predicates;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinMaxCharsPredicateTest {

    private static final String SINGLE_CHAR = "A";
    private static final String CHARS_LENGTH_20 = StringUtils.repeat(SINGLE_CHAR, 20);
    private static final String CHARS_LENGTH_21 = StringUtils.repeat(SINGLE_CHAR, 21);
    private static final String CHARS_LENGTH_50 = StringUtils.repeat(SINGLE_CHAR, 50);
    private static final String CHARS_LENGTH_51 = StringUtils.repeat(SINGLE_CHAR, 51);
    private static final String CHARS_LENGTH_200 = StringUtils.repeat(SINGLE_CHAR, 200);
    private static final String CHARS_LENGTH_201 = StringUtils.repeat(SINGLE_CHAR, 201);
    private static final String CHARS_LENGTH_400 = StringUtils.repeat(SINGLE_CHAR, 400);
    private static final String CHARS_LENGTH_401 = StringUtils.repeat(SINGLE_CHAR, 401);
    private final MinMaxCharsPredicate predicate = new MinMaxCharsPredicate();

    static Stream<MinMaxData> minMaxDataProvider() {
        return Stream.of(
                new MinMaxData(CHARS_LENGTH_20, 20, true),
                new MinMaxData(CHARS_LENGTH_21, 20, false),
                new MinMaxData(SINGLE_CHAR, 20, false),
                new MinMaxData(CHARS_LENGTH_50, 50, true),
                new MinMaxData(CHARS_LENGTH_51, 50, false),
                new MinMaxData(SINGLE_CHAR, 50, false),
                new MinMaxData(CHARS_LENGTH_200, 200, true),
                new MinMaxData(CHARS_LENGTH_201, 200, false),
                new MinMaxData(SINGLE_CHAR, 200, false),
                new MinMaxData(CHARS_LENGTH_400, 400, true),
                new MinMaxData(CHARS_LENGTH_401, 400, false),
                new MinMaxData(SINGLE_CHAR, 400, false),
                new MinMaxData(SINGLE_CHAR, 1, false),
                new MinMaxData(null, 1, false)
        );
    }

    @ParameterizedTest
    @MethodSource("minMaxDataProvider")
    void minMaxCharsPredicateTest(MinMaxData minMaxData) {
        boolean result = predicate.test(minMaxData.getValue(), minMaxData.getMax());
        assertEquals(result, minMaxData.isResult());
    }

    public static class MinMaxData {
        private final String value;
        private final Integer max;
        private final boolean result;

        public MinMaxData(String value, Integer max, boolean result) {
            this.value = value;
            this.max = max;
            this.result = result;
        }

        public String getValue() {
            return value;
        }

        public Integer getMax() {
            return max;
        }

        public boolean isResult() {
            return result;
        }
    }
}