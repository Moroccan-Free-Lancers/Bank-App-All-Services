package com.apps.ws.repositories;

import com.apps.ws.entities.CheckEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.awt.desktop.OpenFilesEvent;
import java.util.List;
import java.util.Optional;

@Repository
public interface CheckRepository extends CrudRepository<CheckEntity, Long> {

    List<CheckEntity> findAllByUserId(String userId);
    Optional<CheckEntity> findChequeEntityByChequeId(String checkId);

}
