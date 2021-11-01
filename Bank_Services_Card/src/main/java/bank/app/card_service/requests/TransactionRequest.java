package bank.app.card_service.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TransactionRequest{

    @NotNull
    private double amount ;

}
