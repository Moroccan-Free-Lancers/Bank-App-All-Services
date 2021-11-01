package bank.app.card_service.services;

import bank.app.card_service.shared.dto.OperationDto;

public interface OperationService {

    OperationDto getOperation(String cardId);
    OperationDto PostOperation(OperationDto operationDto);
}
