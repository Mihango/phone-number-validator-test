package ke.co.sample.services;

import ke.co.sample.config.PhoneValidatorProperties;
import ke.co.sample.utils.PhoneValidator;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class PhoneValidatorService {

    private final PhoneValidator phoneValidator;

    public PhoneValidatorService(PhoneValidatorProperties phoneValidatorProperties) {
        this.phoneValidator = PhoneValidator.getInstance(phoneValidatorProperties.getPattern());
        log.info(">>>>>>>>> PhoneValidatorService pattern: " + phoneValidatorProperties.getPattern() + " <<<<<<<<<<");
    }

    public boolean isValidPhone(String phone) {
        return phoneValidator.isValid(phone);
    }
}
