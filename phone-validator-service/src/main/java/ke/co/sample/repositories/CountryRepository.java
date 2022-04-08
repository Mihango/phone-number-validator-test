package ke.co.sample.repositories;

import ke.co.sample.repositories.entities.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CountryRepository extends CrudRepository<Country, Long> {
    Optional<Country> findByCode(String code);
}
