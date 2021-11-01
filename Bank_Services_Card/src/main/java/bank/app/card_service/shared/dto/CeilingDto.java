package bank.app.card_service.shared.dto;

import lombok.Getter;
import lombok.Setter;


import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class CeilingDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -847701026520772030L;

    private Long id;
    private String ceilingId;
    private String minValuePayment;
    private String maxValuePayment;
    private String minValueRetirement;
    private String maxValueRetirement;
    private String currency;
    private String type;
}
