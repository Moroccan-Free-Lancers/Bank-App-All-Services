package bank.app.card_service.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OperationResponse {

    private String operationId;
    private LocalDateTime operationDate;
    private String description ;
    private double amount ;
    private String currency ;

}
