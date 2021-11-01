package ma.master.compte.Repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.master.compte.Entity.OperationEntity;


@Repository
public interface OperationRepository extends JpaRepository<OperationEntity, Long>{
	
	

}
