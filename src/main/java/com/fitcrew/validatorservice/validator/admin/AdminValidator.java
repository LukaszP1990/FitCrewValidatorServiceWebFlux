package com.fitcrew.validatorservice.validator.admin;

import com.fitcrew.FitCrewAppModel.domain.dto.AdminDto;
import com.fitcrew.validatorservice.core.error.model.ValidationAdminErrorDto;
import com.fitcrew.validatorservice.core.provider.DataValidationProvider;
import com.fitcrew.validatorservice.core.util.AdminErrorDtoUtil;
import com.fitcrew.validatorservice.validator.predicates.AdminDtoAllValuesPredicate;
import com.fitcrew.validatorservice.validator.predicates.EmailRegexPredicate;
import com.fitcrew.validatorservice.validator.predicates.MinMaxCharsPredicate;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AdminValidator {

    private final DataValidationProvider dataValidationProvider;
    private boolean uniqueId = true;

    public AdminValidator(DataValidationProvider dataValidationProvider) {
        this.dataValidationProvider = dataValidationProvider;
    }

    public Validation<Seq<ValidationAdminErrorDto>, AdminDto> validate(AdminDto dto) {
        return Validation.combine(
                validateValue(dto),
                validateFirstName(dto),
                validateLastName(dto),
                validateEmailRegex(dto),
                validateAdminId(dto))
                .ap((order1, order2, order3, order4, order5) -> order5);
    }

    Validation<ValidationAdminErrorDto, AdminDto> validateValue(AdminDto adminDto) {
        return new AdminDtoAllValuesPredicate().test(adminDto)
                ? Validation.valid(adminDto)
                : Validation.invalid(AdminErrorDtoUtil.buildValueAdminErrorDto(adminDto));
    }

    Validation<ValidationAdminErrorDto, AdminDto> validateFirstName(AdminDto adminDto) {
        return new MinMaxCharsPredicate().test(adminDto.getFirstName(), 20)
                ? Validation.valid(adminDto)
                : Validation.invalid(AdminErrorDtoUtil.buildFirstNameErrorDto(adminDto));
    }

    Validation<ValidationAdminErrorDto, AdminDto> validateLastName(AdminDto adminDto) {
        return new MinMaxCharsPredicate().test(adminDto.getLastName(), 20)
                ? Validation.valid(adminDto)
                : Validation.invalid(AdminErrorDtoUtil.buildLastNameErrorDto(adminDto));
    }

    Validation<ValidationAdminErrorDto, AdminDto> validateEmailRegex(AdminDto adminDto) {
        return new EmailRegexPredicate().test(adminDto.getEmail())
                ? Validation.valid(adminDto)
                : Validation.invalid(AdminErrorDtoUtil.buildEmailRegexErrorDto(adminDto));
    }

    Validation<ValidationAdminErrorDto, AdminDto> validateAdminId(AdminDto adminDto) {
        dataValidationProvider.getAdminById(adminDto.getAdminId())
                .subscribe(admin -> uniqueId = Objects.isNull(admin));

        return uniqueId ?
                Validation.valid(adminDto) :
                Validation.invalid(AdminErrorDtoUtil.buildAdminIdErrorDto(adminDto));
    }
}
