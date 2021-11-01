package app.ws.bank_services_user.shared.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProfileDto implements Serializable {
    private static final long serialVersionUID = 5957874046663692068L;

    private Long id;

    private String name;

    private CustomerDto customer;
}
