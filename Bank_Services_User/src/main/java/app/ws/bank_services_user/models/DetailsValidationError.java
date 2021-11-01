package app.ws.bank_services_user.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DetailsValidationError {
    private String fieldName;
    private String defaultMessage;

}
