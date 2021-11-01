package com.apps.ws.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
public class CheckEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 468569808338758167L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String chequeId;

    @Column(nullable = false, length = 32)
    private String userId;

    @Column(nullable = false)
    private LocalDateTime requestDate;

    @Column(nullable = false)
    private Boolean requestStatus;

    @Column(nullable = false)
    private Boolean responseStatus;

    @Column(nullable = false)
    private Boolean cheque;

    @Column(nullable = false)
    private int numberPage;
}
