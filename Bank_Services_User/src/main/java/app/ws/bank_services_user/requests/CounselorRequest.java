package app.ws.bank_services_user.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CounselorRequest {

    @NotNull(message="This field 'firstname' must not be null !")
    @Size(min = 3, message= "This field 'firstname' must have at least 3 characters !")
    private String username;

    @NotNull(message="This field 'email' must not be null !")
    @Email(message = "This field must respect the email format!")
    private String mail;

    @NotNull
    @NotBlank(message="Please enter your phone number")
    private String phoneNumber;

    @NotNull
    @NotBlank(message="Please enter your agency name")
    private String agency;

    @NotNull
    @NotBlank(message="Please enter your agency adresse")
    private String agencyAdresse;
}
