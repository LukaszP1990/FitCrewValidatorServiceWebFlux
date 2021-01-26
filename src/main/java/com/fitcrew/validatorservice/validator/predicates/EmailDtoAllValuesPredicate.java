package com.fitcrew.validatorservice.validator.predicates;

import com.fitcrew.FitCrewAppModel.domain.dto.EmailDto;
import com.fitcrew.validatorservice.core.util.ValueUtil;

import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmailDtoAllValuesPredicate implements Predicate<EmailDto> {

    @Override
    public boolean test(EmailDto emailDto) {
        var listOfFields = Stream.of(
                emailDto.getSubject(),
                emailDto.getRecipient(),
                emailDto.getBodyOfMessage(),
                emailDto.getSender())
                .collect(Collectors.toList());
        return ValueUtil.validateStrings(listOfFields);
    }
}
