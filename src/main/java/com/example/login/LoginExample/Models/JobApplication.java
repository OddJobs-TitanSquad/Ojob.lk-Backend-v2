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
public class JobApplication {
    @Id
    @GeneratedValue
    private Long applicationId;
    private Long jobId;
    private Long userId;
    private Date requestedDateTime;
    private Boolean isAccepted;
    private Date acceptedDate;
    private Boolean isRejected;
    private Date rejectedDate;
    private Boolean isConfirmed;
    private Date confirmedDate;

}