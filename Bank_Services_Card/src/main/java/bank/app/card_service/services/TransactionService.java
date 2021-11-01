package bank.app.card_service.services;

import bank.app.card_service.shared.dto.TransactionDto;

public interface TransactionService {

    TransactionDto getTransaction(String transactionId);
    TransactionDto postTransaction(TransactionDto transactionDto);
}
