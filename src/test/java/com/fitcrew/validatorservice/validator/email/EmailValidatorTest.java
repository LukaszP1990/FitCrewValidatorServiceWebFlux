package com.fitcrew.validatorservice.validator.email;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.fitcrew.validatorservice.validator.util.EmailUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EmailValidatorTest {

    private final EmailValidator emailValidator = new EmailValidator();

    static Stream<EmailData> emailDataProvider() {
        var emailDtoWithoutSender = getEmailDto(null, RECIPIENT, SUBJECT, BODY_OF_MESSAGE);
        var emailDtoWithoutRecipient = getEmailDto(SENDER, null, SUBJECT, BODY_OF_MESSAGE);
        var emailDtoWithoutSubject = getEmailDto(SENDER, RECIPIENT, null, BODY_OF_MESSAGE);
        var emailDtoWithoutBodyOfMessage = getEmailDto(SENDER, RECIPIENT, SUBJECT, null);
        var emailDtoWithAllData = getEmailDto(SENDER, RECIPIENT, SUBJECT, BODY_OF_MESSAGE);

        return Stream.of(
                new EmailData(emailDtoWithoutSender, false),
                new EmailData(emailDtoWithoutRecipient, false),
                new EmailData(emailDtoWithoutSubject, false),
                new EmailData(emailDtoWithoutBodyOfMessage, false),
                new EmailData(emailDtoWithAllData, true)
        );
    }

    @ParameterizedTest
    @MethodSource("emailDataProvider")
    void validateTest(EmailData emailData) {
        //when
        var result = emailValidator.validate(emailData.getEmailDto());

        //then
        checkAssertion(result.isValid(), emailData.isResult());
    }

    private void checkAssertion(boolean result,
                                boolean expected) {
        assertEquals(expected, result);
    }
}