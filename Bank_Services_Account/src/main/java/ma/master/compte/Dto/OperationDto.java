package ma.master.compte.Dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OperationDto implements Serializable{
	
	 private static final long serialVersionUID = -3686547190878829413L;
	 
	 private long Id;
		private String OperationId;
		private String bookDate;
		private String reference;
		private String descript;
		private String valueDate;
		private Integer debit;
		private Integer credit;
		private Integer closingBalance;
		

}
