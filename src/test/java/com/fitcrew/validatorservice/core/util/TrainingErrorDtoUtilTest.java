package com.fitcrew.validatorservice.core.util;

import com.fitcrew.validatorservice.core.error.model.ValidationTrainingErrorDto;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.fitcrew.validatorservice.core.error.model.ErrorType.*;
import static com.fitcrew.validatorservice.validator.util.TrainerUtil.TRAINER_EMAIL;
import static com.fitcrew.validatorservice.validator.util.TrainingUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class TrainingErrorDtoUtilTest {

    @Test
    void shouldBuildValueTrainingErrorDto() {
        //given
        var client = Collections.singletonList(CLIENT);
        var training = getTrainingDto(null, client, DESCRIPTION, TRAINING, TRAINING_NAME);

        //when
        var valueTrainingErrorDto = TrainingErrorDtoUtil.buildValueTrainingErrorDto(training);

        //then
        checkAssertions(
                valueTrainingErrorDto,
                VALUE_ERROR,
                valueTrainingErrorDto.getTrainingDto().getTrainerEmail()
        );
    }

    @Test
    void shouldBuildTrainingNameErrorDto() {
        //given
        var client = Collections.singletonList(CLIENT);
        var training = getTrainingDto(TRAINER_EMAIL, client, DESCRIPTION, TRAINING, null);

        //when
        var buildTrainingNameErrorDto = TrainingErrorDtoUtil.buildTrainingNameErrorDto(training);

        //then
        checkAssertions(
                buildTrainingNameErrorDto,
                TRAINING_NAME_ERROR,
                buildTrainingNameErrorDto.getTrainingDto().getTrainingName()
        );
    }

    @Test
    void shouldBuildTrainingErrorDto() {
        //given
        var client = Collections.singletonList(CLIENT);
        var training = getTrainingDto(TRAINER_EMAIL, client, DESCRIPTION, null, TRAINING_NAME);

        //when
        var trainingErrorDto = TrainingErrorDtoUtil.buildTrainingErrorDto(training);

        //then
        checkAssertions(
                trainingErrorDto,
                TRAINING_ERROR,
                trainingErrorDto.getTrainingDto().getTraining()
        );
    }

    @Test
    void shouldBuildDescriptionErrorDto() {
        //given
        var client = Collections.singletonList(CLIENT);
        var training = getTrainingDto(TRAINER_EMAIL, client, null, TRAINING, TRAINING_NAME);

        //when
        var descriptionErrorDto = TrainingErrorDtoUtil.buildDescriptionErrorDto(training);

        //then
        checkAssertions(
                descriptionErrorDto,
                DESCRIPTION_ERROR,
                descriptionErrorDto.getTrainingDto().getDescription()
        );
    }

    @Test
    void shouldBuildEmailRegexErrorDto() {
        //given
        var client = Collections.singletonList(CLIENT);
        var training = getTrainingDto(null, client, DESCRIPTION, TRAINING, TRAINING_NAME);

        //when
        var emailRegexErrorDto = TrainingErrorDtoUtil.buildEmailRegexErrorDto(training);

        //then
        checkAssertions(
                emailRegexErrorDto,
                EMAIL_REGEX_ERROR,
                emailRegexErrorDto.getTrainingDto().getTrainerEmail()
        );
    }

    private <T extends ValidationTrainingErrorDto> void checkAssertions(T errorDto,
                                                                        String errorType,
                                                                        String errorValue) {
        assertAll(() -> {
            assertNotNull(errorDto);
            assertEquals(errorType, errorDto.getType());
            assertNull(errorValue);
        });
    }
}