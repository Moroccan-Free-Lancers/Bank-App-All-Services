package bank.app.card_service.services.impl;

import bank.app.card_service.entities.Card;
import bank.app.card_service.entities.Ceiling;
import bank.app.card_service.entities.Operation;
import bank.app.card_service.exceptions.CardException;
import bank.app.card_service.exceptions.CeilingException;
import bank.app.card_service.repositories.CardRepository;
import bank.app.card_service.repositories.CeilingRepository;
import bank.app.card_service.services.CardService;
import bank.app.card_service.shared.Utils;
import bank.app.card_service.shared.dto.CardDto;
import bank.app.card_service.shared.dto.CeilingDto;
import bank.app.card_service.shared.dto.OperationDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CeilingRepository ceilingRepository;

    @Autowired
    Utils utils;

    @Override
    public CardDto getCard(String carteId){

        ModelMapper modelMapper = new ModelMapper();
        Card card = cardRepository.findByCardId(carteId).orElseThrow( () -> new CardException("Card not found !"));

        return modelMapper.map(card, CardDto.class);
    }

    @Override
    public CardDto postCard(CardDto cardDto){

        Card card = new Card();
        BeanUtils.copyProperties(cardDto, card);
        card.setCardId(utils.generatedStringId(32));
        long random16Digits = (long) (Math.random() * 10000000000000000L);
        card.setCardNumber(String.valueOf(random16Digits));
        card.setStatus(true);
        card.setOpposed(false);
        card.setIssueDate(LocalDateTime.now());
        card.setExperationDate(LocalDateTime.now().plusYears(10));
        card.setOnlinePayment(false);
        card.setContactless(false);
        card.setWithdrawal(false);
        card.setCeiling(ceilingRepository.findByCeilingId("JQmZg1iHtBVaoKMYkSeThctyafJs4le2").get());

        Card cardCreated = cardRepository.save(card);
        CardDto cardDtoTransfer = new CardDto();
        BeanUtils.copyProperties(cardCreated, cardDtoTransfer);

        return cardDtoTransfer;
    }

    @Override
    public List<CardDto> getCardsByUser(String accountId) {

        ModelMapper modelMapper = new ModelMapper();
        List<CardDto> cardsDto = new ArrayList<>();
        List<Card> cards = new ArrayList<>(cardRepository.findAllByAccountId(accountId));
        cards.forEach(card -> cardsDto.add(modelMapper.map(card, CardDto.class)));

        return cardsDto;
    }

    @Override
    public boolean getDotation(String carteId){

        Card card = cardRepository.findByCardId(carteId).orElseThrow( () -> new CardException("Card not found !"));
        Boolean updatedDotationValue = card.isOnlinePayment();

        return updatedDotationValue;
    }

    @Override
    @Transactional
    public String updateDotation(String carteId, boolean newDotation) {

        Card card = cardRepository.findByCardId(carteId).orElseThrow( () -> new CardException("Card not found !"));
        card.setOnlinePayment(newDotation);
        cardRepository.save(card);

        return "Dotation value updated !";
    }

    @Override
    public boolean getStatus(String carteId){

        Card card = cardRepository.findByCardId(carteId).orElseThrow( () -> new CardException("Card not found !"));
        Boolean updatedStatusValue = card.isStatus();

        return updatedStatusValue;
    }

    @Override
    @Transactional
    public String updateStatus(String carteId, boolean newStatus) {

        Card card = cardRepository.findByCardId(carteId).orElseThrow( () -> new CardException("Card not found !"));
        card.setStatus(newStatus);
        cardRepository.save(card);

        return "Status value updated !";
    }

    @Override
    public boolean getOpposed(String carteId){

        Card card = cardRepository.findByCardId(carteId).orElseThrow( () -> new CardException("Card not found !"));
        Boolean updatedOpposedValue = card.isOpposed();

        return updatedOpposedValue;
    }

    @Override
    @Transactional
    public String updateOpposed(String carteId, boolean newOpposed) {

        Card card = cardRepository.findByCardId(carteId).orElseThrow( () -> new CardException("Card not found !"));
        card.setOpposed(newOpposed);
        cardRepository.save(card);

        return "Opposed value updated !";
    }

    @Override
    public CeilingDto getCeilingByCardId(String carteId){

        ModelMapper modelMapper = new ModelMapper();
        Card card = cardRepository.findByCardId(carteId).orElseThrow( () -> new CardException("Card not found !"));
        Ceiling ceiling = card.getCeiling();
        CeilingDto ceilingDto = modelMapper.map(ceiling, CeilingDto.class);

        return ceilingDto;
    }

    @Override
    @Transactional
    public String  changeCardCeiling(String cardId, String ceilingId){

        Card card = cardRepository.findByCardId(cardId).orElseThrow( () -> new CardException("Card not found !"));
        Ceiling newCeiling = ceilingRepository.findByCeilingId(ceilingId).orElseThrow( () -> new CeilingException("Ceiling not found !"));
        card.setCeiling(newCeiling);
        cardRepository.save(card);

        return "Ceiling value updated !";
    }

    @Override
    public List<OperationDto> getOperationsByCardId(String cardId) {

        cardRepository.findByCardId(cardId).orElseThrow( () -> new CardException("Card not found !"));
        ModelMapper modelMapper = new ModelMapper();
        List<OperationDto> operationsDto = new ArrayList<>();
        List<Operation> operations = cardRepository.findByCardId(cardId).get().getOperations();
        operations.forEach(operation -> operationsDto.add(modelMapper.map(operation, OperationDto.class)));

        return operationsDto;
    }

}



