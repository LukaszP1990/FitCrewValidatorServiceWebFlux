package com.fitcrew.validatorservice.validator.util;

import com.fitcrew.FitCrewAppModel.domain.dto.ClientDto;

import java.util.Optional;

public class ClientUtil {

    public static final String CLIENT_ID = "1";
    public static final String CLIENT_EMAIL = "mockedClient@gmail.com";
    public static final String CLIENT_FIRST_NAME = "firstName";
    public static final String CLIENT_LAST_NAME = "lastName";
    public static final String CLIENT_DATE_OF_BIRTH = "01.01.1990";
    public static final String CLIENT_PHONE_NUMBER = "501928341";
    public static final String CLIENT_ENCRYPTED_PASSWORD = "$2y$12$Y3QFw.tzF7OwIJGlpzk9s.5Ymq4zY3hItIkD0Xes3UWxBo2SkEgei";
    public static final String PASSWORD = "password";

    public static ClientDto getClientDto(String clientId,
                                         String firstName,
                                         String lastName,
                                         String dateOfBirth,
                                         String password,
                                         String phoneNumber,
                                         String email) {
        return ClientDto.builder()
                .clientId(clientId)
                .firstName(firstName)
                .lastName(lastName)
                .dateOfBirth(dateOfBirth)
                .password(password)
                .encryptedPassword(CLIENT_ENCRYPTED_PASSWORD)
                .email(getEmailValue(email))
                .phone(phoneNumber)
                .build();
    }

    private static String getEmailValue(String email) {
        return Optional.ofNullable(email)
                .map(value -> String.valueOf(1).concat(value))
                .orElse(null);
    }

    public static class ClientData{
        private final ClientDto clientDto;
        private final boolean result;

        public ClientData(ClientDto clientDto, boolean result) {
            this.clientDto = clientDto;
            this.result = result;
        }

        public ClientDto getClientDto() {
            return clientDto;
        }

        public boolean isResult() {
            return result;
        }
    }
}
