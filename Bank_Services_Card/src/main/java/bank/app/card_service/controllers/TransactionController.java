package bank.app.card_service.controllers;

import bank.app.card_service.entities.AccountResponse;
import bank.app.card_service.requests.TransactionRequest;
import bank.app.card_service.responses.TransactionResponse;
import bank.app.card_service.services.impl.TransactionServiceImpl;
import bank.app.card_service.shared.dto.TransactionDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionServiceImpl transactionServiceImpl;

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/get/{transactionId}")
    public TransactionResponse getTransaction(@PathVariable("transactionId") String transactionId) {

        TransactionDto transactionDto = transactionServiceImpl.getTransaction(transactionId);
        TransactionResponse transactionResponse = new TransactionResponse();
        BeanUtils.copyProperties(transactionDto, transactionResponse);

        return transactionResponse;
    }

    @PostMapping(path = "/post/{sourceAccId}/to/{targetAccId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TransactionResponse postTransaction(@PathVariable("sourceAccId") String sourceAccId, @PathVariable("targetAccId") String targetAccId,
                                               @Valid @RequestBody TransactionRequest transactionRequest) {

        TransactionDto transactionDto = new TransactionDto();
        BeanUtils.copyProperties(transactionRequest, transactionDto);

        transactionDto.setSourceAccountId(sourceAccId);
        transactionDto.setTargetAccountId(targetAccId);

        AccountResponse sourceAcc = restTemplate.getForObject("http://localhost:8081/account/get/"
                + sourceAccId, AccountResponse.class);

        double sourceNewBalance = sourceAcc.getBalance() - transactionDto.getAmount();

        restTemplate.put("http://localhost:8081/account/put/" + sourceAccId + "?newBalance="
                + String.valueOf(sourceNewBalance), AccountResponse.class);


        AccountResponse targetAcc = restTemplate.getForObject("http://localhost:8081/account/get/"
                + targetAccId, AccountResponse.class);

        double targetNewBalance = targetAcc.getBalance() + transactionDto.getAmount();

        restTemplate.put("http://localhost:8081/account/put/" + targetAccId + "?newBalance="
                + String.valueOf(targetNewBalance), AccountResponse.class);

        TransactionDto transactionCreated = transactionServiceImpl.postTransaction(transactionDto);
        TransactionResponse transactionResponse = new TransactionResponse();
        BeanUtils.copyProperties(transactionCreated, transactionResponse);

        return transactionResponse;
    }
}
