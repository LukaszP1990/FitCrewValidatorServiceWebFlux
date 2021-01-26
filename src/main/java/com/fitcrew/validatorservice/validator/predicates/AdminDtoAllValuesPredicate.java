package com.fitcrew.validatorservice.validator.predicates;

import com.fitcrew.FitCrewAppModel.domain.dto.AdminDto;
import com.fitcrew.validatorservice.core.util.ValueUtil;

import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdminDtoAllValuesPredicate implements Predicate<AdminDto> {

    @Override
    public boolean test(AdminDto adminDto) {
        var listOfFields = Stream.of(
                adminDto.getFirstName(),
                adminDto.getLastName(),
                adminDto.getEmail(),
                adminDto.getPassword(),
                adminDto.getAdminId())
                .collect(Collectors.toList());
        return ValueUtil.validateStrings(listOfFields);
    }
}
