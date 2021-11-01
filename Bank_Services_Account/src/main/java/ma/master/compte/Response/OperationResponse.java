package ma.master.compte.Response;



import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OperationResponse {
	
	private String OperationId;
	private String bookDate;
	private String reference;
	private String descript;
	private String valueDate;
	private Integer debit;
	private Integer credit;
	private Integer closingBalance;
	

}
