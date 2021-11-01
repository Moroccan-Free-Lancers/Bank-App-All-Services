package bank.app.card_service.entities;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AccountResponse {
	
	private String compteId;
	private String userId;
	private String CompteType;
	private String title;
	private double lockedAmount;
	private String lastUpdate;
	private double balance;
	private String currency;
	private String rib;
	private String Agency;
	private String swiftcode;
	private String ribURI;
	private boolean canDebit;
	private boolean canCredit;

}
