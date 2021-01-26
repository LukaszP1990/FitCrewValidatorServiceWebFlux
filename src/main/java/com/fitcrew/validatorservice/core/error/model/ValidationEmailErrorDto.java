package com.fitcrew.validatorservice.core.error.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fitcrew.FitCrewAppModel.domain.dto.EmailDto;
import com.fitcrew.validatorservice.core.error.email.RecipientErrorDto;
import com.fitcrew.validatorservice.core.error.email.SenderErrorDto;
import com.fitcrew.validatorservice.core.error.email.SubjectErrorDto;
import com.fitcrew.validatorservice.core.error.email.ValueEmailErrorDto;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@ToString
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ValueEmailErrorDto.class, name = "EMAIL_VALUES_REQUIREMENTS"),
        @JsonSubTypes.Type(value = SenderErrorDto.class, name = "SENDER_LENGTH"),
        @JsonSubTypes.Type(value = SubjectErrorDto.class, name = "SUBJECT_LENGTH"),
        @JsonSubTypes.Type(value = RecipientErrorDto.class, name = "RECIPIENT_LENGTH"),
})
public abstract class ValidationEmailErrorDto {

    @Getter
    private final String type;

    @JsonIgnore
    @Getter
    private final EmailDto emailDto;

    protected ValidationEmailErrorDto(String type,
                                      EmailDto emailDto) {
        this.type = Objects.requireNonNull(type);
        this.emailDto = emailDto;
    }
}
