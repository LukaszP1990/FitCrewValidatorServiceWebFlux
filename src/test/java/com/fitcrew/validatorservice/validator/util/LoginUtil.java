package com.fitcrew.validatorservice.validator.util;

import com.fitcrew.FitCrewAppModel.domain.dto.LoginDto;

public class LoginUtil {

    public static final String LOGIN = "mocked@gmail.com";
    public static final String PASSWORD = "password";

    public static LoginDto getLoginDto(String login, String password) {
        return LoginDto.builder()
                .email(login)
                .password(password)
                .build();
    }

    public static class LoginData{
        private final LoginDto loginDto;
        private final boolean result;

        public LoginData(LoginDto loginDto, boolean result) {
            this.loginDto = loginDto;
            this.result = result;
        }

        public LoginDto getLoginDto() {
            return loginDto;
        }

        public boolean isResult() {
            return result;
        }
    }
}
