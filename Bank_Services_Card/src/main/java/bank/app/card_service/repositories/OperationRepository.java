package bank.app.card_service.repositories;


import bank.app.card_service.entities.Operation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OperationRepository  extends CrudRepository<Operation, Long>  {
    Optional<Operation> findOperationByOperationId(String operationId);
}
