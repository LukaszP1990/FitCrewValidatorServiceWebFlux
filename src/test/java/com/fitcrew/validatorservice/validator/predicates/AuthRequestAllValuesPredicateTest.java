package com.fitcrew.validatorservice.validator.predicates;

import com.fitcrew.FitCrewAppConstant.message.type.RoleType;
import com.fitcrew.validatorservice.validator.util.AuthRequestUtil.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.fitcrew.validatorservice.validator.util.AuthRequestUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthRequestAllValuesPredicateTest {

    private final AuthRequestAllValuesPredicate predicate = new AuthRequestAllValuesPredicate();

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
    void authRequestAllValuesPredicateTest(AuthRequestData authRequestData) {
        boolean result = predicate.test(authRequestData.getAuthRequest());
        assertEquals(result, authRequestData.isResult());
    }
}