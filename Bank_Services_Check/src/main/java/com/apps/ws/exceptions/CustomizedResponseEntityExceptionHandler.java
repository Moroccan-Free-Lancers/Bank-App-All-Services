package com.apps.ws.exceptions;

import com.apps.ws.models.DetailsValidationError;
import com.apps.ws.responses.ExceptionResponse;
import com.apps.ws.responses.ExceptionResponseValidation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception e, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                "By GeneralException" ,LocalDateTime.now(), e.getMessage(), request.getDescription(false)
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CheckException.class)
    public final ResponseEntity<Object> handleChequeException(CheckException ce, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                "By CheckException" ,LocalDateTime.now(), ce.getMessage(), request.getDescription(false)
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<DetailsValidationError> details = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(field -> details.add(new DetailsValidationError(field.getField(), field.getDefaultMessage())));
        ExceptionResponseValidation exceptionResponseValidation =
                new ExceptionResponseValidation("By NotValidException", LocalDateTime.now(), "Validations fields", details);

        return new ResponseEntity<>(exceptionResponseValidation, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RestTemplateException.class)
    public final ResponseEntity<Object> handleChequeException(RestTemplateException ce, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                "By RestTemplateException" ,LocalDateTime.now(), ce.getMessage(), request.getDescription(false)
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
