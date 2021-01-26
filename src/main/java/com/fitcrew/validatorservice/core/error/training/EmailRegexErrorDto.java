package com.fitcrew.validatorservice.core.error.training;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fitcrew.FitCrewAppModel.domain.dto.TrainingDto;
import com.fitcrew.validatorservice.core.error.model.ValidationTrainingErrorDto;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.beans.ConstructorProperties;

@Getter
@ToString
@JsonIgnoreProperties({"trainingDto"})
public class EmailRegexErrorDto extends ValidationTrainingErrorDto {
    private final String value;

    @Builder
    @ConstructorProperties({"type", "value"})
    protected EmailRegexErrorDto(final String type,
                                 final String value,
                                 final TrainingDto trainingDto) {
        super(type, trainingDto);
        this.value = value;
    }
}
