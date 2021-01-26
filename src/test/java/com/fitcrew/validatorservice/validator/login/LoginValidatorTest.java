package com.fitcrew.validatorservice.validator.login;

import com.fitcrew.validatorservice.validator.util.LoginUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.fitcrew.validatorservice.validator.util.LoginUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginValidatorTest {

    private final LoginValidator loginValidator = new LoginValidator();

    static Stream<LoginUtil.LoginData> loginDataProvider() {
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
    void validateTest(LoginData loginData) {
        //when
        var result = loginValidator.validate(loginData.getLoginDto());

        //then
        checkAssertion(result.isValid(), loginData.isResult());
    }

    private void checkAssertion(boolean result,
                                boolean expected) {
        assertEquals(expected, result);
    }
}