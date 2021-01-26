package com.fitcrew.validatorservice.core.error.login;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fitcrew.FitCrewAppModel.domain.dto.LoginDto;
import com.fitcrew.validatorservice.core.error.model.ValidationLoginErrorDto;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.beans.ConstructorProperties;

@Getter
@ToString
@JsonIgnoreProperties({"loginDto"})
public class ValueLoginErrorDto extends ValidationLoginErrorDto {
    private final String value;

    @Builder
    @ConstructorProperties({"type", "value"})
    protected ValueLoginErrorDto(final String type,
                                 final String value,
                                 final LoginDto loginDto) {
        super(type, loginDto);
        this.value = value;
    }
}
