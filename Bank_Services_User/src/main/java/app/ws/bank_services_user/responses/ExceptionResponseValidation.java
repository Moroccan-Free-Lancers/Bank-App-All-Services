package app.ws.bank_services_user.responses;

import app.ws.bank_services_user.models.DetailsValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponseValidation {
    private String provider;
    private Date timestamp;
    private String message;
    private List<DetailsValidationError> details;
}
