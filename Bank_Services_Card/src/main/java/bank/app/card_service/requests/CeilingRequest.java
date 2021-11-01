package bank.app.card_service.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CeilingRequest {

    @NotNull
    @NotBlank
    private String minValuePayment;
    @NotNull
    @NotBlank
    private String maxValuePayment;
    @NotNull
    @NotBlank
    private String minValueRetirement;
    @NotNull
    @NotBlank
    private String maxValueRetirement;
    @NotNull
    @NotBlank
    private String currency;
    @NotNull
    @NotBlank
    private String type;

}
