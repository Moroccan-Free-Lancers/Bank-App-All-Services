package bank.app.card_service.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {
    private String provider;
    private Date date;
    private String message;
    private String details;
}
