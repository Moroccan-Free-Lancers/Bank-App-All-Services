package bank.app.card_service.exceptions;

import bank.app.card_service.responses.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception e, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                "By GeneraleException" ,new Date(), e.getMessage(), request.getDescription(false)
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CardException.class)
    public final ResponseEntity<Object> handleCustomerException(CardException ce, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                "By CardException" ,new Date(), ce.getMessage(), request.getDescription(false)
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CeilingException.class)
    public final ResponseEntity<Object> handleCustomerException(CeilingException ce, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                "By CeilingException" ,new Date(), ce.getMessage(), request.getDescription(false)
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(OperationException.class)
    public final ResponseEntity<Object> handleCustomerException(OperationException ce, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                "By OperationException" ,new Date(), ce.getMessage(), request.getDescription(false)
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TransactionException.class)
    public final ResponseEntity<Object> handleCustomerException(TransactionException te, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                "By TransactionException" ,new Date(), te.getMessage(), request.getDescription(false)
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RestTemplateException.class)
    public final ResponseEntity<Object> handleCustomerException(RestTemplateException te, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                "By RestTemplateException" ,new Date(), te.getMessage(), request.getDescription(false)
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
