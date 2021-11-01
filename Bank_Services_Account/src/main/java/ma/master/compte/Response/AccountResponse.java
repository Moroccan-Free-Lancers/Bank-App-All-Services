package ma.master.compte.Response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import ma.master.compte.Entity.DocumentsEntity;
import ma.master.compte.Entity.OperationEntity;


@Getter
@Setter
public class AccountResponse {
	
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
