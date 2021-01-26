package com.fitcrew.validatorservice.validator.util;

import com.fitcrew.FitCrewAppModel.domain.dto.AdminDto;

import java.util.Optional;

public class AdminUtil {

    public static final String ADMIN_ID = "1";
    public static final String ADMIN_EMAIL = "mockedAdmin@gmail.com";
    public static final String ADMIN_FIRST_NAME = "firstName";
    public static final String ADMIN_LAST_NAME = "lastName";
    public static final String ADMIN_ENCRYPTED_PASSWORD = "$2y$12$Y3QFw.tzF7OwIJGlpzk9s.5Ymq4zY3hItIkD0Xes3UWxBo2SkEgei";
    public static final String PASSWORD = "password";

    public static AdminDto getAdminDto(String adminId,
                                       String email,
                                       String firstName,
                                       String lastName,
                                       String password) {
        String emailValue = getEmailValue(email);
        return AdminDto.builder()
                .adminId(adminId)
                .email(emailValue)
                .firstName(firstName)
                .lastName(lastName)
                .password(password)
                .encryptedPassword(ADMIN_ENCRYPTED_PASSWORD)
                .build();
    }

    private static String getEmailValue(String email) {
        return Optional.ofNullable(email)
                .map(value -> String.valueOf(1).concat(value))
                .orElse(null);
    }

    public static class AdminData{
        private final AdminDto adminDto;
        private final boolean result;

        public AdminData(AdminDto adminDto, boolean result) {
            this.adminDto = adminDto;
            this.result = result;
        }

        public AdminDto getAdminDto() {
            return adminDto;
        }

        public boolean isResult() {
            return result;
        }
    }
}
