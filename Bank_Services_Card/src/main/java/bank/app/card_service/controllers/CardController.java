package bank.app.card_service.controllers;


import bank.app.card_service.entities.AccountResponse;
import bank.app.card_service.requests.CardRequest;
import bank.app.card_service.responses.CardResponse;
import bank.app.card_service.responses.CeilingResponse;
import bank.app.card_service.responses.OperationResponse;
import bank.app.card_service.services.impl.CardServiceImpl;
import bank.app.card_service.shared.dto.CardDto;
import bank.app.card_service.shared.dto.CeilingDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardServiceImpl cardServiceImpl;

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/get/{carteId}")
    public CardResponse getCard(@PathVariable("carteId") String carteId){

        CardDto cardDto = cardServiceImpl.getCard(carteId);
        CardResponse cardResponse = new CardResponse();
        BeanUtils.copyProperties(cardDto,cardResponse);

        return cardResponse;
    }

    @PostMapping(path = "/post/{accountId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CardResponse postCard(@PathVariable String accountId, @Valid @RequestBody CardRequest cardRequest) {

        // The account checher using restTemplate with Bearer Token

        AccountResponse account = restTemplate.getForObject("http://localhost:8081/account/get/"
                    + accountId, AccountResponse.class);

        CardDto cardDto = new CardDto();
        BeanUtils.copyProperties(cardRequest, cardDto);
        cardDto.setAccountId(accountId);
        CardDto cardCreated = cardServiceImpl.postCard(cardDto);
        CardResponse cardResponse = new CardResponse();
        BeanUtils.copyProperties(cardCreated, cardResponse);

        return cardResponse;
    }

    @GetMapping(
            value = "/getall/{accountId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<CardResponse> getCards(@PathVariable("accountId") String accountId) {

        // The account checher using restTemplate with Bearer Token

        AccountResponse account = restTemplate.getForObject("http://localhost:8081/account/get/"
                    + accountId, AccountResponse.class);

        ModelMapper modelMapper = new ModelMapper();
        List<CardResponse> cardsResponse = new ArrayList<>();
        cardServiceImpl.getCardsByUser(accountId).forEach(card -> cardsResponse.add(modelMapper.map(card, CardResponse.class)));

        return cardsResponse;
    }

    @GetMapping("/dotation/{carteId}")
    public boolean getDtation(@PathVariable("carteId") String carteId){

        return cardServiceImpl.getDotation(carteId);
    }

    @PutMapping(path = "/dotation/{carteId}")
    public String updateDotation(@PathVariable("carteId") String carteId, @RequestParam boolean newDotation){

        return  cardServiceImpl.updateDotation(carteId,newDotation);
    }

    @GetMapping("/status/{carteId}")
    public boolean getStatus(@PathVariable("carteId") String carteId){

        return cardServiceImpl.getStatus(carteId);
    }

    @PutMapping(path = "/status/{carteId}")
    public String updateStatus(@PathVariable("carteId") String carteId, @RequestParam boolean newStatus){

        return cardServiceImpl.updateStatus(carteId,newStatus);
    }

    @GetMapping("/opposed/{carteId}")
    public boolean getOpposed(@PathVariable("carteId") String carteId){

        return cardServiceImpl.getOpposed(carteId);
    }

    @PutMapping(path = "/opposed/{carteId}")
    public String updateOpposed(@PathVariable("carteId") String carteId, @RequestParam boolean newOpposed){

        return cardServiceImpl.updateOpposed(carteId,newOpposed);
    }

    @GetMapping(path = "/ceiling/{carteId}")
    public CeilingResponse getCeilingByCardId(@PathVariable("carteId") String carteId){

        CeilingDto ceilingDto = cardServiceImpl.getCeilingByCardId(carteId);
        CeilingResponse ceilingResponse = new CeilingResponse();
        BeanUtils.copyProperties(ceilingDto,ceilingResponse);

        return ceilingResponse;
    }

    @PutMapping(path = "/ceiling/{carteId}")
    public String updateCeilingOfCardById(@PathVariable("carteId") String carteId, @RequestParam String newCeiling){

        return cardServiceImpl.changeCardCeiling(carteId,newCeiling);
    }

    @GetMapping(path = "/operations/{carteId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<OperationResponse> getOperationsByCardId(@PathVariable("carteId") String carteId){

            ModelMapper modelMapper = new ModelMapper();
            List<OperationResponse> operationsResponse = new ArrayList<>();
            cardServiceImpl.getOperationsByCardId(carteId) .forEach(operationDto -> operationsResponse.add(modelMapper.map(operationDto, OperationResponse.class)));

            return operationsResponse;
        }

}
