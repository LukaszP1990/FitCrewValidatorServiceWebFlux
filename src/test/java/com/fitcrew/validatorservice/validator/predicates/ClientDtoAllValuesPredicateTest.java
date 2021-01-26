package com.fitcrew.validatorservice.validator.predicates;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.fitcrew.validatorservice.validator.util.ClientUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientDtoAllValuesPredicateTest {

    private final ClientDtoAllValuesPredicate predicate = new ClientDtoAllValuesPredicate();

    static Stream<ClientData> clientDataProvider() {
        var clientWithoutFirstName =
                getClientDto(CLIENT_ID, null, CLIENT_LAST_NAME, CLIENT_DATE_OF_BIRTH, PASSWORD, CLIENT_PHONE_NUMBER, CLIENT_EMAIL);
        var clientWithoutLastName =
                getClientDto(CLIENT_ID, CLIENT_FIRST_NAME, null, CLIENT_DATE_OF_BIRTH, PASSWORD, CLIENT_PHONE_NUMBER, CLIENT_EMAIL);
        var clientWithoutDateOfBirth =
                getClientDto(CLIENT_ID, CLIENT_FIRST_NAME, CLIENT_LAST_NAME, null, PASSWORD, CLIENT_PHONE_NUMBER, CLIENT_EMAIL);
        var clientWithoutPhoneNumber =
                getClientDto(CLIENT_ID, CLIENT_FIRST_NAME, CLIENT_LAST_NAME, CLIENT_DATE_OF_BIRTH, PASSWORD, null, CLIENT_EMAIL);
        var clientWithoutEmail =
                getClientDto(CLIENT_ID, CLIENT_FIRST_NAME, CLIENT_LAST_NAME, CLIENT_DATE_OF_BIRTH, PASSWORD, CLIENT_PHONE_NUMBER, null);
        var clientWithoutPassword =
                getClientDto(CLIENT_ID, CLIENT_FIRST_NAME, CLIENT_LAST_NAME, CLIENT_DATE_OF_BIRTH, null, CLIENT_PHONE_NUMBER, CLIENT_EMAIL);
        var clientWithoutClientId =
                getClientDto(null, CLIENT_FIRST_NAME, CLIENT_LAST_NAME, CLIENT_DATE_OF_BIRTH, PASSWORD, CLIENT_PHONE_NUMBER, CLIENT_EMAIL);
        var clientWithAllData =
                getClientDto(CLIENT_ID, CLIENT_FIRST_NAME, CLIENT_LAST_NAME, CLIENT_DATE_OF_BIRTH, PASSWORD, CLIENT_PHONE_NUMBER, CLIENT_EMAIL);

        return Stream.of(
                new ClientData(clientWithoutFirstName, false),
                new ClientData(clientWithoutLastName, false),
                new ClientData(clientWithoutDateOfBirth, false),
                new ClientData(clientWithoutPhoneNumber, false),
                new ClientData(clientWithoutEmail, false),
                new ClientData(clientWithoutEmail, false),
                new ClientData(clientWithoutPassword, false),
                new ClientData(clientWithoutClientId, false),
                new ClientData(clientWithAllData, true)
        );
    }

    @ParameterizedTest
    @MethodSource("clientDataProvider")
    void clientDtoAllValuesPredicateTest(ClientData clientData) {
        boolean result = predicate.test(clientData.getClientDto());
        assertEquals(result, clientData.isResult());
    }
}