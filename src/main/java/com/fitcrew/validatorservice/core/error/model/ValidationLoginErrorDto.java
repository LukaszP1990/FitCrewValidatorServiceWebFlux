package com.fitcrew.validatorservice.core.error.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fitcrew.FitCrewAppModel.domain.dto.LoginDto;
import com.fitcrew.validatorservice.core.error.login.EmailRegexErrorDto;
import com.fitcrew.validatorservice.core.error.login.ValueLoginErrorDto;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@ToString
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ValueLoginErrorDto.class, name = "LOGIN_VALUES_REQUIREMENTS"),
        @JsonSubTypes.Type(value = EmailRegexErrorDto.class, name = "EMAIL_REGEX"),
})
public abstract class ValidationLoginErrorDto {

    @Getter
    private final String type;

    @JsonIgnore
    @Getter
    private final LoginDto loginDto;

    protected ValidationLoginErrorDto(String type,
                                      LoginDto loginDto) {
        this.type = Objects.requireNonNull(type);
        this.loginDto = loginDto;
    }
}
