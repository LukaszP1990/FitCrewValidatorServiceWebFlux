package com.fitcrew.validatorservice.core.util;

import com.fitcrew.FitCrewAppConstant.message.ValidationErrorMessage;
import com.fitcrew.FitCrewAppModel.domain.dto.TrainingDto;
import com.fitcrew.validatorservice.core.error.model.ErrorType;
import com.fitcrew.validatorservice.core.error.training.DescriptionErrorDto;
import com.fitcrew.validatorservice.core.error.training.EmailRegexErrorDto;
import com.fitcrew.validatorservice.core.error.training.TrainingErrorDto;
import com.fitcrew.validatorservice.core.error.training.ValueTrainingErrorDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TrainingErrorDtoUtil {

    public static ValueTrainingErrorDto buildValueTrainingErrorDto(TrainingDto trainingDto) {
        return ValueTrainingErrorDto.builder()
                .type(ErrorType.VALUE_ERROR)
                .value(ValidationErrorMessage.NOT_ALL_TRAINING_VALUES)
                .trainingDto(trainingDto)
                .build();
    }

    public static ValueTrainingErrorDto buildTrainingNameErrorDto(TrainingDto trainingDto) {
        return ValueTrainingErrorDto.builder()
                .type(ErrorType.TRAINING_NAME_ERROR)
                .value(ValidationErrorMessage.TRAINING_NAME_ERROR_MESSAGE)
                .trainingDto(trainingDto)
                .build();
    }

    public static TrainingErrorDto buildTrainingErrorDto(TrainingDto trainingDto) {
        return TrainingErrorDto.builder()
                .type(ErrorType.TRAINING_ERROR)
                .value(ValidationErrorMessage.TRAINING_ERROR_MESSAGE)
                .trainingDto(trainingDto)
                .build();
    }

    public static DescriptionErrorDto buildDescriptionErrorDto(TrainingDto trainingDto) {
        return DescriptionErrorDto.builder()
                .type(ErrorType.DESCRIPTION_ERROR)
                .value(ValidationErrorMessage.TRAINING_DESCRIPTION_ERROR_MESSAGE)
                .trainingDto(trainingDto)
                .build();
    }

    public static EmailRegexErrorDto buildEmailRegexErrorDto(TrainingDto trainingDto) {
        return EmailRegexErrorDto.builder()
                .type(ErrorType.EMAIL_REGEX_ERROR)
                .value(ValidationErrorMessage.EMAIL_FORMAT)
                .trainingDto(trainingDto)
                .build();
    }
}
