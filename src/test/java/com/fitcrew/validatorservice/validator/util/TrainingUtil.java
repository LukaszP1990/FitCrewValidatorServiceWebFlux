package com.fitcrew.validatorservice.validator.util;

import com.fitcrew.FitCrewAppModel.domain.dto.TrainingDto;

import java.util.List;

public class TrainingUtil {

    public static final String CLIENT = "client";
    public static final String DESCRIPTION = "description";
    public static final String TRAINING = "example of training";
    public static final String TRAINING_NAME = "training name";

    public static TrainingDto getTrainingDto(String email,
                                             List<String> client,
                                             String description,
                                             String training,
                                             String trainingName) {
        return TrainingDto.builder()
                .clients(client)
                .trainerEmail(email)
                .description(description)
                .training(training)
                .trainingName(trainingName)
                .build();
    }

    public static class TrainingData {
        private final TrainingDto trainingDto;
        private final boolean result;

        public TrainingData(TrainingDto trainingDto, boolean result) {
            this.trainingDto = trainingDto;
            this.result = result;
        }

        public TrainingDto getTrainingDto() {
            return trainingDto;
        }

        public boolean isResult() {
            return result;
        }
    }
}
