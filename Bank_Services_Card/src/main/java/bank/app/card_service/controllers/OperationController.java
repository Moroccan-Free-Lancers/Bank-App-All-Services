package bank.app.card_service.controllers;

import bank.app.card_service.entities.Card;
import bank.app.card_service.exceptions.OperationException;
import bank.app.card_service.repositories.CardRepository;
import bank.app.card_service.requests.OperationRequest;
import bank.app.card_service.responses.OperationResponse;
import bank.app.card_service.services.impl.CardServiceImpl;
import bank.app.card_service.services.impl.OperationServiceImpl;
import bank.app.card_service.shared.dto.OperationDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/operation")
public class OperationController {

    @Autowired
    OperationServiceImpl operationServiceImpl;

    @Autowired
    CardServiceImpl cardServiceImpl;

    @Autowired
    CardRepository cardRepository;

    @GetMapping("/get/{operationId}")
    public OperationResponse getOpe(@PathVariable("operationId") String operationId) {

        OperationDto operationDto = operationServiceImpl.getOperation(operationId);
        OperationResponse operationResponse = new OperationResponse();
        BeanUtils.copyProperties(operationDto, operationResponse);

        return operationResponse;
    }

    @PostMapping(path = "/post/{cardId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OperationResponse postOperation(@PathVariable String cardId, @Valid @RequestBody OperationRequest operationRequest) {

        OperationDto operationDto = new OperationDto();
        BeanUtils.copyProperties(operationRequest, operationDto);
        Optional<Card> card = cardRepository.findByCardId(cardId);
        if (operationRequest.getDescription().equalsIgnoreCase("retirer")){
            card.get().setCardBalance(card.get().getCardBalance() - operationRequest.getAmount());
            cardRepository.save(card.get());
        }else if (operationRequest.getDescription().equalsIgnoreCase("verser")){
            card.get().setCardBalance(card.get().getCardBalance() + operationRequest.getAmount());
            cardRepository.save(card.get());
        }else{
            throw new OperationException("Operation not fount , you can only remove or deposit !");
        }
        BeanUtils.copyProperties(cardServiceImpl.getCard(cardId), card);
        operationDto.setCard(card.get());
        OperationDto operationCreated = operationServiceImpl.PostOperation(operationDto);
        OperationResponse operationResponse = new OperationResponse();
        BeanUtils.copyProperties(operationCreated, operationResponse);

        return operationResponse;
    }

}
