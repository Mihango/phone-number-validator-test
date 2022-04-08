package ke.co.sample.repositories;

import ke.co.sample.repositories.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
