package app.ws.bank_services_user.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "profiles")
@Getter
@Setter
public class ProfileEntity implements Serializable {
    private static final long serialVersionUID = 386362101255111766L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name="customers_id")
    private CustomerEntity customer;
}
