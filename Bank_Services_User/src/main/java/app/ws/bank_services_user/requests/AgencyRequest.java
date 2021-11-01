package app.ws.bank_services_user.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AgencyRequest {
    @NotNull
    @NotBlank(message = "Please enter the Agency's name")
    private String nomAgence;

    @NotNull
    @NotBlank(message = "Please enter the Agency's adress")
    private String adresseAgence;

    @NotNull
    @NotBlank(message = "Please enter the Responsable's name")
    private String responsableAgence;

    @NotNull
    @NotBlank(message = "Please enter the Agency's Phone")
    private String telephoneAgence;
}
