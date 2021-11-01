package app.ws.bank_services_user.shared.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AgencyDto implements Serializable {

    private static final long serialVersionUID = -3686547190878829413L;

    private long id;
    private String agenceId;
    private String nomAgence;
    private String adresseAgence;
    private String responsableAgence;
    private String telephoneAgence;
}
