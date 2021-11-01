package bank.app.card_service.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CardRequest {

    @NotNull
    @NotBlank
    private String cardName;
    @NotNull
    @NotBlank
    private String cardBalance;


}
