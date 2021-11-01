package app.ws.bank_services_user.shared.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CounselorDto implements Serializable {
    private static final long serialVersionUID = -2562157826905940122L;

    private Long id;

    private String counselorId;

    private String username;

    private String mail;

    private String phoneNumber;

    private String agency;

    private String agencyAdresse;

    private Set<CustomerDto> customers = new HashSet<CustomerDto>();;

}
