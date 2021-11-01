package app.ws.bank_services_user.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgencyResponse {
    private String agenceId;
    private String nomAgence;
    private String adresseAgence;
    private String responsableAgence;
    private String telephoneAgence;
}
