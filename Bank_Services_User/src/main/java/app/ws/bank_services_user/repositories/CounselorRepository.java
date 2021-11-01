package app.ws.bank_services_user.repositories;

import app.ws.bank_services_user.entities.CounselorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CounselorRepository extends CrudRepository<CounselorEntity, Long> {

    Optional<CounselorEntity> findByMail(String mail);

    Optional<CounselorEntity> findByCounselorId(String counselorId);

}
