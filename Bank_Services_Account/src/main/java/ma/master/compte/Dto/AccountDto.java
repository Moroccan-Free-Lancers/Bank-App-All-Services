package ma.master.compte.Dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import ma.master.compte.Entity.DocumentsEntity;
import ma.master.compte.Entity.OperationEntity;

@Getter
@Setter

public class AccountDto implements Serializable{
	
	private static final long serialVersionUID = -3686547190878829413L;
	
	private Long Id;
	private String compteId;
	private String userId;
	private String CompteType;
	private String name;
	private double lockedAmount;
	private LocalDateTime lastUpdate;
	private double balance;
	private String currency;
	private int rib;
	private String Agency;
	private int swiftcode;
	private boolean canDebit;
	private boolean canCredit;
	
	
}
