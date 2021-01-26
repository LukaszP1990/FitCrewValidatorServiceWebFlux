package com.fitcrew.validatorservice.validator.predicates;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.fitcrew.validatorservice.validator.util.TrainerUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TrainerDtoAllValuesPredicateTest {

    private final TrainerDtoAllValuesPredicate predicate = new TrainerDtoAllValuesPredicate();

    static Stream<TrainerData> trainerDataProvider() {
        var trainerDtoWithoutFirstName =
                getTrainerDto(TRAINER_ID, null, TRAINER_LAST_NAME, TRAINER_EMAIL, TRAINER_DATE_OF_BIRTH, TRAINER_PHONE_NUMBER, TRAINER_DESCRIPTION, PASSWORD);
        var trainerDtoWithoutLastName =
                getTrainerDto(TRAINER_ID, TRAINER_FIRST_NAME, null, TRAINER_EMAIL, TRAINER_DATE_OF_BIRTH, TRAINER_PHONE_NUMBER, TRAINER_DESCRIPTION, PASSWORD);
        var trainerDtoWithoutEmail =
                getTrainerDto(TRAINER_ID, TRAINER_FIRST_NAME, TRAINER_LAST_NAME, null, TRAINER_DATE_OF_BIRTH, TRAINER_PHONE_NUMBER, TRAINER_DESCRIPTION, PASSWORD);
        var trainerDtoWithoutDateOfBirth =
                getTrainerDto(TRAINER_ID, TRAINER_FIRST_NAME, TRAINER_LAST_NAME, TRAINER_EMAIL, null, TRAINER_PHONE_NUMBER, TRAINER_DESCRIPTION, PASSWORD);
        var trainerDtoWithoutPhoneNumber =
                getTrainerDto(TRAINER_ID, TRAINER_FIRST_NAME, TRAINER_LAST_NAME, TRAINER_EMAIL, TRAINER_DATE_OF_BIRTH, null, TRAINER_DESCRIPTION, PASSWORD);
        var trainerDtoWithoutDescription =
                getTrainerDto(TRAINER_ID, TRAINER_FIRST_NAME, TRAINER_LAST_NAME, TRAINER_EMAIL, TRAINER_DATE_OF_BIRTH, TRAINER_PHONE_NUMBER, null, PASSWORD);
        var trainerDtoWithoutPassword =
                getTrainerDto(TRAINER_ID, TRAINER_FIRST_NAME, TRAINER_LAST_NAME, TRAINER_EMAIL, TRAINER_DATE_OF_BIRTH, TRAINER_PHONE_NUMBER, TRAINER_DESCRIPTION, null);
        var trainerDtoWithoutTrainerId =
                getTrainerDto(null, TRAINER_FIRST_NAME, TRAINER_LAST_NAME, TRAINER_EMAIL, TRAINER_DATE_OF_BIRTH, TRAINER_PHONE_NUMBER, TRAINER_DESCRIPTION, PASSWORD);
        var trainerDtoWithAllData =
                getTrainerDto(TRAINER_ID, TRAINER_FIRST_NAME, TRAINER_LAST_NAME, TRAINER_EMAIL, TRAINER_DATE_OF_BIRTH, TRAINER_PHONE_NUMBER, TRAINER_DESCRIPTION, PASSWORD);

        return Stream.of(
                new TrainerData(trainerDtoWithoutFirstName, false),
                new TrainerData(trainerDtoWithoutLastName, false),
                new TrainerData(trainerDtoWithoutDateOfBirth, false),
                new TrainerData(trainerDtoWithoutPhoneNumber, false),
                new TrainerData(trainerDtoWithoutDescription, false),
                new TrainerData(trainerDtoWithoutPassword, false),
                new TrainerData(trainerDtoWithoutEmail, false),
                new TrainerData(trainerDtoWithoutTrainerId, false),
                new TrainerData(trainerDtoWithAllData, true)
        );
    }

    @ParameterizedTest
    @MethodSource("trainerDataProvider")
    void trainerDtoAllValuesPredicateTest(TrainerData trainerData) {
        boolean result = predicate.test(trainerData.getTrainerDto());
        assertEquals(result, trainerData.isResult());
    }
}