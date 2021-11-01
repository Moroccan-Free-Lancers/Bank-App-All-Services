package app.ws.bank_services_user.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BeneficiaryResponse {

    private String beneficiaryId;
    private String firstName;
    private String lastName;
    private String rib;
    private String description;
}
