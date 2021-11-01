package com.apps.ws.services.impl;

import com.apps.ws.entities.CheckEntity;
import com.apps.ws.exceptions.CheckException;
import com.apps.ws.repositories.CheckRepository;
import com.apps.ws.services.CheckService;
import com.apps.ws.shared.Utils;
import com.apps.ws.shared.dto.CheckDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CheckServiceImpl implements CheckService {

    @Autowired
    CheckRepository checkRepository;

    @Autowired
    Utils utils;

    @Override
    public CheckDto getCheck(String checkId) {

        CheckEntity chequeById = checkRepository.findChequeEntityByChequeId(checkId).orElseThrow( () -> new CheckException("Check not found !"));
        CheckDto checkDto = new CheckDto();
        BeanUtils.copyProperties(chequeById, checkDto);

        return checkDto;
    }

    @Override
    public CheckDto postCheck(CheckDto checkDto) {

        CheckEntity checkEntity = new CheckEntity();
        BeanUtils.copyProperties(checkDto, checkEntity);
        checkEntity.setChequeId(utils.generatedStringId(32));
        checkEntity.setRequestStatus(false);
        checkEntity.setResponseStatus(false);
        checkEntity.setRequestDate(LocalDateTime.now());
        CheckEntity chequeCreated = checkRepository.save(checkEntity);
        CheckDto checkDto1 = new CheckDto();
        BeanUtils.copyProperties(chequeCreated, checkDto1);

        return checkDto1;
    }

    @Override
    public String removeCheck(String checkId) {

        CheckEntity checkEntity = checkRepository.findChequeEntityByChequeId(checkId).orElseThrow( () -> new CheckException("Check not found !"));
        checkRepository.delete(checkEntity);

        return "Check deleted";
    }

    @Override
    public List<CheckDto> getAllCheckByUser(String userId) {

        List<CheckEntity> chequeEntities = new ArrayList<>(checkRepository.findAllByUserId(userId));
        List<CheckDto> checkDtos = new ArrayList<>();
        chequeEntities.forEach(cheque -> {
            CheckDto checkDto = new CheckDto();
            BeanUtils.copyProperties(cheque, checkDto);
            checkDtos.add(checkDto);
        });

        return checkDtos;
    }

}
