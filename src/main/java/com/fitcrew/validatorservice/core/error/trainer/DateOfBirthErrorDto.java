package com.fitcrew.validatorservice.core.error.trainer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fitcrew.FitCrewAppModel.domain.dto.TrainerDto;
import com.fitcrew.validatorservice.core.error.model.ValidationTrainerErrorDto;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.beans.ConstructorProperties;

@Getter
@ToString
@JsonIgnoreProperties({"trainerDto"})
public class DateOfBirthErrorDto extends ValidationTrainerErrorDto {
    private final String value;

    @Builder
    @ConstructorProperties({"type", "value"})
    protected DateOfBirthErrorDto(final String type,
                                  final String value,
                                  final TrainerDto trainerDto) {
        super(type, trainerDto);
        this.value = value;
    }
}
