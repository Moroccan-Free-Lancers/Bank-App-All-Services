package ma.master.compte.Dto;

import java.io.Serializable;


import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class DocumentsDto implements Serializable{
	
	private static final long serialVersionUID = -2562157826905940122L;
	
	
	private Long Id;
	private String documentId;
	private String nom;
	private String date;
	private String file;
	

}
