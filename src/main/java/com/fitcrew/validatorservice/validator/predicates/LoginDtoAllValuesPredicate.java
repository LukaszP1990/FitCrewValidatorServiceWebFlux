package com.fitcrew.validatorservice.validator.predicates;

import com.fitcrew.FitCrewAppModel.domain.dto.LoginDto;

import java.util.Objects;
import java.util.function.Predicate;

public class LoginDtoAllValuesPredicate implements Predicate<LoginDto> {

    @Override
    public boolean test(LoginDto loginDto) {
        return Objects.nonNull(loginDto.getEmail()) && Objects.nonNull(loginDto.getPassword());
    }
}
