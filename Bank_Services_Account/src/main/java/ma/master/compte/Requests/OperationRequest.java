package ma.master.compte.Requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperationRequest {
	
	private String bookDate;
	private String reference;
	private String descript;
	private String valueDate;
	private Integer debit;
	private Integer credit;
	private Integer closingBalance;

}
