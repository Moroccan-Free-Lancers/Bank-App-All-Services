package bank.app.card_service.repositories;

import bank.app.card_service.entities.Ceiling;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CeilingRepository extends CrudRepository<Ceiling, Long> {

    Optional<Ceiling> findByCeilingId(String ceilingId);
}