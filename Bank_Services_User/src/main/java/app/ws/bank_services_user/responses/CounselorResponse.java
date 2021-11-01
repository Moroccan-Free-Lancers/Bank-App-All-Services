package app.ws.bank_services_user.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CounselorResponse {

    private String counselorId;

    private String username;

    private String mail;

    private String phoneNumber;

    private String agency;

    private String agencyAdresse;
}
