package app.ws.bank_services_user.requests;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class BeneficiaryRequest {

    @NotNull(message="This field 'firstname' must not be null !")
    @Size(min = 3, message= "This field 'firstname' must have at least 3 characters !")
    private String firstName;

    @NotNull(message="This field 'lastname' must not be null !")
    @Size(min = 3, message= "This field 'lastname' must have at least 3 characters !")
    private String lastName;

    @NotNull(message="This field 'rib' must not be null !")
    @Size(min = 24, message= "This field 'rib' must have 24 characters !")
    @Size(max = 24, message= "This field 'rib' must have 24 characters !")
    private String rib;

    @NotNull
    @NotBlank(message="Please enter description !! ")
    private String description;
}
