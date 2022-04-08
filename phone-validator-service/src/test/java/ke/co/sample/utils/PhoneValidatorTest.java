package ke.co.sample.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PhoneValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"0712345678", "+254712345678", "001-541-754-3010", "(541) 754-3010", "+1-541-754-3010", "1-541-754-3010"})
    void givenPhoneNumbers_whenValid_thenReturnTrue(String phoneNumber) {
        // given
        PhoneValidator phoneValidator = PhoneValidator.getInstance(null);

        // when
        boolean valid = phoneValidator.isValid(phoneNumber);

        // then
        assertTrue(valid);
    }

    @ParameterizedTest
    @ValueSource(strings = {"712345678000", "+254000712345678", "001 541-754-3010", "(5412) 754-3010", "+111-541-754-3010", "111-541-754-3010", "9099712345678"})
    void givenPhoneNumbers_whenInvalid_thenReturnFalse(String phoneNumber) {
        // given
        PhoneValidator phoneValidator = PhoneValidator.getInstance(null);

        // when
        boolean invalid = phoneValidator.isValid(phoneNumber);
        System.out.println(invalid);

        // then
        assertFalse(invalid);
    }
}
