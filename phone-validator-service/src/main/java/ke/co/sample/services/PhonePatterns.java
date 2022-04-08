package ke.co.sample.services;

import ke.co.sample.repositories.entities.Country;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;
import java.util.regex.Pattern;

public interface PhonePatterns {
    Iterable<Country> getCountries();
    Map<String, CountryPattern> getSupportedPatterns();

    @Data
    @AllArgsConstructor
    class CountryPattern {
        private Country country;
        private Pattern pattern;
    }
}
