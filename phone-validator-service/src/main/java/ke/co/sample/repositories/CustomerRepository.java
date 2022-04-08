package ke.co.sample.repositories;

import ke.co.sample.repositories.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    long countAllByProcessed(short processed);

    Page<Customer> findByProcessed(short processed, Pageable pg);
}
