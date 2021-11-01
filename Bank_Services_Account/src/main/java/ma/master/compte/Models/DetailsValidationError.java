package ma.master.compte.Models;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

@AllArgsConstructor
public class DetailsValidationError {

	
	private String fieldName;
	private String defaultMessage;
}
