package bank.app.card_service.services;

import bank.app.card_service.shared.dto.CeilingDto;

import java.util.List;

public interface CeilingService {

    CeilingDto getCeiling(String ceilingId);
    CeilingDto postCeiling(CeilingDto ceilingDto);
    List<CeilingDto> getCeilings();

}
