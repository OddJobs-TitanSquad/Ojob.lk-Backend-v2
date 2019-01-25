package com.example.login.LoginExample.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "complain")
public class Complain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public String getComplain() {
        return complain;
    }

    public void setComplain(String complain) {
        this.complain = complain;
    }


    private String complain;



   private  LocalDateTime dt;
   private long toWhoom;

    public LocalDateTime getDt() {
        return dt;
    }

    public void setDt(LocalDateTime dt) {
        this.dt = dt;
    }

    public long getToWhoom() {
        return toWhoom;
    }

    public void setToWhoom(long toWhoom) {
        this.toWhoom = toWhoom;
    }

    public long getComplainer() {
        return complainer;
    }

    public void setComplainer(long complainer) {
        this.complainer = complainer;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getJobid() {
        return jobid;
    }

    public void setJobid(long jobid) {
        this.jobid = jobid;
    }

    private long complainer;
   private String city;
   private long jobid;



    public Complain() {

    }


}
