package com.fitcrew.validatorservice.validator.predicates;

import com.fitcrew.validatorservice.validator.util.LoginUtil.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.fitcrew.validatorservice.validator.util.LoginUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginDtoAllValuesPredicateTest {

    private final LoginDtoAllValuesPredicate predicate = new LoginDtoAllValuesPredicate();

    static Stream<LoginData> loginDataProvider() {
        var loginDtoWithoutLogin = getLoginDto(null, PASSWORD);
        var loginDtoWithoutPassword = getLoginDto(LOGIN, null);
        var loginDtoWithAllData = getLoginDto(LOGIN, PASSWORD);

        return Stream.of(
                new LoginData(loginDtoWithoutLogin, false),
                new LoginData(loginDtoWithoutPassword, false),
                new LoginData(loginDtoWithAllData, true)
        );
    }

    @ParameterizedTest
    @MethodSource("loginDataProvider")
    void loginDtoAllValuesPredicateTest(LoginData loginData) {
        boolean result = predicate.test(loginData.getLoginDto());
        assertEquals(result, loginData.isResult());
    }
}