package app.ws.bank_services_user.repositories;

import app.ws.bank_services_user.entities.AgencyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepository extends CrudRepository<AgencyEntity, Long> {
    AgencyEntity findByAgenceId(String agenceId);

    AgencyEntity findByNomAgence(String nomAgence);

    AgencyEntity findByAdresseAgence(String adresseAgence);

    AgencyEntity findByResponsableAgence(String responsableAgence);

    AgencyEntity findByTelephoneAgence(String telephoneAgence);
}
