package com.fitcrew.validatorservice.validator.trainer;

import com.fitcrew.FitCrewAppModel.domain.dto.TrainerDto;
import com.fitcrew.validatorservice.core.error.model.ValidationTrainerErrorDto;
import com.fitcrew.validatorservice.core.provider.DataValidationProvider;
import com.fitcrew.validatorservice.core.util.TrainerErrorDtoUtil;
import com.fitcrew.validatorservice.validator.predicates.MinMaxCharsPredicate;
import com.fitcrew.validatorservice.validator.predicates.TrainerDtoAllValuesPredicate;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TrainerValidator {

    private final DataValidationProvider dataValidationProvider;
    private boolean uniqueId = true;

    public TrainerValidator(DataValidationProvider dataValidationProvider) {
        this.dataValidationProvider = dataValidationProvider;
    }

    public Validation<Seq<ValidationTrainerErrorDto>, TrainerDto> validate(TrainerDto dto) {
        return Validation.combine(
                validateValue(dto),
                validateFirstName(dto),
                validateLastName(dto),
                validateDateOfBirth(dto),
                validateDescription(dto),
                validateTrainerId(dto))
                .ap((order1, order2, order3, order4, order5, order6) -> order6);
    }

    Validation<ValidationTrainerErrorDto, TrainerDto> validateValue(TrainerDto trainerDto) {
        return new TrainerDtoAllValuesPredicate().test(trainerDto)
                ? Validation.valid(trainerDto)
                : Validation.invalid(TrainerErrorDtoUtil.buildValueTrainerErrorDto(trainerDto));
    }

    Validation<ValidationTrainerErrorDto, TrainerDto> validateFirstName(TrainerDto trainerDto) {
        return new MinMaxCharsPredicate().test(trainerDto.getFirstName(), 20)
                ? Validation.valid(trainerDto)
                : Validation.invalid(TrainerErrorDtoUtil.buildFirstNameErrorDto(trainerDto));
    }

    Validation<ValidationTrainerErrorDto, TrainerDto> validateLastName(TrainerDto trainerDto) {
        return new MinMaxCharsPredicate().test(trainerDto.getLastName(), 20)
                ? Validation.valid(trainerDto)
                : Validation.invalid(TrainerErrorDtoUtil.buildLastNameErrorDto(trainerDto));
    }

    Validation<ValidationTrainerErrorDto, TrainerDto> validateDateOfBirth(TrainerDto trainerDto) {
        return new MinMaxCharsPredicate().test(trainerDto.getDateOfBirth(), 20)
                ? Validation.valid(trainerDto)
                : Validation.invalid(TrainerErrorDtoUtil.buildDateOfBirthErrorDto(trainerDto));
    }

    Validation<ValidationTrainerErrorDto, TrainerDto> validateDescription(TrainerDto trainerDto) {
        return new MinMaxCharsPredicate().test(trainerDto.getDateOfBirth(), 200)
                ? Validation.valid(trainerDto)
                : Validation.invalid(TrainerErrorDtoUtil.buildDescriptionErrorDto(trainerDto));
    }

    Validation<ValidationTrainerErrorDto, TrainerDto> validateTrainerId(TrainerDto trainerDto) {
        dataValidationProvider.getTrainerById(trainerDto.getTrainerId())
                .subscribe(trainer -> uniqueId = Objects.isNull(trainer));

        return uniqueId ?
                Validation.valid(trainerDto) :
                Validation.invalid(TrainerErrorDtoUtil.buildTrainerIdErrorDto(trainerDto));
    }
}
