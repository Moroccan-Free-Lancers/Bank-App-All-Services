package ma.master.compte.Exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ma.master.compte.Response.ExceptionResponse;


@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	 public final ResponseEntity<Object> handleAllException(Exception e, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                "By GeneraleException" ,new Date(), e.getMessage(), request.getDescription(false)
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(AccountException.class)
	public final ResponseEntity<Object> handleCustomerException(AccountException compteE, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				"By AccountException", new Date(), compteE.getMessage(), request.getDescription(false)
		);
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(RestTemplateException.class)
	public final ResponseEntity<Object> handleCustomerException(RestTemplateException compteE, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				"By RestTemplateException", new Date(), compteE.getMessage(), request.getDescription(false)
		);

		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
