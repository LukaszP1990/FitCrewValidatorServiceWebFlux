package com.fitcrew.validatorservice.validator.util;

import com.fitcrew.FitCrewAppModel.domain.dto.TrainerDto;

import java.util.Optional;

public class TrainerUtil {

    public static final String TRAINER_ID = "1";
    public static final String TRAINER_EMAIL = "mockedTrainer@gmail.com";
    public static final String TRAINER_DATE_OF_BIRTH = "01.01.1990";
    public static final String TRAINER_PHONE_NUMBER = "501928341";
    public static final String TRAINER_DESCRIPTION = "Description about trainer";
    public static final String PASSWORD = "password";
    public static final String TRAINER_FIRST_NAME = "firstName";
    public static final String TRAINER_LAST_NAME = "lastName";
    public static final String TRAINER_ENCRYPTED_PASSWORD = "$2y$12$Y3QFw.tzF7OwIJGlpzk9s.5Ymq4zY3hItIkD0Xes3UWxBo2SkEgei";

    public static TrainerDto getTrainerDto(String trainerId,
                                           String firstName,
                                           String lastName,
                                           String email,
                                           String dateOfBirth,
                                           String phoneNumber,
                                           String description,
                                           String password) {
        return TrainerDto.builder()
                .trainerId(trainerId)
                .firstName(firstName)
                .lastName(lastName)
                .email(getEmailValue(email))
                .dateOfBirth(dateOfBirth)
                .phone(phoneNumber)
                .placeInTheRanking(String.valueOf(1))
                .somethingAboutYourself(description)
                .password(password)
                .encryptedPassword(TRAINER_ENCRYPTED_PASSWORD)
                .build();
    }

    private static String getEmailValue(String email) {
        return Optional.ofNullable(email)
                .map(value -> String.valueOf(1).concat(value))
                .orElse(null);
    }

    public static class TrainerData {
        private final TrainerDto trainerDto;
        private final boolean result;

        public TrainerData(TrainerDto trainerDto, boolean result) {
            this.trainerDto = trainerDto;
            this.result = result;
        }

        public TrainerDto getTrainerDto() {
            return trainerDto;
        }

        public boolean isResult() {
            return result;
        }
    }
}
