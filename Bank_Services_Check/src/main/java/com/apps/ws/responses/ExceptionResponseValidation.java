package com.apps.ws.responses;

import com.apps.ws.models.DetailsValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponseValidation {
    private String provider;
    private LocalDateTime timestamp;
    private String message;
    private List<DetailsValidationError> details;
}
