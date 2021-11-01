package bank.app.card_service.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Operation implements Serializable {

        @Serial
        private static final long serialVersionUID = 2153879314836623817L;

        @Id
        @GeneratedValue
        private Long id;

        @Column(nullable = false, unique = true)
        private String operationId;

        @Column(nullable = false)
        private LocalDateTime operationDate;

        @Column(nullable = false)
        private String description ;

        @Column(nullable = false)
        private double amount ;

        @Column(nullable = false)
        private String currency ;

        @ManyToOne(fetch = FetchType.LAZY)
        private Card card;

}
