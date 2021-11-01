package app.ws.bank_services_user.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "agency")
@Getter
@Setter
public class AgencyEntity implements Serializable {
    private static final long serialVersionUID = 5317964114017977952L;

    @Id
    @GeneratedValue
    private Long iD;

    @Column(nullable = false, unique = true)
    private String agenceId;

    @Column(nullable = false, unique = true)
    private String nomAgence;

    @Column(nullable = false, unique = true)
    private String adresseAgence;

    @Column(nullable = false, unique = true)
    private String responsableAgence;

    @Column(nullable = false, unique = true)
    private String telephoneAgence;
}
