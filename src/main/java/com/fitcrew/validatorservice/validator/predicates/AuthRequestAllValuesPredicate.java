package com.fitcrew.validatorservice.validator.predicates;

import com.fitcrew.jwt.model.AuthenticationRequest;
import org.apache.commons.lang3.ObjectUtils;

import java.util.function.Predicate;

public class AuthRequestAllValuesPredicate implements Predicate<AuthenticationRequest> {

    @Override
    public boolean test(AuthenticationRequest authRequest) {
        return ObjectUtils.allNotNull(authRequest.getEmail(), authRequest.getPassword(), authRequest.getRole());
    }
}
