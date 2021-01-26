package com.fitcrew.validatorservice.validator.predicates;

import com.fitcrew.FitCrewAppModel.domain.dto.ClientDto;
import com.fitcrew.validatorservice.core.util.ValueUtil;

import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClientDtoAllValuesPredicate implements Predicate<ClientDto> {

    @Override
    public boolean test(ClientDto clientDto) {
        var listOfFields = Stream.of(
                clientDto.getFirstName(),
                clientDto.getLastName(),
                clientDto.getEmail(),
                clientDto.getDateOfBirth(),
                clientDto.getPhone(),
                clientDto.getPassword(),
                clientDto.getClientId())
                .collect(Collectors.toList());
        return ValueUtil.validateStrings(listOfFields);
    }
}
