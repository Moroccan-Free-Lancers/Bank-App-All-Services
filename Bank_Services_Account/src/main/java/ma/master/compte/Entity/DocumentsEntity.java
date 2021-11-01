package ma.master.compte.Entity;

import java.io.Serial;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Document")
public class DocumentsEntity implements Serializable{
	
	@Serial
    private static final long serialVersionUID = 2153879314836623817L;
	
	
	@javax.persistence.Id
	@GeneratedValue
	private Long Id;
	
	@Column(nullable=false, unique=true, length = 30)
    private String documentId;
	
	@Column(nullable = false)
	private String nom;

	@Column(nullable = false)
    private String date;
	
	@Column(nullable = false)
    private String file;
	
	@ManyToOne(fetch = FetchType.LAZY)
    private AccountEntity compte;
	
	
    
    
    

}
