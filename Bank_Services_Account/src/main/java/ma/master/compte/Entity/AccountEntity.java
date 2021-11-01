package ma.master.compte.Entity;



import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@AllArgsConstructor
@NoArgsConstructor

@Getter
@Setter
@ToString
@Entity
@Table(name = "account")
public class AccountEntity implements Serializable{
	
	@Serial
    private static final long serialVersionUID = 8090375950717610939L;
	
	@Id
	@GeneratedValue
	private Long Id;
	
	@Column(nullable=false, unique=true)
    private String compteId;

	@Column(nullable=false, unique=false)
	private String userId;
	
	@Column(nullable=false)
	private String CompteType;
	
	@Column(nullable=false)
    private String name;
	
	@Column(nullable=false)
	private double lockedAmount;
	
	@Column(nullable=false)
    private LocalDateTime lastUpdate;
	
	@Column(nullable = false)
    private double balance;
	
	@Column(nullable = false)
    private String currency;
	
	@Column(nullable = false)
    private int rib;
	
	@Column(nullable = false)
    private String agency;
	
	@Column(nullable = false)
    private int swiftcode;

	@Column(nullable = false)
    private boolean canDebit;
	
	@Column(nullable = false)
    private boolean canCredit;
    
    @OneToMany(mappedBy = "compte" ,cascade = CascadeType.ALL)
    private List<OperationEntity> operation;
    
    
    @OneToMany(mappedBy = "compte" ,cascade = CascadeType.ALL)
    private List<DocumentsEntity> document;
	
	
	

}
