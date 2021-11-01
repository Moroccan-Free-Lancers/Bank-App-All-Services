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


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Operation")
public class OperationEntity implements Serializable{
	
	
	  @Serial
      private static final long serialVersionUID = 2153879314836623817L;
	
	
	    @javax.persistence.Id
	    @GeneratedValue
	    private Long Id;
	    
	    @Column(nullable=false, unique=true, length = 30)
	    private String OperationId;
	    
	    
	    @Column(name="bookdate", nullable=false)
	    
	    private String bookDate;
	    
	    @Column(nullable = false)
	    private String reference;
	    
	    @Column(nullable = false)
	    private String descript;
	    
	   
	    @Column(name="valuedate", nullable=false)    
	    private String valueDate;
	    
	    
	    @Column(nullable = false)
	    private Integer debit;
	    
	    @Column(nullable = false)
	    private Integer credit;
	    
	    @Column(nullable = false)
	    private Integer closingBalance;
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    private AccountEntity compte;
	    
	    

	
	

}
