package ma.master.compte.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import ma.master.compte.Entity.DocumentsEntity;


@Repository
public interface DocumentRepository extends JpaRepository<DocumentsEntity, Long>{

	
}
