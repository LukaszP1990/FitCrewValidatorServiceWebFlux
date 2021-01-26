package com.fitcrew.validatorservice.core.error.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fitcrew.FitCrewAppModel.domain.dto.ClientDto;
import com.fitcrew.validatorservice.core.error.model.ValidationClientErrorDto;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.beans.ConstructorProperties;

@Getter
@ToString
@JsonIgnoreProperties({"clientDto"})
public class LastNameErrorDto extends ValidationClientErrorDto {
    private final String value;

    @Builder
    @ConstructorProperties({"type", "value"})
    protected LastNameErrorDto(final String type,
                               final String value,
                               final ClientDto clientDto) {
        super(type, clientDto);
        this.value = value;
    }
}
