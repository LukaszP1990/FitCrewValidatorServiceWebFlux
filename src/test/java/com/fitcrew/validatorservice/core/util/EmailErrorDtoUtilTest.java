package com.fitcrew.validatorservice.core.util;

import com.fitcrew.validatorservice.core.error.model.ValidationEmailErrorDto;
import org.junit.jupiter.api.Test;

import static com.fitcrew.validatorservice.core.error.model.ErrorType.*;
import static com.fitcrew.validatorservice.validator.util.EmailUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class EmailErrorDtoUtilTest {

    @Test
    void shouldBuildValueEmailErrorDto() {
        //given
        var email = getEmailDto(null, RECIPIENT, SUBJECT, BODY_OF_MESSAGE);

        //when
        var valueEmailErrorDto = EmailErrorDtoUtil.buildValueEmailErrorDto(email);

        //then
        checkAssertions(
                valueEmailErrorDto,
                VALUE_ERROR,
                valueEmailErrorDto.getEmailDto().getSender()
        );
    }

    @Test
    void shouldBuildSenderErrorDto() {
        //given
        var email = getEmailDto(null, RECIPIENT, SUBJECT, BODY_OF_MESSAGE);

        //when
        var senderErrorDto = EmailErrorDtoUtil.buildSenderErrorDto(email);

        //then
        checkAssertions(
                senderErrorDto,
                SENDER_ERROR,
                senderErrorDto.getEmailDto().getSender()
        );
    }

    @Test
    void shouldBuildSubjectErrorDto() {
        //given
        var email = getEmailDto(SENDER, RECIPIENT, null, BODY_OF_MESSAGE);

        //when
        var subjectErrorDto = EmailErrorDtoUtil.buildSubjectErrorDto(email);

        //then
        checkAssertions(
                subjectErrorDto,
                SUBJECT_ERROR,
                subjectErrorDto.getEmailDto().getSubject()
        );
    }

    @Test
    void shouldBuildRecipientErrorDto() {
        //given
        var email = getEmailDto(SENDER, null, SUBJECT, BODY_OF_MESSAGE);

        //when
        var recipientErrorDto = EmailErrorDtoUtil.buildRecipientErrorDto(email);

        //then
        checkAssertions(
                recipientErrorDto,
                RECIPIENT_ERROR,
                recipientErrorDto.getEmailDto().getRecipient()
        );
    }

    private <T extends ValidationEmailErrorDto> void checkAssertions(T errorDto,
                                                                     String errorType,
                                                                     String errorValue) {
        assertAll(() -> {
            assertNotNull(errorDto);
            assertEquals(errorType, errorDto.getType());
            assertNull(errorValue);
        });
    }
}