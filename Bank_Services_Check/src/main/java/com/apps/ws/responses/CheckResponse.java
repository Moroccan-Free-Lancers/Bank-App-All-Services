package com.apps.ws.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CheckResponse {

    private String chequeId;
    private LocalDateTime requestDate;
    private Boolean requestStatus;
    private Boolean responseStatus;
    private Boolean cheque;
    private int numberPage;
}
