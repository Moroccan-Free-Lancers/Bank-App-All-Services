package bank.app.card_service.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Ceiling implements Serializable {

    @Serial
    private static final long serialVersionUID = 4558034029375099595L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String ceilingId;

    @Column(nullable = false)
    private String minValuePayment;

    @Column(nullable = false)
    private String maxValuePayment;

    @Column(nullable = false)
    private String minValueRetirement;

    @Column(nullable = false)
    private String maxValueRetirement;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private String type;

}
