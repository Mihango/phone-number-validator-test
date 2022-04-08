package ke.co.sample.utils;

import lombok.Data;

import java.util.regex.Pattern;

public class PhoneValidator {
    // default pattern
    public static final String PHONE_PATTERN = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$";

    static PhoneValidator phoneValidator;
    Pattern pattern;

    /**
     * Private constructor for singleton pattern with pattern
     */
    private PhoneValidator(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }

    /**
     * Validate phone number
     * @return Boolean
     */
    public boolean isValid(String phone) {
        return pattern.matcher(phone).matches();
    }

    public Pattern getPattern() {
        return pattern;
    }

    /**
     * Singleton pattern
     * @return PhoneValidator
     */
    public static PhoneValidator getInstance(String pattern) {
        if (pattern == null) {
            pattern = PHONE_PATTERN;
        }

        if(phoneValidator == null) {
            phoneValidator = new PhoneValidator(pattern);
        }

        return phoneValidator;
    }
}
