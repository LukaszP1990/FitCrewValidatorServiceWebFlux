package com.fitcrew.validatorservice.validator.client;

import com.fitcrew.FitCrewAppModel.domain.dto.ClientDto;
import com.fitcrew.validatorservice.core.error.model.ValidationClientErrorDto;
import com.fitcrew.validatorservice.core.provider.DataValidationProvider;
import com.fitcrew.validatorservice.core.util.ClientErrorDtoUtil;
import com.fitcrew.validatorservice.validator.predicates.ClientDtoAllValuesPredicate;
import com.fitcrew.validatorservice.validator.predicates.EmailRegexPredicate;
import com.fitcrew.validatorservice.validator.predicates.MinMaxCharsPredicate;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ClientValidator {

    private final DataValidationProvider dataValidationProvider;
    private boolean uniqueId = true;

    public ClientValidator(DataValidationProvider dataValidationProvider) {
        this.dataValidationProvider = dataValidationProvider;
    }

    public Validation<Seq<ValidationClientErrorDto>, ClientDto> validate(ClientDto dto) {
        return Validation.combine(
                validateValue(dto),
                validateFirstName(dto),
                validateLastName(dto),
                validateDateOfBirth(dto),
                validateDescription(dto),
                validateEmailRegex(dto),
                validateClientId(dto))
                .ap((order1, order2, order3, order4, order5, order6, order7) -> order7);
    }

    Validation<ValidationClientErrorDto, ClientDto> validateValue(ClientDto clientDto) {
        return new ClientDtoAllValuesPredicate().test(clientDto)
                ? Validation.valid(clientDto)
                : Validation.invalid(ClientErrorDtoUtil.buildValueClientErrorDto(clientDto));
    }

    Validation<ValidationClientErrorDto, ClientDto> validateFirstName(ClientDto clientDto) {
        return new MinMaxCharsPredicate().test(clientDto.getFirstName(), 20)
                ? Validation.valid(clientDto)
                : Validation.invalid(ClientErrorDtoUtil.buildFirstNameErrorDto(clientDto));
    }

    Validation<ValidationClientErrorDto, ClientDto> validateLastName(ClientDto clientDto) {
        return new MinMaxCharsPredicate().test(clientDto.getLastName(), 20)
                ? Validation.valid(clientDto)
                : Validation.invalid(ClientErrorDtoUtil.buildLastNameErrorDto(clientDto));
    }

    Validation<ValidationClientErrorDto, ClientDto> validateDateOfBirth(ClientDto clientDto) {
        return new MinMaxCharsPredicate().test(clientDto.getDateOfBirth(), 20)
                ? Validation.valid(clientDto)
                : Validation.invalid(ClientErrorDtoUtil.buildDateOfBirthErrorDto(clientDto));
    }

    Validation<ValidationClientErrorDto, ClientDto> validateDescription(ClientDto clientDto) {
        return new MinMaxCharsPredicate().test(clientDto.getDateOfBirth(), 200)
                ? Validation.valid(clientDto)
                : Validation.invalid(ClientErrorDtoUtil.buildPhoneRegexErrorDto(clientDto));
    }

    Validation<ValidationClientErrorDto, ClientDto> validateEmailRegex(ClientDto clientDto) {
        return new EmailRegexPredicate().test(clientDto.getEmail())
                ? Validation.valid(clientDto)
                : Validation.invalid(ClientErrorDtoUtil.buildPhoneRegexErrorDto(clientDto));
    }

    Validation<ValidationClientErrorDto, ClientDto> validateClientId(ClientDto clientDto) {
        dataValidationProvider.getClientById(clientDto.getClientId())
                .subscribe(client -> uniqueId = Objects.isNull(client));

        return uniqueId ?
                Validation.valid(clientDto) :
                Validation.invalid(ClientErrorDtoUtil.buildClientIdErrorDto(clientDto));
    }
}
