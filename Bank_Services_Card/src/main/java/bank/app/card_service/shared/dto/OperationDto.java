package bank.app.card_service.shared.dto;

import bank.app.card_service.entities.Card;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class OperationDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 7177484906353906716L;

    private Long id;
    private String operationId;
    private LocalDateTime operationDate;
    private String description ;
    private double amount ;
    private String currency ;
    private Card card;

}
