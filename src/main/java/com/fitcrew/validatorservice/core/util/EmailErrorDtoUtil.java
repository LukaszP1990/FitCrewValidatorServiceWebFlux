package com.fitcrew.validatorservice.core.util;

import com.fitcrew.FitCrewAppConstant.message.ValidationErrorMessage;
import com.fitcrew.FitCrewAppModel.domain.dto.EmailDto;
import com.fitcrew.validatorservice.core.error.email.RecipientErrorDto;
import com.fitcrew.validatorservice.core.error.email.SenderErrorDto;
import com.fitcrew.validatorservice.core.error.email.SubjectErrorDto;
import com.fitcrew.validatorservice.core.error.email.ValueEmailErrorDto;
import com.fitcrew.validatorservice.core.error.model.ErrorType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmailErrorDtoUtil {

    public static ValueEmailErrorDto buildValueEmailErrorDto(EmailDto emailDto) {
        return ValueEmailErrorDto.builder()
                .type(ErrorType.VALUE_ERROR)
                .value(ValidationErrorMessage.NOT_ALL_EMAIL_VALUES)
                .emailDto(emailDto)
                .build();
    }

    public static SenderErrorDto buildSenderErrorDto(EmailDto emailDto) {
        return SenderErrorDto.builder()
                .type(ErrorType.SENDER_ERROR)
                .value(ValidationErrorMessage.SENDER_ERROR_MESSAGE)
                .emailDto(emailDto)
                .build();
    }

    public static SubjectErrorDto buildSubjectErrorDto(EmailDto emailDto) {
        return SubjectErrorDto.builder()
                .type(ErrorType.SUBJECT_ERROR)
                .value(ValidationErrorMessage.RECIPIENT_ERROR_MESSAGE)
                .emailDto(emailDto)
                .build();
    }

    public static RecipientErrorDto buildRecipientErrorDto(EmailDto emailDto) {
        return RecipientErrorDto.builder()
                .type(ErrorType.RECIPIENT_ERROR)
                .value(ValidationErrorMessage.SUBJECT_ERROR_MESSAGE)
                .emailDto(emailDto)
                .build();
    }
}
