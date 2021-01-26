package com.fitcrew.validatorservice.core.error.email;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fitcrew.FitCrewAppModel.domain.dto.EmailDto;
import com.fitcrew.validatorservice.core.error.model.ValidationEmailErrorDto;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.beans.ConstructorProperties;

@Getter
@ToString
@JsonIgnoreProperties({"emailDto"})
public class ValueEmailErrorDto extends ValidationEmailErrorDto {
    private final String value;

    @Builder
    @ConstructorProperties({"type", "value"})
    protected ValueEmailErrorDto(final String type,
                                 final String value,
                                 final EmailDto emailDto) {
        super(type, emailDto);
        this.value = value;
    }
}
