package app.ws.bank_services_user.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerResponse {

    private String customerId;

    private String username;

    private String mail;

    private String phoneNumber;

    private String agency;

    private String pictureUrl;

    private List<ProfileResponse> profiles;

    private CounselorResponse counselor;

    private List<BeneficiaryResponse> beneficiaries;
}
