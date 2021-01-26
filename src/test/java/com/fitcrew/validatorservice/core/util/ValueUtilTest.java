package com.fitcrew.validatorservice.core.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ValueUtilTest {

    private static final String ONE = "1";

    static Stream<ArrayList<Object>> stringDataProvider() {
        var listWithoutNull = new ArrayList<>();
        listWithoutNull.add(ONE);
        listWithoutNull.add(ONE);

        var listWithNull = new ArrayList<>();
        listWithNull.add(null);
        listWithNull.add(ONE);

        return Stream.of(listWithoutNull, listWithNull);
    }

    @ParameterizedTest
    @MethodSource("stringDataProvider")
    void validateStrings(List<String> value) {
        var result = ValueUtil.validateStrings(value);
        checkAssertion(result);
    }

    @ParameterizedTest
    @MethodSource("stringDataProvider")
    void validateObjects(List<Object> value) {
        var result = ValueUtil.validateObjects(value);
        checkAssertion(result);
    }

    private void checkAssertion(boolean result) {
        if (result) {
            assertTrue(result);
        } else {
            assertFalse(result);
        }
    }
}