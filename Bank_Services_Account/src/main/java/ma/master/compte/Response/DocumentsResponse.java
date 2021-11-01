package ma.master.compte.Response;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class DocumentsResponse {
	
	private String documentId;
	private String nom;
	private String date;
	private String file;

}
