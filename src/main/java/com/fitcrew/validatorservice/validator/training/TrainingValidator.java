package com.fitcrew.validatorservice.validator.training;

import com.fitcrew.FitCrewAppModel.domain.dto.TrainingDto;
import com.fitcrew.validatorservice.core.error.model.ValidationTrainingErrorDto;
import com.fitcrew.validatorservice.core.util.TrainingErrorDtoUtil;
import com.fitcrew.validatorservice.validator.predicates.EmailRegexPredicate;
import com.fitcrew.validatorservice.validator.predicates.MinMaxCharsPredicate;
import com.fitcrew.validatorservice.validator.predicates.TrainingDtoAllValuesPredicate;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import org.springframework.stereotype.Component;

@Component
public class TrainingValidator {

    public Validation<Seq<ValidationTrainingErrorDto>, TrainingDto> validate(TrainingDto dto) {
        return Validation.combine(
                validateValue(dto),
                validateTrainingName(dto),
                validateTraining(dto),
                validateEmailRegex(dto),
                validateDescription(dto))
                .ap((order1, order2, order3, order4, order5) -> order5);
    }

    Validation<ValidationTrainingErrorDto, TrainingDto> validateValue(TrainingDto trainingDto) {
        return new TrainingDtoAllValuesPredicate().test(trainingDto)
                ? Validation.valid(trainingDto)
                : Validation.invalid(TrainingErrorDtoUtil.buildValueTrainingErrorDto(trainingDto));
    }

    Validation<ValidationTrainingErrorDto, TrainingDto> validateTrainingName(TrainingDto trainingDto) {
        return new MinMaxCharsPredicate().test(trainingDto.getTrainingName(), 20)
                ? Validation.valid(trainingDto)
                : Validation.invalid(TrainingErrorDtoUtil.buildTrainingNameErrorDto(trainingDto));
    }

    Validation<ValidationTrainingErrorDto, TrainingDto> validateTraining(TrainingDto trainingDto) {
        return new MinMaxCharsPredicate().test(trainingDto.getTraining(), 50)
                ? Validation.valid(trainingDto)
                : Validation.invalid(TrainingErrorDtoUtil.buildTrainingErrorDto(trainingDto));
    }

    Validation<ValidationTrainingErrorDto, TrainingDto> validateEmailRegex(TrainingDto trainingDto) {
        return new EmailRegexPredicate().test(trainingDto.getTrainerEmail())
                ? Validation.valid(trainingDto)
                : Validation.invalid(TrainingErrorDtoUtil.buildEmailRegexErrorDto(trainingDto));
    }

    Validation<ValidationTrainingErrorDto, TrainingDto> validateDescription(TrainingDto trainingDto) {
        return new MinMaxCharsPredicate().test(trainingDto.getDescription(), 400)
                ? Validation.valid(trainingDto)
                : Validation.invalid(TrainingErrorDtoUtil.buildDescriptionErrorDto(trainingDto));
    }
}
