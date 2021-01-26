package com.fitcrew.validatorservice.validator.admin;

import com.fitcrew.validatorservice.core.provider.DataValidationProvider;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;

import static com.fitcrew.validatorservice.validator.util.AdminUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AdminValidatorTest {

    private final DataValidationProvider dataValidationProvider = mock(DataValidationProvider.class);
    private final AdminValidator adminValidator = new AdminValidator(dataValidationProvider);

    static Stream<AdminData> adminDataProvider() {
        var adminWithoutPassword = getAdminDto(ADMIN_ID, ADMIN_EMAIL, ADMIN_FIRST_NAME, ADMIN_LAST_NAME, null);
        var adminWithoutLastName = getAdminDto(ADMIN_ID, ADMIN_EMAIL, ADMIN_FIRST_NAME, null, PASSWORD);
        var adminWithoutFirstName = getAdminDto(ADMIN_ID, ADMIN_EMAIL, null, ADMIN_LAST_NAME, PASSWORD);
        var adminWithoutEmail = getAdminDto(ADMIN_ID, null, ADMIN_FIRST_NAME, ADMIN_LAST_NAME, PASSWORD);
        var adminWithAllData = getAdminDto(ADMIN_ID, ADMIN_EMAIL, ADMIN_FIRST_NAME, ADMIN_LAST_NAME, PASSWORD);
        return Stream.of(
                new AdminData(adminWithoutPassword, false),
                new AdminData(adminWithoutLastName, false),
                new AdminData(adminWithoutFirstName, false),
                new AdminData(adminWithoutEmail, false),
                new AdminData(adminWithAllData, true)
        );
    }

    @ParameterizedTest
    @MethodSource("adminDataProvider")
    void validateTest(AdminData adminData) {
        //given
        when(dataValidationProvider.getAdminById(anyString()))
                .thenReturn(Mono.empty());

        //when
        var result = adminValidator.validate(adminData.getAdminDto());

        //then
        checkAssertion(result.isValid(), adminData.isResult());
    }

    private void checkAssertion(boolean result,
                                boolean expected) {
        assertEquals(expected, result);
    }
}