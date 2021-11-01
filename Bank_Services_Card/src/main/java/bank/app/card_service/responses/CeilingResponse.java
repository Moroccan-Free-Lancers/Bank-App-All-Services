package bank.app.card_service.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CeilingResponse {

    private String ceilingId;
    private String minValuePayment;
    private String maxValuePayment;
    private String minValueRetirement;
    private String maxValueRetirement;
    private String currency;
    private String type;

}
