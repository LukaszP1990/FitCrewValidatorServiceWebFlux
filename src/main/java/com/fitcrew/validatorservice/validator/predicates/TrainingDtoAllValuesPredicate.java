package com.fitcrew.validatorservice.validator.predicates;

import com.fitcrew.FitCrewAppModel.domain.dto.TrainingDto;
import com.fitcrew.validatorservice.core.util.ValueUtil;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TrainingDtoAllValuesPredicate implements Predicate<TrainingDto> {

    @Override
    public boolean test(TrainingDto trainingDto) {
        List<Object> listOfFields = Stream.of(
                trainingDto.getTraining(),
                trainingDto.getDescription(),
                trainingDto.getTrainingName(),
                trainingDto.getTrainerEmail(),
                trainingDto.getClients())
                .collect(Collectors.toList());
        return ValueUtil.validateObjects(listOfFields);
    }
}
