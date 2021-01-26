package com.fitcrew.validatorservice.validator.predicates;

import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fitcrew.FitCrewAppModel.domain.dto.TrainerDto;
import com.fitcrew.validatorservice.core.util.ValueUtil;

public class TrainerDtoAllValuesPredicate implements Predicate<TrainerDto> {

    @Override
    public boolean test(TrainerDto trainerDto) {
        var listOfFields = Stream.of(
                trainerDto.getFirstName(),
                trainerDto.getLastName(),
                trainerDto.getEmail(),
                trainerDto.getPhone(),
                trainerDto.getDateOfBirth(),
                trainerDto.getPlaceInTheRanking(),
                trainerDto.getSomethingAboutYourself(),
                trainerDto.getPassword(),
                trainerDto.getTrainerId())
                .collect(Collectors.toList());
        return ValueUtil.validateStrings(listOfFields);
    }
}
