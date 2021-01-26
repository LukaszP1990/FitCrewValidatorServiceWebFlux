package com.fitcrew.validatorservice.core.error.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import com.fitcrew.FitCrewAppModel.domain.dto.AdminDto;
import com.fitcrew.validatorservice.core.error.admin.*;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@ToString
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ValueAdminErrorDto.class, name = "ADMIN_VALUES_REQUIREMENTS"),
        @JsonSubTypes.Type(value = FirstNameErrorDto.class, name = "FIRST_NAME_LENGTH"),
        @JsonSubTypes.Type(value = LastNameErrorDto.class, name = "LAST_NAME_LENGTH"),
        @JsonSubTypes.Type(value = EmailRegexErrorDto.class, name = "EMAIL_REGEX"),
        @JsonSubTypes.Type(value = AdminIdErrorDto.class, name = "ADMIN_UNIQUE_ID"),
})
public abstract class ValidationAdminErrorDto {

    @Getter
    private final String type;

    @JsonIgnore
    @Getter
    private final AdminDto adminDto;

    protected ValidationAdminErrorDto(String type,
                                      AdminDto adminDto) {
        this.type = Objects.requireNonNull(type);
        this.adminDto = adminDto;
    }
}
