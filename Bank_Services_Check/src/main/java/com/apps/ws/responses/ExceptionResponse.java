package com.apps.ws.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {
    private String provider;
    private LocalDateTime date;
    private String message;
    private String details;
}
