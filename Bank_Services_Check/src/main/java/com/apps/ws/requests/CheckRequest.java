package com.apps.ws.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class CheckRequest {

    @NotNull(message = "This Field cheque mustn't be null")
    private Boolean cheque;

    @NotNull(message = "This Field cheque mustn't be null")
    private int numberPage;
}
