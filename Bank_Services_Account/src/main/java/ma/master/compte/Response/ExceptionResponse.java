package ma.master.compte.Response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class ExceptionResponse {

	
	private String provider;
	private Date date;
	private String message;
	private String details;
}
