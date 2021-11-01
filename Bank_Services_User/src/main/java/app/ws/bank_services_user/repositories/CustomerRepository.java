package app.ws.bank_services_user.repositories;

import app.ws.bank_services_user.entities.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByMail(String mail);

    Optional<CustomerEntity> findByCustomerId(String customerId);
}
