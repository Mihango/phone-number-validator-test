package ke.co.sample.resources;

import ke.co.sample.repositories.CustomerPhoneStateRepository;
import ke.co.sample.repositories.CustomerRepository;
import ke.co.sample.repositories.entities.Country;
import ke.co.sample.repositories.entities.Customer;
import ke.co.sample.repositories.entities.CustomerPhoneState;
import ke.co.sample.services.PhonePatterns;
import ke.co.sample.services.PhoneValidatorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/validator")
public class PhoneValidatorResource {

    private final PhoneValidatorService phoneValidatorService;
    private final CustomerRepository customerRepository;
    private final PhonePatterns phonePatterns;
    private final CustomerPhoneStateRepository stateRepository;


    public PhoneValidatorResource(PhoneValidatorService phoneValidatorService, CustomerRepository customerRepository, PhonePatterns phonePatterns, CustomerPhoneStateRepository stateRepository) {
        this.phoneValidatorService = phoneValidatorService;
        this.customerRepository = customerRepository;
        this.phonePatterns = phonePatterns;
        this.stateRepository = stateRepository;
    }

    @GetMapping("/phone/{phone}")
    @ResponseBody
    public boolean isPhoneValid(@PathVariable String phone) {
        return phoneValidatorService.isValidPhone(phone);
    }

    @GetMapping("/customers")
    @ResponseBody
    public Iterable<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/patterns")
    @ResponseBody
    public Iterable<Country> getCountries() {
        return phonePatterns.getCountries();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/status")
    @ResponseBody
    public Iterable<CustomerPhoneState> getPhoneStatus() {
        return stateRepository.findAll();
    }
}
