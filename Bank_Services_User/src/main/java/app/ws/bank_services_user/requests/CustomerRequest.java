package app.ws.bank_services_user.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
public class CustomerRequest {

    @NotNull(message="This field 'firstname' must not be null !")
    @Size(min = 3, message= "This field 'firstname' must have at least 3 characters !")
    private String username;

    @NotNull(message="This field 'password' must not be null !")
    @Size(min=8, message= "Password must be at least 8 characters long")
    @Size( max=12, message= "Password must be at most 12 characters long")
    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$",
            message="This password must have upper and lower case letters and number")
    private String password;

    @NotNull(message="This field 'email' must not be null !")
    @Email(message = "This field must respect the email format!")
    private String mail;

    @NotNull
    @NotBlank(message="Please enter your phone number")
    private String phoneNumber;

    @NotNull
    @NotBlank(message="Please enter your agency name")
    private String agency;

    private List<ProfileRequest> profiles;

}
