package bank.app.card_service.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Card implements Serializable {

        @Serial
        private static final long serialVersionUID = 8090375950717610939L;

        @Id
        @GeneratedValue
        private Long id;

        @Column(nullable = false, unique = true)
        private String cardId;

        @Column(nullable = false, unique = true)
        private String cardName;

        @Column(nullable = false, unique = true)
        private String cardNumber;

        @Column(nullable = false)
        private String accountId;

        @Column(nullable = false)
        private boolean status; /* activated 1 / desactivated 0 */

        @Column(nullable = false)
        private boolean opposed;

        @Column(nullable = false)
        private LocalDateTime issueDate; /* date de creation */

        @Column(nullable = false)
        private double cardBalance;

        @Column(nullable = false)
        private LocalDateTime experationDate;

        @Column(nullable = false)
        private boolean onlinePayment;

        @Column(nullable = false)
        private boolean contactless;

        @Column(nullable = false)
        private boolean withdrawal;

        @OneToOne
        @JoinColumn(name = "ceilingId")
        private Ceiling ceiling;

        @OneToMany(
                mappedBy = "card",
                cascade = CascadeType.ALL
        )
        private List<Operation> operations = new ArrayList<>();
}