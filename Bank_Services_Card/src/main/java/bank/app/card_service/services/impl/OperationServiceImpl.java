package bank.app.card_service.services.impl;


import bank.app.card_service.entities.Operation;
import bank.app.card_service.exceptions.OperationException;
import bank.app.card_service.repositories.OperationRepository;
import bank.app.card_service.services.OperationService;
import bank.app.card_service.shared.Utils;
import bank.app.card_service.shared.dto.OperationDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    OperationRepository operationRepository;

    @Autowired
    Utils utils;

    @Override
    public OperationDto getOperation(String operationId) {

        ModelMapper modelMapper = new ModelMapper();
        Operation operation = operationRepository.findOperationByOperationId(operationId).orElseThrow(
                () -> new OperationException("Operation not found !"));

        return modelMapper.map(operation, OperationDto.class);
    }

    @Override
    public OperationDto PostOperation(OperationDto operationDto) {

        Operation operation = new Operation();
        BeanUtils.copyProperties(operationDto, operation);
        operation.setOperationId(utils.generatedStringId(32));
        operation.setOperationDate(LocalDateTime.now());
        Operation operationCreated = operationRepository.save(operation);
        OperationDto operationDtoTransfer = new OperationDto();
        BeanUtils.copyProperties(operationCreated, operationDtoTransfer);

        return operationDtoTransfer;
    }
}
