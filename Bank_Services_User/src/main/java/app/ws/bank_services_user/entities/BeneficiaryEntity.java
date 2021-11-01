package app.ws.bank_services_user.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "beneficiary")
@Getter
@Setter
public class BeneficiaryEntity implements Serializable {
    private static final long serialVersionUID = -7126902503849179161L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable=false, unique=true, length = 30)
    private String beneficiaryId;
    
    @Column(nullable=false, length=50)
    private String firstName;
    
    @Column(nullable=false, length=50)
    private String lastName;
    
    @Column(nullable=false, length=24)
    private String rib;

    @Column(nullable=false)
    private String description;

    @ManyToOne
    @JoinColumn(name= "customers_id")
    private CustomerEntity customer;
}
