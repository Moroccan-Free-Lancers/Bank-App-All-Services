package app.ws.bank_services_user.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "customers")
@Getter
@Setter
public class CustomerEntity implements Serializable {
    private static final long serialVersionUID = -7826800545780306850L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 30)
    private String customerId;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false)
    private String encryptPassword;

    @Column(nullable = false, length = 120, unique = true)
    private String mail;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false, length = 50)
    private String agency;


    private String pictureUrl;

    @OneToMany(mappedBy="customer", cascade=CascadeType.ALL)
    private List<ProfileEntity> profiles;

    @ManyToOne
    @JoinColumn(name = "counselors_id")
    private CounselorEntity counselor;

    @OneToMany(mappedBy="customer", fetch=FetchType.EAGER)
    private List<BeneficiaryEntity> beneficiaries;
}
