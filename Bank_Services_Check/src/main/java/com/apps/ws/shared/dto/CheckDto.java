package com.apps.ws.shared.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
public class CheckDto implements Serializable {
    private static final long serialVersionUID = -985750820907551425L;

    private String chequeId;
    private String userId;
    private LocalDateTime requestDate;
    private Boolean requestStatus;
    private Boolean responseStatus;
    private Boolean cheque;
    private int numberPage;
}
