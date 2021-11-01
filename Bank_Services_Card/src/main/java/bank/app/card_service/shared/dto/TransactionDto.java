package bank.app.card_service.shared.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -8600150069022319390L;

    private Long id;
    private String transactionId;
    private String sourceAccountId;
    private String targetAccountId;
    private double amount ;
    private LocalDateTime transactionDate;
    
}
