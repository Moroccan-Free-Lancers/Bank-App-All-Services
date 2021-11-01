package bank.app.card_service.responses;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -3827219001518493954L;

    private String transactionId;
    private String sourceAccountId;
    private String targetAccountId;
    private double amount ;
    private LocalDateTime transactionDate;

}
