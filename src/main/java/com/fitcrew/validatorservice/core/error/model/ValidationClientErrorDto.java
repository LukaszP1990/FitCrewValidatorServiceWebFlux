package com.fitcrew.validatorservice.core.error.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fitcrew.FitCrewAppModel.domain.dto.ClientDto;
import com.fitcrew.validatorservice.core.error.client.*;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@ToString
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ValueClientErrorDto.class, name = "CLIENT_VALUES_REQUIREMENTS"),
        @JsonSubTypes.Type(value = FirstNameErrorDto.class, name = "FIRST_NAME_LENGTH"),
        @JsonSubTypes.Type(value = LastNameErrorDto.class, name = "LAST_NAME_LENGTH"),
        @JsonSubTypes.Type(value = DateOfBirthErrorDto.class, name = "DAY_OF_BIRTH_LENGTH"),
        @JsonSubTypes.Type(value = PhoneRegexErrorDto.class, name = "PHONE_REGEX"),
        @JsonSubTypes.Type(value = EmailRegexErrorDto.class, name = "EMAIL_REGEX"),
        @JsonSubTypes.Type(value = ClientIdErrorDto.class, name = "CLIENT_UNIQUE_ID"),
})
public abstract class ValidationClientErrorDto {

    @Getter
    private final String type;

    @JsonIgnore
    @Getter
    private final ClientDto clientDto;

    protected ValidationClientErrorDto(String type,
                                       ClientDto clientDto) {
        this.type = Objects.requireNonNull(type);
        this.clientDto = clientDto;
    }
}
