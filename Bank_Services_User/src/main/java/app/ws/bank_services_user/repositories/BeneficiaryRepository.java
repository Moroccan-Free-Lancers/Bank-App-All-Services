package app.ws.bank_services_user.repositories;

import app.ws.bank_services_user.entities.BeneficiaryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeneficiaryRepository extends CrudRepository<BeneficiaryEntity, Long> {

    Optional<BeneficiaryEntity> findByBeneficiaryId(String beneficiaryId);
}
