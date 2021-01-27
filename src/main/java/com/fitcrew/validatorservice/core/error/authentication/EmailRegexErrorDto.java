package com.fitcrew.validatorservice.core.error.authentication;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fitcrew.jwt.model.AuthenticationRequest;
import com.fitcrew.validatorservice.core.error.model.ValidationAuthReqErrorDto;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.beans.ConstructorProperties;

@Getter
@ToString
@JsonIgnoreProperties({"authRequest"})
public class EmailRegexErrorDto extends ValidationAuthReqErrorDto {
    private final String value;

    @Builder
    @ConstructorProperties({"type", "value"})
    protected EmailRegexErrorDto(final String type,
                                 final String value,
                                 final AuthenticationRequest authRequest) {
        super(type, authRequest);
        this.value = value;
    }
}
