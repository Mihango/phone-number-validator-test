package ke.co.sample.services.impl;

import ke.co.sample.repositories.CountryRepository;
import ke.co.sample.repositories.entities.Country;
import ke.co.sample.services.PhonePatterns;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Log4j2
@Service
public class PhonePatternsImpl implements PhonePatterns {
    private final CountryRepository countryRepository;
    Map<String, CountryPattern> supportedPatterns = new HashMap<>();

    public PhonePatternsImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    /**
     * get all supported countries
     *
     * @return Iterable<Country> a list of countries
     */
    @Override
    public Iterable<Country> getCountries() {
        return countryRepository.findAll();
    }

    /**
     * @return a map of country code to regex pattern
     */
    public Map<String, CountryPattern> getSupportedPatterns() {
        if (supportedPatterns.isEmpty()) {
            countryRepository.findAll().forEach(country ->
                    supportedPatterns.put(country.getCode(), new CountryPattern(country, Pattern.compile(country.getPhonePattern()))));
        }
        supportedPatterns.forEach((key, value) -> log.info(key + " >>>> " + value.getPattern()));
        return supportedPatterns;
    }
}
