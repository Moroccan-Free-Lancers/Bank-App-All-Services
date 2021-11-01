package bank.app.card_service.shared.dto;

import bank.app.card_service.entities.Ceiling;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class CardDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 6565972569893973213L;

    private Long id;
    private String cardId;
    private String cardName;
    private String cardNumber;
    private String accountId;
    private boolean status; /* activated 1 / desactivated 0 */
    private boolean opposed;
    private LocalDateTime issueDate; /* date de creation */
    private double cardBalance;
    private LocalDateTime validityDate;
    private LocalDateTime experationDate;
    private boolean onlinePayment;
    private boolean contactless;
    private boolean withdrawal;
    private Ceiling ceiling;

}
