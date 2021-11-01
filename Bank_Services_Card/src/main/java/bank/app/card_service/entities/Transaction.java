package bank.app.card_service.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Transaction implements Serializable {

    @Serial
    private static final long serialVersionUID = 2238624712731663116L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String transactionId;

    @Column(nullable = false)
    private String sourceAccountId;

    @Column(nullable = false)
    private String targetAccountId;

    @Column(nullable = false)
    private double amount ;

    @Column(nullable = false)
    private LocalDateTime transactionDate;
}
