package com.example.login.LoginExample.Models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity // Says that this is the Entity

@Getter // Automatically getter and setter method using Lombok
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class JobPayment {
    @Id
    @GeneratedValue
    private long paymentId;
    private @NonNull
    long jobId;
    private int packageId;
    private Date dateTime;
    private int paymentTypeId;
}

