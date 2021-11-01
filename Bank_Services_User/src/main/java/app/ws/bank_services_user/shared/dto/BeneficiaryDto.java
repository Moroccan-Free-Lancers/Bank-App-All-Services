package app.ws.bank_services_user.shared.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BeneficiaryDto implements Serializable {
    private static final long serialVersionUID = -3186242627094096496L;

    private Long id;
    private String beneficiaryId;
    private String firstName;
    private String lastName;
    private String rib;
    private String description;
    private CustomerDto customer;
}
