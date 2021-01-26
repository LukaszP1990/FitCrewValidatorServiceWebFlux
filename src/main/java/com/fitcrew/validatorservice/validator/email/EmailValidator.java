package com.fitcrew.validatorservice.validator.email;

import com.fitcrew.FitCrewAppModel.domain.dto.EmailDto;
import com.fitcrew.validatorservice.core.error.model.ValidationEmailErrorDto;
import com.fitcrew.validatorservice.core.util.EmailErrorDtoUtil;
import com.fitcrew.validatorservice.validator.predicates.EmailDtoAllValuesPredicate;
import com.fitcrew.validatorservice.validator.predicates.MinMaxCharsPredicate;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import org.springframework.stereotype.Component;

@Component
public class EmailValidator {

    public Validation<Seq<ValidationEmailErrorDto>, EmailDto> validate(EmailDto dto) {
        return Validation.combine(
                validateValue(dto),
                validateSender(dto),
                validateRecipient(dto),
                validateSubject(dto))
                .ap((order1, order2, order3, order4) -> order4);
    }

    Validation<ValidationEmailErrorDto, EmailDto> validateValue(EmailDto emailDto) {
        return new EmailDtoAllValuesPredicate().test(emailDto)
                ? Validation.valid(emailDto)
                : Validation.invalid(EmailErrorDtoUtil.buildValueEmailErrorDto(emailDto));
    }

    Validation<ValidationEmailErrorDto, EmailDto> validateSender(EmailDto emailDto) {
        return new MinMaxCharsPredicate().test(emailDto.getSender(), 20)
                ? Validation.valid(emailDto)
                : Validation.invalid(EmailErrorDtoUtil.buildSenderErrorDto(emailDto));
    }

    Validation<ValidationEmailErrorDto, EmailDto> validateSubject(EmailDto emailDto) {
        return new MinMaxCharsPredicate().test(emailDto.getSubject(), 20)
                ? Validation.valid(emailDto)
                : Validation.invalid(EmailErrorDtoUtil.buildSubjectErrorDto(emailDto));
    }

    Validation<ValidationEmailErrorDto, EmailDto> validateRecipient(EmailDto emailDto) {
        return new MinMaxCharsPredicate().test(emailDto.getRecipient(), 50)
                ? Validation.valid(emailDto)
                : Validation.invalid(EmailErrorDtoUtil.buildRecipientErrorDto(emailDto));
    }
}
