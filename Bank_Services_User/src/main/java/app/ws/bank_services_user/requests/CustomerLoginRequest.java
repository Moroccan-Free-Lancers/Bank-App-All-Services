package app.ws.bank_services_user.requests;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CustomerLoginRequest {
    private String mail;
    private String password;
}
