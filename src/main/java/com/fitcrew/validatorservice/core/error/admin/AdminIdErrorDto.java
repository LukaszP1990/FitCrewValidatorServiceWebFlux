package com.fitcrew.validatorservice.core.error.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fitcrew.FitCrewAppModel.domain.dto.AdminDto;
import com.fitcrew.validatorservice.core.error.model.ValidationAdminErrorDto;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.beans.ConstructorProperties;

@Getter
@ToString
@JsonIgnoreProperties({"adminDto"})
public class AdminIdErrorDto extends ValidationAdminErrorDto {
    private final String value;

    @Builder
    @ConstructorProperties({"type", "value"})
    protected AdminIdErrorDto(final String type,
                              final String value,
                              final AdminDto adminDto) {
        super(type, adminDto);
        this.value = value;
    }
}
