package ke.co.sample.repositories;

import ke.co.sample.repositories.entities.CustomerPhoneState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CustomerPhoneStateRepository extends JpaRepository<CustomerPhoneState, Long> {

}
