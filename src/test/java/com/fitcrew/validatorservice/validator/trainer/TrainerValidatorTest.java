package com.fitcrew.validatorservice.validator.trainer;

import com.fitcrew.validatorservice.core.provider.DataValidationProvider;
import com.fitcrew.validatorservice.validator.util.TrainerUtil;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;

import static com.fitcrew.validatorservice.validator.util.TrainerUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TrainerValidatorTest {

    private final DataValidationProvider dataValidationProvider = mock(DataValidationProvider.class);
    private final TrainerValidator trainerValidator = new TrainerValidator(dataValidationProvider);

    static Stream<TrainerUtil.TrainerData> trainerDataProvider() {
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
                new TrainerData(trainerDtoWithAllData, true)
        );
    }

    @ParameterizedTest
    @MethodSource("trainerDataProvider")
    void validateTest(TrainerData trainerData) {
        //given
        when(dataValidationProvider.getTrainerById(anyString()))
                .thenReturn(Mono.empty());

        //when
        var result = trainerValidator.validate(trainerData.getTrainerDto());

        //then
        checkAssertion(result.isValid(), trainerData.isResult());
    }

    private void checkAssertion(boolean result,
                                boolean expected) {
        assertEquals(expected, result);
    }
}