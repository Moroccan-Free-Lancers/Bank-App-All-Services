package bank.app.card_service.services.impl;


import bank.app.card_service.entities.Transaction;
import bank.app.card_service.exceptions.TransactionException;
import bank.app.card_service.repositories.TransactionRepository;
import bank.app.card_service.services.TransactionService;
import bank.app.card_service.shared.Utils;
import bank.app.card_service.shared.dto.TransactionDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    Utils utils;

    @Override
    public TransactionDto getTransaction(String transactionId) {

        ModelMapper modelMapper = new ModelMapper();
        Transaction transaction = transactionRepository.findTransactionByTransactionId(transactionId).orElseThrow(
                () -> new TransactionException("Transaction not found !"));

        return modelMapper.map(transaction, TransactionDto.class);
    }

    @Override
    public TransactionDto postTransaction(TransactionDto transactionDto) {

        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(transactionDto, transaction);
        transaction.setTransactionId(utils.generatedStringId(32));
        transaction.setTransactionDate(LocalDateTime.now());
        Transaction transactionCreated = transactionRepository.save(transaction);
        TransactionDto transactionDtoTransfer = new TransactionDto();
        BeanUtils.copyProperties(transactionCreated, transactionDtoTransfer);

        return transactionDtoTransfer;
    }
}
