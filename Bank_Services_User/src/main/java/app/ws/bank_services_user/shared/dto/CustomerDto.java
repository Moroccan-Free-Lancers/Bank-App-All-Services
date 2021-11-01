package app.ws.bank_services_user.shared.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class CustomerDto implements Serializable {
    private static final long serialVersionUID = 8930398728751359281L;

    private Long id;

    private String customerId;

    private String username;

    private String password;

    private String encryptPassword;

    private String mail;

    private String phoneNumber;

    private String agency;

    private String pictureUrl;

    private List<ProfileDto> profiles;

    private CounselorDto counselor;

    private List<BeneficiaryDto> beneficiaries;
}
