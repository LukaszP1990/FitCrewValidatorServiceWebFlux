package com.fitcrew.validatorservice.core.error.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fitcrew.jwt.model.AuthenticationRequest;
import com.fitcrew.validatorservice.core.error.authentication.EmailRegexErrorDto;
import com.fitcrew.validatorservice.core.error.authentication.ValueAuthReqErrorDto;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@ToString
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ValueAuthReqErrorDto.class, name = "AUTHENTICATION_REQ_VALUES_REQUIREMENTS"),
        @JsonSubTypes.Type(value = EmailRegexErrorDto.class, name = "EMAIL_REGEX"),
})
public abstract class ValidationAuthReqErrorDto {

    @Getter
    private final String type;

    @JsonIgnore
    @Getter
    private final AuthenticationRequest authRequest;

    protected ValidationAuthReqErrorDto(String type,
                                        AuthenticationRequest authRequest) {
        this.type = Objects.requireNonNull(type);
        this.authRequest = authRequest;
    }
}
