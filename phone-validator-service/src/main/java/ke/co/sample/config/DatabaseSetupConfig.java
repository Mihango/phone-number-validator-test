package ke.co.sample.config;

import ke.co.sample.repositories.CustomerPhoneStateRepository;
import ke.co.sample.repositories.CustomerRepository;
import ke.co.sample.repositories.entities.CustomerPhoneState;
import ke.co.sample.services.PhonePatterns;
import ke.co.sample.utils.PhoneNumberStatus;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.*;

@Configuration
public class DatabaseSetupConfig {

    private final Environment env;

    public DatabaseSetupConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("driver-class-name")));
        dataSource.setUrl(env.getProperty("url"));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("ke.co.sample.repositories.entities");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(additionalProperties());
        return em;
    }

    final Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();
        if (env.getProperty("hibernate.hbm2ddl.auto") != null) {
            hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        }
        if (env.getProperty("hibernate.dialect") != null) {
            hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        }
        if (env.getProperty("hibernate.show_sql") != null) {
            hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        }
        return hibernateProperties;
    }


    /**
     * Command line runner to classify and validate customer phone numbers to countries
     */
    @Log4j2
    @Component
    static class PhoneClassification implements CommandLineRunner {
        private final PhonePatterns phonePatterns;
        private final CustomerRepository customerRepository;
        private final CustomerPhoneStateRepository customerPhoneStateRepository;

        PhoneClassification(PhonePatterns phonePatterns, CustomerRepository customerRepository, CustomerPhoneStateRepository customerPhoneStateRepository) {
            this.phonePatterns = phonePatterns;
            this.customerRepository = customerRepository;
            this.customerPhoneStateRepository = customerPhoneStateRepository;
        }

        @Override
        public void run(String... args) throws Exception {
            // get all supported patterns from the database
            Map<String, PhonePatterns.CountryPattern> supportedPatterns = phonePatterns.getSupportedPatterns();

            // load customer phone numbers from database
            List<CustomerPhoneState> stateList = new ArrayList<>();

            // classify and validate phone numbers
            customerRepository.findAll().forEach(customer -> {
                String phoneNumber = customer.getPhone();

                // check if phone number is supported
                String code = getCountryCode(phoneNumber);
                CustomerPhoneState state = new CustomerPhoneState();
                if (supportedPatterns.containsKey(code)) {
                    // check if phone number is valid
                    state.setCountryId(supportedPatterns.get(code).getCountry().getId());
                    state.setCustomerPhoneId(customer.getId());

                    if (supportedPatterns.get(code).getPattern().matcher(phoneNumber).matches()) {
                        state.setStatus(PhoneNumberStatus.VALID);
                    } else {
                        state.setStatus(PhoneNumberStatus.INVALID);
                    }
                    log.info("Phone number {} is {}", phoneNumber, state.getStatus());
                } else {
                    // phone number is not supported
                    log.info("Phone number {} is not supported", phoneNumber);
                }
                stateList.add(state);
            });

            // drop all existing phone numbers
            customerPhoneStateRepository.deleteAll();
            // save phone number states to database
            customerPhoneStateRepository.saveAll(stateList);
        }

        /**
         * Get country code from phone number
         *
         * @param phoneNumber phone number
         * @return country code
         */
        private String getCountryCode(String phoneNumber) {
            if (phoneNumber.startsWith("(")) {
                return "+" + phoneNumber.substring(1, 4);
            } else if (phoneNumber.startsWith("+")) {
                return "+" + phoneNumber.substring(1, 3);
            } else {
                return "+" + phoneNumber.substring(0, 2);
            }
        }
    }
}
