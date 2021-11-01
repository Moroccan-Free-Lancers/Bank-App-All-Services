package app.ws.bank_services_user.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "counselors")
@Getter
@Setter
public class CounselorEntity implements Serializable {
    private static final long serialVersionUID = -8146111603926124083L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 32)
    private String counselorId;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 120)
    private String mail;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false, length = 50)
    private String agency;

    @Column(nullable = false)
    private String agencyAdresse;

    @OneToMany(mappedBy="counselor")
    private Set<CustomerEntity> customers = new HashSet<CustomerEntity>();

}
