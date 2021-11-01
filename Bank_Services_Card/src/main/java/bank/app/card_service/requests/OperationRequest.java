package bank.app.card_service.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OperationRequest {

    @NotNull
    @NotBlank
    private String description ;
    @NotNull
    private double amount ;
    @NotNull
    @NotBlank
    private String currency ;

}
