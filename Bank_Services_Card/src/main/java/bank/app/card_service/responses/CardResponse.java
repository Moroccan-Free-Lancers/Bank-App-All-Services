package bank.app.card_service.responses;

import bank.app.card_service.entities.Ceiling;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CardResponse {

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
