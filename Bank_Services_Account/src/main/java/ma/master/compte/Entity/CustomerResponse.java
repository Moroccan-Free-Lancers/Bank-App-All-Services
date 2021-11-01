package ma.master.compte.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponse {

    private String customerId;

    private String username;

    private String mail;

    private String phoneNumber;

    private String agency;

    private String pictureUrl;

}
