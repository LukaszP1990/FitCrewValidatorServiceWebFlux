package com.fitcrew.validatorservice.validator.training;

import com.fitcrew.validatorservice.validator.util.TrainingUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.stream.Stream;

import static com.fitcrew.validatorservice.validator.util.TrainerUtil.TRAINER_EMAIL;
import static com.fitcrew.validatorservice.validator.util.TrainingUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TrainingValidatorTest {

    private final TrainingValidator trainingValidator = new TrainingValidator();

    static Stream<TrainingUtil.TrainingData> trainingDataProvider() {
        var client = Collections.singletonList(CLIENT);
        var trainingDtoWithoutEmail = getTrainingDto(null, client, DESCRIPTION, TRAINING, TRAINING_NAME);
        var trainingDtoWithoutClient = getTrainingDto(TRAINER_EMAIL, null, DESCRIPTION, TRAINING, TRAINING_NAME);
        var trainingDtoWithoutDescription = getTrainingDto(TRAINER_EMAIL, client, null, TRAINING, TRAINING_NAME);
        var trainingDtoWithoutTraining = getTrainingDto(TRAINER_EMAIL, client, DESCRIPTION, null, TRAINING_NAME);
        var trainingDtoWithoutTrainingName = getTrainingDto(TRAINER_EMAIL, client, DESCRIPTION, TRAINING, null);
        var trainingDtoWithAllData = getTrainingDto(TRAINER_EMAIL, client, DESCRIPTION, TRAINING, TRAINING_NAME);

        return Stream.of(
                new TrainingData(trainingDtoWithoutEmail, false),
                new TrainingData(trainingDtoWithoutClient, false),
                new TrainingData(trainingDtoWithoutDescription, false),
                new TrainingData(trainingDtoWithoutTraining, false),
                new TrainingData(trainingDtoWithoutTrainingName, false),
                new TrainingData(trainingDtoWithAllData, true)
        );
    }

    @ParameterizedTest
    @MethodSource("trainingDataProvider")
    void validateTest(TrainingData trainingData) {
        //when
        var result = trainingValidator.validate(trainingData.getTrainingDto());

        //then
        checkAssertion(result.isValid(), trainingData.isResult());
    }

    private void checkAssertion(boolean result,
                                boolean expected) {
        assertEquals(expected, result);
    }
}