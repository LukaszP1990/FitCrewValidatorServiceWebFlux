package com.fitcrew.validatorservice.validator.util;

import com.fitcrew.FitCrewAppConstant.message.type.RoleType;
import com.fitcrew.jwt.model.AuthenticationRequest;

public class AuthRequestUtil {

    public static final String LOGIN = "mocked@gmail.com";
    public static final String PASSWORD = "password";

    public static AuthenticationRequest getAuthRequest(String login,
                                                       String password,
                                                       RoleType roleType) {
        return AuthenticationRequest.builder()
                .email(login)
                .password(password)
                .role(roleType)
                .build();
    }

    public static class AuthRequestData {
        private final AuthenticationRequest authRequest;
        private final boolean result;

        public AuthRequestData(AuthenticationRequest authRequest, boolean result) {
            this.authRequest = authRequest;
            this.result = result;
        }

        public AuthenticationRequest getAuthRequest() {
            return authRequest;
        }

        public boolean isResult() {
            return result;
        }
    }
}
