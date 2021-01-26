package com.fitcrew.validatorservice.validator.client;

import com.fitcrew.validatorservice.core.provider.DataValidationProvider;
import com.fitcrew.validatorservice.validator.util.ClientUtil;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;

import static com.fitcrew.validatorservice.validator.util.ClientUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ClientValidatorTest {

    private final DataValidationProvider dataValidationProvider = mock(DataValidationProvider.class);
    private final ClientValidator clientValidator = new ClientValidator(dataValidationProvider);

    static Stream<ClientUtil.ClientData> clientDataProvider() {
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
                new ClientData(clientWithAllData, true)
        );
    }

    @ParameterizedTest
    @MethodSource("clientDataProvider")
    void validateTest(ClientData clientData) {
        //given
        when(dataValidationProvider.getClientById(anyString()))
                .thenReturn(Mono.empty());

        //when
        var result = clientValidator.validate(clientData.getClientDto());

        //then
        checkAssertion(result.isValid(), clientData.isResult());
    }

    private void checkAssertion(boolean result,
                                boolean expected) {
        assertEquals(expected, result);
    }
}