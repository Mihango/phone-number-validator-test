package ke.co.sample;

import ke.co.sample.config.PhoneValidatorProperties;
import ke.co.sample.repositories.CustomerPhoneStateRepository;
import ke.co.sample.repositories.CustomerRepository;
import ke.co.sample.repositories.entities.CustomerPhoneState;
import ke.co.sample.services.PhonePatterns;
import ke.co.sample.utils.PhoneNumberStatus;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.*;
import java.util.concurrent.Executor;

@SpringBootApplication
@EnableConfigurationProperties(PhoneValidatorProperties.class)
public class PhoneValidatorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhoneValidatorServiceApplication.class, args);
    }

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("PhoneValidator-");
        executor.initialize();
        return executor;
    }
}
