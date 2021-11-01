package bank.app.card_service.services;

import bank.app.card_service.shared.dto.CardDto;
import bank.app.card_service.shared.dto.CeilingDto;
import bank.app.card_service.shared.dto.OperationDto;

import java.util.List;

public interface CardService {
    CardDto getCard(String cardId);
    CardDto postCard(CardDto cardDto);
    List<CardDto> getCardsByUser(String accountId);
    boolean getDotation(String cardId);
    String updateDotation(String cardId, boolean newDotation);
    boolean getStatus(String cardId);
    String updateStatus(String cardId, boolean newStatus);
    boolean getOpposed(String cardId);
    String updateOpposed(String cardId, boolean newOpposed);
    CeilingDto getCeilingByCardId(String carteId);
    String  changeCardCeiling(String carteId, String ceilingId);
    List<OperationDto> getOperationsByCardId(String cardId);
}
