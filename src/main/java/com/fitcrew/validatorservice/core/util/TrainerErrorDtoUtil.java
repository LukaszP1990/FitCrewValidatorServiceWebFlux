package com.fitcrew.validatorservice.core.util;

import com.fitcrew.FitCrewAppConstant.message.ValidationErrorMessage;
import com.fitcrew.FitCrewAppModel.domain.dto.TrainerDto;
import com.fitcrew.validatorservice.core.error.model.ErrorType;
import com.fitcrew.validatorservice.core.error.trainer.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TrainerErrorDtoUtil {

    public static ValueTrainerErrorDto buildValueTrainerErrorDto(TrainerDto trainerDto) {
        return ValueTrainerErrorDto.builder()
                .type(ErrorType.VALUE_ERROR)
                .value(ValidationErrorMessage.NOT_ALL_TRAINER_VALUES)
                .trainerDto(trainerDto)
                .build();
    }

    public static FirstNameErrorDto buildFirstNameErrorDto(TrainerDto trainerDto) {
        return FirstNameErrorDto.builder()
                .type(ErrorType.FIRST_NAME_ERROR)
                .value(ValidationErrorMessage.FIRST_NAME_ERROR_MESSAGE)
                .trainerDto(trainerDto)
                .build();
    }

    public static LastNameErrorDto buildLastNameErrorDto(TrainerDto trainerDto) {
        return LastNameErrorDto.builder()
                .type(ErrorType.LAST_NAME_ERROR)
                .value(ValidationErrorMessage.LAST_NAME_ERROR_MESSAGE)
                .trainerDto(trainerDto)
                .build();
    }

    public static DateOfBirthErrorDto buildDateOfBirthErrorDto(TrainerDto trainerDto) {
        return DateOfBirthErrorDto.builder()
                .type(ErrorType.DATE_OF_BIRTH_ERROR)
                .value(ValidationErrorMessage.DATE_OF_BIRTH_ERROR_MESSAGE)
                .trainerDto(trainerDto)
                .build();
    }

    public static DescriptionErrorDto buildDescriptionErrorDto(TrainerDto trainerDto) {
        return DescriptionErrorDto.builder()
                .type(ErrorType.DESCRIPTION_ERROR)
                .value(ValidationErrorMessage.TRAINER_DESCRIPTION_ERROR_MESSAGE)
                .trainerDto(trainerDto)
                .build();
    }

    public static TrainerIdErrorDto buildTrainerIdErrorDto(TrainerDto trainerDto) {
        return TrainerIdErrorDto.builder()
                .type(ErrorType.TRAINER_ID_ERROR)
                .value(ValidationErrorMessage.TRAINER_ID_ERROR_MESSAGE)
                .trainerDto(trainerDto)
                .build();
    }
}
