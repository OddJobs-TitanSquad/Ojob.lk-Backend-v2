package com.example.login.LoginExample.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter // Automatically getter and setter method using Lombok
@Setter

@Entity
@Table(name = "complain")
public class Complain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String complain;
    private  LocalDateTime dt;
    private long toWhoom;
    private long complainer;
    private String city;
    private long jobid;
    private String adminReply;

    public Complain() {

    }


}
