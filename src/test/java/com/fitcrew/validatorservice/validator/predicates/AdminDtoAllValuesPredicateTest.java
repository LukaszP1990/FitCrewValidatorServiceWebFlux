package com.fitcrew.validatorservice.validator.predicates;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.fitcrew.validatorservice.validator.util.AdminUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AdminDtoAllValuesPredicateTest {

    private final AdminDtoAllValuesPredicate predicate = new AdminDtoAllValuesPredicate();

    static Stream<AdminData> adminDataProvider() {
        var adminWithoutPassword = getAdminDto(ADMIN_ID, ADMIN_EMAIL, ADMIN_FIRST_NAME, ADMIN_LAST_NAME, null);
        var adminWithoutLastName = getAdminDto(ADMIN_ID, ADMIN_EMAIL, ADMIN_FIRST_NAME, null, PASSWORD);
        var adminWithoutFirstName = getAdminDto(ADMIN_ID, ADMIN_EMAIL, null, ADMIN_LAST_NAME, PASSWORD);
        var adminWithoutEmail = getAdminDto(ADMIN_ID, null, ADMIN_FIRST_NAME, ADMIN_LAST_NAME, PASSWORD);
        var adminWithoutAdminId = getAdminDto(null, ADMIN_EMAIL, ADMIN_FIRST_NAME, ADMIN_LAST_NAME, PASSWORD);
        var adminWithAllData = getAdminDto(ADMIN_ID, ADMIN_EMAIL, ADMIN_FIRST_NAME, ADMIN_LAST_NAME, PASSWORD);
        return Stream.of(
                new AdminData(adminWithoutPassword, false),
                new AdminData(adminWithoutLastName, false),
                new AdminData(adminWithoutFirstName, false),
                new AdminData(adminWithoutEmail, false),
                new AdminData(adminWithoutAdminId, false),
                new AdminData(adminWithAllData, true)
        );
    }

    @ParameterizedTest
    @MethodSource("adminDataProvider")
    void adminDtoAllValuesPredicateTest(AdminData adminData) {
        boolean result = predicate.test(adminData.getAdminDto());
        assertEquals(result, adminData.isResult());
    }
}