package ma.master.compte.Repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.master.compte.Entity.AccountEntity;


@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String>{
	
    Optional<AccountEntity> findByCompteId(String compteId);
    List<AccountEntity> findAllByUserId(String userId);

}
