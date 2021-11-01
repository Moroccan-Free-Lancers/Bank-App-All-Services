package ma.master.compte.Requests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AccountRequest {

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
