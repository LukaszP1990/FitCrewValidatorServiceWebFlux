package com.fitcrew.validatorservice.validator.authentication;

import com.fitcrew.FitCrewAppConstant.message.type.RoleType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.fitcrew.validatorservice.validator.util.AuthRequestUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthRequestValidatorTest {

    private final AuthRequestValidator authRequestValidator = new AuthRequestValidator();

    static Stream<AuthRequestData> authRequestDataProvider() {
        var authRequestWithoutLogin = getAuthRequest(null, PASSWORD, RoleType.ROLE_TRAINER);
        var authRequestWithoutPassword = getAuthRequest(LOGIN, null, RoleType.ROLE_TRAINER);
        var authRequestWithoutRole = getAuthRequest(LOGIN, PASSWORD, null);
        var authRequestWithAllData = getAuthRequest(LOGIN, PASSWORD, RoleType.ROLE_TRAINER);

        return Stream.of(
                new AuthRequestData(authRequestWithoutLogin, false),
                new AuthRequestData(authRequestWithoutPassword, false),
                new AuthRequestData(authRequestWithoutRole, false),
                new AuthRequestData(authRequestWithAllData, true)
        );
    }

    @ParameterizedTest
    @MethodSource("authRequestDataProvider")
    void validateTest(AuthRequestData authRequestData) {
        //when
        var result = authRequestValidator.validate(authRequestData.getAuthRequest());

        //then
        checkAssertion(result.isValid(), authRequestData.isResult());
    }

    private void checkAssertion(boolean result,
                                boolean expected) {
        assertEquals(expected, result);
    }
}