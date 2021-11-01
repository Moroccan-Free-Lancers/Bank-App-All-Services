package app.ws.bank_services_user.exceptions;

import app.ws.bank_services_user.models.DetailsValidationError;
import app.ws.bank_services_user.responses.ExceptionResponse;
import app.ws.bank_services_user.responses.ExceptionResponseValidation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception e, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                "By GeneraleException" ,new Date(), e.getMessage(), request.getDescription(false)
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomerException.class)
    public final ResponseEntity<Object> handleCustomerException(CustomerException ce, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                "By CustomerException" ,new Date(), ce.getMessage(), request.getDescription(false)
        );
        
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CounselorException.class)
    public final ResponseEntity<Object> handleCustomerException(CounselorException co, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
               "By CounselorException" ,new Date(), co.getMessage(), request.getDescription(false)
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BeneficiaryException.class)
    public final ResponseEntity<Object> handleCustomerException(BeneficiaryException be, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                "By BeneficiaryException" ,new Date(), be.getMessage(), request.getDescription(false)
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<DetailsValidationError> details = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(field -> details.add(new DetailsValidationError(field.getField(), field.getDefaultMessage())));
        ExceptionResponseValidation exceptionResponseValidation =
                new ExceptionResponseValidation("By NotValidException", new Date(), "Validations fields", details);

        return new ResponseEntity<>(exceptionResponseValidation, HttpStatus.BAD_REQUEST);
    }
}
